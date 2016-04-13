package teste;
import snapcity.model.Usuario;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.json.*;
import org.json.JSONObject;

import snapcity.dao.DaoEvento;
import snapcity.dao.DaoUsuario;

public class teste {
	public static void main(String[] args) throws JSONException { 
		try {
		File file = new File("C:/Users/informatica02.financeiro-05/Desktop/snapcity.jpg");
		FileInputStream imageInFile = new FileInputStream(file);
		byte imageData[] = new byte[(int)file.length()];
		
			imageInFile.read(imageData);
			
		
	snapcity.dao.DaoUsuario usuarios = new DaoUsuario();
	snapcity.dao.DaoEvento eventos = new DaoEvento();
	DaoUsuario dao = new DaoUsuario();
	JSONObject obj = new JSONObject();
	
	// Insere Usuario
	//String json_str = "{\"nome\":\"andersen\",\"senha\":\"753\",\"email\":\"teste@2152.com.br\"}";
	//Usuario user = DaoUsuario.fromJSON(json_str);
	//dao.insereUsuario(user);
	
	//Atualiza Usuario
	//String json_str = "{\"nome\":\"andersen\",\"senha\":\"753\",\"email\":\"teste@2152.com.br\"}";
	//Usuario user = DaoUsuario.fromJSON(json_str);
	//String json_str1 = "{\"nome\":\"andersen1\",\"senha\":\"7534\",\"email\":\"teste1@2152.com.br\"}";
	//Usuario user1 = DaoUsuario.alteraJSON(json_str1);
	//dao.atualizaUsuario(28, user1);
	
	//usuarios.mostrarUsuario();
	//usuarios.buscaUsuario(28);
	//usuarios.excluiUsuario(3);
	//usuarios.buscaUsuariosEventos(1);
	
	// Teste eventos
	//System.out.println(eventos.encodeToString(imageData));
	
	//String foto = eventos.encodeToString(imageData);
	//eventos.insereEventos(foto, "Foto de descricao 3", -54.90876, -34.98765, 1, "posst caido");
	//eventos.mostrarEventos();
	//eventos.buscaEventos("Foto de descricao 3");
	//eventos.excluiEventos(3);
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}