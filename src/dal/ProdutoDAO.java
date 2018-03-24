package dal;

import java.util.ArrayList;

import model.Categoria;
import model.Marca;
import model.Produto;

public class ProdutoDAO {

	private static ArrayList<Produto> produtos = new ArrayList<Produto>();

	public static void inicializarProduto() {
		Produto produto = new Produto();
		Categoria categoria = new Categoria();
		Marca marca = new Marca();

		produto.setId(1);
		produto.setNome("ProdutoTeste");
		produto.setDescricao("Descrição de produto Teste");
		categoria = CategoriaDAO.buscarCategoriaPorNome("Teste");
		produto.setCategoria(categoria);
		marca = MarcaDAO.buscarMarcaPorNome("Teste");
		produto.setMarca(marca);
		produto.setQuantidade(10);
		produto.setValor(10);
		produtos.add(produto);
		produto = new Produto();
	}

	public static void cadastrarProduto(Produto produto) {
		if (produtos.size() > 0) {
			int ultimoId = produtos.get(produtos.size() - 1).getId();
			produto.setId(ultimoId + 1);
		} else {
			produto.setId(1);
		}
		produtos.add(produto);
	}

	public static ArrayList<Produto> retornarProdutos() {
		return produtos;
	}

	public static Produto buscarProdutoPorNome(String nomeProduto) {
		for (Produto produtoCadastrado : produtos) {
			if (produtoCadastrado.getNome().equals(nomeProduto)) {
				return produtoCadastrado;
			}
		}
		return null;
	}

	public static Produto buscarProduto(Produto produto) {
		for (Produto produtoCadastrado : produtos) {
			if (produtoCadastrado.getNome().equals(produto.getNome())) {
				return produtoCadastrado;
			}
		}
		return null;
	}

	public static ArrayList<Produto> retornarProdutosPorMarca(Marca marca) {
		ArrayList<Produto> arrayAuxiliar = new ArrayList<Produto>();

		for (int i = 0; i < produtos.size(); i++) {
			if (produtos.get(i).getMarca() == marca) {
				arrayAuxiliar.add(produtos.get(i));
			}
		}

		return arrayAuxiliar;

	}
	
	public static ArrayList<Produto> retornarProdutosPorCategoria(Categoria categoria) {
		ArrayList<Produto> arrayAuxiliar = new ArrayList<Produto>();

		for (int i = 0; i < produtos.size(); i++) {
			if (produtos.get(i).getCategoria() == categoria) {
				arrayAuxiliar.add(produtos.get(i));
			}
		}

		return arrayAuxiliar;

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
