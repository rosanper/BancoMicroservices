package com.letscode.contamicroservice.clients;


import com.letscode.contamicroservice.clients.dto.Usuario;
import com.letscode.contamicroservice.clients.dto.UsuarioRequest;
import com.letscode.contamicroservice.dtos.ContaRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class UsuarioClientService {
    private static final String URL_OBTER_USUARIO_ID = "http://localhost:8080/usuarios/{id}";
    private static final String URL_OBTER_USUARIO_CPF = "http://localhost:8080/usuarios/getByCpf/{cpf}";
    private static final String URL_CRIAR_USUARIO = "http://localhost:8080/usuarios";

    public Usuario getUsuarioById(String id){
        try{
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
            ResponseEntity<Usuario> usuario =
                    restTemplate.exchange(URL_OBTER_USUARIO_ID,
                            HttpMethod.GET,
                            httpEntity,
                            Usuario.class,
                            id);
            return usuario.getBody();
        }catch (RestClientException e){
            throw new RestClientException(e.getMessage());
        }
    }

    public Usuario getUsuarioByCpf(String cpf){
        try{
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
            ResponseEntity<Usuario> usuario =
                    restTemplate.exchange(URL_OBTER_USUARIO_CPF,
                            HttpMethod.GET,
                            httpEntity,
                            Usuario.class,
                            cpf);
            return usuario.getBody();
        }catch (RestClientException e){
            throw new RestClientException(e.getMessage());
        }
    }

    public Usuario createUsuario(ContaRequest contaRequest){
        try{
            UsuarioRequest usuarioRequest = new UsuarioRequest();
            usuarioRequest.setCpf(contaRequest.getCpf());
            usuarioRequest.setNome(contaRequest.getNome());
            usuarioRequest.setSenha(String.valueOf(Math.round(new Random().nextFloat() * 1000000)));

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Usuario> usuario = restTemplate.postForEntity(URL_CRIAR_USUARIO,
                    usuarioRequest, Usuario.class);

            return usuario.getBody();
        }catch (RestClientException e){
            throw new RestClientException(e.getMessage());
        }
    }
}
