package com.betanalyzer.api.controller;

import com.betanalyzer.api.domain.model.Campeonato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CampeonatoController {

    @PersistenceContext
    private EntityManager maneger;

    @GetMapping("/campeonatos")
    public List<Campeonato> listarJogos() {
        return maneger.createQuery("from Campeonato", Campeonato.class)
                .getResultList();
    }
}
