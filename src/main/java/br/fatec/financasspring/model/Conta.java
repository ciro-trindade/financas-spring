package br.fatec.financasspring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "tb_conta")
@Entity
@NamedQueries({
	@NamedQuery(name = "Conta.listarPorBanco", 
	            query = "select c from Conta c where c.banco like ?1"),
	@NamedQuery(name = "Conta.listarPorBancoENumero", 
	            query = "select c from Conta c where c.banco=?1 and" 
	                  + " c.numero between ?2 and ?3"),
	@NamedQuery(name = "Conta.listarPorNomeCliente", 
	            query = "select c from Conta c join Cliente cc on" 
	            + " cc.conta = c where cc.nome like ?1")
})
public class Conta extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "nm_banco", length = 60)
    private String banco;
	@Column(name = "nm_agencia", length = 60)
    private String agencia;
	@Column(name = "nr_numero")
    private Integer numero;
	
	
	@OneToMany(cascade = CascadeType.ALL, 
			   mappedBy = "conta")	
	private List<Movimentacao> movimentacoes;
	
	
    public Conta() {
	}
    
    public Conta(Long id) {
    	setId(id);
    }

    public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	
	@JsonIgnore
	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	@JsonProperty
	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

}
