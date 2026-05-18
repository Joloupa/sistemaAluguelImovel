package web.sistemaAluguelImovel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import web.sistemaAluguelImovel.dto.ImovelDTOInput;
import web.sistemaAluguelImovel.filter.ImovelFilter;

import web.sistemaAluguelImovel.model.Imovel;
import web.sistemaAluguelImovel.model.StatusImovel;
import web.sistemaAluguelImovel.model.TipoImovel;

import web.sistemaAluguelImovel.notification.NotificacaoSweetAlert2;
import web.sistemaAluguelImovel.notification.TipoNotificaoSweetAlert2;

import web.sistemaAluguelImovel.pagination.PageWrapper;

import web.sistemaAluguelImovel.service.ImovelService;

@Controller
public class ImovelController {

    private static final Logger logger = LoggerFactory.getLogger(ImovelController.class);

    private ImovelService imovelService;

    public ImovelController(ImovelService imovelService) {
        this.imovelService = imovelService;
    }

    @GetMapping("/imovel/abrirpesquisar")
    public String abrirPesquisa() {
        return "imoveis/pesquisar :: formulario";
    }

    @GetMapping("/imovel/pesquisar")
    public String pesquisar(
            ImovelFilter filtro,
            Model model,

            @PageableDefault(size = 9) @SortDefault(sort = "codigo", direction = Sort.Direction.ASC) Pageable pageable,

            HttpServletRequest request) {

        Page<Imovel> pagina = imovelService.pesquisar(filtro, pageable);

        logger.info("Imoveis pesquisados: {}", pagina.getContent());

        PageWrapper<Imovel> paginaWrapper = new PageWrapper<>(pagina, request);

        model.addAttribute("pagina", paginaWrapper);

        return "imoveis/mostrar :: tabela";
    }

    @GetMapping("/imovel/cadastrar")
    public String abrirCadastro(ImovelDTOInput dto, Model model) {

        // Adicionado: garante o objeto esperado pelo th:object
        model.addAttribute("imovelDTOInput", dto);

        // Envia os valores do enum para o Thymeleaf
        model.addAttribute("tipos", TipoImovel.values());

        return "imoveis/cadastrar :: formulario";
    }

    @PostMapping("/imovel/cadastrar")
    public String cadastrar(
            @Valid ImovelDTOInput dto,
            BindingResult resultado,
            RedirectAttributes atributos,
            Model model) {

        if (resultado.hasErrors()) {

            logger.info("O imóvel recebido para cadastro não é válido.");
            logger.info("Erros encontrados:");

            for (FieldError erro : resultado.getFieldErrors()) {
                logger.info("{}", erro);
            }

            for (ObjectError erro : resultado.getGlobalErrors()) {
                logger.info("{}", erro);
            }

            // Reenvia os enums para o formulário em caso de erro
            model.addAttribute("tipos", TipoImovel.values());

            return "imoveis/cadastrar :: formulario";
        }

        imovelService.salvar(dto.toImovel());

        atributos.addFlashAttribute(
                "notificacao",
                new NotificacaoSweetAlert2(
                        "Imóvel cadastrado com sucesso!",
                        TipoNotificaoSweetAlert2.SUCCESS,
                        4000));

        return "redirect:/imovel/cadastrar";
    }

    @GetMapping("/imovel/alterar/{codigo}")
    public String abrirAlterar(
            @PathVariable Long codigo,
            Model model) {

        ImovelDTOInput dto = ImovelDTOInput.fromImovel(
                imovelService.buscar(codigo));

        model.addAttribute("imovelDTOInput", dto);

        // Envia os enums para o select
        model.addAttribute("tipos", TipoImovel.values());

        return "imoveis/alterar :: formulario";
    }

    @PostMapping("/imovel/alterar")
    public String alterar(
            @Valid ImovelDTOInput dto,
            BindingResult resultado,
            RedirectAttributes atributos,
            Model model) {

        if (resultado.hasErrors()) {

            logger.info("O imóvel recebido para alteração não é válido.");
            logger.info("Erros encontrados:");

            for (FieldError erro : resultado.getFieldErrors()) {
                logger.info("{}", erro);
            }

            for (ObjectError erro : resultado.getGlobalErrors()) {
                logger.info("{}", erro);
            }

            // Reenvia os enums em caso de erro
            model.addAttribute("tipos", TipoImovel.values());

            return "imoveis/alterar :: formulario";
        }

        imovelService.atualizar(dto.toImovel());

        atributos.addFlashAttribute(
                "notificacao",
                new NotificacaoSweetAlert2(
                        "Imóvel alterado com sucesso!",
                        TipoNotificaoSweetAlert2.SUCCESS,
                        4000));

        return "redirect:/imovel/abrirpesquisar";
    }

    @GetMapping("/imovel/remover/{codigo}")
    public String remover(
            @PathVariable Long codigo,
            RedirectAttributes atributos) {

        Imovel imovel = imovelService.buscar(codigo);

        if (imovel != null) {

            imovel.setStatus(StatusImovel.ALUGADO);

            imovelService.atualizar(imovel);

            atributos.addFlashAttribute(
                    "mensagem",
                    "Imóvel removido com sucesso");

        } else {

            atributos.addFlashAttribute(
                    "mensagem",
                    "Não foi encontrado um imóvel com esse código");
        }

        return "redirect:/mensagem";
    }
}
