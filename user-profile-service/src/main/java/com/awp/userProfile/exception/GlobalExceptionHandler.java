package com.awp.userProfile.exception;

import com.awp.userProfile.exception.responseBuilder.ExceptionResponseBuilder;
import com.awp.userProfile.exception.userDomain.PhoneAlreadyExistsException;
import com.awp.userProfile.exception.userDomain.UserProfileNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ExceptionResponseBuilder responseBuilder;

// ********************** UserProfile Domain Exceptions *****************************

    @ExceptionHandler(UserProfileNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserProfileNotFoundException exception, WebRequest request) {
        log.warn("Resource access warning: UserProfile lookup failed at path {}. Error: {}",
                request.getDescription(false).replace("uri=", ""), exception.getMessage());
        return responseBuilder.buildResponse(request, exception.getMessage(), HttpStatus.NOT_FOUND, null);
    }

    @ExceptionHandler(PhoneAlreadyExistsException.class)
    public ResponseEntity<Object> handlePhoneAlreadyExistsException(PhoneAlreadyExistsException exception, WebRequest request) {
        log.warn("Data conflict encountered: Phone number already registered at path {}. Details: {}",
                request.getDescription(false).replace("uri=", ""), exception.getMessage());
        return responseBuilder.buildResponse(request, exception.getMessage(), HttpStatus.BAD_REQUEST, null);
    }

    // ********************** Global Exceptions *****************************

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String defaultMessage = error.getDefaultMessage();
            errors.put(fieldName, defaultMessage);
        });

        log.warn("Validation constraint failure for request path {}: Field ErrorsMap -> {}", request.getDescription(false).replace("uri=", ""), errors);

        return responseBuilder.buildResponse(request, "Validation failed!", HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception exception, WebRequest request) {
        log.error("CRITICAL ERROR: An unexpected system failure occurred at path {}: ", request.getDescription(false).replace("uri=", ""), exception);
        return responseBuilder.buildResponse(request, "An internal server error occurred. Please try again later!.", HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
}