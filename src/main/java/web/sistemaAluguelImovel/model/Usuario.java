package web.sistemaAluguelImovel.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
@DynamicUpdate
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="gerador2", sequenceName="usuario_codigo_seq", allocationSize=1)
	@GeneratedValue(generator="gerador2", strategy=GenerationType.SEQUENCE)
	private Long codigo;
	private String nome;
	private String email;
	private String cpf;
	private String telefone;
	private Boolean ativo;

	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;
	@Enumerated(EnumType.STRING)
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataCadastro;
	}

	public void setDataNascimento(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public StatusUsuario getStatus() {
		return status;
	}

	public void setStatus(StatusUsuario status) {
		this.status = status;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	

	@Override
	public String toString() {
		return "codigo: " + codigo + "\nnome: " + nome + "\nemail: " + email + "\ncpf: " + cpf + "\ntelefone: "
				+ telefone + "\nativo: " + ativo + "\ndataCadastro: " + dataCadastro + "\nstatus: " + status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
}
