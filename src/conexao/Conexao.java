package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {

	public static Connection conexao() throws SQLException {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			return DriverManager.getConnection("jdbc:mysql://localhost/db_pet", "root", "admin");

		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getException());
		}
	}
}
