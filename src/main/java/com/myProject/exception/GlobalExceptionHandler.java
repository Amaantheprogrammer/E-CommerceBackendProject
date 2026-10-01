package com.myProject.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
                ErrorResponse error = new ErrorResponse(
                                HttpStatus.NOT_FOUND.value(),
                                ex.getMessage(),
                                LocalDateTime.now());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        @ExceptionHandler(BadRequestException.class)
        public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
                ErrorResponse error = new ErrorResponse(
                                HttpStatus.BAD_REQUEST.value(),
                                ex.getMessage(),
                                LocalDateTime.now());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        @ExceptionHandler(DuplicateResourceException.class)
        public ResponseEntity<ErrorResponse> handleDuplicateResourceException(DuplicateResourceException ex) {
            ErrorResponse error = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    ex.getMessage(),
                    LocalDateTime.now()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
                String message = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                                .collect(Collectors.joining(", "));
                ErrorResponse error = new ErrorResponse(
                                HttpStatus.BAD_REQUEST.value(),
                                message,
                                LocalDateTime.now());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        @ExceptionHandler(Exception.class)
        @SuppressWarnings("CallToPrintStackTrace")
        public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {

                ex.printStackTrace();

                ErrorResponse error = new ErrorResponse(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                ex.getMessage(),
                                LocalDateTime.now());

                return ResponseEntity
                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(error);
        }
}
