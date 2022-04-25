package com.letscode.contamicroservice.config;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.letscode.contamicroservice.exceptions.ErroDadosCadastro;
import com.letscode.contamicroservice.exceptions.ErroMensagem;
import com.letscode.contamicroservice.exceptions.ErroNotFound;
import com.letscode.contamicroservice.exceptions.ErroValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroHandler {
    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroValidation> handlerValidation(MethodArgumentNotValidException exception){
        List<ErroValidation> erros = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e->{
            String mensagemError = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroValidation errorValidacao = new ErroValidation(e.getField(), mensagemError);
            erros.add(errorValidacao);
        });
        return erros;
    }

    @ExceptionHandler(ErroNotFound.class)
    public ErroMensagem handlerNotFind(ErroNotFound e){
        return ErroMensagem.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Objeto não encontrado.")
                .message(e.getMessage()).build();
    }

    @ExceptionHandler(ErroDadosCadastro.class)
    public ErroMensagem handlerCpf(ErroDadosCadastro e){
        return ErroMensagem.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Erro de regra de cadastro.")
                .message(e.getMessage()).build();
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ErroMensagem handlerCpf(InvalidFormatException e){
        return ErroMensagem.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Erro na passagem de informações.")
                .message(e.getMessage()).build();            // Ajustar a mensagem
    }

    @ExceptionHandler(RestClientException.class)
    public ErroMensagem handlerCpf(RestClientException e){
        return ErroMensagem.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Erro com a requisição ao cliente.")
                .message(e.getMessage()).build();            // Ajustar a mensagem
    }
}
