package teste;

import org.json.JSONException;

import snapcity.dao.DaoEvento;
import snapcity.model.Evento;

public class TesteEvento {

	public static void main(String[] args) throws JSONException {

		snapcity.dao.DaoEvento eventos = new DaoEvento();

		// Teste eventos
		// System.out.println(eventos.encodeToString(imageData));

		 String foto = eventos.encodeToString("/Users/marcelodasrodrigues/Desktop/snapcity.jpg");
		
		 eventos.insereEvento(foto, "Teste novo método include", -54.90876,-34.98765, 21, "poste caido");
		// eventos.mostrarEvento();
		// eventos.buscaEvento("Foto de descricao 3");
		// eventos.excluiEvento(3);

	}
}
