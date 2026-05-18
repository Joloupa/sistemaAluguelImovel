package web.sistemaAluguelImovel.repository.queries.contrato;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.sistemaAluguelImovel.filter.ContratoFilter;
import web.sistemaAluguelImovel.model.Contrato;

public interface ContratoQueries {

    Page<Contrato> pesquisar(
            ContratoFilter filtro,
            Pageable pageable);
}
