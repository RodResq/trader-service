package com.betanalyzer.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassificacaoInput {

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

    @NotNull
    @Positive
    private Integer empate;

    @NotNull
    @Positive
    private Integer derrota;


    private Integer golsPro;
    private Integer golsContra;
    private Integer golsSaldo;

    @Valid
    @NotNull
    private CampeonatoInput campeonato;
}
