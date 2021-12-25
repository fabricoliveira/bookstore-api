package com.fabricio.bookstore.handler;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError extends Error {

    private List<FieldError> fieldErrors = new ArrayList<>();

    public ValidationError() {
        super();
    }

    public ValidationError(Long timestamp, Integer status, String error) {
        super(timestamp, status, error);
    }

    public void addError(String fieldName, String message) {
        fieldErrors.add(new FieldError(fieldName, message));
    }

}
