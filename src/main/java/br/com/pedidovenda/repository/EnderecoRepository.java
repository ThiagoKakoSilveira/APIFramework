package br.com.pedidovenda.repository;

import br.com.pedidovenda.model.Endereco;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;

public class EnderecoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;

	public Endereco buscaPorId(Long id) {
		return entityManager.find(Endereco.class, id);
	}

	public Endereco salvar(Endereco endereco) {
		return entityManager.merge(endereco);

	}
}
