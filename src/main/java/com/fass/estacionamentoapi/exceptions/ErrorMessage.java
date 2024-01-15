package com.fass.estacionamentoapi.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ErrorMessage {

   private String path;

   private String method;

   private int status;

   private String statusText;

   private String message;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Map<String , String> errors;
   public ErrorMessage(HttpServletRequest httpServletRequest , HttpStatus httpStatus, String message){

         this.path = httpServletRequest.getRequestURI();
         this.method = httpServletRequest.getMethod();
         this.status =  httpStatus.value();
         this.statusText = httpStatus.getReasonPhrase();
         this.message = message;

   }

    public ErrorMessage(HttpServletRequest httpServletRequest , HttpStatus httpStatus, String message, BindingResult result){

        this.path = httpServletRequest.getRequestURI();
        this.method = httpServletRequest.getMethod();
        this.status =  httpStatus.value();
        this.statusText = httpStatus.getReasonPhrase();
        this.message = message;
        addError(result);

    }


    private void addError(BindingResult result){

       this.errors = new HashMap<>();

       for(FieldError error : result.getFieldErrors()){
           this.errors.put(error.getField(), error.getDefaultMessage());
       }

    }



}
