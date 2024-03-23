package com.betanalyzer.api.domain.model;

import com.betanalyzer.api.domain.validation.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Classificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idClassificacao;

    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.CampeonatoId.class)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "idCampeonato")
    private Campeonato campeonato;

    @NotBlank
    @Size(max = 20)
    private String clube;

    @NotNull
    @Positive
    private Integer ponto;

    @NotNull
    @Positive
    private Integer partidaJogada;

    @NotNull
    @Positive
    private Integer vitoria;
    private Integer empate;
    private Integer derrota;
    private Integer golsPro;
    private Integer golsContra;
    private Integer golsSaldo;


    private OffsetDateTime data;
}
