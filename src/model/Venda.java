package model;

public class Venda {
	private String produto;
	private int quantidade;
	private double preco;
	private double total;

	// Construtor com parâmetros
	public Venda(String produto, int quantidade, double preco, double total) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.preco = preco;
		this.total = total;
	}

	// Getters e Setters
	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
