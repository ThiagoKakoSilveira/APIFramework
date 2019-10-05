package br.com.pedidovenda.repository;

import br.com.pedidovenda.model.Endereco;
import br.com.pedidovenda.util.jpa.Transactional;

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

	@Transactional
	public void remover(Endereco endereco){
		if (endereco.getId() != null){
			Endereco seraExcluido = entityManager.find(Endereco.class, endereco.getId());
			if (seraExcluido != null){
				entityManager.remove(seraExcluido);
				entityManager.flush();
			}
		}
	}
}
