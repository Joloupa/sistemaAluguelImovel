package web.sistemaAluguelImovel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import web.sistemaAluguelImovel.model.StatusUsuario;
import web.sistemaAluguelImovel.model.Usuario;
import web.sistemaAluguelImovel.validation.UniqueValueAttribute;
import web.sistemaAluguelImovel.validation.cpfunico.CPFUnicoService;

@UniqueValueAttribute(attribute = "cpf", message = "Esse CPF já foi usado por outra usuario", service = CPFUnicoService.class)
public class UsuarioDTOInput {

    private Long codigo;
    @NotBlank(message = "O nome do usuario é obrigatório")
    @Size(max = 255, message = "O tamanho máximo do nome é 255 caracteres")
    private String nome;
    @NotBlank(message = "O CPF do usuario é obrigatório")
    // @CPF(message = "O formato do CPF é inválido")
    private String cpf;
    @NotBlank(message = "O E-mail do usuario é obrigatório")
    // @CPF(message = "O formato do E-mail é inválido")
    private String email;
    @NotBlank(message = "O telefone do usuario é obrigatório")
    // @CPF(message = "O formato do CPF é inválido")
    private String telefone;

    // @NotNull(message = "A data de nascimento é obrigatória")
    // @Past(message = "A data de nascimento deve ser anterior ao dia de hoje")
    // private LocalDate dataNascimento;
    private StatusUsuario status = StatusUsuario.ATIVO;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public StatusUsuario getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setStatus(StatusUsuario status) {
        this.status = status;
    }

    public Usuario toPessoa() {
        Usuario usuario = new Usuario();
        usuario.setCodigo(codigo);
        usuario.setNome(nome);
        usuario.setCpf(cpf);
        usuario.setEmail(email);
        usuario.setTelefone(telefone);
        usuario.setStatus(status);
        return usuario;
    }

    public static UsuarioDTOInput fromPessoa(Usuario usuario) {
        UsuarioDTOInput dto = new UsuarioDTOInput();
        dto.setCodigo(usuario.getCodigo());
        dto.setNome(usuario.getNome());
        dto.setCpf(usuario.getCpf());
        dto.setEmail(usuario.getEmail());
        dto.setTelefone(usuario.getTelefone());
        dto.setStatus(usuario.getStatus());
        return dto;
    }

    @Override
    public String toString() {
        return "codigo: " + codigo + "\nnome: " + nome + "\ncpf: " + cpf + "\nemail: " + email + "\ntelefone: "
                + telefone + "\nstatus: " + status;
    }

    
}
