package com.booking.exceptions;

import com.booking.payload.MessageResponse;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

    @ExceptionHandler(ServiceNotFoundException.class)
    public ResponseEntity<MessageResponse> serviceNotFoundException(ServiceNotFoundException serviceNotFoundException){
        LOGGER.error("Error occurred: {}", serviceNotFoundException.getMessage());
        return ResponseEntity.status(404).body(new MessageResponse(404, serviceNotFoundException.getMessage(), "SNE404"));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageResponse> runtimeException(RuntimeException runtimeException){
        LOGGER.error("Error occurred: {}", runtimeException.getMessage());
        LOGGER.error("Stacktrace shows: {}", runtimeException.getStackTrace()[0]);
            return ResponseEntity.status(500).body(new MessageResponse(500, runtimeException.getMessage(), "ISE500"));
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<MessageResponse> objectNotFoundException(ObjectNotFoundException objectNotFoundException){
        LOGGER.error("Error occurred: {}", objectNotFoundException.getMessage());
        LOGGER.error("Stacktrace shows: {}", objectNotFoundException.getStackTrace()[0]);
        return ResponseEntity.status(404).body(new MessageResponse(404, objectNotFoundException.getMessage(), "ISE404"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResponse> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        LOGGER.error("Error occurred: {}", methodArgumentNotValidException.getMessage());
        LOGGER.error("Stacktrace shows: {}", methodArgumentNotValidException.getStackTrace()[0]);
        return ResponseEntity.status(400).body(new MessageResponse(400, methodArgumentNotValidException.getMessage(), "ISE400"));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageResponse> illegalArgumentException(IllegalArgumentException illegalArgumentException){
        LOGGER.error("Error occurred: {}", illegalArgumentException.getMessage());
        LOGGER.error("Stacktrace shows: {}", illegalArgumentException.getStackTrace()[0]);
        return ResponseEntity.status(400).body(new MessageResponse(400, illegalArgumentException.getMessage(), "ISE400"));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<MessageResponse> nullPointerException(NullPointerException nullPointerException){
        LOGGER.error("Error occurred: {}", nullPointerException.getMessage());
        LOGGER.error("Stacktrace shows: {}", nullPointerException.getStackTrace()[0]);
        return ResponseEntity.status(500).body(new MessageResponse(500, nullPointerException.getMessage(), "ISE500"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> exception(Exception exception){
        LOGGER.error("Error occurred: {}", exception.getMessage());
        LOGGER.error("Stacktrace shows: {}", exception.getStackTrace()[0]);
        return ResponseEntity.status(500).body(new MessageResponse(500, exception.getMessage(), "ISE500"));
    }

    @ExceptionHandler(NoSuchFieldException.class)
    public ResponseEntity<MessageResponse> noSuchFieldException(NoSuchFieldException noSuchFieldException){
        LOGGER.error("Error occurred: {}", noSuchFieldException.getMessage());
        LOGGER.error("Stacktrace shows: {}", noSuchFieldException.getStackTrace()[0]);
        return ResponseEntity.status(500).body(new MessageResponse(500, noSuchFieldException.getMessage(), "ISE500"));
    }

    @ExceptionHandler(NoSuchMethodException.class)
    public ResponseEntity<MessageResponse> noSuchMethodException(NoSuchMethodException noSuchMethodException){
        LOGGER.error("Error occurred: {}", noSuchMethodException.getMessage());
        LOGGER.error("Stacktrace shows: {}", noSuchMethodException.getStackTrace()[0]);
        return ResponseEntity.status(500).body(new MessageResponse(500, noSuchMethodException.getMessage(), "ISE500"));
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<MessageResponse> securityException(SecurityException securityException){
        LOGGER.error("Error occurred: {}", securityException.getMessage());
        LOGGER.error("Stacktrace shows: {}", securityException.getStackTrace()[0]);
        return ResponseEntity.status(500).body(new MessageResponse(500, securityException.getMessage(), "ISE500"));
    }

    @ExceptionHandler(ClassNotFoundException.class)
    public ResponseEntity<MessageResponse> classNotFoundException(ClassNotFoundException classNotFoundException){
        LOGGER.error("Error occurred: {}", classNotFoundException.getMessage());
        LOGGER.error("Stacktrace shows: {}", classNotFoundException.getStackTrace()[0]);
        return ResponseEntity.status(500).body(new MessageResponse(500, classNotFoundException.getMessage(), "ISE500"));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<MessageResponse> constraintViolationException(ConstraintViolationException constraintViolationException){
        LOGGER.error("Error occurred: {}", constraintViolationException.getMessage());
        LOGGER.error("Stacktrace shows: {}", constraintViolationException.getStackTrace()[0]);
        return ResponseEntity.status(500).body(new MessageResponse(500, constraintViolationException.getMessage(), "ISE500"));
    }

}
