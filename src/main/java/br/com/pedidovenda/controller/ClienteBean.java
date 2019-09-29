package br.com.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.el.MethodExpression;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.pedidovenda.model.Cliente;
import br.com.pedidovenda.model.Endereco;
import br.com.pedidovenda.model.TipoPessoa;
import br.com.pedidovenda.repository.ClienteRepository;
import br.com.pedidovenda.repository.EnderecoRepository;
import br.com.pedidovenda.repository.filter.ClienteFilter;
import br.com.pedidovenda.service.ClienteService;
import br.com.pedidovenda.util.jsf.FacesUtil;

@Named("clienteBean")
@ViewScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteRepository clienteRepository;

	@Inject
	private ClienteService clienteService;

	@Inject
	private EnderecoRepository enderecoRepository;

	private Cliente cliente;
	private Endereco endereco;

//	private List<Endereco> enderecos;

	private List<Cliente> clientesFiltrados;
	private ClienteFilter clienteFilter;

	public ClienteBean() {
		limpar();
	}

	public void salvar() {
		this.cliente = clienteService.salvar(cliente);
		FacesUtil.addInfoMessage("Cliente cadastrado com sucesso!");
		limpar();
	}

	public void excluir() {
		clienteRepository.remover(cliente);
		clientesFiltrados.remove(cliente);
		FacesUtil.addInfoMessage("Cliente " + cliente.getNome()
				+ " excluído com sucesso!");
	}

	public void pesquisarClientes() {
		clientesFiltrados = clienteRepository.filtrado(clienteFilter);
	}

	private void limpar() {
		cliente = new Cliente();
		endereco = new Endereco();
		cliente.setTipo(TipoPessoa.FISICA);
		clienteFilter = new ClienteFilter();
//		enderecos = new ArrayList<>();
		cliente.setEnderecos(new ArrayList<>());
	}

	public void criaEndereco(){
		endereco = new Endereco();
	}

	public boolean isEditando() {
		return this.cliente.getId() != null;
	}

	public boolean isEditandoEndereco (){return this.endereco.getId() != null; }

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

//	public List<Endereco> getEnderecos() {
//		return enderecos;
//	}
//
//	public void setEnderecos(List<Endereco> enderecos) {
//		this.enderecos = enderecos;
//	}

	public ClienteFilter getClienteFilter() {
		return clienteFilter;
	}

	public void setClienteFilter(ClienteFilter clienteFilter) {
		this.clienteFilter = clienteFilter;
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public void integraClienteEndereco(){
		if(this.cliente.getEnderecos().contains(this.endereco)){
			System.out.println("Irá excluir");
			this.cliente.getEnderecos().remove(this.endereco);
		}
		this.endereco.setCliente(this.getCliente());
		this.cliente.getEnderecos().add(this.endereco);
		endereco = new Endereco();
	}

	public void teste() {
		System.out.println("Será que bombou irá aparecer os dados de endereço aqui em baixo");
		System.out.println(this.endereco.getId());
		System.out.println(this.endereco.getLogradouro());
		System.out.println(this.endereco.getCep());
		System.out.println(this.endereco.getCidade());
		System.out.println(this.endereco.getNumero());
		System.out.println(this.endereco.getUf());
//		this.endereco = enderecoRepository.buscaPorId();
	}
}
