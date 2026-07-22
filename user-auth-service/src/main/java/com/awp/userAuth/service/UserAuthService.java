package com.awp.userAuth.service;

import com.awp.userAuth.admin.RegisteredUser;
import com.awp.userAuth.dto.*;

import java.util.List;

public interface UserAuthService {

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse register(RegisterRequest registerRequest);

    void logout();

    // ************************** Admin Endpoints **************************

    List<RegisteredUser> fetchRegisteredUsers();

    String makeAdmin(String userEmail);

    RegisteredUser fetchRegisteredUserById(Long id);

    String removeRegisteredUserById(Long id);


}
