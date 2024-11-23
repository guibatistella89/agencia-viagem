package com.agenciaviagem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Destino {
    private Long id;
    private String nome;
    private String localizacao;
    private String descricao;
    private double mediaAvaliacao;
    private int totalAvaliacoes;
}