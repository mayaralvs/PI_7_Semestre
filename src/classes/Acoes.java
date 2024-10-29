package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import conexao.Conexao;

public class Acoes {
	
	private int id;
	private String usuario;
	private String senha;
	 //p de parametro
	public Acoes(int id_p) {
		
		this.id = id_p;
		
	}
	
	public Acoes(String us, String se) {
		
		this.usuario = us;
		this.senha = se;
	}
	
	public Acoes(int id_p, String us, String se) {
		
		this.id = id_p;
		this.usuario = us;
		this.senha = se;
	}
	//metodo salvar
	public void salvar() {
		
		try {
			Connection con = Conexao.conexao();
			String sql = "INSERT INTO dados_senhas(usuario, senha) VALUE (?, ?)";
		
			PreparedStatement stmt = con.prepareStatement(sql);
		
			stmt.setString(1, usuario);
			stmt.setString(2, senha);
		
			stmt.execute();
		
			stmt.close();
			con.close();
		
		
			JOptionPane.showMessageDialog(null, "Dados Cadastrados!");
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	} //FIM metodo salvar
	
	public void atualizar() {
		
		
	}

}



