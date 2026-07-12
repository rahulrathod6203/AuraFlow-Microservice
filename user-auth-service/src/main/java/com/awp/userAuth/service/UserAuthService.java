package com.awp.userAuth.service;

import com.awp.userAuth.admin.UserResponse;
import com.awp.userAuth.dto.*;

import java.util.List;

public interface UserAuthService {

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse register(RegisterRequest registerRequest);

    void logout();

    // Admin features
    List<UserResponse> fetchRegisteredUsers();

    String makeAdmin(String userEmail);

}
