package com.letscode.contamicroservice.services.impl;

import com.letscode.contamicroservice.clients.UsuarioClientService;
import com.letscode.contamicroservice.clients.dto.Usuario;
import com.letscode.contamicroservice.dtos.ContaRequest;
import com.letscode.contamicroservice.dtos.ContaRequestUpdate;
import com.letscode.contamicroservice.exceptions.ErroDadosCadastro;
import com.letscode.contamicroservice.exceptions.ErroNotFound;
import com.letscode.contamicroservice.models.Conta;
import com.letscode.contamicroservice.repositories.ContaRepository;
import com.letscode.contamicroservice.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private UsuarioClientService usuarioClientService;

    @Override
    public Conta create(ContaRequest contaRequest) {
        Usuario usuario = this.getUsuarioByCpf(contaRequest);
        Conta conta = new Conta(contaRequest,usuario);

        //gerar numero aleatorio
        int numeroConta;
        boolean temConta;

        do {
            numeroConta = Math.round(new Random().nextFloat() * 1000000);
            temConta = this.existsConta(numeroConta);
        }while (temConta == true);

        conta.setNumero(numeroConta);

        contaRepository.save(conta);
        return conta;

    }

    @Override
    public List<Conta> getAll() {
        return contaRepository.findAll();
    }

    @Override
    public Conta getById(UUID id) {
        return contaRepository.findById(id).orElseThrow(() -> new ErroNotFound("Não existe conta com esse id"));
    }

    @Override
    public Conta update(UUID id, ContaRequestUpdate contaRequestUpdate) {
        Conta conta = this.getById(id);
        conta.setAgencia(contaRequestUpdate.getAgencia());
        conta.setTipoConta(contaRequestUpdate.getTipoConta());
        conta.setSaldo(contaRequestUpdate.getSaldo());
        conta.setDataAtualizacao(LocalDateTime.now());

        contaRepository.save(conta);
        return conta;
    }

    @Override
    public void delete(UUID id) {
        Conta conta = this.getById(id);
        contaRepository.delete(conta);
    }

    @Override
    public boolean existsConta(int numero) {
        return contaRepository.existsByNumero(numero);
    }

    public Usuario getUsuarioByCpf(ContaRequest contaRequest){
        Usuario usuario = usuarioClientService.getUsuarioByCpf(contaRequest.getCpf());
        if (usuario.getCpf() == null) {
            return usuarioClientService.createUsuario(contaRequest);
        }
        return usuario;
    }

    public Usuario getUsuario(String id){
        Usuario usuario = usuarioClientService.getUsuarioById(id);
        if (usuario.getCpf() == null) throw new ErroDadosCadastro("Não existe usuário com esse id.");
        return usuario;
    }
}
