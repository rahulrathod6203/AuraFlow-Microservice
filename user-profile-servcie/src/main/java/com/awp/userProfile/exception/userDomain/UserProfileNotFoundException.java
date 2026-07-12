package com.awp.userProfile.exception.userDomain;

public class UserProfileNotFoundException extends RuntimeException{
    public UserProfileNotFoundException(String message) {
        super(message);
    }
}
