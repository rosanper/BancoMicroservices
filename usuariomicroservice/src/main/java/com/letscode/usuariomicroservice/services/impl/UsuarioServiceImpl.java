package com.letscode.usuariomicroservice.services.impl;

import com.letscode.usuariomicroservice.dtos.UsuarioRequest;
import com.letscode.usuariomicroservice.exceptions.ErroDadosCadastro;
import com.letscode.usuariomicroservice.exceptions.ErroNotFound;
import com.letscode.usuariomicroservice.models.Usuario;
import com.letscode.usuariomicroservice.repositories.UsuarioRepository;
import com.letscode.usuariomicroservice.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario create(UsuarioRequest usuarioRequest) {
        this.verifyByCpf(usuarioRequest.getCpf());
        this.verifyBySenha(usuarioRequest.getSenha());
        Usuario usuario = new Usuario(usuarioRequest);
        usuarioRepository.save(usuario);
        return usuario;
    }

    @Override
    public Usuario getById(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new ErroNotFound("Não possui usuário com esse Id."));
        return usuario;
    }

    @Override
    public Usuario getByCpf(String cpf) {
        Usuario usuario = usuarioRepository.findByCpf(cpf)
                .orElseThrow(()-> new ErroNotFound("Não possui usuário com esse Id."));
        return usuario;
    }

    @Override
    public Usuario update(UsuarioRequest usuarioRequest, UUID id) {
        Usuario usuario = this.getById(id);

        if(usuarioRequest.getCpf() != usuario.getCpf()) this.verifyByCpf(usuarioRequest.getCpf());
        if(usuarioRequest.getSenha() != usuario.getSenha()) this.verifyBySenha(usuarioRequest.getSenha());

        usuario.setCpf(usuarioRequest.getCpf());
        usuario.setNome(usuarioRequest.getNome());
        usuario.setSenha(usuarioRequest.getSenha());
        usuario.setDataAtualizacao(LocalDateTime.now());
        usuarioRepository.save(usuario);
        return usuario;
    }

    @Override
    public void delete(UUID id) {
        Usuario usuario = this.getById(id);
        usuarioRepository.delete(usuario);
    }

    @Override
    public void verifyByCpf(String cpf) {
        Usuario usuario = usuarioRepository.findByCpf(cpf).orElse(null);
        if(usuario != null){
           throw new ErroDadosCadastro("Já possui usuário com esse Cpf");
        }
    }

    @Override
    public void verifyBySenha(String senha) {
        Usuario usuario = usuarioRepository.findBySenha(senha).orElse(null);
        if(usuario != null){
            throw new ErroDadosCadastro("Essa senha já foi cadastrada, você deve passar outra");
        }
    }

}
