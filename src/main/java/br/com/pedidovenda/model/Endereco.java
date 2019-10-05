package br.com.pedidovenda.model;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@NotBlank
	@Size(max = 150)
	@Column(nullable = false, length = 150)
	private String logradouro;

	@NotNull
	@NotBlank
	@Size(max = 20)
	@Column(length = 20)
	private String numero;

	@Size(max = 150)
	@Column(length = 150)
	private String complemento;

	@NotNull
	@NotBlank
	@Size(max = 60)
	@Column(length = 60)
	private String cidade;

	@NotNull
	@NotBlank
	@Size(max = 60)
	@Column(length = 60)
	private String uf;

	@NotNull
	@NotBlank
	@Size(max = 9)
	@Column(length = 9)
	private String cep;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = true)
	private Cliente cliente;

	@Transient
	private String idEdit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getIdEdit() {
		return idEdit;
	}

	public void setIdEdit(String idEdit) {
		this.idEdit = idEdit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
