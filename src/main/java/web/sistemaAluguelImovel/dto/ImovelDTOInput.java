package web.sistemaAluguelImovel.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
// import web.sistemaAluguelImovel.model.Usuario;
import web.sistemaAluguelImovel.model.Imovel;
import web.sistemaAluguelImovel.model.StatusImovel;

public class ImovelDTOInput {

    private Long codigo;
    @NotBlank(message = "O nome é obrigatório")
    // @Size(max = 255, message = "O tamanho máximo do nome é 255 caracteres")
    // private String nome;
    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 255, message = "O tamanho máximo da descrição é 255 caracteres")
    private String descricao;

    @NotNull
    private StatusImovel status = StatusImovel.DISPONIVEL;

    @NotNull
    @Positive
    private BigDecimal valorAluguel;

    @NotBlank(message = "É obrigatório informar o Nome do proprietario")
    @Size(max = 255, message = "O tamanho máximo do nome é 255 caracteres")
    // private Usuario proprietario;
    private String proprietario;

    @NotBlank(message = "É obrigatório informar o tipo do imovel")
    private String tipoImovel;

    @NotBlank(message = "É obrigatório informar o bairro")
    @Size(max = 255, message = "O tamanho máximo do nome do bairro é 255 caracteres")
    private String bairro;

    @NotBlank(message = "É obrigatório informar a cidade")
    @Size(max = 255, message = "O tamanho máximo do nome da cidade é 255 caracteres")
    private String cidade;

    @NotBlank(message = "É obrigatório informar o Estado")
    @Size(max = 2, message = "O tamanho máximo de 2 caracteres")
    private String estado;

    private String numero;

    private String cep;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    // public String getNome() {
    // return nome;
    // }

    // public void setNome(String nome) {
    // this.nome = nome;
    // }

    public String getDescricao() {
        return descricao;
    }

    public StatusImovel getStatus() {
        return status;
    }

    public void setStatus(StatusImovel status) {
        this.status = status;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(BigDecimal valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(String tipoImovel) {
        this.tipoImovel = tipoImovel;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Imovel toImovel() {
        Imovel imovel = new Imovel();
        imovel.setCodigo(codigo);
        imovel.setDescricao(descricao);
        imovel.setStatus(status);
        imovel.setValorAluguel(valorAluguel);
        imovel.setProprietario(proprietario);
        imovel.setTipoImovel(tipoImovel);
        imovel.setBairro(bairro);
        imovel.setCidade(cidade);
        imovel.setEstado(estado);
        imovel.setNumero(numero);
        imovel.setCep(cep);

        imovel.setDataCadastro(LocalDateTime.now());
        return imovel;
    }

    public static ImovelDTOInput fromImovel(Imovel imovel) {
        ImovelDTOInput dto = new ImovelDTOInput();
        dto.setCodigo(imovel.getCodigo());
        dto.setDescricao(imovel.getDescricao());
        dto.setValorAluguel(imovel.getValorAluguel());
        dto.setProprietario(imovel.getProprietario());
        dto.setTipoImovel(imovel.getTipoImovel());
        dto.setBairro(imovel.getBairro());
        dto.setCidade(imovel.getCidade());
        dto.setEstado(imovel.getEstado());
        dto.setStatus(imovel.getStatus());

        dto.setNumero(imovel.getNumero());
        dto.setCep(imovel.getCep());
        return dto;
    }

    @Override
    public String toString() {
        return "codigo: " + codigo + "\ndescricao: " + descricao + "\nstatus: " + status + "\nvalorAluguel: "
                + valorAluguel + "\nproprietario: " + proprietario + "\ntipoImovel: " + tipoImovel + "\nbairro: "
                + bairro + "\ncidade: " + cidade + "\nestado: " + estado + "\nnumero: " + numero + "\ncep: " + cep;
    }

}
