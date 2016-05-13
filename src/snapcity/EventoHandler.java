package snapcity;

import java.util.Iterator;
 import java.util.List;
 import java.io.*;
 
 import javax.servlet.http.HttpServlet;
 import javax.ws.rs.Consumes;
 import javax.ws.rs.DELETE;
 import javax.ws.rs.GET;
 import javax.ws.rs.POST;
 import javax.ws.rs.PUT;
 import javax.ws.rs.Path;
 import javax.ws.rs.PathParam;
 import javax.ws.rs.Produces;
 import javax.ws.rs.core.MediaType;
 import javax.ws.rs.core.Response;
 
 import org.json.JSONArray;
 import org.json.JSONObject;
 
 import snapcity.dao.DaoEvento;
 import snapcity.dao.DaoUsuario;
 import snapcity.model.Evento;
 import snapcity.model.Usuario;



@Path("/evento")
public class EventoHandler   {
	
	
	DaoEvento daoEventos = new DaoEvento();
	Evento modelEventos = new Evento();
	Usuario usuario = new Usuario();
	
	
	//ok
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEvento() {
		List<Evento> evento = daoEventos.mostrarEvento();
		
		JSONObject o = new JSONObject();
		JSONArray json = new JSONArray();
		
			for (Evento e : evento) {
				 json.put(DaoEvento.toJson(e));
			}
			
	//	o.put("eventos", json);
		
		return Response.ok(200).entity(json.toString()).build();
		
		
	}
	
	
	//ok
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postEvento(String jsonString) {
		System.err.println(jsonString);
		Evento evento = daoEventos.fromJson(jsonString);
		 daoEventos.insereEvento(evento);
		return Response.ok().entity("Cadastro Efetuado com Sucesso!").build();
		}
	
	
	// ok
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response buscaEvento(@PathParam("id") int id) {
		Evento eventos = daoEventos.buscaEvento(id);
		JSONObject json = DaoEvento.toJson(eventos);
		
		return Response.ok(200).entity(json.toString()).build();		
	}
	// ok
	@DELETE
    @Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id) {
		System.out.println(id);
		daoEventos.excluiEvento(id);
		return Response.ok(200).entity("Evento de numero " +id+ " foi removido").build();
    }
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putUsuario(String jsonString){
		Evento evento = daoEventos.fromJson(jsonString);
		daoEventos.atualizaEvento(evento);
		return Response.status(200).entity("Cadastro Alterado com Sucesso!").build();
	}

}