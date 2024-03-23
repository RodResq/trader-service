package com.betanalyzer.api.controller;

import com.betanalyzer.api.domain.exception.NegocioException;
import com.betanalyzer.api.domain.model.Campeonato;
import com.betanalyzer.api.domain.repository.CampeonatoRepository;
import com.betanalyzer.api.domain.service.CadastroCampeonatoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/campeonatos")
public class CampeonatoController {

    private final CadastroCampeonatoService cadastroCampeonatoService;
    private final CampeonatoRepository repository;

    @GetMapping
    public List<Campeonato> listar() {
        return repository.findAll();
    }

    @GetMapping("/{idCampeonato}")
    public ResponseEntity<Campeonato> buscarPorId(@PathVariable Long idCampeonato) {
        Optional<Campeonato> optional =  repository.findById(idCampeonato);

        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Campeonato salvar(@Valid @RequestBody Campeonato campeonato) {
        return cadastroCampeonatoService.salvar(campeonato);
    }

    @PutMapping("/{idCampeonato}")
    public ResponseEntity<Campeonato> atualizar(@PathVariable Long idCampeonato, @Valid @RequestBody Campeonato campeonato) {
        if (!repository.existsById(idCampeonato)) {
            return ResponseEntity.notFound().build();
        }

        campeonato.setIdCampeonato(idCampeonato);
        cadastroCampeonatoService.salvar(campeonato);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idCampeonato}")
    public ResponseEntity<Void> excluir(@PathVariable Long idCampeonato) {
        if (!repository.existsById(idCampeonato)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(idCampeonato);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> caputurarException(NegocioException negocioException) {
        return ResponseEntity.badRequest().body(negocioException.getMessage());
    }
}
