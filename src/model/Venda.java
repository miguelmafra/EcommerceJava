package model;

import java.util.ArrayList;
import java.util.Calendar;

public class Venda {
	public Venda() {
		itens = new ArrayList<ItemVenda>();
		cliente = new Cliente();
	}

	private int id;
	private ArrayList<ItemVenda> itens;
	private double valorDaVenda;
	private Calendar dataDaVenda;
	private Cliente cliente;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<ItemVenda> getItens() {
		return itens;
	}
	public void setItens(ArrayList<ItemVenda> itens) {
		this.itens = itens;
	}
	public double getValorDaVenda() {
		return valorDaVenda;
	}
	public void setValorDaVenda(double valorDaVenda) {
		this.valorDaVenda = valorDaVenda;
	}
	public Calendar getDataDaVenda() {
		return dataDaVenda;
	}
	public void setDataDaVenda(Calendar dataDaVenda) {
		this.dataDaVenda = dataDaVenda;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
