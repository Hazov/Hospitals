package ru.hazov.center.exception.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.hazov.center.dto.api.in.error.ErrorResponse;
import ru.hazov.center.exception.controller.ControllerException;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(ControllerException ex) {
    return ResponseEntity.internalServerError().body(new ErrorResponse(ex.getMessage()));
    }
}
