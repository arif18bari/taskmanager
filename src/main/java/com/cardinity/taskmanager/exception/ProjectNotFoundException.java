package com.cardinity.taskmanager.exception;
public class ProjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2533194229906054487L;

    public ProjectNotFoundException(String message) {
        super(message);
    }

}
