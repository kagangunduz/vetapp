package com.kagangunduz.vet.advice;

import com.kagangunduz.vet.exception.GenusNotFoundException;
import com.kagangunduz.vet.exception.OwnerNotFoundException;
import com.kagangunduz.vet.exception.PetNotFoundException;
import com.kagangunduz.vet.exception.SpeciesNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({OwnerNotFoundException.class, PetNotFoundException.class, GenusNotFoundException.class,
            SpeciesNotFoundException.class})
    public final String hanleExceptions(Model model, Exception exception) {
        System.out.println(exception.getMessage());
        model.addAttribute("message", exception.getMessage());
        return "error";
    }

    @ExceptionHandler({NumberFormatException.class, IllegalArgumentException.class})
    public final String handleNumberFormatIllegalArgument(Model model, Exception exception) {
        System.out.println(exception.getMessage());
        model.addAttribute("message", "Sorun oluştu.");
        return "error";
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final String handleMethodArgumentTypeMismatchException(Model model, Exception exception) {
        System.out.println(exception.getMessage());
        model.addAttribute("message", "Sayfa bulunamadı.");
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public final String handleAllExceptions(Model model, Exception exception) {
        System.out.println(exception.getMessage());
        model.addAttribute("message", "Sayfa bulunamadı.");
        return "error";
    }

}
