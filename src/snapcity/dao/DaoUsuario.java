package snapcity.dao;
import snapcity.model.Evento;
import snapcity.model.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.json.JSONObject;

import snapcity.dao.banco.ConectionFactory;

public class DaoUsuario {
	Connection c = null;
	Statement stmt = null;

	// mostra todos os usuarios
	/**
	 * Mostra todos os Usuarios cadastrados
	 * @return Lista de {@link mostrarUsuarios} cadastros.
	 */
	public List<Usuario> mostrarUsuario() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			c = ConectionFactory.getConnection();
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios;");
			
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setEmail(rs.getString("email"));
				usuario.setDatacriacao(rs.getString("datacriacao"));
				usuarios.add(usuario);
			}
			rs.close();
			c.close();
			stmt.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		System.out.println("Operacao com mostarUsuarios com sucesso");
		return usuarios;
	}

	// Método que busca eventos relacioandos com o usuario
	/**
	 * Método busca todos os eventos que um usuario esteja cadastrado
	 * @param usuario
	 * @return Lista de {@link buscaUsuariosEventos} cadastros.
	 */
	public List<Evento> buscaUsuariosEvento(Usuario usuario) {
		ArrayList<Evento> eventos = new ArrayList<Evento>();
		try {
			c = ConectionFactory.getConnection();
			stmt = c.createStatement();

			DaoEvento daoEvento = new DaoEvento();
			ResultSet rs = stmt.executeQuery("select usuarios.nome, eventos.* from eventos,usuarios where  '"+ usuario.getId() + "' = usuarios.id;");
			while (rs.next()) {
				int idEvento = rs.getInt("idEventos");
				//Evento evento = daoEvento.buscaEvento(idEvento);
				//if (evento != null)
					//eventos.add(evento);
			}
			rs.close();
			c.close();
			stmt.close();
			System.out.println("Operacao com buscaUsuarios com sucesso");	

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

		return eventos;
	}
	// método que busca usuarios pelo id
	/**
	 * Busca todos usuario pelo seu ID de usuario
	 * @return Lista de {@link buscaUsuarios} cadastros.
	 * @param id
	 */
	public String buscaUsuario(int id) {  
		Usuario usuarios = new Usuario();
		try {
			c = ConectionFactory.getConnection();
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios WHERE id = '"+ id + "';");
			while (rs.next()) {
				usuarios.setId(rs.getInt("id"));
				usuarios.setNome(rs.getString("nome"));
				usuarios.setSenha(rs.getString("senha"));
				usuarios.setEmail(rs.getString("email"));
				usuarios.setDatacriacao(rs.getString("datacriacao"));
			}
			rs.close();
			c.close();
			stmt.close();
			System.out.println("Operacao com buscaUsuarios com sucesso");

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return usuarios.toString();

	}  

	//Exclui usuário identificado pelo id
	/**
	 * Exclui o usuario através de seu ID
	 * @param usuario
	 * @return Lista de {@link excluiUsuarios} cadastros.
	 */
	public Usuario excluiUsuario(Usuario usuario) {    
		try {  
			c = ConectionFactory.getConnection();
			stmt = c.createStatement();
			String sql = "DELETE FROM usuarios WHERE id = '" + usuario.getId() + "';";
			stmt.executeUpdate(sql);
			c.commit();
			c.close();
			stmt.close();

		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		}
		System.out.println("Operacao com excluiUsuario com sucesso");
		return usuario;
	} 

	//Atualiza usuarios
	/**
	 * Faz atualização de um usuario cadastrado
	 * @param usuario
	 * @return Lista de {@link atualizaUsuarios} cadastros.
	 */
	public void atualizaUsuario(Usuario usuario) {    
		try {  
			c = ConectionFactory.getConnection();
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String sql = "UPDATE usuarios set nome = '"+ usuario.getNome() +
					"',senha ='"+ usuario.getSenha() +
					"', email = '"+ usuario.getId() +
					"' where id='"+ usuario.getId() +"';";
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

	
	/**
	 * Método para cadastrar um novo Usuário
	 * @param usuario
	 * @return Lista de {@link insereUsuarios} cadastros.
	 */
	@Path ("/insereUsuario")
	public void insereUsuario( Usuario usuario ){
		try{
 
			c.setAutoCommit(false); 
			stmt = c.createStatement();
			String sql = "INSERT INTO usuarios (nome,senha,email,datacriacao) values ('"
			+ usuario.getNome()+"','"+usuario.getSenha()+"','"+usuario.getEmail()+"','"+usuario.getDatacriacao()+"');";
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
			System.out.println("Usuario foi criado com sucesso");
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+" Erro: "+ e.getMessage() );			
		}
	}

	public String toJson (Usuario usuario){
		JSONObject obj = new JSONObject();
		obj.put("nome", usuario.getNome());
		obj.put("senha", usuario.getSenha());
		obj.put("email", usuario.getEmail());
		obj.put("datacriacao", usuario.getDatacriacao());
		obj.put("id", usuario.getId());
		return obj.toString();
	}

	public static Usuario fromJSON(String jsonString){
		JSONObject obj = new JSONObject(jsonString);
		String nome = obj.getString ("nome");
		String senha = obj.getString ("senha");
		String email = obj.getString ("email");
		String datacriacao = obj.getString("datacriacao");
		int id = obj.getInt("id");

		Usuario usuario = new Usuario();
		usuario.setDatacriacao(nome);
		usuario.setSenha(senha);
		usuario.setEmail(email);
		usuario.setDatacriacao(datacriacao);
		usuario.setId(id);

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
	
	public static String toJsonArray (List<Usuario> usuario){
		JSONObject obj = new JSONObject();
		obj.put("nome", ((Usuario) usuario).getNome());
		obj.put("senha", ((Usuario) usuario).getSenha());
		obj.put("email", ((Usuario) usuario).getEmail());
		obj.put("datacriacao", ((Usuario) usuario).getDatacriacao());
		//obj.put("id", ((Evento) usuario).getId());
		return obj.toString();
	}
}