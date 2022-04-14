package com.kagangunduz.vet.advice;

import com.kagangunduz.vet.exception.OwnerNotFoundException;
import com.kagangunduz.vet.exception.PetNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            OwnerNotFoundException.class,
            PetNotFoundException.class,
            IllegalArgumentException.class
    })
    public final String handleExceptions(Model model, EntityNotFoundException ex) {
        model.addAttribute("message", ex.getMessage());
        return "error";
    }

}
