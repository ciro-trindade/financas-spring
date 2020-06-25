package br.fatec.financasspring.repositories;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.fatec.financasspring.model.Conta;
import br.fatec.financasspring.model.Movimentacao;
import br.fatec.financasspring.model.TipoMovimentacao;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
	
	@Query("select avg(m.valor) from Movimentacao m where m.conta = ?1 and m.tipo = ?2")
	BigDecimal mediaMovimentaoesPorContaETipo(Conta conta, TipoMovimentacao tipo);
	
	@Query("select count(m) from Movimentacao m where m.conta = ?1")
	Long numeroMovimentacoesPorConta(Conta conta);
	
	@Query("select max(m.valor) from Movimentacao m where m.conta=?1 and m.tipo=?2")
	BigDecimal maiorMovimentacaoPorContaETipo(Conta conta, TipoMovimentacao tipo);
	
	@Query("select min(m.valor) from Movimentacao m where m.conta=?1 and m.tipo=?2")
	BigDecimal menorMovimentacaoPorContaETipo(Conta conta, TipoMovimentacao tipo);
	
	@Query("select sum(m.valor) from Movimentacao m where m.conta = ?1 and m.tipo = ?2 and m.data between ?3 and ?4")
	BigDecimal somaMovimentacoesPorContaTipoEPeriodo(Conta conta, TipoMovimentacao tipo, Date from, Date to);
}
