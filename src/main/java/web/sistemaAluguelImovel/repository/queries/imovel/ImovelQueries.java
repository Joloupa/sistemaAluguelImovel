package web.sistemaAluguelImovel.repository.queries.imovel;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import web.sistemaAluguelImovel.filter.ImovelFilter;
import web.sistemaAluguelImovel.model.Imovel;

public interface ImovelQueries {

	Page<Imovel> pesquisar(ImovelFilter filtro, Pageable pageable);
	
	List<Imovel> pesquisar(ImovelFilter filtro);

	List<Imovel> pesquisarGeral(String filtro);

}
