package snapcity;

import java.util.*; 

import dao.DaoEventos;
import dao.DaoUsuarios;

public class teste {
	public static void main(String[] args) { 
		
	dao.DaoUsuarios usuarios = new DaoUsuarios();
	

	//usuarios.insereUsuarios("teste2","4545"," marcelohcer@outlook.com");
	usuarios.mostrarUsuarios();
	//usuarios.buscaUsuarios(2);
	//usuarios.atualizaUsuarios(2, "Marcelo S. Rodrigues");
	//usuarios.excluiUsuario(3);
	
	// Teste eventos
	
	dao.DaoEventos eventos = new DaoEventos();
	
	//eventos.mostrarEventos();
	//eventos.buscaEventos("Por");
	}
}