package web.sistemaAluguelImovel.model;

public enum StatusUsuario {

	ATIVO("ativo"),
	INATIVO("inativo");

	
	private String descricao;
	
	private StatusUsuario(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
