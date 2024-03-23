package com.betanalyzer.api.assembler;

import com.betanalyzer.api.domain.model.Campeonato;
import com.betanalyzer.api.model.CampeonatoModel;
import com.betanalyzer.api.model.input.CampeonatoInput;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class CampeonatoAssembler {

    private final ModelMapper modelMapper;

    public CampeonatoModel toModel(Campeonato campeonato) {
        return modelMapper.map(campeonato, CampeonatoModel.class);
    }

    public Campeonato toEntity(CampeonatoInput campeonatoInput) {
        return modelMapper.map(campeonatoInput, Campeonato.class);
    }

    public List<CampeonatoModel> toCollectionModel(List<Campeonato> campeonatos) {
        return campeonatos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
