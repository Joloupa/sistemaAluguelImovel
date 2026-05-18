package web.sistemaAluguelImovel.repository.queries.imovel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import web.sistemaAluguelImovel.filter.ImovelFilter;
import web.sistemaAluguelImovel.model.Imovel;
import web.sistemaAluguelImovel.pagination.PaginacaoUtil;

public class ImovelQueriesImpl implements ImovelQueries {

	@PersistenceContext
	private EntityManager em;

	public List<Imovel> pesquisar(ImovelFilter filtro) {
		StringBuilder queryImoveis = new StringBuilder("select distinct v from Imovel v");
		StringBuilder condicoes = new StringBuilder();
		Map<String, Object> parametros = new HashMap<>();
		preencherCondicoesEParametros(filtro, condicoes, parametros);
		if (condicoes.isEmpty()) {
			condicoes.append(" where v.status = 'ATIVO'");
		} else {
			condicoes.append(" and v.status = 'ATIVO'");
		}
		queryImoveis.append(condicoes);
		queryImoveis.append(" order by v.codigo");
		TypedQuery<Imovel> typedQuery = em.createQuery(queryImoveis.toString(), Imovel.class);
		PaginacaoUtil.preencherParametros(parametros, typedQuery);
		List<Imovel> imoveis = typedQuery.getResultList();
		return imoveis;
	}

	public Page<Imovel> pesquisar(ImovelFilter filtro, Pageable pageable) {

		StringBuilder queryImoveis = new StringBuilder("select distinct v from Imovel v");
		StringBuilder condicoes = new StringBuilder();
		Map<String, Object> parametros = new HashMap<>();

		preencherCondicoesEParametros(filtro, condicoes, parametros);

		if (condicoes.isEmpty()) {
			condicoes.append(" where v.status = 'ATIVO'");
		} else {
			condicoes.append(" and v.status = 'ATIVO'");
		}

		queryImoveis.append(condicoes);
		PaginacaoUtil.prepararOrdemJPQL(queryImoveis, "v", pageable);
		TypedQuery<Imovel> typedQuery = em.createQuery(queryImoveis.toString(), Imovel.class);
		PaginacaoUtil.prepararIntervalo(typedQuery, pageable);
		PaginacaoUtil.preencherParametros(parametros, typedQuery);
		List<Imovel> imoveis = typedQuery.getResultList();

		long totalImoveis = PaginacaoUtil.getTotalRegistros("Imovel", "v", condicoes, parametros, em);

		return new PageImpl<>(imoveis, pageable, totalImoveis);
	}

	public List<Imovel> pesquisarGeral(String filtro) {
		StringBuilder queryImoveis = new StringBuilder("select distinct v from Imovel v");
		StringBuilder condicoes = new StringBuilder();
		Map<String, Object> parametros = new HashMap<>();
		preencherCondicoesEParametros(filtro, condicoes, parametros);
		if (condicoes.isEmpty()) {
			condicoes.append(" where v.status = 'ATIVO'");
		} else {
			condicoes.append(" and v.status = 'ATIVO'");
		}
		queryImoveis.append(condicoes);
		TypedQuery<Imovel> typedQuery = em.createQuery(queryImoveis.toString(), Imovel.class);
		PaginacaoUtil.preencherParametros(parametros, typedQuery);
		List<Imovel> imoveis = typedQuery.getResultList();
		return imoveis;
	}

	private void preencherCondicoesEParametros(String filtro, StringBuilder condicoes,
			Map<String, Object> parametros) {
		boolean condicao = false;
		try {
			Long codigo = Long.parseLong(filtro);
			if (!condicao) {
				condicoes.append(" where ");
			} else {
				condicoes.append(" or ");
			}
			condicoes.append("v.codigo = :codigo");
			parametros.put("codigo", codigo);
			condicao = true;
		} catch (NumberFormatException e) {
			if (!condicao) {
				condicoes.append(" where ");
			} else {
				condicoes.append(" or ");
			}
			condicoes.append("lower(v.nome) like :nome");
			parametros.put("nome", "%" + filtro.toLowerCase() + "%");
			condicao = true;
		}
	}

	private void preencherCondicoesEParametros(ImovelFilter filtro, StringBuilder condicoes,
			Map<String, Object> parametros) {
		boolean condicao = false;

		if (filtro.getCodigo() != null) {
			PaginacaoUtil.fazerLigacaoCondicoes(condicoes, condicao);
			condicoes.append("v.codigo = :codigo");
			parametros.put("codigo", filtro.getCodigo());
			condicao = true;
		}
		if (StringUtils.hasText(filtro.getTipoImovel())) {
			PaginacaoUtil.fazerLigacaoCondicoes(condicoes, condicao);
			condicoes.append("lower(v.tipoImovel) like :tipoImovel");
			parametros.put("nome", "%" + filtro.getTipoImovel().toLowerCase() + "%");
			condicao = true;
		}
		if (StringUtils.hasText(filtro.getDescricao())) {
			PaginacaoUtil.fazerLigacaoCondicoes(condicoes, condicao);
			condicoes.append("lower(v.descricao) like :descricao");
			parametros.put("descricao", "%" + filtro.getDescricao().toLowerCase() + "%");
		}
		if (StringUtils.hasText(filtro.getEndereco())) {
			PaginacaoUtil.fazerLigacaoCondicoes(condicoes, condicao);
			condicoes.append("lower(v.endereco) like :endereco");
			parametros.put("descricao", "%" + filtro.getEndereco().toLowerCase() + "%");
		}
		if (StringUtils.hasText(filtro.getBairro())) {
			PaginacaoUtil.fazerLigacaoCondicoes(condicoes, condicao);
			condicoes.append("lower(v.bairro) like :bairro");
			parametros.put("descricao", "%" + filtro.getBairro().toLowerCase() + "%");
		}
		if (StringUtils.hasText(filtro.getCidade())) {
			PaginacaoUtil.fazerLigacaoCondicoes(condicoes, condicao);
			condicoes.append("lower(v.cidade) like :cidade");
			parametros.put("descricao", "%" + filtro.getCidade().toLowerCase() + "%");
		}
		if (StringUtils.hasText(filtro.getEstado())) {
			PaginacaoUtil.fazerLigacaoCondicoes(condicoes, condicao);
			condicoes.append("lower(v.estado) like :estado");
			parametros.put("descricao", "%" + filtro.getEstado().toLowerCase() + "%");
		}
		if (filtro.getValorAluguel() != null) {
			PaginacaoUtil.fazerLigacaoCondicoes(condicoes, condicao);
			condicoes.append("v.valorAluguel = :valorAluguel");
			parametros.put("valorAluguel", filtro.getValorAluguel());
		}
		if (StringUtils.hasText(filtro.getDataCadastro())) {
			PaginacaoUtil.fazerLigacaoCondicoes(condicoes, condicao);
			condicoes.append("lower(v.dataCadastro) like :dataCadastro");
			parametros.put("descricao", "%" + filtro.getDataCadastro().toLowerCase() + "%");
		}
	}

}
