package br.fatec.financasspring.model;

public enum TipoMovimentacao {
	ENTRADA(1, "Entrada"), 
	SAIDA(2, "Saída");
	
	private Integer cod;
	private String descricao;

	private TipoMovimentacao(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoMovimentacao toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (TipoMovimentacao x : TipoMovimentacao.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Código de movimentação inválido: " + cod);
	}
	
}
