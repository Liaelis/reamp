package br.com.elisscherer.reamp.repository;

import br.com.elisscherer.reamp.entity.PrevisaoCidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrevisaoRepository extends JpaRepository<PrevisaoCidade,Integer> {

    public Optional<PrevisaoCidade> findByNomeCidade(String nome);
}
