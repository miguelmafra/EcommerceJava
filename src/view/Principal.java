package view;

import java.util.Calendar;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.ArrayList;
import dal.CategoriaDAO;
import dal.ClienteDAO;
import dal.MarcaDAO;
import dal.ProdutoDAO;
import dal.VendaDAO;
import model.Categoria;
import model.Cliente;
import model.ItemVenda;
import model.Marca;
import model.Produto;
import model.Venda;
import util.Console;

public class Principal {

	private static Categoria categoria = new Categoria();
	private static Marca marca = new Marca();
	private static Produto produto = new Produto();
	private static Venda venda = new Venda();
	private static ItemVenda itemVenda = new ItemVenda();
	private static Cliente cliente = new Cliente();
	private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) throws java.text.ParseException {
		CategoriaDAO.inicializarCategoria();
		MarcaDAO.inicializarMarca();
		ProdutoDAO.inicializarProduto();
		ClienteDAO.inicializarCliente();

		int opcao;
		do {

			System.out.println();
			System.out.println(" -- E-COMMERCE -- ");
			System.out.println("1 - Área Administrativa");
			System.out.println("2 - Área Comercial");
			System.out.println("0 - Sair");
			opcao = Console.readInt("\nDigite uma opcao: ");

			switch (opcao) {
			case 1:
				areaAdministrativa();
				break;
			case 2:
				areaComercial();
				break;
			default:
				break;
			}
		} while (opcao != 0);
	}

	private static void areaAdministrativa() {
		int opcao;
		do {
			System.out.println();
			System.out.println(" -- ÁREA ADMINISTRATIVA -- ");
			System.out.println("1 - Cadastrar categoria");
			System.out.println("2 - Cadastrar marca");
			System.out.println("3 - Cadastrar produto");
			System.out.println("4 - Listar produto");
			System.out.println("5 - Listar vendas");
			System.out.println("6 - Listar produtos por marca");
			System.out.println("7 - Atualizar estoque de produtos");
			System.out.println("8 - Listar produtos por categoria");
			System.out.println("0 - Voltar");
			opcao = Console.readInt("\nDigite uma opcao: ");
			switch (opcao) {
			case 1:
				cadastrarCategoria();
				break;
			case 2:
				cadastrarMarca();
				break;
			case 3:
				cadastrarProduto();
				break;
			case 4:
				listarProduto();
				break;
			case 5:
				listarVendas();
				break;
			case 6:
				listarProdutoPorMarca();
				break;
			case 7:
				atualizarEstoque();
				break;
			case 8: 
				listarProdutoPorCategoria();
			default:
				break;
			}
		} while (opcao != 0);
	}

	private static void listarProdutoPorMarca() {
		System.out.println();
		System.out.println(" -- LISTAR PRODUTOS POR MARCA -- ");

		String nomeMarca = Console.readString("Digite o nome da marca: ");
		marca = MarcaDAO.buscarMarcaPorNome(nomeMarca);

		ArrayList<Produto> arrayAuxiliar = ProdutoDAO.retornarProdutosPorMarca(marca);

		if (!arrayAuxiliar.isEmpty()) {
			for (Produto produtoCadastrado : arrayAuxiliar) {
				System.out.println();
				System.out.println("Id: " + produtoCadastrado.getId());
				System.out.println("Nome: " + produtoCadastrado.getNome());
				System.out.println("Valor: R$ " + produtoCadastrado.getValor());
				System.out.println("Quantidade: " + produtoCadastrado.getQuantidade());
				System.out.println("Descrição: " + produtoCadastrado.getDescricao());
				System.out.println("Marca: " + produtoCadastrado.getMarca().getNome());
				System.out.println("Categoria: " + produtoCadastrado.getCategoria().getNome());
			}

		} else {
			System.out.println("\nNenhum produto cadastrado.");
		}
	}

	private static void listarProdutoPorCategoria() {
		System.out.println();
		System.out.println(" -- LISTAR PRODUTOS POR CATEGORIA -- ");

		String nomeCategoria = Console.readString("Digite o nome da categoria: ");
		categoria = CategoriaDAO.buscarCategoriaPorNome(nomeCategoria);

		ArrayList<Produto> arrayAuxiliar = ProdutoDAO.retornarProdutosPorMarca(marca);

		if (!arrayAuxiliar.isEmpty()) {
			for (Produto produtoCadastrado : arrayAuxiliar) {
				System.out.println();
				System.out.println("Id: " + produtoCadastrado.getId());
				System.out.println("Nome: " + produtoCadastrado.getNome());
				System.out.println("Valor: R$ " + produtoCadastrado.getValor());
				System.out.println("Quantidade: " + produtoCadastrado.getQuantidade());
				System.out.println("Descrição: " + produtoCadastrado.getDescricao());
				System.out.println("Marca: " + produtoCadastrado.getMarca().getNome());
				System.out.println("Categoria: " + produtoCadastrado.getCategoria().getNome());
			}
		} else {
			System.out.println("\nNenhum produto cadastrado.");
		}
	}

	private static void areaComercial() throws java.text.ParseException {
		int opcao;
		do {
			System.out.println();
			System.out.println(" -- ÁREA COMERCIAL -- ");
			System.out.println("1 - Cadastrar cliente");
			System.out.println("2 - Comprar produtos");
			System.out.println("3 - Listar produtos");
			System.out.println("0 - Voltar");
			opcao = Console.readInt("\nDigite uma opcao: ");
			switch (opcao) {
			case 1:
				cadastrarCliente();
				break;
			case 2:
				venderProduto();
				break;
			case 3:
				listarProduto();
				break;
			default:
				break;
			}
		} while (opcao != 0);
	}

	private static void cadastrarCliente() throws java.text.ParseException {
		System.out.println();
		System.out.println(" -- CADASTRO DE CLIENTES -- ");
		System.out.println();

		cliente = new Cliente();
		String clienteNome;
		clienteNome = Console.readString("Digite seu nome: ");
		cliente = ClienteDAO.buscarClientePorNome(clienteNome);

		if (cliente != null) {
			System.out.println();
			System.out.println("Cliente já existente!");
			System.out.println();
		} else {
			cliente = new Cliente();
			cliente.setNome(clienteNome);
			cliente.setCpf(Console.readString("Digite seu CPF: "));
			cliente.setEmail(Console.readString("Digite seu e-mail: "));
			cliente.setEndereco(Console.readString("Digite seu endereço: "));
			String dataNascimento;
			try {
				dataNascimento = Console.readString("Informe a data de nascimento: ");
				Calendar cal = Calendar.getInstance();
				cal.setTime(df.parse(dataNascimento));
				cliente.setDataNascimento(cal);
			} catch (java.text.ParseException e) {
				System.out.println("\nData Inválida!");
			}
			if (cliente.getDataNascimento() != null) {
				ClienteDAO.cadastrarCliente(cliente);
				System.out.println();
				System.out.println("Cliente cadastrado com sucesso!");
				System.out.println();
			} else {
				System.out.println("\nInforme os dados corretamente.");
			}

		}
	}

	private static void cadastrarCategoria() {
		System.out.println();
		System.out.println(" -- CADASTRAR CATEGORIA -- ");
		System.out.println();

		categoria = new Categoria();
		categoria.setNome(Console.readString("Digite o nome da categoria: "));
		categoria.setDescricao(Console.readString("Digite a descrição da categoria: "));

		if (CategoriaDAO.cadastrarCategoria(categoria)) {
			System.out.println();
			System.out.println("Categoria cadastrada com sucesso!");
			System.out.println();
		} else {
			System.out.println();
			System.out.println("Categoria já existente!");
			System.out.println();
		}
	}

	private static void cadastrarMarca() {
		System.out.println();
		System.out.println(" -- CADASTRAR MARCA -- ");
		System.out.println();

		marca = new Marca();
		marca.setNome(Console.readString("Digite o nome da marca: "));
		marca.setEndereco(Console.readString("Digite o endereço da marca: "));

		if (MarcaDAO.cadastrarMarca(marca)) {
			System.out.println();
			System.out.println("Marca cadastrada com sucesso!");
			System.out.println();
		} else {
			System.out.println();
			System.out.println("Marca já existente!");
			System.out.println();
		}
	}

	private static void cadastrarProduto() {
		System.out.println();
		System.out.println(" -- CADASTRAR PRODUTO -- ");
		System.out.println();

		produto = new Produto();
		categoria = new Categoria();

		String nomeCategoria = Console.readString("Digite o nome da categoria: ");
		categoria = CategoriaDAO.buscarCategoriaPorNome(nomeCategoria);

		if (categoria != null) {
			String nomeMarca = Console.readString("Digite o nome da marca: ");
			marca = MarcaDAO.buscarMarcaPorNome(nomeMarca);

			if (marca != null) {
				produto.setCategoria(categoria);
				produto.setMarca(marca);
				produto.setNome(Console.readString("Digite o nome do produto: "));
				produto.setDescricao(Console.readString("Digite a descrição do produto: "));
				produto.setValor(Console.readDouble("Digite o valor do produto: "));
				produto.setQuantidade(Console.readInt("Digite a quantidade do produto: "));
				ProdutoDAO.cadastrarProduto(produto);
				System.out.println();
				System.out.println("Produto gravado com sucesso!");
				System.out.println();
			} else {
				System.out.println();
				System.out.println("Marca não encontrada!");
				System.out.println();
			}
		} else {
			System.out.println();
			System.out.println("Categoria não encontrada!");
			System.out.println();
		}
	}

	private static void listarProduto() {
		System.out.println();
		System.out.println(" -- LISTAR PRODUTOS -- ");

		ArrayList<Produto> arrayCadastrado = ProdutoDAO.retornarProdutos();

		if (arrayCadastrado.size() == 0) {
			System.out.println("\nNenhum produto cadastrado.");

		} else {
			for (Produto produtoCadastrado : ProdutoDAO.retornarProdutos()) {
				System.out.println();
				System.out.println("Id: " + produtoCadastrado.getId());
				System.out.println("Nome: " + produtoCadastrado.getNome());
				System.out.println("Valor: R$ " + produtoCadastrado.getValor());
				System.out.println("Quantidade: " + produtoCadastrado.getQuantidade());
				System.out.println("Descrição: " + produtoCadastrado.getDescricao());
				System.out.println("Marca: " + produtoCadastrado.getMarca().getNome());
				System.out.println("Categoria: " + produtoCadastrado.getCategoria().getNome());
			}
		}
	}

	private static void venderProduto() {
		System.out.println();
		System.out.println(" -- COMPRAR PRODUTOS -- ");
		System.out.println();

		venda = new Venda();

		String op, nomeProduto, nomeCliente;
		double total = 0;
		int qtd = 0;
		do {
			qtd = 0;
			produto = new Produto();
			itemVenda = new ItemVenda();

			nomeProduto = Console.readString("Digite o nome do produto: ");
			produto = ProdutoDAO.buscarProdutoPorNome(nomeProduto);

			if (produto != null) {
				System.out.println("\nProduto: " + produto.getNome());
				System.out.println("Valor: R$" + produto.getValor());
				System.out.println("Quantidade disponível: " + (produto.getQuantidade() - qtd));
				System.out.println("Quantidade selecionada: " + qtd);
				qtd = Console.readInt("\nDigite a quantidade desejada: ");
				if (produto.getQuantidade() < qtd) {
					System.out.println("Quantidade não disponível.");
					qtd = 0;
				} else {
					if (qtd > 0) {
						itemVenda.setProduto(produto);
						itemVenda.setQuantidade(qtd);
						itemVenda.setDataDaAdicao(Calendar.getInstance());
						venda.getItens().add(itemVenda);
						total += itemVenda.getQuantidade() * produto.getValor();
					} else {
						System.out.println("\nA quantidade deve ser maior que 0!");
					}
				}
			} else {
				System.out.println("\nProduto não encontrado!");
			}

			op = Console.readString("\nDeseja selecionar outro produto? (S/N): ");
		} while (op.toUpperCase().equals("S"));

		if (venda.getItens().size() != 0) {
			nomeCliente = Console.readString("Digite o nome do cliente: ");
			cliente = ClienteDAO.buscarClientePorNome(nomeCliente);

			if (cliente != null) {
				venda.setCliente(cliente);
				venda.setDataDaVenda(Calendar.getInstance());
				venda.setValorDaVenda(total);
				VendaDAO.cadastrarVenda(venda);

				ArrayList<ItemVenda> arrayItens = venda.getItens();
				for (int i = 0; i < arrayItens.size(); i++) {
					produto = new Produto();
					produto = ProdutoDAO.buscarProdutoPorNome(arrayItens.get(i).getProduto().getNome());
					if (produto != null) {
						produto.setQuantidade(produto.getQuantidade() - arrayItens.get(i).getQuantidade());
					}
				}

				System.out.println();
				System.out.println(" -- COMPRA REALIZADA COM SUCESSO -- ");
				System.out.println();
				System.out.println("Cliente: " + venda.getCliente().getNome());
				System.out.println("Data: " + df.format(venda.getDataDaVenda().getTime()));
				System.out.println("Valor total: R$" + venda.getValorDaVenda());

			} else {

				System.out.println("\nCliente não encontrado!\n");
			}
		} else {
			System.out.println("\nCompra não realizada!");
		}
	}

	private static void listarVendas() {
		System.out.println();
		System.out.println(" -- LISTAR VENDAS -- ");

		ArrayList<Venda> arrayVenda = VendaDAO.retornarVendas();

		if (arrayVenda.size() == 0) {
			System.out.println("\nNenhuma venda efetuada.");
		} else {
			for (Venda vendaCadastrada : VendaDAO.retornarVendas()) {
				System.out.println();
				System.out.println("Id: " + vendaCadastrada.getId());
				System.out.println("Nome: " + vendaCadastrada.getCliente().getNome());
				System.out.println("Valor total: R$" + vendaCadastrada.getValorDaVenda());
				System.out.println("Data: " + df.format(vendaCadastrada.getDataDaVenda().getTime()));
				System.out.println("\n -- ITENS DA VENDA -- ");
				for (ItemVenda itemVendaCadastrado : venda.getItens()) {
					System.out.println("\n\tProduto: " + itemVendaCadastrado.getProduto().getNome());
					System.out.println("\tQuantidade: " + itemVendaCadastrado.getQuantidade());
					System.out.println("\tData: " + df.format(itemVendaCadastrado.getDataDaAdicao().getTime()));
				}
			}
		}
	}

	private static void atualizarEstoque() {
		System.out.println();
		System.out.println(" -- ATUALIZAR ESTOQUE DE PRODUTOS -- ");
		System.out.println();

		produto = new Produto();

		String nomeProduto = Console.readString("Digite o nome do produto: ");
		produto = ProdutoDAO.buscarProdutoPorNome(nomeProduto);

		if (produto != null) {
			int qtd;
			qtd = produto.getQuantidade();

			System.out.println("Estoque atual: " + produto.getQuantidade());
			qtd = Console.readInt("Digite a quantidade para inserir no estoque: ");
			produto.setQuantidade(produto.getQuantidade() + qtd);
			System.out.println("Estoque atualizado!");
		} else {
			System.out.println("Produto não encontrado!");
		}
	}

}
