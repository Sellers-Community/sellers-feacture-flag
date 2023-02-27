package com.br.sellers.feactureflagclient.service;

import com.br.sellers.feactureflagclient.client.GetIsEnabledFeactureFlagClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SellerFlagService {

    private final GetIsEnabledFeactureFlagClient getIsEnabledFeactureFlagClient;

    public Boolean isEnabledFeactureFlag(String feactureName, Map<String, String> feactureParams){
        return getIsEnabledFeactureFlagClient.execute(feactureName, feactureParams);
    }
}
