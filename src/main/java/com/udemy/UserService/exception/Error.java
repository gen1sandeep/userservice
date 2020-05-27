
package com.udemy.UserService.exception;

abstract class Error {

    }

class ApiValidationError extends Error {
    private String code;
    private String reason;
    private String field;
    private Object rejectedValue;

    ApiValidationError(String code, String message) {
        this.code = code;
        this.reason = message;
    }

    public ApiValidationError(String code, String field, Object rejectedValue, String reason) {
        this.code = code;
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.reason = reason;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String message) {
        this.reason = message;
    }
}