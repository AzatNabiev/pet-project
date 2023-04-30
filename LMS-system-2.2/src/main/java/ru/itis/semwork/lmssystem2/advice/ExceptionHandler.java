package ru.itis.semwork.lmssystem2.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itis.semwork.lmssystem2.dto.ExceptionDto;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleError(Exception e) {
        return ResponseEntity.badRequest().body(ExceptionDto.builder().message(e.getLocalizedMessage()).build());
    }
}