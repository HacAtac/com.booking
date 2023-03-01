package com.booking.exceptions;

import com.booking.payload.MessageResponse;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class ControllerExceptionAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<MessageResponse> handleApplicationAPIException(ApiException applicationAPIException,
                                                                         WebRequest webRequest){
        LOGGER.error("Error occurred: {}", applicationAPIException.getMessage());
        MessageResponse messageResponse = new MessageResponse(applicationAPIException.getStatus().value(),
                applicationAPIException.getMessage(), applicationAPIException.getStatus().getReasonPhrase());
        return ResponseEntity.status(applicationAPIException.getStatus()).body(messageResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<MessageResponse> accessDeniedException(AccessDeniedException accessDeniedException){
        LOGGER.error("Error occurred: {}", accessDeniedException.getMessage());
        return ResponseEntity.status(401).body(new MessageResponse(401, accessDeniedException.getMessage(), "ADE401"));
    }

    @ExceptionHandler(ServiceNotFoundException.class)
    public ResponseEntity<MessageResponse> serviceNotFoundException(ServiceNotFoundException serviceNotFoundException){
        LOGGER.error("Error occurred: {}", serviceNotFoundException.getMessage());
        return ResponseEntity.status(404).body(new MessageResponse(404, serviceNotFoundException.getMessage(), "SNE404"));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<MessageResponse> usernameNotFoundException(UsernameNotFoundException usernameNotFoundException){
        LOGGER.error("Error occurred: {}", usernameNotFoundException.getMessage());
        return ResponseEntity.status(404).body(new MessageResponse(404, usernameNotFoundException.getMessage(), "UNFE404"));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageResponse> runtimeException(RuntimeException runtimeException) {
        LOGGER.error("Error occurred: {}", runtimeException.getMessage());
        LOGGER.error("Stacktrace shows: {}", runtimeException.getStackTrace()[0]);
        return ResponseEntity.status(500).body(new MessageResponse(500, runtimeException.getMessage(), "RTE500"));
    }



}
