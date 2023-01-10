package com.br.sellers.feactureflagservice.exceptions.handler;

import com.br.sellers.feactureflagservice.exceptions.FeactureNotFoundException;
import com.br.sellers.feactureflagservice.exceptions.ParamsNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends DefaultErrorAttributes {

    @ExceptionHandler(FeactureNotFoundException.class)
    public final ResponseEntity<Object> handleAllExceptions(FeactureNotFoundException exception) {
        Map<String, Object> map = new HashMap<>();
        map.put("error", exception.getMessage());
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ParamsNotFoundException.class)
    public final ResponseEntity<Object> handleAllExceptions(ParamsNotFoundException exception) {
        Map<String, Object> map = new HashMap<>();
        map.put("error", exception.getMessage());
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

}
