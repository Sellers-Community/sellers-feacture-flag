package com.br.sellers.feactureflagservice.service;

import com.br.sellers.feactureflagservice.domain.Feacture;
import com.br.sellers.feactureflagservice.exceptions.ParamsNotFoundException;
import com.br.sellers.feactureflagservice.gateway.repository.FeactureRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GetFeactureService {

    private final FeactureRepository feactureRepository;

    private static Map<String, Feacture> allFeacture;
    private static Map<String, Map<String, Feacture>> allKeys;

    @PostConstruct
    public void loadFeactureMaps() {
        allFeacture = new HashMap<>();
        allKeys = new HashMap<>();
        List<Feacture> feactureList = getFeactureList();
        if (!ObjectUtils.isEmpty(feactureList)) {
            setAllFeactureMapAndSetAllKeysMap(feactureList);
        }
    }

    public Boolean getAllFeacturesByParams(String feactureName, Map<String, String> feactureParams) {
        Boolean existsFeacture = allFeacture.values().stream()
                .anyMatch(feacture ->
                        feacture.getName().equals(feactureName)
                );
        if (existsFeacture.equals(false)) {
            return false;
        }

        Feacture feacture = allFeacture.values().stream()
                .filter(f -> f.getName().equals(feactureName))
                .findAny()
                .orElse(null);
        if (feacture == null) {
            return false;
        }

        if (feacture.getTypeFeacture().name().equals("DISABLED_FOR_ALL")) {
            return false;
        }

        if (feacture.getTypeFeacture().name().equals("ENABLED_FOR_ALL")) {
            return true;
        }

        if (feacture.getTypeFeacture().name().equals("ENABLED_FOR_PARAMS")) {
            Set<String> keys = feactureParams.keySet();
            Boolean equalsKeys = keys.stream().anyMatch(key -> allKeys.containsKey(key));
            return equalsKeys;
        }

        if (feacture.getTypeFeacture().name().equals("DISABLED_FOR_PARAMS")) {
            Set<String> keys = feactureParams.keySet();
            Boolean equalsKeys = !keys.stream().anyMatch(key -> allKeys.containsKey(key));
            return equalsKeys;
        }

        throw new ParamsNotFoundException();
    }

    private List<Feacture> getFeactureList() {
        return Optional.ofNullable(feactureRepository.findAll())
                .orElse(Collections.emptyList());
    }

    private static void setAllFeactureMapAndSetAllKeysMap(List<Feacture> feactureList) {
        feactureList.stream().forEach(feacture -> {
            feacture.getFeactureParams().stream().forEach(feactureParam -> {
                allFeacture.put(feactureParam.getKey(), feacture);
                allKeys.put(feactureParam.getKey(), allFeacture);
            });
        });
    }

}
