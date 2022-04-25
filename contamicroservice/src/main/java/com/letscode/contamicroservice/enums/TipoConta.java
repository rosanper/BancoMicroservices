package com.letscode.contamicroservice.enums;

import lombok.Getter;

@Getter
public enum TipoConta {
    CONTA_CORRENTE("Conta Corrente"),
    CONTA_POUPANCA("Conta Poupança");

    private String nome;

    TipoConta(String nome) {
        this.nome = nome;
    }
}
