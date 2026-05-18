package web.sistemaAluguelImovel.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import web.sistemaAluguelImovel.model.StatusImovel;
import web.sistemaAluguelImovel.model.Imovel;
import web.sistemaAluguelImovel.repository.queries.imovel.ImovelQueries;

public interface ImovelRepository extends JpaRepository<Imovel, Long>, ImovelQueries {

    Optional<Imovel> findByCodigoAndStatus(long codigo, StatusImovel status);

}
