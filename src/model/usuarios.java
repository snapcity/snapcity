package model;

public class usuarios {
	
	private String nome;
	private String senha;
	private String email;
	private String datacriacao;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setDatacriacao(String datacriacao) {
		this.datacriacao = datacriacao;
	}
	
	public String getNome() {
		return nome;
	}
	public String getSenha() {
		return senha;
	}
	public String getEmail() {
		return email;
	}
	public String getDatacriacao() {
		return datacriacao;
	} 
	
	
	

}
