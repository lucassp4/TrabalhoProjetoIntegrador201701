package model;

/**
 * Created by Ritchely on 03/05/2017.
 */
public class CadastroBloco  {


    private String nome;
    private String unidade = "" ;
    private String numeroSalas ;
    private String descricao ;
    private int id;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getNumeroSalas() {
        return numeroSalas;
    }

    public void setNumeroSalas(String numeroSalas) {
        this.numeroSalas = numeroSalas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
