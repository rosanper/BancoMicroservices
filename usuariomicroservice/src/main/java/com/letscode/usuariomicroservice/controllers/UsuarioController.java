package com.letscode.usuariomicroservice.controllers;

import com.letscode.usuariomicroservice.dtos.UsuarioRequest;
import com.letscode.usuariomicroservice.models.Usuario;
import com.letscode.usuariomicroservice.services.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping
    public List<Usuario> getAllUsuarios(){
        return usuarioService.getAll();
    }

    @PostMapping
    public Usuario create(@RequestBody @Valid UsuarioRequest usuarioRequest){
        return usuarioService.create(usuarioRequest);
    }

    @GetMapping("/{id}")
    public Usuario getById(@PathVariable("id") UUID id){
        return usuarioService.getById(id);
    }

    @GetMapping("/getByCpf/{cpf}")
    public Usuario getByCpf(@PathVariable("cpf") String cpf){
        return usuarioService.getByCpf(cpf);
    }

    @PutMapping("/{id}")
    public Usuario update(@PathVariable UUID id, @RequestBody @Valid UsuarioRequest usuarioRequest){
        return usuarioService.update(usuarioRequest,id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        usuarioService.delete(id);
    }
}
