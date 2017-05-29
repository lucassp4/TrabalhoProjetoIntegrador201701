package model;

import java.sql.SQLException;

import dao.UsuarioDAO;

/**
 * Created by lucas.pereira on 27/04/2017.
 */
public class Usuario {

	private int id;
	private String nome;
	private String telefone;
	private String celular;
	private String matricula;
	private String senha;
	private String email;
	private String funcao;
	protected UsuarioDAO dao = new UsuarioDAO();

	public Usuario() {
	}

	public Usuario(int id, String nome, String telefone, String celular, String matricula, String email, String funcao) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.celular = celular;
		this.matricula = matricula;
		this.email = email;
		this.funcao = funcao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public void save(){
		if(this.id == 0){
			dao.create(this);
		}else{
			dao.update(this);
		}
	}

}
