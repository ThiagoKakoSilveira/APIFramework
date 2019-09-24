package br.com.pedidovenda.service;

import br.com.pedidovenda.model.Categoria;
import br.com.pedidovenda.model.Produto;
import br.com.pedidovenda.repository.CategoriaRepository;
import br.com.pedidovenda.repository.ProdutoRepository;
import br.com.pedidovenda.util.jpa.Transactional;

import javax.inject.Inject;
import java.io.Serializable;

public class CadastroCategoriaService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CategoriaRepository categoriaRepository;

    @Transactional
    public Categoria salvar(Categoria categoria) {
//        Categoria categoriaExistente = categoriaRepository.porSku(produto.getSku());
//        if (categoriaExistente != null && !categoriaExistente.equals(categoria)) {
//            throw new NegocioException("Já existe produto com o sku informado");
//        }
        return categoriaRepository.salvar(categoria);
    }

}
