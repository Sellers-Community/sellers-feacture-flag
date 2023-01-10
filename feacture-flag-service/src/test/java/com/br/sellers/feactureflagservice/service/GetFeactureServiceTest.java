package com.br.sellers.feactureflagservice.service;

import com.br.sellers.feactureflagservice.domain.Feacture;
import com.br.sellers.feactureflagservice.domain.FeactureParam;
import com.br.sellers.feactureflagservice.enums.TypeFeacture;
import com.br.sellers.feactureflagservice.gateway.repository.FeactureRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetFeactureServiceTest {

    @Mock
    private FeactureRepository feactureRepository;

    @InjectMocks
    private GetFeactureService getFeactureService;

    private List<Feacture> feactureList;

    @Before
    public void init() {
        feactureList = Arrays.asList(
                Feacture.builder()
                        .id(UUID.fromString("a52a6c4d-f14d-48dd-a0aa-eea87f38644a"))
                        .name("feacture-test-00")
                        .typeFeacture(TypeFeacture.ENABLED_FOR_ALL)
                        .feactureParams(Arrays.asList(
                                FeactureParam.builder()
                                        .id(UUID.randomUUID())
                                        .key("key-flag-test-00")
                                        .value("value-flag-test-00")
                                        .feacture(Feacture.builder()
                                                .id(UUID.fromString("a52a6c4d-f14d-48dd-a0aa-eea87f38644a"))
                                                .name("feacture-test-00")
                                                .typeFeacture(TypeFeacture.ENABLED_FOR_ALL)
                                                .build())
                                        .build()))
                        .build(),
                Feacture.builder()
                        .id(UUID.fromString("84a73ee1-90c4-49f1-8ae2-10689d970dd0"))
                        .name("feacture-test-01")
                        .typeFeacture(TypeFeacture.DISABLED_FOR_ALL)
                        .feactureParams(Arrays.asList(
                                FeactureParam.builder()
                                        .id(UUID.randomUUID())
                                        .key("key-flag-test-01")
                                        .value("value-flag-test-01")
                                        .feacture(Feacture.builder()
                                                .id(UUID.fromString("84a73ee1-90c4-49f1-8ae2-10689d970dd0"))
                                                .name("feacture-test-01")
                                                .typeFeacture(TypeFeacture.DISABLED_FOR_ALL)
                                                .build())
                                        .build()
                        ))
                        .build(),


                Feacture.builder()
                        .id(UUID.fromString("82eff2d9-cef5-497e-95a6-3d4d49529813"))
                        .name("feacture-test-02")
                        .typeFeacture(TypeFeacture.ENABLED_FOR_PARAMS)
                        .feactureParams(Arrays.asList(
                                FeactureParam.builder()
                                        .id(UUID.randomUUID())
                                        .key("key-flag-test-02")
                                        .value("value-flag-test-02")
                                        .feacture(Feacture.builder()
                                                .id(UUID.fromString("82eff2d9-cef5-497e-95a6-3d4d49529813"))
                                                .name("feacture-test-02")
                                                .typeFeacture(TypeFeacture.ENABLED_FOR_PARAMS)
                                                .build())
                                        .build()
                        ))
                        .build(),


                Feacture.builder()
                        .id(UUID.fromString("392b25a6-26e4-4ce5-91e5-d304dab78e0d"))
                        .name("feacture-test-03")
                        .typeFeacture(TypeFeacture.DISABLED_FOR_PARAMS)
                        .feactureParams(Arrays.asList(
                                FeactureParam.builder()
                                        .id(UUID.randomUUID())
                                        .key("key-flag-test-03")
                                        .value("value-flag-test-03")
                                        .feacture(Feacture.builder()
                                                .id(UUID.fromString("392b25a6-26e4-4ce5-91e5-d304dab78e0d"))
                                                .name("feacture-test-03")
                                                .typeFeacture(TypeFeacture.DISABLED_FOR_PARAMS)
                                                .build())
                                        .build()
                        ))
                        .build()
        );
    }

    @Test
    public void should_return_false_when_feacture_name_not_found() {
        when(feactureRepository.findAll()).thenReturn(feactureList);
        getFeactureService.loadFeactureMaps();
        assertFalse(getFeactureService.getAllFeacturesByParams("nameNotFoundTest", new HashMap<>()));
    }

    @Test
    public void should_return_true_when_feacture_type_is_ENABLED_FOR_ALL() {
        when(feactureRepository.findAll()).thenReturn(feactureList);
        getFeactureService.loadFeactureMaps();
        assertTrue(getFeactureService.getAllFeacturesByParams("feacture-test-00", new HashMap<>()));
    }

    @Test
    public void should_return_false_when_feacture_type_is_DISABLED_FOR_ALL() {
        when(feactureRepository.findAll()).thenReturn(feactureList);
        getFeactureService.loadFeactureMaps();
        assertFalse(getFeactureService.getAllFeacturesByParams("feacture-test-01", new HashMap<>()));
    }

    @Test
    public void should_return_false_when_feacture_type_is_ENABLED_FOR_PARAMS_and_keys_notPresent() {
        Map<String, String> feactureParams = new HashMap<>();
        feactureParams.put("incorrect-key-00", "incorrect-value-00");
        feactureParams.put("incorrect-key-01", "incorrect-value-01");
        when(feactureRepository.findAll()).thenReturn(feactureList);
        getFeactureService.loadFeactureMaps();
        assertFalse(getFeactureService.getAllFeacturesByParams("feacture-test-02", feactureParams));
    }

    @Test
    public void should_return_true_when_feacture_type_is_ENABLED_FOR_PARAMS_and_keys_isPresent() {
        Map<String, String> feactureParams = new HashMap<>();
        feactureParams.put("incorrect-key-00", "incorrect-value-00");
        feactureParams.put("key-flag-test-02", "value-flag-test-02");
        when(feactureRepository.findAll()).thenReturn(feactureList);
        getFeactureService.loadFeactureMaps();
        assertTrue(getFeactureService.getAllFeacturesByParams("feacture-test-02", feactureParams));
    }

    @Test
    public void should_return_false_when_feacture_type_is_DISABLED_FOR_PARAMS_and_keys_isPresent() {
        Map<String, String> feactureParams = new HashMap<>();
        feactureParams.put("incorrect-key-00", "incorrect-value-00");
        feactureParams.put("key-flag-test-02", "value-flag-test-02");
        when(feactureRepository.findAll()).thenReturn(feactureList);
        getFeactureService.loadFeactureMaps();
        assertFalse(getFeactureService.getAllFeacturesByParams("feacture-test-03", feactureParams));
    }

    @Test
    public void should_return_true_when_feacture_type_is_DISABLED_FOR_PARAMS_and_keys_notPresent() {
        Map<String, String> feactureParams = new HashMap<>();
        feactureParams.put("incorrect-key-00", "incorrect-value-00");
        feactureParams.put("incorrect-key-01", "incorrect-value-01");
        when(feactureRepository.findAll()).thenReturn(feactureList);
        getFeactureService.loadFeactureMaps();
        assertTrue(getFeactureService.getAllFeacturesByParams("feacture-test-03", feactureParams));
    }
}
