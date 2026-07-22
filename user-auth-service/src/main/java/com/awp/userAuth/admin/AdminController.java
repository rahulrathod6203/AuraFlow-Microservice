package com.awp.userAuth.admin;

import com.awp.userAuth.entity.User;
import com.awp.userAuth.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserAuthService userAuthService;

    @GetMapping("/list-users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RegisteredUsers>> fetchRegisteredUsers(){
        return ResponseEntity.ok(userAuthService.fetchRegisteredUsers());
    }

    @PatchMapping("/make-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> makeAdmin(@RequestBody User user){
        String message = userAuthService.makeAdmin(user.getEmail());
        return ResponseEntity.ok(message);
    }
}
