package conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Funcionario;
import conexao.Conexao;

public class FuncionarioDAO {

	// Inserir funcionário
	public boolean salvar(Funcionario funcionario) {
		String sql = "INSERT INTO dados_senhas (usuario, senha) VALUES (?, ?)";
		try (Connection con = Conexao.conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, funcionario.getUsuario());
			stmt.setString(2, funcionario.getSenha());
			stmt.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Atualizar funcionário
	public boolean atualizar(Funcionario funcionario) {
		String sql = "UPDATE dados_senhas SET usuario = ?, senha = ? WHERE id = ?";
		try (Connection con = Conexao.conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, funcionario.getUsuario());
			stmt.setString(2, funcionario.getSenha());
			stmt.setInt(3, funcionario.getId());
			stmt.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Excluir funcionário
	public boolean excluir(int id) {
		String sql = "DELETE FROM dados_senhas WHERE id = ?";
		try (Connection con = Conexao.conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, id);
			stmt.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Buscar funcionário por ID ou nome
	public Funcionario buscarPorIdOuNome(String termo) {
		String sql = "SELECT id, usuario, senha FROM dados_senhas WHERE id LIKE ? OR usuario LIKE ?";
		try (Connection con = Conexao.conexao(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, "%" + termo + "%");
			stmt.setString(2, "%" + termo + "%");

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Funcionario(rs.getInt("id"), rs.getString("usuario"), rs.getString("senha"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Listar todos os funcionários
	public List<Funcionario> listarTodos() {
		List<Funcionario> lista = new ArrayList<>();
		String sql = "SELECT id, usuario, senha FROM dados_senhas";
		try (Connection con = Conexao.conexao();
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Funcionario funcionario = new Funcionario(rs.getInt("id"), rs.getString("usuario"),
						rs.getString("senha"));
				lista.add(funcionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
}
