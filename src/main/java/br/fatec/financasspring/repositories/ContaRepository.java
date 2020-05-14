package br.fatec.financasspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fatec.financasspring.model.Conta;


@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

}
