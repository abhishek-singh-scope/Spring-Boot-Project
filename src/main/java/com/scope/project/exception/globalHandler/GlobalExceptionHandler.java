package com.scope.project.exception.globalHandler;

import com.scope.project.exception.customException.IdNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String,String> errMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err->{
            errMap.put(err.getField(),err.getDefaultMessage());
        });

        logger.info(errMap);
        return errMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IdNotFoundException.class)
    public Map<String,String> handleIDException(IdNotFoundException ex){
            Map<String,String> errmap = new HashMap<>();
            errmap.put("errorMessage",ex.getMessage());

            logger.info(errmap);
            return errmap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String,String> handleGeneralException(Exception ex){
        Map<String,String> errmap = new HashMap<>();
        errmap.put("errorMessage",ex.getMessage());

        logger.info(errmap);
        return errmap;
    }
}
