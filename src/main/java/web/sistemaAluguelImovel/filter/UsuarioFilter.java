package web.sistemaAluguelImovel.filter;

public class UsuarioFilter {

    private Long codigo;

    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    
    private String dataCadastro;
    

    private String Tipo;//se é Cliente, Funcionario, Adm


// ================ //
// GETERS E SETTERS //
// ================ //

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

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    // public String getDataCadastroFim() {
    //     return dataCadastroFimal;
    // }

    // public void setDataCadastroFim(String dataCadastroFimal) {
    //     this.dataCadastroFimal = dataCadastroFimal;
    // }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    @Override
    public String toString() {
        return "UsuarioFilter [codigo: " + codigo + "\nnome: " + nome + "\ncpf: " + cpf + "\nemail: " + email + "\ntelefone: "
                + telefone + "\ndataCadastroInicio: " + dataCadastro + "\nTipo: " + Tipo + "]";
    }
    
    
}
