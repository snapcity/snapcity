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

/**
 * REST método que retorna e grava dados em formato Json
 * 
 * @author Marcelo e Andersen
 * 
 */

@Path("/evento")
public class EventoHandler {

	/**
	 * Retorna todos os eventos em formato json
	 * 
	 * @return
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEventos() {

		DaoEvento daoEventos = new DaoEvento();

		List<Evento> evento = daoEventos.buscaEventos();

		JSONObject o = new JSONObject();
		JSONArray json = new JSONArray();

		for (Evento e : evento) {
			json.put(DaoEvento.toJson(e));
		}

		o.put("eventos", json);

		return Response.ok().entity(json.toString()).build();

	}

	/**
	 * Grava eventos recebendo dados em formato json
	 * 
	 * @param evento
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insereEvento(String evento) {
		
		DaoEvento daoEventos = new DaoEvento();
		System.err.println(evento);
		Evento e = daoEventos.fromJson(evento);
		daoEventos.insereEvento(e);
		return Response.ok().build();
	}

	/**
	 * Mostra eventos pesquisado por id
	 * 
	 * @param id
	 * @return
	 */
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response buscaEvento(@PathParam("id") int id) {
		
		DaoEvento daoEventos = new DaoEvento();
		Evento eventos = daoEventos.buscaEvento(id);
		JSONObject json = DaoEvento.toJson(eventos);
		return Response.ok().entity(json.toString()).build();
	}

	/**
	 * Remove evento por id 
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id) {
		
		DaoEvento daoEventos = new DaoEvento();
		daoEventos.excluiEvento(id);
		return Response.ok().build();
	}

	/**
	 * Atualiza Evento
	 * @param evento
	 * @return
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizaEvento(String evento) {
		
		DaoEvento daoEventos = new DaoEvento();
		Evento e = daoEventos.fromJson(evento);
		daoEventos.atualizaEvento(e);
		return Response.ok().build();
	}

}