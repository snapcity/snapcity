package snapcity.dao;
import snapcity.model.Evento;
import snapcity.model.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import snapcity.dao.banco.ConexaoFactory;

/**
 * classe DaoUsuario faz a conexão dos metodos solicitados com o banco de dados
 * @author Andersen Silva e Marcelo
 *
 */
public class DaoUsuario {

	// TODO remover atributos e coloca-los como variavel de metodo
	Connection c = null;
	Statement stmt = null;


	/**
	 * Mostra todos os Usuarios cadastrados
	 * @return Lista de {@link mostrarUsuarios} cadastros.
	 */
	public List<Usuario> mostrarUsuario() {
		// TODO renomear para buscarUsuarios
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			c = ConexaoFactory.getConnection();
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


	/**
	 * Método busca todos os eventos que um usuario esteja cadastrado
	 * @param usuario
	 * @return Lista de {@link buscaUsuariosEventos} cadastros.
	 */
	public List<Evento> buscaUsuariosEvento(int id) {
		// TODO renomear buscarEventosDoUsuario
		ArrayList<Evento> eventos = new ArrayList<Evento>();
		try {
			c = ConexaoFactory.getConnection();
			stmt = c.createStatement();

			DaoEvento daoEvento = new DaoEvento();
			ResultSet rs = stmt.executeQuery("select usuarios.nome, eventos.* from eventos,usuarios where usuarios.id = '"+ id + "' and eventos.id_usuario ='"+ id +"';");
			while (rs.next()) {
				int idEvento = rs.getInt("idEventos");
				Evento event = daoEvento.buscaEvento(idEvento);
				if (event != null)
					eventos.add(event);
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

	/**
	 * Busca todos usuario pelo seu ID de usuario
	 * @return Lista de {@link buscaUsuarios} cadastros.
	 * @param id
	 */
	public Usuario buscaUsuario(int id) {  
		Usuario usuarios = new Usuario();
		try {
			c = ConexaoFactory.getConnection();
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
		return usuarios;
	}  

	/**
	 * Exclui o usuario através de seu ID
	 * @param id
	 * @return Lista de {@link excluiUsuarios} cadastros.
	 */
	public void excluiUsuario(Integer id) {    
		try {   
			c = ConexaoFactory.getConnection();
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String sql = "DELETE FROM usuarios WHERE id = '" +id + "';";
			stmt.executeUpdate(sql);
			c.commit();
			c.close();
			stmt.close();

		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		}
		System.out.println("Operacao com excluiUsuario com sucesso");
	} 

	/**
	 * Faz atualização de um usuario cadastrado
	 * @param usuario
	 * @return Lista de {@link atualizaUsuarios} cadastros.
	 */
	public void atualizaUsuario(Usuario usuario) {    
		try {  
			c = ConexaoFactory.getConnection();
			c.setAutoCommit(false);
			stmt = c.createStatement();
			Timestamp datacriacao = new Timestamp(System.currentTimeMillis());
			String sql = "UPDATE usuarios set nome = '"+ usuario.getNome() +
					"',senha ='"+ usuario.getSenha() +
					"', email = '"+ usuario.getEmail() +
					"', datacriacao = '"+datacriacao+
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
	public Usuario insereUsuario(Usuario usuario){
		try{
			c = ConexaoFactory.getConnection();
			c.setAutoCommit(false); 
			stmt = c.createStatement();
			Timestamp datacriacao = new Timestamp(System.currentTimeMillis()); 
			String sql = "INSERT INTO usuarios (nome,senha,email,datacriacao) values ('"
					+ usuario.getNome()+"','"+usuario.getSenha()+"','"+usuario.getEmail()+"','"+datacriacao+"');";
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
			System.out.println("Usuario foi criado com sucesso");
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+" Erro: "+ e.getMessage() );		
			System.exit(0);
		}
		return usuario;
	}

	public static JSONObject toJson (Usuario usuario) {
		JSONObject obj = new JSONObject();
		obj.put("nome", usuario.getNome());
		obj.put("senha", usuario.getSenha());
		obj.put("email", usuario.getEmail());
		obj.put("datacriacao", usuario.getDatacriacao());
		obj.put("id", usuario.getId());
		return obj;
	}


	public static Usuario fromJSON(String jsonString){
		JSONObject obj = new JSONObject(jsonString);
		Usuario usuario = new Usuario();

		if (obj.has("id")){
			Integer id = obj.getInt("id");
			usuario.setId(id);
		}

		String nome = obj.getString ("nome");
		String senha = obj.getString ("senha");
		String email = obj.getString ("email");

		usuario.setNome(nome);
		usuario.setSenha(senha);
		usuario.setEmail(email);

		return usuario;
	}	

}