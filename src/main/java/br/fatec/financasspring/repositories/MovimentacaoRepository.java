package br.fatec.financasspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.fatec.financasspring.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

}
