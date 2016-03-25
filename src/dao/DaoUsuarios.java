package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException; 
import dao.banco.conexao;


public class DaoUsuarios {
	Connection c = null;
	Statement stmt = null;


	public void mostrarUsuarios() {

		try {
			
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/snapcity", "postgres",
					"snap");
			c.setAutoCommit(false);
			System.out.println("conectado com sucesso");

			stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios;");
			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String senha = rs.getString("senha");
				String email = rs.getString("email");
				String datacriacao = rs.getString("datacriacao");
				System.out.println("ID = " + id);
				System.out.println("NOME = " + nome);
				System.out.println("SENHA = " + senha);
				System.out.println("E-MAIL = " + email);
				System.out.println("DATA DE CRIACAO = " + datacriacao);
				System.out.println();
			}
			rs.close();
			c.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operacao com mostarUsuarios com sucesso");
	}
	public void buscaUsuarios(int id) {  
		try {
			
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/snapcity", "postgres","snap");
			c.setAutoCommit(false);
			System.out.println("conectado com sucesso");

			stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios WHERE id = '"+ id + "';");
			while (rs.next()) {
				int iduser = rs.getInt("id");
				String nome = rs.getString("nome");
				String senha = rs.getString("senha");
				String email = rs.getString("email");
				String datacriacao = rs.getString("datacriacao");
				System.out.println("ID = " + iduser);
				System.out.println("NOME = " + nome);
				System.out.println("SENHA = " + senha);
				System.out.println("E-MAIL = " + email);
				System.out.println("DATA DE CRIACAO = " + datacriacao);
				System.out.println();
			}
			rs.close();
			c.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operacao com buscaUsuarios com sucesso");
	  
	   }  

	 public void excluiUsuario(int id) {    
	      try {  
	    	  Class.forName("org.postgresql.Driver");
				c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/snapcity", "postgres","snap");
				c.setAutoCommit(false);
				System.out.println("conectado com sucesso");
				 
				stmt = c.createStatement();
		         String sql = "DELETE FROM usuarios WHERE id = '" + id + "';";
		         stmt.executeUpdate(sql);
		         c.commit();
				
	      } catch ( Exception e ) {
	          System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	          System.exit(0);
	        }
	        System.out.println("Operacao com excluiUsuario com sucesso");
	      
	   } 
	 public void atualizaUsuarios(int id, String nome) {    
	      try {  
	    	  Class.forName("org.postgresql.Driver");
				c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/snapcity", "postgres","snap");
				c.setAutoCommit(false);
				System.out.println("conectado com sucesso");
				 
				stmt = c.createStatement();
		         String sql = "UPDATE usuarios set nome = '"+ nome +"' where id='"+ id +"';";
		         stmt.executeUpdate(sql);
		         c.commit();
				
	      } catch ( Exception e ) {
	          System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	          System.exit(0);
	        }
	        System.out.println("Operacao com atualizaUsuarios com sucesso");
	      
	   } 
	 public void insereUsuarios( String nome, String senha, String email ){
		 try{
			 
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/snapcity", "postgres","snap");
			c.setAutoCommit(false);
			System.out.println("conectado com sucesso");
			stmt = c.createStatement();
			//String sql = "INSERT INTO usuarios (nome,senha,email) values ('"+nome+"','"+senha+"','"+email+"');";
			String sql = "INSERT INTO usuarios (nome,senha,email) values ('nome','senha','email');";
			
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
       } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
       }
       System.out.println("Usuario foi criado com sucesso");
     }
	 
	
}