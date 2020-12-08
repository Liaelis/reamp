package br.com.elisscherer.reamp.repository;

import br.com.elisscherer.reamp.entity.PrevisaoCidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PrevisaoRepository extends JpaRepository<PrevisaoCidade,Integer> {

     Optional<PrevisaoCidade> findByNomeCidade(String nome);

    @Query("select p from PrevisaoCidade p WHERE p.nomeCidade like :nome AND  p.hora > (:localDateTime)")
    Optional<PrevisaoCidade>findByNomeCidadeAndHora(String nome , LocalDateTime localDateTime);
}
