package com.letscode.usuariomicroservice.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UsuarioRequest {
    @NotBlank(message = "O cpf não pode ser nulo nem branco")
    @Size(max = 11)
    private String cpf;

    @NotBlank(message = "O nome não pode ser nulo nem branco")
    @Size(min = 2)
    private String nome;

    @NotBlank(message = "A senha não pode ser nulo nem branco")
    @Size(max = 8)
    private String senha;
}
