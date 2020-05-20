package br.fatec.financasspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.fatec.financasspring.model.PessoaJuridica;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {

}
