package com.cardinity.taskmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cardinity.taskmanager.util.MethodUtils;

@ControllerAdvice
public class ProjectCustomExceptionHandler {

    @ExceptionHandler(value = ApplicationException.class)
    public ResponseEntity<String> applicationException(ApplicationException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(MethodUtils.prepareErrorJSON(status, exception), status);
    }

    @ExceptionHandler(value = ProjectNotFoundException.class)
    public ResponseEntity<String> projectNotFoundException(ProjectNotFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(MethodUtils.prepareErrorJSON(status, exception), status);
    }

}
