package com.project.StoreManagement.exceptions;

import com.project.StoreManagement.models.ResponseMessage;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseMessage> notFoundExceptionHandler(NotFoundException notFoundException) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .date(LocalDate.now())
                        .message(List.of(notFoundException.getMessage()))
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ResponseMessage> alreadyExistExceptionHandler(AlreadyExistsException alreadyExistsException) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .date(LocalDate.now())
                        .message(List.of(alreadyExistsException.getMessage()))
                        .statusCode(HttpStatus.CONFLICT.value())
                        .build(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessage> jakartaExceptionHandler(MethodArgumentNotValidException exception) {
        List<String> errorList = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach(objectError ->
                errorList.add(
                        ((FieldError) objectError).getField() + "|" + objectError.getDefaultMessage()
                )
        );
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .date(LocalDate.now())
                        .message(errorList)
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseMessage> jakartaExceptionHandler2(ConstraintViolationException exception) {
        List<String> errorList = new ArrayList<>();
        exception.getConstraintViolations().forEach(objectError ->
                errorList.add(
                        objectError.getPropertyPath() + "|" + objectError.getMessageTemplate()
                )
        );
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .date(LocalDate.now())
                        .message(errorList)
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }
}
