package com.betanalyzer.api.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Classificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idClassificacao;

    @ManyToOne
    @JoinColumn(name = "idCampeonato")
    private Campeonato campeonato;

    private String clube;
    private Integer ponto;
    private Integer partidaJogada;
    private Integer vitoria;
    private Integer empate;
    private Integer derrota;
    private Integer golsPro;
    private Integer golsContra;
    private Integer golsSaldo;
    private LocalDateTime data;
}
