package com.letscode.contamicroservice.dtos;

import com.letscode.contamicroservice.enums.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContaRequestUpdate {
    @NotNull(message = "A agencia não pode ser nula")
    private Integer agencia;

    @NotNull(message = "O saldo não pode ser nulo")
    private BigDecimal saldo;

    @NotNull(message = "O tipo de transação não pode ser nulo")
    private TipoConta tipoConta;
}
