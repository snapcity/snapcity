package snapcity;

import java.util.*; 

import dao.DaoEventos;
import dao.DaoUsuarios;

public class teste {
	public static void main(String[] args) { 
		
	dao.DaoUsuarios usuarios = new DaoUsuarios();
	dao.DaoEventos eventos = new DaoEventos();

	//usuarios.insereUsuarios("timestamp","1212"," marcelohcer@outlook.com");
	//usuarios.mostrarUsuarios();
	//usuarios.buscaUsuarios(2);
	//usuarios.atualizaUsuarios(2, "Marcelo S. Rodrigues");
	//usuarios.excluiUsuario(3);
	usuarios.buscaUsuariosEventos(1);
	
	// Teste eventos

	//eventos.insereEventos("image teste 2", "Foto de descricao 2", -54.90876, -34.98765, 1, "sinaleira estragada");
	//eventos.mostrarEventos();
	//eventos.buscaEventos("Por");
	//eventos.excluiEventos(4);
	}
}