package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Categoria;
import br.com.pedidovenda.repository.CategoriaRepository;
import br.com.pedidovenda.service.CadastroCategoriaService;
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

    @Inject
    private CadastroCategoriaService cadastroCategoriaService;

    @NotNull
    private Categoria categoria;

    private List<Categoria> categoriasRaizes;

    public CategoriaBean(){
        limpar();
    }

    public void inicializar() {
        System.out.println("Inicializando...");
        /*
         * Classe FacesUtil - isNotPostBack - apenas para carregar o Combobox
         * somente 1 vez, não fica refazendo consulta toda vez que clicamos em
         * salvar
         */
        if (FacesUtil.isNotPostback()) {
            categoriasRaizes = categoriaRepository.buscarCategoriasRaizes();
        }
    }

    private void atualizarRaizes(){
        categoriasRaizes = categoriaRepository.buscarCategoriasRaizes();
    }

    private void limpar() {
        categoria = new Categoria();
    }

    public void salvar(){
        this.categoria = cadastroCategoriaService.salvar(categoria);
        FacesUtil.addInfoMessage("Categoria cadastrada com sucesso!");
        atualizarRaizes();
        limpar();
    }

    public boolean isEditando() {
        if (this.categoria != null){
            return this.categoria.getId() != null;
        }
        return false;
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
}
