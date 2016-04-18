package snapcity.model;
import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class UsuarioHandler {
	
	public static void main(String[] args){
		ClientConfig config = new ClientConfig();
		
		Client client = ClientBuilder.newClient(config);
		
		//WebTarget target = client
	}

}
