package com.br.sellers.feactureflagservice.gateway.repository;

import com.br.sellers.feactureflagservice.domain.FeactureParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FeactureParamRepository extends JpaRepository<FeactureParam, UUID> {
}
