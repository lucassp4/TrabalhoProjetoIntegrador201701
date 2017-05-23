package model;

import java.time.LocalDate;

import model.CadEquipamento;


public class CadEquipamento {
	private int id;
    private String tipo;
    private String modelo;
    private String marca;
    private LocalDate data;
    private String unidade;
    
    public CadEquipamento(String tipo, String modelo, String marca, LocalDate data, String unidade) {
        this.tipo = tipo;
        this.modelo = modelo;
        this.marca = marca;
        this.data = data;
        this.unidade = unidade;
    }
    	public CadEquipamento (){
    		
    	}
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
}
        
        
	