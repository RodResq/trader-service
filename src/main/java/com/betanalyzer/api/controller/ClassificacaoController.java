package com.betanalyzer.api.controller;

import com.betanalyzer.api.assembler.ClassificacaoAssembler;
import com.betanalyzer.api.domain.model.Classificacao;
import com.betanalyzer.api.domain.repository.ClassificacaoRepository;
import com.betanalyzer.api.domain.service.ClassificacaoService;
import com.betanalyzer.api.model.ClassificacaoModel;
import com.betanalyzer.api.model.input.ClassificacaoInput;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/classificacoes")
public class ClassificacaoController {

    private final ClassificacaoRepository classificacaoRepository;
    private final ClassificacaoService classificacaoService;
    private final ClassificacaoAssembler classificacaoAssembler;

    @GetMapping
    public List<ClassificacaoModel> listar() {
        return classificacaoAssembler.toCollectionModel(classificacaoRepository.findAll());
    }

    @GetMapping("/{idClassifcacao}")
    public ResponseEntity<ClassificacaoModel> buscar(@PathVariable Long idClassifcacao) {
        return classificacaoRepository.findById(idClassifcacao)
                .map(classificacaoAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClassificacaoModel cadstrar(@Valid @RequestBody ClassificacaoInput classificacaoInput) {
        Classificacao classificacao = classificacaoAssembler.toEntity(classificacaoInput);
        return classificacaoAssembler.toModel(classificacaoService.cadastrar(classificacao));
    }

}
