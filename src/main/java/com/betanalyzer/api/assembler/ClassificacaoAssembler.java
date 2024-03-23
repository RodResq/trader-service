package com.betanalyzer.api.assembler;

import com.betanalyzer.api.domain.model.Classificacao;
import com.betanalyzer.api.model.ClassificacaoModel;
import com.betanalyzer.api.model.input.ClassificacaoInput;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ClassificacaoAssembler {

    private final ModelMapper modelMapper;

    public ClassificacaoModel toModel(Classificacao classificacao) {
        return modelMapper.map(classificacao, ClassificacaoModel.class);
    }

    public List<ClassificacaoModel> toCollectionModel(List<Classificacao> classificacoes) {
        return classificacoes.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Classificacao toEntity(ClassificacaoInput classificacaoInput) {
        return modelMapper.map(classificacaoInput, Classificacao.class);
    }
}
