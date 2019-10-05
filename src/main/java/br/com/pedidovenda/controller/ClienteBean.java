package br.com.pedidovenda.controller;

import br.com.pedidovenda.model.Cliente;
import br.com.pedidovenda.model.Endereco;
import br.com.pedidovenda.model.TipoPessoa;
import br.com.pedidovenda.repository.ClienteRepository;
import br.com.pedidovenda.repository.EnderecoRepository;
import br.com.pedidovenda.repository.filter.ClienteFilter;
import br.com.pedidovenda.service.ClienteService;
import br.com.pedidovenda.util.java.JavaUtil;
import br.com.pedidovenda.util.jsf.FacesUtil;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

	private Endereco enderecoDeExclusao;

	private boolean ehNovo;

//	private List<Endereco> enderecos;

	private List<Cliente> clientesFiltrados;
	private ClienteFilter clienteFilter;

	public ClienteBean() {
		limpar();
	}

//	public String salvar() {
    public void salvar() {
		this.cliente = clienteService.salvar(cliente);
		FacesUtil.addInfoMessage("Cliente cadastrado com sucesso!");
		limpar();
//		return "CadastraCliente?faces-redirect=true";
	}

	public void integraClienteEndereco(){

		System.out.println("Vai começar a testar se existe");

		if (!this.ehNovo){
			System.out.println("Não é novo!");
			Iterator itr = this.cliente.getEnderecos().iterator();
			while (itr.hasNext())
			{
				Endereco end = (Endereco) itr.next();
				System.out.println("Pegou o próximo item! E");
				if (end.getIdEdit() != null) {
					if (end.getIdEdit().equals(this.endereco.getIdEdit())) {
						itr.remove();
						System.out.println("Excluiu um novo!");
					}
				}else {
					if (end.equals(endereco)) {
						itr.remove();
						System.out.println("Excluiu um antigo!");
					}
				}
				System.out.println("Nada Fez");
			}
		}
		this.endereco.setCliente(this.getCliente());
		System.out.println("Irá salvar na lista");
		this.cliente.getEnderecos().add(this.endereco);
		this.ehNovo=false;
//		criaEndereco();
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
		System.out.println("Vai criar Endereço");
		endereco = new Endereco();
		System.out.println("Criou Endereço");
		this.ehNovo = true;
		endereco.setIdEdit(JavaUtil.entregaString());
	}

	public void excluiEndereco(){
		enderecoRepository.remover(enderecoDeExclusao);

		FacesUtil.addInfoMessage("Endereço de logradouro: " + enderecoDeExclusao.getLogradouro() + "Vinculado ao cliente: " + enderecoDeExclusao.getCliente().getNome());
	}

	public boolean isEditando() {
		return this.cliente.getId() != null;
	}

	public boolean isEditandoEndereco (){return this.endereco.getId() != null; }

	public boolean isEhNovo() {
		return ehNovo;
	}

	public void setEhNovo(boolean ehNovo) {
		this.ehNovo = ehNovo;
	}

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

	public ClienteFilter getClienteFilter() {
		return clienteFilter;
	}

	public void setClienteFilter(ClienteFilter clienteFilter) {
		this.clienteFilter = clienteFilter;
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public Endereco getEnderecoDeExclusao() {
		return enderecoDeExclusao;
	}

	public void setEnderecoDeExclusao(Endereco enderecoDeExclusao) {
		this.enderecoDeExclusao = enderecoDeExclusao;
	}
}
