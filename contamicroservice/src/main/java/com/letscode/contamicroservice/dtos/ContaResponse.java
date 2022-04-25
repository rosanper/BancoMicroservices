package com.letscode.contamicroservice.dtos;

import com.letscode.contamicroservice.clients.dto.Usuario;
import com.letscode.contamicroservice.models.Conta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContaResponse {

    private Integer numero;
    private Integer agencia;
    private BigDecimal saldo;
    private String tipoConta;
    private Usuario usuario;

    public ContaResponse(Conta conta, Usuario usuario) {
        this.numero = conta.getNumero();
        this.agencia = conta.getAgencia();
        this.saldo = conta.getSaldo();
        this.tipoConta = conta.getTipoConta().getNome();
        this.usuario = usuario;
    }
}
