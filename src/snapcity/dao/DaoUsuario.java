package snapcity.dao;
import snapcity.model.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import org.json.JSONException;
import org.json.JSONObject;

import snapcity.dao.banco.ConectionFactory;

public class DaoUsuario {
	Connection c = null;
	Statement stmt = null;

	// mostra todos os usuarios
	public Usuario mostrarUsuario() {
		
		Usuario result = new Usuario();
		
		try {
			c = ConectionFactory.getConnection();
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios;");
			Usuario temp = new Usuario();
			while (rs.next()) {
				temp.setId(rs.getInt("id"));
				temp.setNome(rs.getString("nome"));
				temp.setSenha(rs.getString("senha"));
				temp.setEmail(rs.getString("email"));
				temp.setDatacriacao(rs.getString("datacriacao"));
				
			}
			rs.close();
			c.close();
			stmt.close();
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operacao com mostarUsuarios com sucesso");
		return result;
	}
	
	
	// Método que busca eventos relacioandos com o usuario
	public void buscaUsuariosEvento(int id) {  
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
	public Usuario buscaUsuario(int id,Usuario usuario) { 
		
		Usuario result = new Usuario();
		
		try {
			c = ConectionFactory.getConnection();
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios WHERE id = '"+ id + "';");
			Usuario temp = new Usuario();
			while (rs.next()) {
				temp.setId(rs.getInt("id"));
				temp.setNome(rs.getString("nome"));
				temp.setSenha(rs.getString("senha"));
				temp.setEmail(rs.getString("email"));
				temp.setDatacriacao(rs.getString("datacriacao"));
			}
			rs.close();
			c.close();
			stmt.close();
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operacao com buscaUsuarios com sucesso");
		return result;
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
	 public void atualizaUsuario(int id, Usuario usuario) {    
	      try {  
	    	  c = ConectionFactory.getConnection();
	    	  c.setAutoCommit(false);
	    	  Timestamp datacriacao = new Timestamp(System.currentTimeMillis());
	    	  stmt = c.createStatement();
		      String sql = "UPDATE usuarios set nome = '"+ usuario.getNome() +"',senha ='"+ usuario.getSenha() +"', email = '"+ usuario.getEmail() +"', datacriacao = '"+ datacriacao +"' where id='"+ id +"';";
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
	 
	 //Insere novo usu�rio, data de criacao pega valor atual da máquina em timestamp
	 public void insereUsuario( Usuario usuario ){
		 try{
			 c = ConectionFactory.getConnection();
			 c.setAutoCommit(false);
			Timestamp datacriacao = new Timestamp(System.currentTimeMillis()); 
			stmt = c.createStatement();
					String sql = "INSERT INTO usuarios (nome,senha,email,datacriacao) values ('"+usuario.getNome()+"','"+usuario.getSenha()+"','"+usuario.getEmail()+"','"+datacriacao+"');";
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
	 
	 public  String toJson (String nome, String senha, String email){
			JSONObject usuario = new JSONObject();
			
			Timestamp datacriacao = new Timestamp(System.currentTimeMillis());
			
			usuario.put("nome", nome);
			usuario.put("senha", senha);
			usuario.put("email", email);
			usuario.put("datacriacao", datacriacao);
			
			String usuarioJson = usuario.toString();
			
			
			return usuarioJson;
	 }
	 
	 public static Usuario fromJSON(String jsonString){
		 
		 JSONObject obj = new JSONObject(jsonString);
		 
		 String nome = obj.getString ("nome");
		 String senha = obj.getString ("senha");
		 String email = obj.getString ("email");
		 
		 Usuario usuario = new Usuario();
		 
		 usuario.setNome(nome);
		 usuario.setSenha(senha);
		 usuario.setEmail(email);
		 
		return usuario;
	 }	 
	 
	 public static Usuario alteraJSON(String jsonString){
		 
		 JSONObject obj = new JSONObject(jsonString);
		 
		 String json_string= obj.toString();
		 		 
		 json_string = obj.toString();
		 
		 String nome = obj.getString("nome");
		 String senha = obj.getString("senha");
		 String email = obj.getString("email");
		 
		 
		 Usuario usuario = new Usuario();
		 
		 
		usuario.setNome(nome);
		usuario.setSenha(senha);
		usuario.setEmail(email);
		
		
		return usuario;		 
		 	 
	 }
}