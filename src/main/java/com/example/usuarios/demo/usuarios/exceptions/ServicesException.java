package com.example.usuarios.demo.usuarios.exceptions;
import org.springframework.http.HttpStatus;

import java.util.Date;

public class ServicesException extends Exception {
    private static final long serialVersionUID = 1L;
    private Integer status;
    private String userMessage;
    private String developerMessage;
    private Date timestamp;

    public ServicesException(Integer status, String message, String developerMessage) {
        this.status = status;
        this.userMessage = message;
        this.developerMessage = developerMessage;
        this.timestamp = new Date();
    }

    public ServicesException(HttpStatus status, String message, String developerMessage) {
        this.status = status.value();
        this.userMessage = message;
        this.developerMessage = developerMessage;
        this.timestamp = new Date();
    }

    public Integer getSatus() {
        return status;
    }

    public void setSatus(Integer satus) {
        this.status = satus;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    @Override
    public String toString() {
        return "ServicesException{" +
                "status=" + status +
                ", userMessage='" + userMessage + '\'' +
                ", developerMessage='" + developerMessage + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}


