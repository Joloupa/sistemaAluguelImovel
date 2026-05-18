package web.sistemaAluguelImovel.model;

public enum StatusImovel {

    DISPONIVEL("disponivel"),
    ALUGADO("alugado");
	
	private String descricao;
	
	private StatusImovel(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}


// public enum StatusImovel {

//     DISPONIVEL("disponivel"),
//     ALUGADO("alugado");
	
// 	private String descricao;
	
// 	private StatusImovel(String descricao) {
// 		this.descricao = descricao;
// 	}
	
// 	public String getDescricao() {
// 		return descricao;
// 	}
// }