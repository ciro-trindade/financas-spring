package br.fatec.financasspring.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "tb_movimentacao")
@Entity
public class Movimentacao extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "vl_valor")
	private BigDecimal valor;

	@Enumerated(EnumType.STRING)
	@Column(name = "nm_tipo_movimentacao")
	private TipoMovimentacao tipo;

	@Column(name = "ds_descricao", length = 100)
	private String descricao;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_data")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date data;

	@ManyToOne(fetch = FetchType.LAZY)
	private Conta conta;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_movimentacao_categoria", 
	           joinColumns = @JoinColumn(name = "fk_movimentacao_id"), 
	           inverseJoinColumns = @JoinColumn(name = "fk_categoria_id"))
	private List<Categoria> categorias;

	public Movimentacao() {
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoMovimentacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimentacao tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@JsonIgnore
	public Conta getConta() {
		return conta;
	}

	@JsonProperty
	public void setConta(Conta conta) {
		this.conta = conta;
	}

	
	public List<Categoria> getCategorias() {
		return categorias;
	}

	
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

}
