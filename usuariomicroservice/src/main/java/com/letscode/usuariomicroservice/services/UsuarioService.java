package com.letscode.usuariomicroservice.services;

import com.letscode.usuariomicroservice.dtos.UsuarioRequest;
import com.letscode.usuariomicroservice.models.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioService {
    List<Usuario> getAll();
    Usuario create(UsuarioRequest usuarioRequest);
    Usuario getById(UUID id);

    Usuario getByCpf(String cpf);

    Usuario update(UsuarioRequest usuarioRequest, UUID id);
    void delete(UUID id);

    void verifyByCpf(String cpf);

    void verifyBySenha(String senha);
}
