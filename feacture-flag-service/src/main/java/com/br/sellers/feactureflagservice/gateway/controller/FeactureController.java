package com.br.sellers.feactureflagservice.gateway.controller;

import com.br.sellers.feactureflagservice.service.GetFeactureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/feactures")
@RequiredArgsConstructor
public class FeactureController {

    private final GetFeactureService getFeactureService;

    @GetMapping(path = "/{feactureName}", produces = "application/json")
    public Boolean execute(@PathVariable String feactureName,
                           @RequestParam Map<String, String> feactureParams){
        return getFeactureService.getAllFeacturesByParams(feactureName, feactureParams);
    }
}
