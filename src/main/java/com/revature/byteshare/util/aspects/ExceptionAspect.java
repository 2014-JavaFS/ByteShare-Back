package com.revature.byteshare.util.aspects;

import com.revature.byteshare.util.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAspect {
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String dataNotFound(DataNotFoundException dnf){
        return dnf.getMessage();
    }

    @ExceptionHandler(value = {NutritionixException.class})
    @ResponseStatus(value = HttpStatus.FAILED_DEPENDENCY)
    public String nutritionixException(NutritionixException e) { return e.getMessage(); }
}
