package web.sistemaAluguelImovel.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

import web.sistemaAluguelImovel.model.Contrato;
import web.sistemaAluguelImovel.model.Imovel;
import web.sistemaAluguelImovel.model.Usuario;

public class ContratoDTOInput {

    private Long codigo;

    @NotNull
    private Long proprietarioCodigo;

    @NotNull
    private Long locatarioCodigo;

    @NotNull
    private Long imovelCodigo;

    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private LocalDate dataFim;

    @NotNull
    private BigDecimal valorMensal;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getProprietarioCodigo() {
        return proprietarioCodigo;
    }

    public void setProprietarioCodigo(Long proprietarioCodigo) {
        this.proprietarioCodigo = proprietarioCodigo;
    }

    public Long getLocatarioCodigo() {
        return locatarioCodigo;
    }

    public void setLocatarioCodigo(Long locatarioCodigo) {
        this.locatarioCodigo = locatarioCodigo;
    }

    public Long getImovelCodigo() {
        return imovelCodigo;
    }

    public void setImovelCodigo(Long imovelCodigo) {
        this.imovelCodigo = imovelCodigo;
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

    public Contrato toContrato() {

        Contrato contrato = new Contrato();

        contrato.setCodigo(codigo);

        Usuario proprietario = new Usuario();
        proprietario.setCodigo(proprietarioCodigo);

        Usuario locatario = new Usuario();
        locatario.setCodigo(locatarioCodigo);

        Imovel imovel = new Imovel();
        imovel.setCodigo(imovelCodigo);

        contrato.setProprietario(proprietario);
        contrato.setLocatario(locatario);
        contrato.setImovel(imovel);

        contrato.setDataInicio(dataInicio);
        contrato.setDataFim(dataFim);
        contrato.setValorMensal(valorMensal);

        return contrato;
    }

    public static ContratoDTOInput fromContrato(Contrato contrato) {

        ContratoDTOInput dto = new ContratoDTOInput();

        dto.setCodigo(contrato.getCodigo());

        if (contrato.getProprietario() != null) {
            dto.setProprietarioCodigo(
                contrato.getProprietario().getCodigo()
            );
        }

        if (contrato.getLocatario() != null) {
            dto.setLocatarioCodigo(
                contrato.getLocatario().getCodigo()
            );
        }

        if (contrato.getImovel() != null) {
            dto.setImovelCodigo(
                contrato.getImovel().getCodigo()
            );
        }

        dto.setDataInicio(contrato.getDataInicio());
        dto.setDataFim(contrato.getDataFim());
        dto.setValorMensal(contrato.getValorMensal());

        return dto;
    }
}