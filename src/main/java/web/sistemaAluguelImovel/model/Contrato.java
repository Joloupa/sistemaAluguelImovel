package web.sistemaAluguelImovel.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "contrato")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proprietario_codigo", nullable = false)
    private Usuario proprietario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locatario_codigo", nullable = false)
    private Usuario locatario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imovel_codigo", nullable = false)
    private Imovel imovel;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate dataFim;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorMensal;

    @Column(nullable = false)
    private Boolean ativo = true;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Usuario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Usuario proprietario) {
        this.proprietario = proprietario;
    }

    public Usuario getLocatario() {
        return locatario;
    }

    public void setLocatario(Usuario locatario) {
        this.locatario = locatario;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public BigDecimal getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(BigDecimal valorMensal) {
        this.valorMensal = valorMensal;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

  

    @Override
    public String toString() {
        return "codigo: " + codigo + "\nproprietario: " + proprietario + "\nlocatario: " + locatario + "\nimovel: "
                + imovel + "\ndataInicio: " + dataInicio + "\ndataFim: " + dataFim + "\nvalorMensal: " + valorMensal
                + "\nativo: " + ativo;
    }

@Override
public int hashCode() {
    return codigo == null ? 0 : codigo.hashCode();
}

@Override
public boolean equals(Object obj) {

    if (this == obj)
        return true;

    if (obj == null)
        return false;

    if (getClass() != obj.getClass())
        return false;

    Contrato other = (Contrato) obj;

    if (codigo == null || other.codigo == null)
        return false;

    return codigo.equals(other.codigo);
}

    
}