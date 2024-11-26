package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.Conexao;
import model.Cliente;

public class ClienteDAO {

	public void cadastrar(Cliente cliente) throws SQLException {
		String sql = "INSERT INTO clientes(nome, telefone, email, cpf, logradouro, numero, cidade, estado, cep, complemento) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection con = Conexao.conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getTelefone());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getCpf());
			stmt.setString(5, cliente.getLogradouro());
			stmt.setString(6, cliente.getNumero());
			stmt.setString(7, cliente.getCidade());
			stmt.setString(8, cliente.getEstado());
			stmt.setString(9, cliente.getCep());
			stmt.setString(10, cliente.getComplemento());
			stmt.execute();
		}
	}

	public void atualizar(Cliente cliente) throws SQLException {
		String sql = "UPDATE clientes SET nome = ?, telefone = ?, email = ?, cpf = ?, logradouro = ?, numero = ?, "
				+ "cidade = ?, estado = ?, cep = ?, complemento = ? WHERE id = ?";
		try (Connection con = Conexao.conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getTelefone());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getCpf());
			stmt.setString(5, cliente.getLogradouro());
			stmt.setString(6, cliente.getNumero());
			stmt.setString(7, cliente.getCidade());
			stmt.setString(8, cliente.getEstado());
			stmt.setString(9, cliente.getCep());
			stmt.setString(10, cliente.getComplemento());
			stmt.setInt(11, cliente.getId());
			stmt.execute();
		}
	}

	public void excluir(int id) throws SQLException {
		String sql = "DELETE FROM clientes WHERE id = ?";
		try (Connection con = Conexao.conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}

	public Cliente buscarPorId(int id) throws SQLException {
		String sql = "SELECT * FROM clientes WHERE id = ?";
		try (Connection con = Conexao.conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("telefone"),
						rs.getString("email"), rs.getString("cpf"), rs.getString("logradouro"), rs.getString("numero"),
						rs.getString("cidade"), rs.getString("estado"), rs.getString("cep"),
						rs.getString("complemento"), rs.getString("dt_cadastro") // Certifique-se de que o campo exista
																					// na tabela
				);
			}
		}
		return null; // Retorna null se não encontrar
	}

	// Método para verificar se o email já está cadastrado
	public boolean emailJaCadastrado(String email) throws SQLException {
		String sql = "SELECT * FROM clientes WHERE email = ?";
		try (Connection con = Conexao.conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			return rs.next(); // Retorna true se já existir
		}
	}
}
