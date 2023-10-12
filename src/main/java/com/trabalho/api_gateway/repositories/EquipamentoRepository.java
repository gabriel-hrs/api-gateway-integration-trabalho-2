package com.trabalho.api_gateway.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabalho.api_gateway.entities.Equipamento;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long>{

    List<Equipamento> findByNumeroAndIndice(Long numero, String indice);

    List<Equipamento> findByNumero(Long numero);

    Optional<Equipamento> findEquipByNumero(Long numero);

    List<Equipamento> findAllByDescricao(String descricao);
    
}
    
