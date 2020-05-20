package br.fatec.financasspring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "tb_conta")
@Entity
public class Conta extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "nm_titular", length = 100)
	private String titular;
	@Column(name = "nm_banco", length = 60)
    private String banco;
	@Column(name = "nm_agencia", length = 60)
    private String agencia;
	@Column(name = "nr_numero")
    private Integer numero;

    public Conta() {
	}
    
    public Conta(Long id) {
    	setId(id);
    }

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
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

	@Override
	public String toString() {
		return "Conta [titular=" + titular + ", banco=" + banco + ", agencia=" + agencia + ", numero=" + numero + "]";
	}
	
}
