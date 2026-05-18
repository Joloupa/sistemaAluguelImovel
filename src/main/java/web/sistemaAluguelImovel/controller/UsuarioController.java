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
import web.sistemaAluguelImovel.dto.UsuarioDTOInput;
import web.sistemaAluguelImovel.filter.UsuarioFilter;
import web.sistemaAluguelImovel.model.Usuario;
import web.sistemaAluguelImovel.model.StatusUsuario;
import web.sistemaAluguelImovel.notification.NotificacaoSweetAlert2;
import web.sistemaAluguelImovel.notification.TipoNotificaoSweetAlert2;
import web.sistemaAluguelImovel.pagination.PageWrapper;
import web.sistemaAluguelImovel.service.UsuarioService;


@Controller
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuario/abrirpesquisar")
    public String abrirPesquisa() {
        return "usuarios/pesquisar :: formulario";
    }

    @GetMapping("/usuario/pesquisar")
    public String pesquisar(UsuarioFilter filtro, Model model,
            @PageableDefault(size = 9) @SortDefault(sort = "codigo",
                    direction = Sort.Direction.ASC) Pageable pageable,
            HttpServletRequest request) {
        Page<Usuario> pagina = usuarioService.pesquisar(filtro, pageable);
        logger.info("Usuarios pesquisadas: {}", pagina.getContent());
        PageWrapper<Usuario> paginaWrapper = new PageWrapper<>(pagina, request);
        model.addAttribute("pagina", paginaWrapper);
        return "usuarios/mostrar :: tabela";
    }

    @GetMapping("/usuario/cadastrar")
    public String abrirCadastro(UsuarioDTOInput dto) {
        return "usuarios/cadastrar :: formulario";
    }

    @PostMapping("/usuario/cadastrar")
    public String cadastrar(@Valid UsuarioDTOInput dto, BindingResult resultado,
            RedirectAttributes atributos) {
        if (resultado.hasErrors()) {
            logger.info("A usuario recebida para cadastrar não é válida.");
            logger.info("Erros encontrados:");
            for (FieldError erro : resultado.getFieldErrors()) {
                logger.info("{}", erro);
            }
            for (ObjectError erro : resultado.getGlobalErrors()) {
                logger.info("{}", erro);
            }
            return "usuarios/cadastrar :: formulario";
        } else {
            usuarioService.salvar(dto.toPessoa());
            atributos.addFlashAttribute("notificacao", new NotificacaoSweetAlert2(
                    "Usuario cadastrada com sucesso!", TipoNotificaoSweetAlert2.SUCCESS, 4000));
            return "redirect:/usuario/cadastrar";
        }
    }

    @GetMapping("/usuario/alterar/{codigo}")
    public String abrirAlterar(@PathVariable Long codigo, Model model) {
        UsuarioDTOInput dto = UsuarioDTOInput.fromPessoa(usuarioService.buscar(codigo));
        model.addAttribute("usuarioDTOInput", dto);
        return "usuarios/alterar :: formulario";
    }

    @PostMapping("/usuario/alterar")
    public String alterar(@Valid UsuarioDTOInput dto, BindingResult resultado,
            RedirectAttributes atributos) {
        if (resultado.hasErrors()) {
            logger.info("A usuario recebida para alterar não é válida.");
            logger.info("Erros encontrados:");
            for (FieldError erro : resultado.getFieldErrors()) {
                logger.info("{}", erro);
            }
            for (ObjectError erro : resultado.getGlobalErrors()) {
                logger.info("{}", erro);
            }
            return "usuarios/alterar :: formulario";
        } else {
            usuarioService.atualizar(dto.toPessoa());
            atributos.addFlashAttribute("notificacao", new NotificacaoSweetAlert2("Usuario alterada com sucesso!", TipoNotificaoSweetAlert2.SUCCESS, 4000));
            return "redirect:/usuario/abrirpesquisar";
        }
    }

    @GetMapping("/usuario/remover/{codigo}")
    public String remover(@PathVariable Long codigo, RedirectAttributes atributos) {
        Usuario usuario = usuarioService.buscar(codigo);
        if (usuario != null) {
            usuario.setStatus(StatusUsuario.INATIVO);
            usuarioService.atualizar(usuario);
            atributos.addFlashAttribute("mensagem", "Usuario removida com sucesso");
        } else {
            atributos.addFlashAttribute("mensagem",
                    "Não foi encontrada uma usuario com esse codigo");
        }
        return "redirect:/mensagem";
    }

}
