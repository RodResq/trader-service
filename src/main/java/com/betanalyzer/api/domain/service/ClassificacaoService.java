package com.betanalyzer.api.domain.service;

import com.betanalyzer.api.domain.exception.NegocioException;
import com.betanalyzer.api.domain.model.Campeonato;
import com.betanalyzer.api.domain.model.Classificacao;
import com.betanalyzer.api.domain.repository.CampeonatoRepository;
import com.betanalyzer.api.domain.repository.ClassificacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ClassificacaoService {

    private final ClassificacaoRepository classificacaoRepository;
    private final CadastroCampeonatoService cadastroCampeonatoService;

    @Transactional
    public Classificacao cadastrar(Classificacao classificacao) {
        if (classificacao.getIdClassificacao() != null) {
            throw new NegocioException("Classificacao ja existe!");
        }

        Campeonato campeonato = cadastroCampeonatoService.buscar(classificacao.getCampeonato().getIdCampeonato());

        classificacao.setCampeonato(campeonato);
        classificacao.setData(LocalDateTime.now());

        return classificacaoRepository.save(classificacao);
    }
}
