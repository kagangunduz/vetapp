package com.kagangunduz.vet.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public String handlePetNotFoundException(Model model, EntityNotFoundException ex) {
        model.addAttribute("message", ex.getMessage());
        return "error";
    }
    
}
