package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import controller.Conexao;
import model.Venda;

public class VendaDAO {

	// Carrega os produtos e preços do banco de dados
	public ArrayList<Venda> carregarProdutos() {
		ArrayList<Venda> listaVendas = new ArrayList<>();
		try (Connection con = Conexao.conexao();
				PreparedStatement stmt = con.prepareStatement("SELECT nome, preco FROM produtos");
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				String nome = rs.getString("nome");
				double preco = rs.getDouble("preco");
				listaVendas.add(new Venda(nome, 0, preco, 0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaVendas;
	}

	// Atualiza a quantidade do produto no banco após a venda
	public void atualizarEstoque(String nomeProduto, int quantidade) {
		try (Connection con = Conexao.conexao();
				PreparedStatement stmt = con
						.prepareStatement("UPDATE produtos SET quantidade = quantidade - ? WHERE nome = ?")) {

			stmt.setInt(1, quantidade);
			stmt.setString(2, nomeProduto);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
