package com.letscode.usuariomicroservice.exceptions;

import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErroMensagem {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
}
