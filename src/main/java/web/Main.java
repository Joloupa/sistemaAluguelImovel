package web;

import web.sistemaAluguelImovel.ProcessadorPagamento;

public class Main {
    public static void main(String[] args) {
        ProcessadorPagamento sistema = new ProcessadorPagamento();
        sistema.processar("Joao", 150.00);
        sistema.processar("Maria", -50.00);
        System.out.println("Sistema de contratos carregado com sucesso.");
    }

}