package snapcity;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

import java.net.URI;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.sun.org.apache.xerces.internal.util.Status;

import javafx.scene.chart.PieChart.Data;
import snapcity.dao.DaoUsuario;
import snapcity.model.Usuario;
import sun.rmi.transport.Target;





@Path("/usuarios")
public class UsuarioHandler  {
	
	  
    public UsuarioHandler() {  
        super();  
        // TODO Auto-generated constructor stub  
    }
	DaoUsuario dao = new DaoUsuario();

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuario(Usuario usuario) {
		List<Usuario> usuarios = dao.mostrarUsuario();
		JSONArray array = new JSONArray();
		for (Usuario user : usuarios)
			array.put(dao.toJson(user));
		return Response.ok(200).entity(array.toString()).build();
		
	}
		
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postUsuario(String jsonString) {
		System.out.println(jsonString);	
		String json_srt = jsonString;
		Usuario user = DaoUsuario.fromJSON(json_srt);
		System.out.println(user);
		dao.insereUsuario(user);
		
		return Response.ok().entity(("").toString()).build();
		//Usuario user = dao.fromJSON(json_str);
		
		//Usuario usuarioInserido = dao.insereUsuario(user);
	    //return Response.ok().entity(dao.insereUsuario(json_str).toString()).build();
	}	

} 