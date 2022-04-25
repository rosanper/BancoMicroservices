package com.letscode.contamicroservice.dtos;

import com.letscode.contamicroservice.enums.TipoConta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ContaRequest {
//    @NotNull(message = "O número não pode ser nulo")
//    private Integer numero;

    @NotNull(message = "A agencia não pode ser nula")
    private Integer agencia;

    @NotNull(message = "O saldo não pode ser nulo")
    private BigDecimal saldo;

    @NotNull(message = "O tipo de transação não pode ser nulo")
    private TipoConta tipoConta;

    @NotBlank(message = "O cpf não pode ser nulo nem branco")
    @Size(max = 11)
    private String cpf;

    @NotBlank(message = "O nome não pode ser nulo nem branco")
    @Size(min = 2)
    private String nome;

//    @NotNull(message = "Tem que ser passado o id do Usuário da conta")
//    private String idUsuarioConta;

}
