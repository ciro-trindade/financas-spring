package br.fatec.financasspring.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name="tb_cliente")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Cliente extends AbstractEntity {
	private static final long serialVersionUID = 1L;

    @Column(name="nm_nome", length=60)
    private String nome;
    @Column(name="ds_endereco", length=120)
    private String endereco;
    
    @Column(name = "nm_login", length = 80)
    private String login;
    
    @Column(name = "nm_senha")
    private String senha;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tb_perfil")
    private Set<Integer> perfis = new HashSet<>();
    
    @OneToOne(cascade = CascadeType.ALL)    
    @JoinColumn(name = "fk_conta_id", unique = true)
    private Conta conta;

    public Cliente() { 
    	addPerfil(TipoPerfil.CLIENTE);
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@JsonIgnore
	public String getSenha() {
		return senha;
	}

	@JsonProperty
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<TipoPerfil> getPerfis() {
		Set<TipoPerfil> p = new HashSet<>();
		for (Integer tp : perfis) {
			p.add(TipoPerfil.toEnum(tp));
		}
		return p;
		//return perfis.stream().map(x -> TipoPerfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(TipoPerfil perfil) {
		this.perfis.add(perfil.getCod());
	}
	
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}
