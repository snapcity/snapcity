package snapcity;
import java.net.URI;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.HTTP;

import com.sun.research.ws.wadl.Request;

import snapcity.dao.DaoEvento;
import snapcity.model.Evento;

//configura o caminho para a base URL + /hello
@Path("/evento")
public class EventoHandler extends HttpServlet {
	
	@Context
	  UriInfo uriInfo;
	  @Context
	  Request request;
	  String id;
	  public void TodoResource(UriInfo uriInfo, Request request, String id) {
	    this.uriInfo = uriInfo;
	    this.request = request;
	    this.id = id;
	  }
	  DaoEvento daoEvento = new DaoEvento();
	
	  //Application integration     
	  @POST
	  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	  public Evento getEvento(@PathParam("id") int id) {
		  Evento evento = new Evento();
		  evento = daoEvento.buscaEvento(id);
	    if(evento==null)
	      throw new RuntimeException("Get: Todo with " + id +  " not found");
	    return evento;
	  }
	  


} 