package web.sistemaAluguelImovel.service; 

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.sistemaAluguelImovel.filter.ImovelFilter;
import web.sistemaAluguelImovel.model.StatusImovel;
import web.sistemaAluguelImovel.model.Imovel;
import web.sistemaAluguelImovel.repository.ImovelRepository; 

@Service 
public class ImovelService {

    private static final Logger logger = LoggerFactory.getLogger(ImovelService.class); 

    private ImovelRepository imovelRepository;

    public ImovelService(ImovelRepository imovelRepository) {
        this.imovelRepository = imovelRepository;
    }

    @Transactional(readOnly = true)
    public List<Imovel> pesquisar(ImovelFilter filtro) {
        logger.info("Pesquisando imoveis com o filtro {}", filtro);
        return imovelRepository.pesquisar(filtro);
    }

    @Transactional(readOnly = true)
    public Page<Imovel> pesquisar(ImovelFilter filtro, Pageable pageable) {
        logger.info("Pesquisando imoveis com o filtro {}", filtro);
        return imovelRepository.pesquisar(filtro, pageable);
    }

    @Transactional 
    public void salvar(Imovel imovel) { 
        logger.info("Salvando imovel: {}", imovel); 
        imovelRepository.save(imovel); 
    } 

    @Transactional 
    public void atualizar(Imovel imovel) { 
        logger.info("Atualizando imovel: {}", imovel); 
        imovelRepository.save(imovel); 
    } 

    @Transactional 
    public void remover(Long codigo) { 
        logger.info("Removendo imovel com código: {}", codigo); 
        imovelRepository.deleteById(codigo); 
    } 

    @Transactional(readOnly = true)
    public Imovel buscar(Long codigo) {
        logger.info("Buscando a imovel com código: {}", codigo);
        return imovelRepository.findByCodigoAndStatus(codigo, StatusImovel.DISPONIVEL).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Imovel> pesquisarGeral(String busca) {
        return imovelRepository.pesquisarGeral(busca);
    }
} 