package br.fatec.financasspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.fatec.financasspring.model.PessoaFisica;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {

}
