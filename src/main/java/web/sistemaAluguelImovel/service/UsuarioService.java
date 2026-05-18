package web.sistemaAluguelImovel.service; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.sistemaAluguelImovel.filter.UsuarioFilter;
import web.sistemaAluguelImovel.model.StatusUsuario;
import web.sistemaAluguelImovel.model.Usuario;
import web.sistemaAluguelImovel.repository.UsuarioRepository;

@Service 
public class UsuarioService { 

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class); 

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public Page<Usuario> pesquisar(UsuarioFilter filtro, Pageable pageable) {
        logger.info("Pesquisando usuarios com o filtro {}", filtro);
        return usuarioRepository.pesquisar(filtro, pageable);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPeloCPF(String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }

    @Transactional 
    public void salvar(Usuario usuario) { 
        logger.info("Salvando usuario: {}", usuario); 
        usuarioRepository.save(usuario); 
    } 

    @Transactional 
    public void atualizar(Usuario usuario) { 
        logger.info("Atualizando usuario: {}", usuario); 
        usuarioRepository.save(usuario); 
    } 

    @Transactional 
    public void remover(Long codigo) { 
        logger.info("Removendo usuario com código: {}", codigo); 
        usuarioRepository.deleteById(codigo); 
    } 

    @Transactional(readOnly = true)
    public Usuario buscar(Long codigo) {
        logger.info("Buscando a usuario com código: {}", codigo);
        return usuarioRepository.findByCodigoAndStatus(codigo, StatusUsuario.ATIVO).orElse(null);
    }
} 