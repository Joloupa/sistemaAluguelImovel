package web.sistemaAluguelImovel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "imovel")
@DynamicUpdate
public class Imovel implements Serializable {
    @Id
    @SequenceGenerator(name = "gerador5", sequenceName = "imovel_codigo_seq", allocationSize = 1)
    @GeneratedValue(generator = "gerador5", strategy = GenerationType.SEQUENCE)
    private Long codigo;

    @Column(name = "tipoimovel")
    private String tipoImovel;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @Column(name = "numero")
    private String numero;

    @Column(name = "cep")
    private String cep;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @Column(name = "nomeproprietario")
    private String proprietario;

    @Column(name = "cpf_proprietario", nullable = false, length = 14)
    private String cpfProprietario;

    @Column(name = "valoraluguel")
    private BigDecimal valorAluguel;

    @Column(name = "descricao")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "statusimovel")
    private StatusImovel status = StatusImovel.DISPONIVEL;

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

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

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

    // public String getDataCadastro() {
    // return dataCadastro;
    // }

    // public void setDataCadastro(String dataCadastro) {
    // this.dataCadastro = dataCadastro;
    // }

    // public Usuario getProprietario() {
    // return proprietario;
    // }

    public String getProprietario() {
        return proprietario;
    }

    // public void setProprietario(Usuario proprietario) {
    // this.proprietario = proprietario;
    // }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getCpfProprietario() {
    return cpfProprietario;
}

public void setCpfProprietario(String cpfProprietario) {
    this.cpfProprietario = cpfProprietario;
}

    public BigDecimal getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(BigDecimal valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusImovel getStatus() {
        return status;
    }

    public void setStatus(StatusImovel status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "codigo: " + codigo + "\ntipoImovel: " + tipoImovel + "\nbairro: " + bairro
                + "\ncidade: " + cidade + "\nestado: " + estado + "\ndataCadastro: " + dataCadastro + "\nproprietario: "
                + proprietario + "\nvalorAluguel: " + valorAluguel + "\ndescricao: " + descricao + "\nstatus: "
                + status + "\ncpfProprietario: "
                + cpfProprietario;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Imovel other = (Imovel) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

}
