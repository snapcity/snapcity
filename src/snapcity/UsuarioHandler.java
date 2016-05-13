package snapcity;
import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import snapcity.dao.DaoEvento;
import snapcity.dao.DaoUsuario;
import snapcity.model.Evento;
import snapcity.model.Usuario;

@Path("/usuarios")
public class UsuarioHandler {
	
		DaoUsuario dao = new DaoUsuario();

	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getUsuario(Usuario usuario) {
		List<Usuario> usuarios = dao.mostrarUsuario();
		JSONArray array = new JSONArray();
		for (Usuario user : usuarios)
			array.put(DaoUsuario.toJson(user));
		return Response.ok().entity(array.toString()).build();
				
	}
	
	
	@GET
	@Path("/evento/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response BuscaEventoUsuario(@PathParam("id") Integer id) {
		List<Evento> evento = dao.buscaUsuariosEvento(id);
		JSONArray array = new JSONArray();
		for (Evento user : evento)
			array.put(DaoEvento.toJson(user));
		return Response.ok().entity(array.toString()).build();
	}
		
		
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response buscaUsuario(@PathParam("id") Integer id) {
		Usuario usuario = dao.buscaUsuario(id);
		JSONObject json = DaoUsuario.toJson(usuario);
		return Response.ok(200).entity(json.toString()).build();		
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postUsuario(String jsonString) {
		Usuario user = DaoUsuario.fromJSON(jsonString);
		dao.insereUsuario(user);
		return Response.ok().entity("Cadastro Efetuado com Sucesso!").build();
		}


	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putUsuario(String jsonString){
		Usuario user = DaoUsuario.fromJSON(jsonString);
		dao.atualizaUsuario(user);
		return Response.status(200).entity("Cadastro Alterado com Sucesso!").build();
	}
	
	@DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id) {
        dao.excluiUsuario(id);
        return Response.ok().build();
    }
}