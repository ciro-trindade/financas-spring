package br.fatec.financasspring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.fatec.financasspring.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

	/*
	@Query("select c from Conta c where c.banco like %?1%")
	List<Conta> listarPorBanco(String banco);
	
	@Query("select c from Conta c where c.banco=?1 and c.numero between ?2 and ?3")
	List<Conta> listarPorBancoENumero(String banco, Integer from, Integer to);
	*/
	
	List<Conta> findByBancoContaining(String banco);
	
	List<Conta> findByBancoAndNumeroBetween(String banco, Integer from, Integer to);
	
	@Query("select c from Conta c join Cliente cc on cc.conta = c where cc.nome like %?1%")
	List<Conta> listarPorNomeCliente(String nome);

}
