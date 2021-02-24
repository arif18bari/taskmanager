package com.cardinity.taskmanager.exception;

public class TaskNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2533194229906054497L;

    public TaskNotFoundException(String message) {
        super(message);
    }

}
