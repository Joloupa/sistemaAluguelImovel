package web.sistemaAluguelImovel.repository.queries.usuario;

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
import web.sistemaAluguelImovel.filter.UsuarioFilter;
import web.sistemaAluguelImovel.model.Usuario;
import web.sistemaAluguelImovel.pagination.PaginacaoUtil;

public class UsuarioQueriesImpl implements UsuarioQueries {

	@PersistenceContext
	private EntityManager em;

	public Page<Usuario> pesquisar(UsuarioFilter filtro, Pageable pageable) {

		StringBuilder queryUsuarios = new StringBuilder("select distinct p from Usuario p");
		StringBuilder condicoes = new StringBuilder();
		Map<String, Object> parametros = new HashMap<>();

		preencherCondicoesEParametros(filtro, condicoes, parametros);

		if (condicoes.isEmpty()) {
			condicoes.append(" where p.status = 'ATIVO'");
		} else {
			condicoes.append(" and p.status = 'ATIVO'");
		}

		queryUsuarios.append(condicoes);
		PaginacaoUtil.prepararOrdemJPQL(queryUsuarios, "p", pageable);
		TypedQuery<Usuario> typedQuery = em.createQuery(queryUsuarios.toString(), Usuario.class);
		PaginacaoUtil.prepararIntervalo(typedQuery, pageable);
		PaginacaoUtil.preencherParametros(parametros, typedQuery);
		List<Usuario> imoveis = typedQuery.getResultList();

		long totalPessoas = PaginacaoUtil.getTotalRegistros("Usuario", "p", condicoes, parametros, em);

		return new PageImpl<>(imoveis, pageable, totalPessoas);
	}

	private void preencherCondicoesEParametros(UsuarioFilter filtro, StringBuilder condicoes,
			Map<String, Object> parametros) {
		boolean condicao = false;

		if (filtro.getCodigo() != null) {
			PaginacaoUtil.fazerLigacaoCondicoes(condicoes, condicao);
			condicoes.append("p.codigo = :codigo");
			parametros.put("codigo", filtro.getCodigo());
			condicao = true;
		}
		if (StringUtils.hasText(filtro.getNome())) {
			PaginacaoUtil.fazerLigacaoCondicoes(condicoes, condicao);
			condicoes.append("lower(p.nome) like :nome");
			parametros.put("nome", "%" + filtro.getNome().toLowerCase() + "%");
			condicao = true;
		}
		if (StringUtils.hasText(filtro.getCpf())) {
			PaginacaoUtil.fazerLigacaoCondicoes(condicoes, condicao);
			condicoes.append("p.cpf like :cpf");
			parametros.put("cpf", "%" + filtro.getCpf().toLowerCase() + "%");
			condicao = true;
		}

		if (filtro.getDataCadastro() != null) {
			PaginacaoUtil.fazerLigacaoCondicoes(condicoes, condicao);
			condicoes.append("p.dataNascimento >= :dataCadastroInicial");
			parametros.put("dataCadastroInicial", filtro.getDataCadastro());
			condicao = true;
		}

		// if (filtro.getDataCadastroFinal() != null) {
		// PaginacaoUtil.fazerLigacaoCondicoes(condicoes, condicao);
		// condicoes.append("p.dataCadastro <= :dataCadastroFinal");
		// parametros.put("dataCadastroFinal", filtro.getDataCadastroFinal());
		// }
	}

}
