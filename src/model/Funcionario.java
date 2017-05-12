package model;

/**
 * Created by lucas.pereira on 27/04/2017.
 */
public class Funcionario {

    private Integer id;
    private String nome;
    private String telefone;
    private String celular;
    private String matricula;
    private String senha;
    private String email;

	public boolean Login(){
		return false;
	}
	
	public void Cadastrar(String nome, String email, String telefone, String celular, String matricula, String senha){
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.celular = celular;
		this.matricula = matricula;
		this.senha = senha;
	}
	
	public void Editar(){
		
	}
	
	public void Excluir(){
		
	}



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
