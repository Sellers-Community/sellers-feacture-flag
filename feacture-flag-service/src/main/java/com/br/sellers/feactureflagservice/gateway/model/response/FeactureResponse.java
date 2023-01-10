package com.br.sellers.feactureflagservice.gateway.model.response;

import com.br.sellers.feactureflagservice.domain.Feacture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeactureResponse {

    private String name;
    private String typeFeacture;
    private List<FeactureParamResponse> feactureParams;

    public static FeactureResponse to(Feacture feacture) {
        List<FeactureParamResponse> feactureParamResponseList = Optional.ofNullable(feacture.getFeactureParams())
                .orElse(Collections.emptyList())
                .stream().map(FeactureParamResponse::to)
                .collect(Collectors.toList());

        return FeactureResponse.builder()
                .name(feacture.getName())
                .typeFeacture(feacture.getTypeFeacture().toString())
                .feactureParams(feactureParamResponseList.isEmpty() ? Collections.emptyList() : feactureParamResponseList)
                .build();
    }

    public static List<FeactureResponse> toList(List<Feacture> feactureList) {
        return Optional.ofNullable(feactureList)
                .orElse(Collections.emptyList())
                .stream().map(FeactureResponse::to)
                .collect(Collectors.toList());
    }

}
