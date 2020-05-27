package com.udemy.UserService.exception;

public class ValidationException extends BaseException {
    private String field;
    private String rejectedValue;

    public ValidationException(String errorCode, String errorMessage, String field, String rejectedValue) {
        super(errorCode,errorMessage);
        this.field = field;
        this.rejectedValue = rejectedValue;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(String rejectedValue) {
        this.rejectedValue = rejectedValue;
    }
}
