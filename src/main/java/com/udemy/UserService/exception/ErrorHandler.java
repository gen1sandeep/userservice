package com.udemy.UserService.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST);
        errorResponse.setMessage("Validation error");
        errorResponse.addValidationErrors(ex.getBindingResult().getFieldErrors());
        errorResponse.addValidationError(ex.getBindingResult().getGlobalErrors());
        return buildResponseEntity(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST);
        errorResponse.setMessage("Validation error");
        Error error = new ApiValidationError("invalid", "validationError.getField()",
                "", "Error While processing request. Please Contact Administrator");
        errorResponse.addError(error);
        return buildResponseEntity(errorResponse);
    }
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(
            javax.validation.ConstraintViolationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST);
        errorResponse.setMessage("Validation error");
        errorResponse.addValidationErrors(ex.getConstraintViolations());
        return buildResponseEntity(errorResponse);
    }


    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Object> handleBusinessException(
            ValidationException validationError) {
        ErrorResponse errorResponse = new ErrorResponse(UNPROCESSABLE_ENTITY);
        errorResponse.setMessage("Validation error");
        Error error = new ApiValidationError(validationError.getErrorCode(), validationError.getField(),
                validationError.getRejectedValue(), validationError.getErrorMessage());
        errorResponse.addError(error);
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(CriticalException.class)
    protected ResponseEntity<Object> handleBusinessException(
            CriticalException criticalException) {
        criticalException.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse(INTERNAL_SERVER_ERROR);
        errorResponse.setMessage("Critical error");
        Error error = new ApiValidationError(criticalException.getErrorCode(), null,
                null, criticalException.getErrorMessage());
        errorResponse.addError(error);
        return buildResponseEntity(errorResponse);
    }
}