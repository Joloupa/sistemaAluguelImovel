package web.sistemaAluguelImovel.filter;

import java.math.BigInteger;

public class ImovelFilter {

    private Long codigo;

    private String tipoImovel;
    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;

    private BigInteger valorAluguel;

    private String dataCadastro;
    private String descricao;
    private String Status;

    // ================ //
    // GETERS E SETTERS //
    // ================ //

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(String tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigInteger getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(BigInteger valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastroInicio(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "ImovelFIlter [codigo: " + codigo + "\ntipoImovel: " + tipoImovel + "\nendereco: " + endereco
                + "\nbairro: " + bairro
                + "\ncidade: " + cidade + "\nestado: " + estado + "\nvalorMaximo: " + valorAluguel
                + "\ndataCadastroInicio: " + dataCadastro
                + "\nStatus: " + Status + "]";
    }

}
