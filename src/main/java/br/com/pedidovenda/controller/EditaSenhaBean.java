package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Usuario;
import br.com.pedidovenda.repository.UsuarioRepository;
import br.com.pedidovenda.security.Seguranca;
import br.com.pedidovenda.security.UsuarioSistema;
import br.com.pedidovenda.service.UsuarioService;
import br.com.pedidovenda.util.jsf.FacesUtil;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class EditaSenhaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    Usuario usuarioSenhaEditável;

//    @Inject
//    UsuarioSistema usuarioSistema;

    @Inject
    Seguranca seguranca;

    @Inject
    UsuarioService usuarioService;

//    @Inject
//    private UsuarioRepository usuarioRepository;

    public EditaSenhaBean() {
        limpar();
    }

    public void inicializar(){
        if (FacesUtil.isNotPostback()) {
            usuarioSenhaEditável = seguranca.getUsuarioLogado().getUsuario();
        }
    }

    public void salvar(){
        this.usuarioSenhaEditável = usuarioService.salvar(this.usuarioSenhaEditável);
        FacesUtil.addInfoMessage("Senha de Usuário Atualizada!");
    }

    private void limpar() {
        this.usuarioSenhaEditável = new Usuario();
    }

    public Usuario getUsuarioSenhaEditável() {
        return usuarioSenhaEditável;
    }

    public void setUsuarioSenhaEditável(Usuario usuarioSenhaEditável) {
        this.usuarioSenhaEditável = usuarioSenhaEditável;
    }
}
