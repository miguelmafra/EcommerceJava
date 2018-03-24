package dal;

import java.util.ArrayList;

import model.Categoria;

public class CategoriaDAO {

	private static ArrayList<Categoria> categorias = new ArrayList<Categoria>();
	
	public static void inicializarCategoria(){
		Categoria categoria = new Categoria();
		categoria.setId(1);
		categoria.setNome("Teste");
		categoria.setDescricao("DescriçãoTeste");
		categorias.add(categoria);
		categoria = new Categoria();
	}

	public static boolean cadastrarCategoria(Categoria categoria) {
		if (buscarCategoriaPorNome(categoria.getNome()) == null) {
			if (categorias.size() > 0) {
				int ultimoId = categorias.get(categorias.size() - 1).getId();
				categoria.setId(ultimoId + 1);
			}else{
				categoria.setId(1);
			}
			categorias.add(categoria);
			return true;
		}
		return false;
	}

	public static Categoria buscarCategoriaPorNome(String nome) {
		for (Categoria categoriaCadastrada : categorias) {
			if (categoriaCadastrada.getNome().equals(nome)) {
				return categoriaCadastrada;
			}
		}
		return null;
	}

}
