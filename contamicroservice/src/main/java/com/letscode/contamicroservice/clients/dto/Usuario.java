package com.letscode.contamicroservice.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    private String id;
    private String cpf;
    private String nome;
}
