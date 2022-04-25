package com.letscode.contamicroservice.services;

import com.letscode.contamicroservice.dtos.ContaRequest;
import com.letscode.contamicroservice.dtos.ContaRequestUpdate;
import com.letscode.contamicroservice.models.Conta;

import java.util.List;
import java.util.UUID;

public interface ContaService {
    Conta create(ContaRequest contaRequest);
    List<Conta> getAll();
    Conta getById(UUID id);
    Conta update(UUID id, ContaRequestUpdate contaRequestUpdate);
    void delete(UUID id);
    boolean existsConta(int numero);
}
