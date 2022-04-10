package com.kagangunduz.vet.advice;

import com.kagangunduz.vet.exception.OwnerNotFoundException;
import com.kagangunduz.vet.exception.PetNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({PetNotFoundException.class, OwnerNotFoundException.class, EntityNotFoundException.class})
    public ModelAndView handlePetNotFoundException(PetNotFoundException ex, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    /*@ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView handleEntityNotFoundException(EntityNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }*/


}
