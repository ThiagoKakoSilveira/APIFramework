package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Categoria;
import br.com.pedidovenda.repository.CategoriaRepository;
import br.com.pedidovenda.util.jsf.FacesUtil;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Named("categoriaBean")
@ViewScoped
public class CategoriaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CategoriaRepository categoriaRepository;

    @NotNull
    private Categoria categoria;

    private List<Categoria> categoriasRaizes;
    private List<Categoria> subCategorias;

    public void inicializar() {
        System.out.println("Inicializando...");
        /*
         * Classe FacesUtil - isNotPostBack - apenas para carregar o Combobox
         * somente 1 vez, não fica refazendo consulta toda vez que clicamos em
         * salvar
         */
        if (FacesUtil.isNotPostback()) {
            categoriasRaizes = categoriaRepository.buscarCategoriasRaizes();
            // preenchendo o combo de subcategorias
            if (this.categoria != null) {
                carregarSubCategorias();
            }
        }
    }

    public void carregarSubCategorias() {
        subCategorias = categoriaRepository.buscarSubCategorias(categoria);
    }

    public boolean isEditando() {
        return this.categoria.getId() != null;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getCategoriasRaizes() {
        return categoriasRaizes;
    }

    public List<Categoria> getSubCategorias() { return subCategorias; }

}
