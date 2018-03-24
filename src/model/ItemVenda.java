package model;

import java.util.Calendar;

public class ItemVenda {
	public ItemVenda(){
		produto = new Produto();
	}
	
	private int id;
	private Produto produto;
	private int quantidade;
	private Calendar dataDaAdicao;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Calendar getDataDaAdicao() {
		return dataDaAdicao;
	}
	public void setDataDaAdicao(Calendar dataDaAdicao) {
		this.dataDaAdicao = dataDaAdicao;
	}

}
