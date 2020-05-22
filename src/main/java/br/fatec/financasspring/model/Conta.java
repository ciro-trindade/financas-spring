package br.fatec.financasspring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "tb_conta")
@Entity
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
