package com.letscode.contamicroservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErroValidation {
    private String campo;
    private String mensagemErro;
}
