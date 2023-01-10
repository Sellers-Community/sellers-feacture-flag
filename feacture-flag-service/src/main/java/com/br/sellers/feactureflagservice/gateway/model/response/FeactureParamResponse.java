package com.br.sellers.feactureflagservice.gateway.model.response;

import com.br.sellers.feactureflagservice.domain.FeactureParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeactureParamResponse {

    private String key;
    private String value;

    public static FeactureParamResponse to(FeactureParam feactureParam){
        return FeactureParamResponse.builder()
                .key(feactureParam.getKey())
                .value(feactureParam.getValue())
                .build();
    }

}
