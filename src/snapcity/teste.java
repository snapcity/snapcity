package snapcity;

import java.util.*; 

import dao.DaoEvento;
import dao.DaoUsuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class teste {
	public static void main(String[] args) { 
		try {
		File file = new File("/Users/marcelodasrodrigues/Desktop/snapcity.jpg");
		FileInputStream imageInFile = new FileInputStream(file);
		byte imageData[] = new byte[(int)file.length()];
		
			imageInFile.read(imageData);
			
		
	dao.DaoUsuario usuarios = new DaoUsuario();
	dao.DaoEvento eventos = new DaoEvento();
	
	//usuarios.insereUsuarios("timestamp","1212"," marcelohcer@outlook.com");
	//usuarios.mostrarUsuarios();
	//usuarios.buscaUsuarios(2);
	//usuarios.atualizaUsuarios(2, "Andersen");
	//usuarios.excluiUsuario(3);
	//usuarios.buscaUsuariosEventos(1);
	
	// Teste eventos
	//System.out.println(eventos.encodeToString(imageData));
	
	//String foto = eventos.encodeToString(imageData);
	//eventos.insereEventos(foto, "Foto de descricao 3", -54.90876, -34.98765, 1, "posst caido");
	eventos.mostrarEventos();
	//eventos.buscaEventos("Foto de descricao 3");
	//eventos.excluiEventos(3);
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}