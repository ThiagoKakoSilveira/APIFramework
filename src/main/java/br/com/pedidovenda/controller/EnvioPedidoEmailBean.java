package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Pedido;
import br.com.pedidovenda.util.jsf.FacesUtil;
import br.com.pedidovenda.util.mail.Mailer;
import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;
import org.apache.velocity.tools.generic.NumberTool;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

@Named
@RequestScoped
public class EnvioPedidoEmailBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Mailer mailer;

	@Inject
	@PedidoEdicao
	private Pedido pedido;

	public void enviarPedido() throws IOException {
		MailMessage message = mailer.novaMensagem();

//		URL caminhoTemplate = getClass().getResource("/emails/pedido.template");
//		System.out.println(caminhoTemplate.toString());

//		File file = new File(caminhoTemplate.toString());

//		URL url2 = getClass().getClassLoader().getResource("/emails/pedido.template");
//		System.out.println(url2);
        String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/classes/emails/pedido.template");

		message.to(this.pedido.getCliente().getEmail())
				.subject("Pedido " + this.pedido.getId())
//				.bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/pedido.template")))
//				.bodyHtml(new VelocityTemplate(file))
//				.bodyHtml(new VelocityTemplate(new File("D:\\TesteIntelliJ\\PedidoVendaFielAoCurso\\PedidoVenda-master\\src\\main\\resources\\emails\\pedido.template")))
				.bodyHtml(new VelocityTemplate(new File(realPath)))
				.put("pedido", this.pedido)
				.put("numberTool", new NumberTool())
				.put("locale", new Locale("pt", "BR"))
				.send();

		FacesUtil.addInfoMessage("Pedido enviado por e-mail com sucesso!");
	}


}
