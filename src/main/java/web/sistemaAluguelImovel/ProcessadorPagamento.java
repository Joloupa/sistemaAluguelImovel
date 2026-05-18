package web.sistemaAluguelImovel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProcessadorPagamento {

    // Criação do Logger para esta classe
    private static final Logger logger = LoggerFactory.getLogger(ProcessadorPagamento.class);

    public void processar(String usuario, double valor) {
        // Uso de placeholders {} (Vantagem de performance e limpeza)
        logger.info("Iniciando processamento para: {}", usuario);
        
        if (valor <= 0) {
            logger.error("O valor {} é inválido para o usuário {}", valor, usuario);
            return;
        }

        try {
            logger.debug("Conexão com banco estabelecida."); // Só deve aparecer se configurado
            Thread.sleep(100); 
        } catch (InterruptedException e) {
            logger.error("Erro na thread", e);
        }

        logger.info("Pagamento de R${} processado com sucesso.", valor);
    }

    public void processarPagamento(BigDecimal valor) {

    System.out.println("Pagamento processado: R$ " + valor);
}

}