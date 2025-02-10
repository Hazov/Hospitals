package ru.hazov.center.exception.controller.handler.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import ru.hazov.center.dto.api.in.error.ErrorResponse;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice(annotations = Controller.class)
public class ControllersParamsValidation {
    @ExceptionHandler(exception = {MethodArgumentNotValidException.class, HandlerMethodValidationException.class})
    public ResponseEntity<ErrorResponse> paramsHandler(Exception e, Errors errors) {
        String errorsStr = Arrays.stream(Objects.requireNonNull(((MethodArgumentNotValidException) errors)
                .getDetailMessageArguments()))
                .map(Object::toString)
                .filter(f -> !f.isBlank())
                .collect(Collectors.joining(","));
        return ResponseEntity.badRequest().body(new ErrorResponse(errorsStr));
    }
}
