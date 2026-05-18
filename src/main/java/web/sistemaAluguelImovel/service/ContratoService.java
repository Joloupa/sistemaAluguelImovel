package web.sistemaAluguelImovel.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.sistemaAluguelImovel.dto.ContratoDTOInput;
import web.sistemaAluguelImovel.model.Contrato;
import web.sistemaAluguelImovel.model.Imovel;
import web.sistemaAluguelImovel.model.Usuario;
import web.sistemaAluguelImovel.repository.ContratoRepository;
import web.sistemaAluguelImovel.repository.ImovelRepository;
import web.sistemaAluguelImovel.repository.UsuarioRepository;
import web.sistemaAluguelImovel.ProcessadorPagamento;


@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ImovelRepository imovelRepository;
    private final ProcessadorPagamento processadorPagamento;

    public ContratoService(
            ContratoRepository contratoRepository,
            UsuarioRepository usuarioRepository,
            ImovelRepository imovelRepository,
            ProcessadorPagamento processadorPagamento) {

        this.contratoRepository = contratoRepository;
        this.usuarioRepository = usuarioRepository;
        this.imovelRepository = imovelRepository;
        this.processadorPagamento = processadorPagamento;
    }

     @Transactional
    public Contrato salvar(ContratoDTOInput dto) {

        Usuario proprietario = usuarioRepository
                .findById(dto.getProprietarioCodigo())
                .orElseThrow();

        Usuario locatario = usuarioRepository
                .findById(dto.getLocatarioCodigo())
                .orElseThrow();

        Imovel imovel = imovelRepository
                .findById(dto.getImovelCodigo())
                .orElseThrow();

        validarProprietario(proprietario, imovel);

        Contrato contrato = new Contrato();

        contrato.setProprietario(proprietario);
        contrato.setLocatario(locatario);
        contrato.setImovel(imovel);
        contrato.setDataInicio(dto.getDataInicio());
        contrato.setDataFim(dto.getDataFim());
        contrato.setValorMensal(dto.getValorMensal());
        contrato.setAtivo(true);

        Contrato contratoSalvo = contratoRepository.save(contrato);

        // Integração com ProcessadorPagamento
        processadorPagamento.processarPagamento(
                contratoSalvo.getValorMensal());

        return contratoSalvo;
    }

    private void validarProprietario(
            Usuario proprietario,
            Imovel imovel) {

        boolean nomeValido = proprietario
                .getNome()
                .equalsIgnoreCase(imovel.getProprietario());

        boolean cpfValido = proprietario
                .getCpf()
                .equals(imovel.getCpfProprietario());

        if (!nomeValido || !cpfValido) {
            throw new RuntimeException(
                    "O proprietário informado não corresponde ao proprietário do imóvel.");
        }
    }
}
