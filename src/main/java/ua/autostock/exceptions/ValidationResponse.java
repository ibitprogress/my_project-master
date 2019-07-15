package ua.autostock.exceptions;

import org.springframework.validation.BindingResult;
import ua.autostock.DTO.exception.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class ValidationResponse {

    private List<ValidationError> errors;
    private String error;
    private int status;

    public ValidationResponse(BindingResult errors, String error, int status) {
        this.errors = new ArrayList<ValidationError>();
        errors.getFieldErrors().stream().forEach(err ->  this.errors.add(new ValidationError(err.getField(), err.getDefaultMessage())));
        errors.getGlobalErrors().stream().forEach(err ->  this.errors.add(new ValidationError(err.getObjectName(), err.getDefaultMessage())));
        this.error = error;
        this.status = status;
    }

}
