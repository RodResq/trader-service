package com.betanalyzer.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampeonatoInput {

    @NotBlank
    private String nome;

    private boolean ativo;
}
