package com.awp.userAuth.service;

import com.awp.userAuth.admin.UserResponse;
import com.awp.userAuth.dto.*;
import com.awp.userAuth.entity.Role;
import com.awp.userAuth.exception.userDomain.EmailAlreadyExistsException;
import com.awp.userAuth.exception.userDomain.UserNotFoundException;
import com.awp.userAuth.jwt.JWTTokenProvider;
import com.awp.userAuth.mapper.UserMapper;
import com.awp.userAuth.entity.User;
import com.awp.userAuth.entity.UserPrincipal;
import com.awp.userAuth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAuthServiceImpl implements UserAuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JWTTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        log.info("Attempting authentication processing for login email: {}", loginRequest.email());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("Authentication manager verification successful. SecurityContext updated for email: {}", loginRequest.email());

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        String jwtToken = jwtTokenProvider.generateToken(authentication);

        String userRole = principal.getAuthorities().iterator().next().getAuthority();

        UserSummary userSummary = UserSummary.builder()
                .id(principal.getId())
                .email(principal.getUsername())
                .role(userRole)
                .build();

        return AuthResponse.builder()
                .token(jwtToken)
                .tokenType("Bearer")
                .timestamp(Instant.now())
                .message("Authentication verification successful.")
                .user(userSummary).build();

    }

    @Transactional
    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        log.info("Processing account registration request for email: {}", registerRequest.email());

        if (userRepository.existsByEmail(registerRequest.email())) {
            log.warn("Registration rejected: Email conflict discovered for address: {}", registerRequest.email());
            throw new EmailAlreadyExistsException("Email already exits, please try a different one!");
        }

        User user = userMapper.toEntity(registerRequest);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setRole(Role.USER); // sets the default role as USER

        User savedUser = userRepository.save(user);
        log.info("User identity record persisted successfully. Assigned Generated ID: {} with role: USER", savedUser.getId());

        UserSummary userSummary = UserSummary.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();

        return AuthResponse.builder()
                .timestamp(Instant.now())
                .message("Registration successful! Please proceed to the login screen.")
                .user(userSummary).build();
    }

    public void logout() {
        // Clear the SecurityContext for the current authenticated user thread
        SecurityContextHolder.clearContext();
        log.info("Spring Security context successfully cleared for the user session.");
    }

    @Override
    public List<UserResponse> fetchRegisteredUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toResponse).toList();
    }

    @Transactional
    @Override
    public String makeAdmin(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("User not found!, please check your email address."));
        if(user.getRole() == Role.ADMIN ){
            return "Current user already has an ADMIN role!";
        }
        user.setRole(Role.ADMIN);
        userRepository.save(user);
        return "User has been granted as Admin role successfully!";
    }
}