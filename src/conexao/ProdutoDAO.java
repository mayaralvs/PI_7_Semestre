package conexao;

import classes.Produto;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

	// Método para cadastrar um produto
	public void cadastrar(Produto produto) throws SQLException {
		String sql = "INSERT INTO produtos (nome, fabricante, lote, custo, categoria, publico, fornecedor, preco, quantidade) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection con = Conexao.conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getFabricante());
			stmt.setString(3, produto.getLote());
			stmt.setDouble(4, produto.getCusto());
			stmt.setString(5, produto.getCategoria());
			stmt.setString(6, produto.getPublico());
			stmt.setString(7, produto.getFornecedor());
			stmt.setDouble(8, produto.getPreco());
			stmt.setInt(9, produto.getQuantidade());
			stmt.executeUpdate();
		}
	}

	// Método para atualizar um produto
	public void atualizar(Produto produto) throws SQLException {
		String sql = "UPDATE produtos SET nome = ?, fabricante = ?, lote = ?, custo = ?, categoria = ?, publico = ?, fornecedor = ?, preco = ?, quantidade = ? WHERE id = ?";
		try (Connection con = Conexao.conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getFabricante());
			stmt.setString(3, produto.getLote());
			stmt.setDouble(4, produto.getCusto());
			stmt.setString(5, produto.getCategoria());
			stmt.setString(6, produto.getPublico());
			stmt.setString(7, produto.getFornecedor());
			stmt.setDouble(8, produto.getPreco());
			stmt.setInt(9, produto.getQuantidade());
			stmt.setInt(10, produto.getId());
			stmt.executeUpdate();
		}
	}

	// Método para excluir um produto
	public void excluir(int id) throws SQLException {
		String sql = "DELETE FROM produtos WHERE id = ?";
		try (Connection con = Conexao.conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}

	// Método para listar todos os produtos
	public List<Produto> listar() throws SQLException {
		List<Produto> produtos = new ArrayList<>();
		String sql = "SELECT * FROM produtos";
		try (Connection con = Conexao.conexao();
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Produto produto = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getString("fabricante"),
						rs.getString("lote"), rs.getDouble("custo"), rs.getString("categoria"), rs.getString("publico"),
						rs.getString("fornecedor"), rs.getDouble("preco"), rs.getInt("quantidade"));
				produtos.add(produto);
			}
		}
		return produtos;
	}

	// Método para buscar um produto pelo ID
	public Produto buscarPorId(int id) throws SQLException {
		Produto produto = null;
		String sql = "SELECT * FROM produtos WHERE id = ?";
		try (Connection con = Conexao.conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					produto = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getString("fabricante"),
							rs.getString("lote"), rs.getDouble("custo"), rs.getString("categoria"),
							rs.getString("publico"), rs.getString("fornecedor"), rs.getDouble("preco"),
							rs.getInt("quantidade"));
				}
			}
		}
		return produto;
	}
}
