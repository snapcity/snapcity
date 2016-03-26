package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import dao.banco.conexao;

public class DaoEventos {

	Connection c = null;
	Statement stmt = null;
	
	// método de conexao
	public void conecta() {
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
	
	// mostra todos evento registrados
	public void mostrarEventos() {

		try {
			conecta();
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

	//Busca eventos por descricao
	public void buscaEventos(String eventos) {

		try {
			conecta();
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from eventos where descricao like  '%"+ eventos + "%';");
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
	
	//Método para excluir um evento pelo id
	public void excluiEventos(int id) {    
	      try {  
	    	  conecta(); 
	    	  stmt = c.createStatement();
		      String sql = "DELETE FROM eventos WHERE eventos.\"idEventos\" = '" + id + "';";
		      stmt.executeUpdate(sql);
		      c.commit();
		      c.close();
		      stmt.close();
				
	      }catch ( Exception e ) {
	          System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	          System.exit(0);
	        }
	        System.out.println("Operacao com exluirEventos com sucesso");
	      
	   }
	
	//Atualiza eventos, datahora é atualizado por uma var timestamp que pega data e hora atual.
	public void atualizaEventos(int id,String foto,String descricao,Float latitude,Float longitude,String tags) {    
	      try {  
	    	  conecta();
	    	  Timestamp datahora = new Timestamp(System.currentTimeMillis());
	    	  stmt = c.createStatement();
		      String sql = "UPDATE eventos set foto = '"+ foto +"',descricao = '"+ descricao +"',latitude = '"+ latitude +"',longitude = '"+ longitude +"', tags = '"+ tags +"',datahora = '"+ datahora +"' where idEventos ='"+ id +"';";
		      stmt.executeUpdate(sql);
		      c.commit();
		      c.close();
		      stmt.close();
				
	      } catch ( Exception e ) {
	          System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	          System.exit(0);
	        }
	        System.out.println("Operacao com atualizaEventos com sucesso");
	      
	   } 
	
	//Insere eventos e datahota insere data e hora atual timestamp
	public void insereEventos(String foto,String descricao,double latitude,double longitude,int id_usuario,String tags){
		 try{
			conecta();
			Timestamp datahora = new Timestamp(System.currentTimeMillis()); 
			stmt = c.createStatement();
			String sql = "INSERT INTO eventos (foto,descricao,latitude,longitude,id_usuario,tags,datahora)"
			+" values ('"+foto+"','"+descricao+"','"+latitude+"','"+longitude+"','"+id_usuario+"','"+tags+"','"+datahora+"');";
			 
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
			
      } catch ( Exception e ) {
        System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        System.exit(0);
      }
      System.out.println("Usuario foi criado com sucesso");
    }
}
