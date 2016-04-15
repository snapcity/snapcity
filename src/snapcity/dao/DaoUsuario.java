package snapcity.dao;
import snapcity.model.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import snapcity.dao.banco.ConectionFactory;

public class DaoUsuario {
	Connection c = null;
	Statement stmt = null;

	// mostra todos os usuarios
	public Usuario mostrarUsuario() {
		
		Usuario usuario = new Usuario();
		
		try {
			c = ConectionFactory.getConnection();
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios;");
			while (rs.next()) {
				int idUser = rs.getInt("id");
				String nome = rs.getString("nome");
				String senha = rs.getString("senha");
				String email = rs.getString("email");
				String datacriacao = rs.getString("datacriacao");
		
				usuario.setId(idUser);
				usuario.setNome(nome);
				usuario.setEmail(email);
				usuario.setSenha(senha);
				usuario.setDatacriacao(datacriacao);
				
			}
			rs.close();
			c.close();
			stmt.close();
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operacao com mostarUsuarios com sucesso");
		return usuario;
		
	}
	
	
	// M√©todo que busca eventos relacioandos com o usuario
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
	// m√©todo que busca usuarios pelo id
	public Usuario buscaUsuario(int id) { 
		Usuario usuario = new Usuario();
		
		try {
			c = ConectionFactory.getConnection();
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios WHERE id = '"+ id + "';");
			while (rs.next()) {
				int idUser = rs.getInt("id");
				String nome = rs.getString("nome");
				String senha = rs.getString("senha");
				String email = rs.getString("email");
				String datacriacao = rs.getString("datacriacao");
				
				usuario.setId(idUser);
				usuario.setNome(nome);
				usuario.setEmail(email);
				usuario.setSenha(senha);
				usuario.setDatacriacao(datacriacao);
			}
			rs.close();
			c.close();
			stmt.close();
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operacao com buscaUsuarios com sucesso");
		return usuario;
	   }  
	 
	//Exclui usu√°rio identificado pelo id
	 public Usuario excluiUsuario(int id) {    
		 Usuario usuario = new Usuario();
	      try {  
	    	  c = ConectionFactory.getConnection();
	    	  stmt = c.createStatement();
	    	  c.setAutoCommit(false);
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
	        return usuario;
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
	 
	 //Insere novo usu√rio, data de criacao pega valor atual da m√°quina em timestamp
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
	 
	 public static  String toJson (Usuario usuario){
		 
			JSONObject user = new JSONObject(usuario);
			
			String nome = user.getString("nome");
			String senha = user.getString("senha");
			String email = user.getString("email");
			int idUser = user.getInt("id");
			
			Timestamp datacriacao = new Timestamp(System.currentTimeMillis());
			
			user.put("nome", nome);
			user.put("senha", senha);
			user.put("email", email);
			user.put("datacriacao", datacriacao);
			
			
			
			
			return user.toString();
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
	 
	 public static String toJsonArray (Usuario usuario){
		 try{
		 JSONObject user = new JSONObject(usuario);
		 
		 JSONArray usuarios = user.getJSONArray("user1");
		 
		 for (int i = 0; i <  user.length(); i++){
		 String nome = user.getString("nome");
		 String senha = user.getString("senha");
		 String email = user.getString("email");
		 int idUser = user.getInt("id");
		 
		 //user1.put("nome");
		 usuarios.put(user.getString(nome));
		 usuarios.put(user.getString(senha));
		 usuarios.put(user.getString(email));
		
		 System.out.println(nome);
		 }
		 } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+" Erro: "+ e.getMessage() );
	         System.exit(0);
	       }
	       System.out.println("Usuario foi criado com sucesso");
	       return usuario.toString();
		 }
	 
//	 public static String toJsonArray (Usuario usuario){
//		 try {
//		 JSONObject user = new JSONObject(usuario);
//		                
//		 JSONArray array = new JSONArray();
//		  
//		 /*                  * CriaÁ„o do Objeto JSONObject                  */   
//		 
//		 String nome = user.getString("nome");
//		 String senha = user.getString("senha");
//		 String email = user.getString("email");
//		 int idUser = user.getInt("id");
//		  
//		 Usuario json = new Usuario(); 
//		  
//		 json.setNome("nome" + nome); 
//		 json.setSenha("senha" + senha); 
//		 json.setEmail("email" + email); 
//		  
//		 JSONObject jsonOne = new JSONObject();
//		  
//		 jsonOne.put("nome", json.getNome());
//		 jsonOne.put("senha", json.getSenha());
//		 jsonOne.put("email", json.getEmail());
//		  
//		 Usuario pTwo = new Usuario();
//		  
//		 pTwo.setNome("nome" + nome); 
//		 pTwo.setSenha("senha" + senha); 
//		 pTwo.setEmail("email" + email);
//		  
//		 JSONObject jsonTwo = new JSONObject();
//		  
//		 jsonTwo.put("nome", pTwo.getNome()); 
//		 jsonTwo.put("senha", pTwo.getSenha());
//		 jsonTwo.put("email", pTwo.getEmail());
//		  
//		 array.put(jsonOne);
//		 array.put(jsonTwo);
//		  
//		 System.out.println("JSONArray: " + jsonOne);
//		 }
//		 catch (JSONException e) {
//		 e.printStackTrace();
//		 } 
//		 return usuario.toString();
//		}
	 
}
		 