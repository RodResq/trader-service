package com.betanalyzer.api.domain.repository;

import com.betanalyzer.api.domain.model.Campeonato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CampeonatoRepository extends PagingAndSortingRepository<Campeonato, Long>, JpaRepository<Campeonato, Long> {

    List<Campeonato> findByNomeContaining(String nome);

    Optional<Campeonato> findByNome(String nome);

}
