package br.com.pedidovenda.converter;

import br.com.pedidovenda.model.Endereco;
import br.com.pedidovenda.repository.EnderecoRepository;
import br.com.pedidovenda.util.cdi.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Endereco.class)
public class EnderecoConverter implements Converter {

	private EnderecoRepository enderecoRepository;

	public EnderecoConverter() {
		enderecoRepository = CDIServiceLocator.getBean(EnderecoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext faces, UIComponent component,
			String value) {
		Endereco retorno = null;
		if (value != null) {
			Long id = new Long(value);
			retorno = enderecoRepository.buscaPorId(id);
		}
		return retorno;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			return ((Endereco) value).getId().toString();
		}

		return "";
	}

}
