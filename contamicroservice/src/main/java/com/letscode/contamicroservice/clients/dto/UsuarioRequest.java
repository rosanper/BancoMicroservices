package com.letscode.contamicroservice.clients.dto;

import com.letscode.contamicroservice.dtos.ContaRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioRequest {

    private String cpf;
    private String nome;
    private String senha;

    public UsuarioRequest(ContaRequest contaRequest) {
        this.cpf = contaRequest.getCpf();
        this.nome = contaRequest.getNome();
    }
}
