package com.udemy.UserService.exception;

public class CriticalException extends BaseException {
    public CriticalException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
