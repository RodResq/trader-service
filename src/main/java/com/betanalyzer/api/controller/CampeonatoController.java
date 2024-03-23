package com.betanalyzer.api.controller;

import com.betanalyzer.api.assembler.CampeonatoAssembler;
import com.betanalyzer.api.domain.model.Campeonato;
import com.betanalyzer.api.domain.repository.CampeonatoRepository;
import com.betanalyzer.api.domain.service.CadastroCampeonatoService;
import com.betanalyzer.api.model.CampeonatoModel;
import com.betanalyzer.api.model.input.CampeonatoInput;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/campeonatos")
public class CampeonatoController {

    private final CadastroCampeonatoService cadastroCampeonatoService;
    private final CampeonatoRepository repository;
    private final CampeonatoAssembler campeonatoAssembler;

    @GetMapping
    public List<Campeonato> listar() {
        return repository.findAll();
    }

    @GetMapping("/{idCampeonato}")
    public ResponseEntity<CampeonatoModel> buscarPorId(@PathVariable Long idCampeonato) {
        return  repository.findById(idCampeonato)
                .map(campeonato -> {
                    var campeonatoModel = new CampeonatoModel();
                    campeonatoModel.setId(campeonato.getIdCampeonato());
                    campeonatoModel.setNomeCampeonato(campeonato.getNome());

                    return ResponseEntity.ok().body(campeonatoModel);
                }).orElse(ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CampeonatoModel salvar(@Valid @RequestBody CampeonatoInput campeonatoInput) {
        Campeonato campeonato = campeonatoAssembler.toEntity(campeonatoInput);
        return  campeonatoAssembler.toModel(cadastroCampeonatoService.salvar(campeonato));
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

}
