package web.sistemaAluguelImovel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.sistemaAluguelImovel.model.Contrato;
import web.sistemaAluguelImovel.repository.queries.contrato.ContratoQueries;

public interface ContratoRepository extends JpaRepository<Contrato, Long>, ContratoQueries {
}
