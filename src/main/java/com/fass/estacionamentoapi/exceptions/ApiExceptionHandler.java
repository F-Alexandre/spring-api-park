package com.fass.estacionamentoapi.exceptions;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request, BindingResult result){

        log.error("Api ERROr - " , ex);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).contentType(MediaType.APPLICATION_JSON).body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY , "CAMPO INVALIDO" , result));


    }
    @ExceptionHandler(UsernameUniqueViolationExceptions.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidExceptionUsuername(RuntimeException ex, HttpServletRequest request){

        log.error("Api ERROr - " , ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).contentType(MediaType.APPLICATION_JSON).body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY , ex.getMessage()));


    }
    @ExceptionHandler(EntityNotFoundExceptionCustom.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidExceptionId(RuntimeException ex, HttpServletRequest request){

        log.error("Api ERROr - " , ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY , ex.getMessage()));

    }

    @ExceptionHandler(PasswordNotEqualsExceptions.class)
    public ResponseEntity<ErrorMessage> passArgumentNotValidExceptionId(RuntimeException ex, HttpServletRequest request){
        log.error("Api ERROr - " , ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(new ErrorMessage(request, HttpStatus.BAD_REQUEST , ex.getMessage()));

    }
}
