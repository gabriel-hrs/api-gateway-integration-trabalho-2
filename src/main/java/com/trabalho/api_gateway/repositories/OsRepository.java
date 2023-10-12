package com.trabalho.api_gateway.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabalho.api_gateway.entities.OrdemServico;

@Repository
public interface OsRepository extends JpaRepository<OrdemServico, Long>{

    Optional<OrdemServico> findOsByNumero(Long numero);
    
}

