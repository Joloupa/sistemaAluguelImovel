package web.sistemaAluguelImovel.repository.queries.contrato;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
// import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import web.sistemaAluguelImovel.filter.ContratoFilter;
import web.sistemaAluguelImovel.model.Contrato;

@Repository
public class ContratoQueriesImpl implements ContratoQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Contrato> pesquisar(
            ContratoFilter filtro,
            Pageable pageable) {

        StringBuilder jpql = new StringBuilder();

        jpql.append("select c from Contrato c ");
        jpql.append("join fetch c.proprietario p ");
        jpql.append("join fetch c.locatario l ");
        jpql.append("join fetch c.imovel i ");
        jpql.append("where 1 = 1 ");

        Map<String, Object> parametros = new HashMap<>();

        if (StringUtils.hasText(filtro.getProprietario())) {

            jpql.append(
                    "and lower(p.nome) like lower(:proprietario) ");

            parametros.put(
                    "proprietario",
                    "%" + filtro.getProprietario() + "%");
        }

        if (StringUtils.hasText(filtro.getLocatario())) {

            jpql.append(
                    "and lower(l.nome) like lower(:locatario) ");

            parametros.put(
                    "locatario",
                    "%" + filtro.getLocatario() + "%");
        }

        if (StringUtils.hasText(filtro.getCidade())) {

            jpql.append(
                    "and lower(i.cidade) like lower(:cidade) ");

            parametros.put(
                    "cidade",
                    "%" + filtro.getCidade() + "%");
        }

        // Query query =
        //         manager.createQuery(
        //                 jpql.toString(),
        //                 Contrato.class);

        TypedQuery<Contrato> query =
        manager.createQuery(
                jpql.toString(),
                Contrato.class);

        parametros.forEach(query::setParameter);

        query.setFirstResult(
                (int) pageable.getOffset());

        query.setMaxResults(
                pageable.getPageSize());

        // Alterado: evita executar a query duas vezes
        List<Contrato> resultados =
                query.getResultList();

        return new PageImpl<>(
                resultados,
                pageable,
                resultados.size());
    }
}