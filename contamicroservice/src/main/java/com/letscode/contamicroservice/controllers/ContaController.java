package com.letscode.contamicroservice.controllers;

import com.letscode.contamicroservice.dtos.ContaRequest;
import com.letscode.contamicroservice.dtos.ContaRequestUpdate;
import com.letscode.contamicroservice.dtos.ContaResponse;
import com.letscode.contamicroservice.models.Conta;
import com.letscode.contamicroservice.services.impl.ContaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaServiceImpl contaService;

    @PostMapping
    private Conta create(@RequestBody @Valid ContaRequest contaRequest){
        return contaService.create(contaRequest);
    }

    @GetMapping
    private List<ContaResponse> getAll(){
        List<ContaResponse> contas = contaService.getAll().stream()
                .map(conta -> new ContaResponse(conta, contaService.getUsuario(conta.getIdUsuarioConta())))
                .collect(Collectors.toList());
        return contas;
    }

    @GetMapping("/{id}")
    private ContaResponse findById(@PathVariable UUID id){
        Conta conta = contaService.getById(id);
        return new ContaResponse(conta, contaService.getUsuario(conta.getIdUsuarioConta()));
    }

    @PutMapping("/{id}")
    private Conta update(@PathVariable UUID id, @RequestBody @Valid ContaRequestUpdate contaRequestUpdate){
        return contaService.update(id, contaRequestUpdate);
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable UUID id){
        contaService.delete(id);
    }

//    @GetMapping("/teste")
//    private Usuario teste(String id){
//        return contaService.getUsuario(id);
//    }

}
