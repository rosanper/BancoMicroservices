package com.letscode.usuariomicroservice.configs;

import com.letscode.usuariomicroservice.exceptions.ErroDadosCadastro;
import com.letscode.usuariomicroservice.exceptions.ErroMensagem;
import com.letscode.usuariomicroservice.exceptions.ErroNotFound;
import com.letscode.usuariomicroservice.exceptions.ErroValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
