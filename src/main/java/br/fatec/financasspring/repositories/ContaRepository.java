package br.fatec.financasspring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fatec.financasspring.model.Conta;


@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
	   List<Conta> listarPorBanco(String banco);
	   
	   List<Conta> listarPorBancoENumero(String banco, 
	                               Integer from, Integer to);

	   List<Conta> listarPorNomeCliente(String nome);

}
