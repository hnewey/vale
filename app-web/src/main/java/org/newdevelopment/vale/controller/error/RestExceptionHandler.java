package org.newdevelopment.vale.controller.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.newdevelopment.vale.data.exception.AuthenticationException;
import org.newdevelopment.vale.data.exception.AuthorizationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> handleAuthenticationException(AuthenticationException ae) {
        ApiError apiError = new ApiError(ae.getStatus());
        apiError.setMessage(ae.getMessage());
        apiError.setDebugMessage(ae.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(AuthorizationException.class)
    protected ResponseEntity<Object> handleAuthorizationException(AuthorizationException ae) {
        ApiError apiError = new ApiError(ae.getStatus());
        apiError.setMessage(ae.getMessage());
        apiError.setDebugMessage(ae.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(JsonProcessingException.class)
    protected ResponseEntity<Object> handleJsonProcessingException(JsonProcessingException jpe) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Malformed JSON");
        apiError.setDebugMessage(jpe.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException iae) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(iae.getMessage());
        apiError.setDebugMessage(iae.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    protected ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException erdae) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(erdae.getMessage());
        apiError.setDebugMessage(erdae.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(NumberFormatException.class)
    protected ResponseEntity<Object> handleNumberFormatException(NumberFormatException nfe) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(nfe.getMessage());
        apiError.setDebugMessage(nfe.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(NoSuchAlgorithmException.class)
    protected ResponseEntity<Object> handleNoSuchAlgorithmException(NoSuchAlgorithmException nsae) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
        apiError.setMessage(nsae.getMessage());
        apiError.setDebugMessage(nsae.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(InvalidKeySpecException.class)
    protected ResponseEntity<Object> handleInvalidKeySpecException(InvalidKeySpecException ikse) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
        apiError.setMessage(ikse.getMessage());
        apiError.setDebugMessage(ikse.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError.toString(), apiError.getStatus());
    }
}

