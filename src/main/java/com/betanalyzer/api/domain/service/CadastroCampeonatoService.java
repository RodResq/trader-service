package com.betanalyzer.api.domain.service;

import com.betanalyzer.api.domain.exception.NegocioException;
import com.betanalyzer.api.domain.model.Campeonato;
import com.betanalyzer.api.domain.repository.CampeonatoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CadastroCampeonatoService {

    private final CampeonatoRepository campeonatoRepository;

    public Campeonato buscar(Long idCampeonato) {
        return  campeonatoRepository.findById(idCampeonato)
                .orElseThrow(() -> new NegocioException("Campeonato nao encontrado!"));
    }

    @Transactional
    public Campeonato salvar(Campeonato campeonato) {
        boolean nomeExiste = campeonatoRepository.findByNome(campeonato.getNome())
                .filter(c -> !c.equals(campeonato))
                .isPresent();

        if (nomeExiste) {
            throw new NegocioException("Nome do campeonato ja existe!");
        }

        return campeonatoRepository.save(campeonato);
    }
}
