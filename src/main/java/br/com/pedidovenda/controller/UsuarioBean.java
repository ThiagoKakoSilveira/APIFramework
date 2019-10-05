package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Grupo;
import br.com.pedidovenda.model.Usuario;
import br.com.pedidovenda.repository.GrupoRepository;
import br.com.pedidovenda.repository.UsuarioRepository;
import br.com.pedidovenda.repository.filter.UsuarioFilter;
import br.com.pedidovenda.service.UsuarioService;
import br.com.pedidovenda.util.java.JavaUtil;
import br.com.pedidovenda.util.jsf.FacesUtil;
import br.com.pedidovenda.util.mail.Mailer;
import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Mailer mailer;

	private Usuario usuario;

	private UsuarioFilter filtro;

	@Inject
	private UsuarioRepository usuarioRepository;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private GrupoRepository grupoRepository;

	private List<Usuario> usuarioFiltrado;
	private List<Grupo> grupos;
	private Grupo novoGrupo;

	public UsuarioBean() {
		limpar();
	}

	public void pesquisarUsuarios() {
		usuarioFiltrado = usuarioRepository.filtrado(filtro);
	}

	public void salvar() throws IOException {
		this.usuario.setSenha(JavaUtil.entregaString());
		enviaSenhaPorEmail();
		this.usuario = usuarioService.salvar(this.usuario);
		FacesUtil.addInfoMessage("Usuário salvo com sucesso e senha envida por Email!");
		limpar();
	}

	private void enviaSenhaPorEmail() throws IOException {
		MailMessage message = mailer.novaMensagem();
        String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/classes/emails/senhaEmail.template");
		message.to(this.usuario.getEmail()).subject("Senha do Sistema de Venda")
//				.bodyHtml(new VelocityTemplate(new File("D:\\TesteIntelliJ\\PedidoVendaFielAoCurso\\PedidoVenda-master\\src\\main\\resources\\emails\\senhaEmail.template")))
                .bodyHtml(new VelocityTemplate(new File(realPath)))
				.put("usuario", this.usuario)
				.send();
	}

	public void excluir() {
		usuarioRepository.remover(usuario);
		usuarioFiltrado.remove(usuario);
		FacesUtil.addInfoMessage("Usuário " + usuario.getNome()
				+ " excluído com sucesso!");
	}

	private void limpar() {
		usuario = new Usuario();
		filtro = new UsuarioFilter();
		grupos = new ArrayList<>();
	}

	public void adicionarGrupo() {
		this.usuario.getGrupos().add(novoGrupo);
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			System.out.println("Carregando combo de Grupos no preRender");
			this.grupos = grupoRepository.buscarGrupos();
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarioFiltrado() {
		return usuarioFiltrado;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public Grupo getNovoGrupo() {
		return novoGrupo;
	}

	public void setNovoGrupo(Grupo novoGrupo) {
		this.novoGrupo = novoGrupo;
	}

	public UsuarioFilter getFiltro() {
		return filtro;
	}

}
