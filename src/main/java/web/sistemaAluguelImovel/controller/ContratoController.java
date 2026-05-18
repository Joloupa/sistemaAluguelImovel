package web.sistemaAluguelImovel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import web.sistemaAluguelImovel.dto.ContratoDTOInput;
import web.sistemaAluguelImovel.service.ContratoService;

@Controller
public class ContratoController {

    private final ContratoService contratoService;

    public ContratoController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    @GetMapping("/contrato/cadastrar")
    public String abrirCadastro(ContratoDTOInput dto) {
        return "contratos/cadastrar";
    }

    @PostMapping("/contrato/cadastrar")
    public String cadastrar(
            @Valid ContratoDTOInput dto,
            RedirectAttributes atributos) {

        contratoService.salvar(dto);

        atributos.addFlashAttribute(
                "mensagem",
                "Contrato cadastrado com sucesso!");

        return "redirect:/contrato/cadastrar";
    }

}
