package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DaoEventos {
	
	Connection c = null;
	Statement stmt = null;


	public void mostrarEventos() {

		try {
			
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/snapcity", "postgres",
					"snap");
			c.setAutoCommit(false);
			System.out.println("conectado com sucesso");

			stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM eventos;");
			while (rs.next()) {
				int id = rs.getInt("idEventos");
				String foto = rs.getString("foto");
				String descricao = rs.getString("descricao");
				String tags = rs.getString("tags");
				int latitude = rs.getInt("latitude");
				int longitude = rs.getInt("longitude");
				String datahora = rs.getString("datahora");
				int id_usuario = rs.getInt("id_usuario");
				System.out.println("ID = " + id);
				System.out.println("FOTO = " + foto);
				System.out.println("DESCRICAO = " + descricao);
				System.out.println("TAGS = " + tags);
				System.out.println("LATITUDE = " + latitude);
				System.out.println("LONGITUDE = " + longitude);
				System.out.println("DATA DE CRIACAO = " + datahora);
				System.out.println("Usuario = " + id_usuario);
				System.out.println();
			}
			rs.close();
			c.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operacao com mostraEventos com sucesso");
	}
	



public void buscaEventos(String eventos) {

	try {
		
		Class.forName("org.postgresql.Driver");
		c = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/snapcity", "postgres",
				"snap");
		c.setAutoCommit(false);
		System.out.println("conectado com sucesso");

		stmt = c.createStatement();

		ResultSet rs = stmt.executeQuery("select * from eventos where descricao like  '%"+eventos+"%';");
		while (rs.next()) {
			int id = rs.getInt("idEventos");
			String foto = rs.getString("foto");
			String descricao = rs.getString("descricao");
			String tags = rs.getString("tags");
			int latitude = rs.getInt("latitude");
			int longitude = rs.getInt("longitude");
			String datahora = rs.getString("datahora");
			int id_usuario = rs.getInt("id_usuario");
			System.out.println("ID = " + id);
			System.out.println("FOTO = " + foto);
			System.out.println("DESCRICAO = " + descricao);
			System.out.println("TAGS = " + tags);
			System.out.println("LATITUDE = " + latitude);
			System.out.println("LONGITUDE = " + longitude);
			System.out.println("DATA DE CRIACAO = " + datahora);
			System.out.println("Usuario = " + id_usuario);
			System.out.println();
		}
		rs.close();
		c.close();
		stmt.close();
	} catch (Exception e) {
		System.err.println(e.getClass().getName() + ": " + e.getMessage());
		System.exit(0);
	}
	System.out.println("Operacao com buscaEventos com sucesso");
}


}
