package web.sistemaAluguelImovel.repository.queries.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import web.sistemaAluguelImovel.filter.UsuarioFilter;
import web.sistemaAluguelImovel.model.Usuario;

public interface UsuarioQueries {

	Page<Usuario> pesquisar(UsuarioFilter filtro, Pageable pageable);
	
}
