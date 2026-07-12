package com.awp.periodLog.exception;

import com.awp.periodLog.exception.periodDomain.InvalidPeriodDateRangeException;
import com.awp.periodLog.exception.periodDomain.PeriodLogNotFoundException;
import com.awp.periodLog.exception.periodDomain.PeriodLogOverlapException;
import com.awp.periodLog.exception.responseBuilder.ExceptionResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.AccessDeniedException;
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

    // ********************** Period Log Exceptions *****************************

    @ExceptionHandler(PeriodLogNotFoundException.class)
    public ResponseEntity<Object> handlePeriodLogNotFoundException(PeriodLogNotFoundException exception, WebRequest request) {
        log.warn("Warning : No period log found for user");
        return responseBuilder.buildResponse(request, exception.getMessage(), HttpStatus.NOT_FOUND, null);
    }

    @ExceptionHandler(InvalidPeriodDateRangeException.class)
    public ResponseEntity<Object> handleInvalidPeriodDateRangeException(InvalidPeriodDateRangeException exception, WebRequest request) {
        log.warn("Warning : User entered start date is after end date");
        return responseBuilder.buildResponse(request, exception.getMessage(), HttpStatus.BAD_REQUEST, null);
    }

    @ExceptionHandler(PeriodLogOverlapException.class)
    public ResponseEntity<Object> handlePeriodLogOverlapException(PeriodLogOverlapException exception, WebRequest request) {
        log.warn("Warning : Period log overlap!, Period log already exists!");
        return responseBuilder.buildResponse(request, exception.getMessage(), HttpStatus.BAD_REQUEST, null);
    }


    // ********************** Global Exceptions *****************************

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException exception, WebRequest request) {
        log.warn("Access denied...");
        return responseBuilder.buildResponse(request, "Access Denied: You do not have permission to view or modify this resource!", HttpStatus.FORBIDDEN, null);
    }

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