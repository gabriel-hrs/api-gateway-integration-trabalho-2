package com.trabalho.api_gateway.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trabalho.api_gateway.entities.Equipamento;
import com.trabalho.api_gateway.entities.OrdemServico;
import com.trabalho.api_gateway.repositories.EquipamentoRepository;
import com.trabalho.api_gateway.repositories.OsRepository;

@RestController
@RequestMapping("/api/v1/os")
public class Controller {

    @Autowired
    private OsRepository osRepo;

    @Autowired
    private EquipamentoRepository equipRepo;

    // Ordens de servico
    @PostMapping
    public ResponseEntity<OrdemServico> insert(@RequestBody OrdemServico ordemServico) {
        OrdemServico novaOS = osRepo.save(ordemServico);
        return new ResponseEntity<>(novaOS, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrdemServico>> findAll() {
        List<OrdemServico> list = osRepo.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{numero}")
    public ResponseEntity<OrdemServico> findOsByNumero(@PathVariable(value = "numero") Long numero) {
        Optional<OrdemServico> ordemServico = osRepo.findOsByNumero(numero);
        return ordemServico.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Equipamentos
    @PostMapping("/{numero}/equipamento")
    public ResponseEntity<Equipamento> insertEquipamento(@PathVariable(value ="numero") Long numero, @RequestBody Equipamento equipamento){
        Optional<OrdemServico> ordemServico = osRepo.findOsByNumero(numero);
        if(ordemServico.isPresent()){
            equipamento.setOrdemServico(ordemServico.get());
            Equipamento novoEquipamento = equipRepo.save(equipamento);
            return new ResponseEntity<>(novoEquipamento, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{numero}/equipamento/{indice}")
    public ResponseEntity<List<Equipamento>> findEquipamento(@PathVariable(value ="numero") Long numero, 
                                                                @PathVariable(value ="indice") String indice) {
        Optional<Equipamento> equipResult = equipRepo.findEquipByNumero(numero);
        if(equipResult.isPresent()){
            List<Equipamento> Result = equipRepo.findByNumeroAndIndice(numero, indice);
            return new ResponseEntity<>(Result, HttpStatus.OK);
                    
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 
               
    }

    @GetMapping("/{numero}/equipamento")
    public ResponseEntity<List<Equipamento>> findByNumero(@PathVariable(value = "numero") Long numero){     
        List<Equipamento> list = equipRepo.findByNumero(numero);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
     

    @GetMapping("/equipamento")
    public ResponseEntity<List<Equipamento>> findAllByDescricao(@RequestParam(value = "descricao") String descricao){
        List<Equipamento> list = equipRepo.findAllByDescricao(descricao);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
