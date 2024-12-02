package com.agenciaviagem.dominio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DestinoListDTO {
    private Long id;
    private String nome;
}
