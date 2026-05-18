package web.sistemaAluguelImovel.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import web.sistemaAluguelImovel.model.Usuario;
import web.sistemaAluguelImovel.model.StatusUsuario;
import web.sistemaAluguelImovel.repository.queries.usuario.UsuarioQueries;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioQueries {

    Optional<Usuario> findByCodigoAndStatus(long codigo, StatusUsuario status);

    Usuario findByCpf(String cpf);

}
