package dao.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {
	public void conecta() {
		Connection c = null;
		
		
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/snapcity", "postgres","snap");
			c.setAutoCommit(false);
			System.out.println("conectado com sucesso");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}
	

}
