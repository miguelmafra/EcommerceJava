package dal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import model.Cliente;


public class ClienteDAO {
	private static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void inicializarCliente() throws ParseException{
		Cliente cliente = new Cliente();
		cliente.setId(1);
		cliente.setNome("Cliente Teste");
		cliente.setCpf("123456789-10");
		cliente.setEmail("teste@teste.com");
		cliente.setEndereco("Curitiba-PR");
		Calendar cal = Calendar.getInstance();
		String data = "01/01/1990";
		cal.setTime(df.parse(data));
		cliente.setDataNascimento(cal);
		clientes.add(cliente);
		cliente = new Cliente();
	}

	public static boolean cadastrarCliente(Cliente cliente) {
		if (buscarClientePorNome(cliente.getNome()) == null) {
			if (clientes.size() > 0) {
				int ultimoId = clientes.get(clientes.size() - 1).getId();
				cliente.setId(ultimoId + 1);
			}else{
				cliente.setId(1);
			}
			clientes.add(cliente);
			return true;
		}
		return false;
	}

	public static Cliente buscarClientePorNome(String nome) {
		for (Cliente clienteCadastrado : clientes) {
			if (clienteCadastrado.getNome().equals(nome)) {
				return clienteCadastrado;
			}
		}
		return null;
	}
	
}
