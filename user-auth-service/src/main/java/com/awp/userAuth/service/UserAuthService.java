package com.awp.userAuth.service;

import com.awp.userAuth.admin.RegisteredUsers;
import com.awp.userAuth.dto.*;

import java.util.List;

public interface UserAuthService {

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse register(RegisterRequest registerRequest);

    void logout();

    // Admin features

    List<RegisteredUsers> fetchRegisteredUsers();

    String makeAdmin(String userEmail);

}
