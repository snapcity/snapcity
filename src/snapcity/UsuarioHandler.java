package snapcity;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.json.JSONArray;

import snapcity.dao.DaoUsuario;
import snapcity.model.Usuario;

import java.net.URI;







@Path("/usuarios")
public class UsuarioHandler  {
		DaoUsuario dao = new DaoUsuario();

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuario(Usuario usuario) {
		List<Usuario> usuarios = dao.mostrarUsuario();
		JSONArray array = new JSONArray();
		for (Usuario user : usuarios)
			array.put(dao.toJson(user));
		return Response.ok().entity(array.toString()).build();		
	}

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postUsuario(String jsonString) {
		System.out.println(jsonString);
		Usuario user = DaoUsuario.fromJSON(jsonString);
		dao.insereUsuario(user);
		return Response.ok().entity("Cadastro Efetuado com Sucesso!").build();
		}


	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putUsuario(String jsonString){
		Usuario user = DaoUsuario.fromJSONal(jsonString);
		dao.atualizaUsuario(user);
		return Response.status(200).entity("Cadastro Alterado com Sucesso!").build();
	}
	
	@DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id2") Usuario id) {
        
        dao.excluiUsuario(id);
        return Response.ok(200).entity("Evento de numero "+id+" foi removido").build();
    }
}
