package web.sistemaAluguelImovel.model;

public enum TipoImovel {

    APARTAMENTO("Apartamento"),
    CASA("Casa"),
    TERRENO("Terreno"),
    COMERCIAL("Comercial");

    private final String descricao;

    TipoImovel(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
