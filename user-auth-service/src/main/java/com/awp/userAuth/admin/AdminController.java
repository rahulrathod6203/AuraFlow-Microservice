package com.awp.userAuth.admin;

import com.awp.userAuth.entity.User;
import com.awp.userAuth.exception.userDomain.UserNotFoundException;
import com.awp.userAuth.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/registered-users")
@RequiredArgsConstructor
public class AdminController {

    private final UserAuthService userAuthService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RegisteredUser>> fetchRegisteredUsers(){
        return ResponseEntity.ok(userAuthService.fetchRegisteredUsers());
    }

    @PatchMapping("/make-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> makeAdmin(@RequestBody User user){
        String message = userAuthService.makeAdmin(user.getEmail());
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RegisteredUser> fetchRegisteredUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userAuthService.fetchRegisteredUserById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> removeRegisteredUserById(@PathVariable Long id) {
        String response = userAuthService.removeRegisteredUserById(id);
        return ResponseEntity.ok(response);
    }
}
