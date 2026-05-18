package web.sistemaAluguelImovel.validation.cpfunico;

import java.security.InvalidParameterException;
import org.springframework.stereotype.Service;
import web.sistemaAluguelImovel.dto.UsuarioDTOInput;
import web.sistemaAluguelImovel.model.Usuario;
import web.sistemaAluguelImovel.service.UsuarioService;

@Service
public class CPFUnicoServiceImpl implements CPFUnicoService {
	private UsuarioService usuarioService;

    public CPFUnicoServiceImpl(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

	@Override
	public boolean isValueUnique(Object value, String fieldName) throws UnsupportedOperationException {
		if (!fieldName.equals("cpf")) {
			throw new UnsupportedOperationException("A anotação deveria ser usada no atributo cpf");
		}

		Usuario nova = ((UsuarioDTOInput) value).toPessoa();
		//A validacao "foi preenchido um cpf" nao eh obrigacao dessa verificacao
		if (nova.getCpf() == null || nova.getCpf().isBlank()) {
			return true;
		}
		
		//Busca uma usuario com esse CPF
		Usuario comEsseCPF = usuarioService.buscarPeloCPF(nova.getCpf());
		
		//Nao existe uma usuario com esse cpf, entao tudo bem
		if (comEsseCPF == null) {
			return true;
		} else {  //Existe uma usuario com esse cpf
			//Estao tentando validar uma nova usuario com um cpf que ja existe 
			if (nova.getCodigo() == null) {
				return false;
			} else {  //A usuario sendo validada ja existe
				Usuario antiga = usuarioService.buscar(nova.getCodigo());
                if (antiga == null) {
                    throw new InvalidParameterException("O código do contato a validar não existe.");
                }
				// Se o cpf sendo validado for o mesmo que ja existia no BD entao tudo bem
				if (comEsseCPF.equals(antiga)) {
					return true;
				}
				// Senao eh pq estao tentando validar um cpf que eh de outra usuario
				return false;
			}
		}
	}
}
