package com.br.sellers.feactureflagclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "feacture-flag", url = "${service.flag-clients.url}")
public interface GetIsEnabledFeactureFlagClient {

    @GetMapping("/")
    Boolean execute(@PathVariable("feactureName") String feactureName,
                    @RequestParam Map<String, String> feactureParams);

}
