package com.betanalyzer.api.controller;

import com.betanalyzer.api.domain.exception.NegocioException;
import com.betanalyzer.api.domain.model.Classificacao;
import com.betanalyzer.api.domain.repository.ClassificacaoRepository;
import com.betanalyzer.api.domain.service.CadastroCampeonatoService;
import com.betanalyzer.api.domain.service.ClassificacaoService;
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

    @GetMapping
    public List<Classificacao> listar() {
        return classificacaoRepository.findAll();
    }

    @GetMapping("/{idClassifcacao}")
    public ResponseEntity<Classificacao> buscar(@PathVariable Long idClassifcacao) {
        return classificacaoRepository.findById(idClassifcacao)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Classificacao cadstrar(@RequestBody Classificacao classificacao) {
        return classificacaoService.cadastrar(classificacao);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> caputurarException(NegocioException negocioException) {
        return ResponseEntity.badRequest().body(negocioException.getMessage());
    }

}
