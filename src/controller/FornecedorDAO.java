package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Fornecedor;

public class FornecedorDAO {
	private Connection connection;

	// Construtor que recebe a conexão com o banco
	public FornecedorDAO(Connection connection) {
		this.connection = connection;
	}

	// Método para inserir fornecedor
	public boolean inserirFornecedor(Fornecedor fornecedor) {
		String sql = "INSERT INTO fornecedores (nome, telefone, email, cnpj, logradouro, numero, cidade, uf, cep, complemento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			preencherStatement(fornecedor, stmt);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Método para atualizar fornecedor
	public boolean atualizarFornecedor(Fornecedor fornecedor) {
		String sql = "UPDATE fornecedores SET nome=?, telefone=?, email=?, cnpj=?, logradouro=?, numero=?, cidade=?, uf=?, cep=?, complemento=? WHERE id=?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			preencherStatement(fornecedor, stmt);
			stmt.setString(11, fornecedor.getId());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Método para deletar fornecedor pelo ID
	public boolean deletarFornecedor(String id) {
		String sql = "DELETE FROM fornecedores WHERE id=?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, id);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Método para listar todos os fornecedores
	public List<Fornecedor> listarFornecedores() {
		List<Fornecedor> lista = new ArrayList<>();
		String sql = "SELECT * FROM fornecedores";

		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Fornecedor fornecedor = new Fornecedor(rs.getString("id"), rs.getString("nome"),
						rs.getString("telefone"), rs.getString("email"), rs.getString("cnpj"),
						rs.getString("logradouro"), rs.getString("numero"), rs.getString("cidade"), rs.getString("uf"),
						rs.getString("cep"), rs.getString("complemento"));
				lista.add(fornecedor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	// Método para preencher o PreparedStatement com os dados do fornecedor
	private void preencherStatement(Fornecedor fornecedor, PreparedStatement stmt) throws SQLException {
		stmt.setString(1, fornecedor.getId());
		stmt.setString(2, fornecedor.getNome());
		stmt.setString(3, fornecedor.getTelefone());
		stmt.setString(4, fornecedor.getEmail());
		stmt.setString(5, fornecedor.getCnpj());
		stmt.setString(6, fornecedor.getLogradouro());
		stmt.setString(7, fornecedor.getNumero());
		stmt.setString(8, fornecedor.getCidade());
		stmt.setString(9, fornecedor.getUf());
		stmt.setString(10, fornecedor.getCep());
		stmt.setString(11, fornecedor.getComplemento());
	}
//up
}
