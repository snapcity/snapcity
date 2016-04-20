package snapcity;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONArray;
import org.json.JSONObject;

import snapcity.dao.DaoUsuario;
import snapcity.model.Usuario;
import sun.rmi.transport.Target;



@Path ("/usuarios")
public class UsuarioHandler extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Primeira Servlet</title>");
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<h1>Oi mundo Servlet!</h1>");
	    out.println("</body>");
	    out.println("</html>");
	   
	}
	/*@GET
	@Consumes(MediaType.APPLICATION_JSON)
	 public Response executa(HttpServletRequest req, HttpServletResponse res)
		        throws Exception {

		      // Monta a lista de contatos
		      DaoUsuario array = new DaoUsuario();

		      // Guarda a lista no request
		      req.setAttribute("usuarios", array);
		      
		      return Response.ok(200).entity(array.toString()).build();
		} 
		//Response resposta = Response.ok(usuario).build(); return resposta; */

	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getUsuarios() {
		
		DaoUsuario dao = new DaoUsuario();
		List<Usuario> user =  dao.mostrarUsuario();
		
		JSONArray array = new JSONArray();
		
		for (Usuario usuarios : user){
			array.put(dao.toString());
			//System.out.println("usuarios");
		}
		
		return Response.ok(200).entity(array.toString()).build();
	
	}
}	
/*	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postUsuario(Usuario usuario) {
		DaoUsuario dao = new DaoUsuario();
		Usuario user = dao.insereUsuario();
		
		JSONObject obj = new JSONObject();
		
		//for (Usuario usuarios : user){
		//obj.put(usuarios);
		//}
		return Response.ok(200).entity(obj.toString()).build();
		
		
	}*/

	
