package com.project.stacktrace.exceptions;


public class UserIsExistException extends RuntimeException {

    public UserIsExistException(String message){
        super(message);
    }

}
