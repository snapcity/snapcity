package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;  
import dao.banco.ConectionFactory;

public class DaoUsuario {
	Connection c = null;
	Statement stmt = null;

	// mostra todos os usuarios
	public void mostrarUsuarios() {

		try {
			c = ConectionFactory.getConnection();
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
	
	// Método que busca eventos relacioandos com o usuario
	public void buscaUsuariosEventos(int id) {  
		try {
			c = ConectionFactory.getConnection();
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select usuarios.nome, eventos.* from eventos,usuarios where  '"+ id + "' = usuarios.id;");
			while (rs.next()) {
				String nome = rs.getString("nome");
				int idEventos = rs.getInt("idEventos");
				String foto = rs.getString("foto");
				String descricao = rs.getString("descricao");
				String tags = rs.getString("tags");
				int latitude = rs.getInt("latitude");
				int longitude = rs.getInt("longitude");
				String datahora = rs.getString("datahora");
				int id_usuario = rs.getInt("id_usuario");
				
				System.out.println("ID = " + idEventos);
				System.out.println("FOTO = " + foto);
				System.out.println("DESCRICAO = " + descricao);
				System.out.println("TAGS = " + tags);
				System.out.println("LATITUDE = " + latitude);
				System.out.println("LONGITUDE = " + longitude);
				System.out.println("DATA DE CRIACAO = " + datahora);
				System.out.println("Usuario = " + nome);
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
	// método que busca usuarios pelo id
	public void buscaUsuarios(int id) {  
		try {
			c = ConectionFactory.getConnection();
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
	 
	//Exclui usuário identificado pelo id
	 public void excluiUsuario(int id) {    
	      try {  
	    	  c = ConectionFactory.getConnection();
	    	  stmt = c.createStatement();
		      String sql = "DELETE FROM usuarios WHERE id = '" + id + "';";
		      stmt.executeUpdate(sql);
		      c.commit();
		      c.close();
		      stmt.close();
				
	      } catch ( Exception e ) {
	          System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	          System.exit(0);
	        }
	        System.out.println("Operacao com excluiUsuario com sucesso");
	      
	   } 
	 
	 //Atualiza usuarios
	 public void atualizaUsuarios(int id, String nome, String senha, String email) {    
	      try {  
	    	  c = ConectionFactory.getConnection();
	    	  c.setAutoCommit(false);
	    	  Timestamp datacriacao = new Timestamp(System.currentTimeMillis());
	    	  stmt = c.createStatement();
		      String sql = "UPDATE usuarios set nome = '"+ nome +"',senha ='"+ senha +"', email = '"+ email +"' where id='"+ id +"';";
		      stmt.executeUpdate(sql);
		      c.commit();
		      c.close();
		      stmt.close();
				
	      } catch ( Exception e ) {
	          System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	          System.exit(0);
	        }
	        System.out.println("Operacao com atualizaUsuarios com sucesso");
	      
	   } 
	 
	 //Inser novos usuário, data de criacao pega valor atual da máquina em timestamp
	 public void insereUsuarios( String nome, String senha, String email ){
		 try{
			 c = ConectionFactory.getConnection();
			 c.setAutoCommit(false);
			Timestamp datacriacao = new Timestamp(System.currentTimeMillis()); 
			stmt = c.createStatement();
			//System.out.println("INSERT INTO usuarios (nome,senha,email,datacriacao) values ('"+nome+"','"+senha+"','"+email+"','"+datacriacao+"')");
			String sql = "INSERT INTO usuarios (nome,senha,email,datacriacao) values ('"+nome+"','"+senha+"','"+email+"','"+datacriacao+"');";
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
			
       } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+" Erro: "+ e.getMessage() );
         System.exit(0);
       }
       System.out.println("Usuario foi criado com sucesso");
     }
	 
	
	 
}