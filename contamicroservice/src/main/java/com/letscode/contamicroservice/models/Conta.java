package com.letscode.contamicroservice.models;

import com.letscode.contamicroservice.clients.dto.Usuario;
import com.letscode.contamicroservice.dtos.ContaRequest;
import com.letscode.contamicroservice.enums.TipoConta;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "conta")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "agencia_conta")
    private Integer agencia;

    @Column(name = "data_criacao")
    @CreatedDate
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    @CreatedDate
    private LocalDateTime dataAtualizacao;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "tipo_conta")
    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

    @Column(name = "id_usuario_conta")
    private String idUsuarioConta;


    public Conta(ContaRequest contaRequest, Usuario usuario) {
        this.agencia = contaRequest.getAgencia();
        this.saldo = contaRequest.getSaldo();
        this.tipoConta = contaRequest.getTipoConta();
        this.idUsuarioConta = usuario.getId();
    }
}
