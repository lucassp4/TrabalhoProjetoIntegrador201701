package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import modelo.ModeloTelaLogin;

public class ControlePaginaLogin implements Initializable{

	
	
	@FXML
	private TextField txtUsuario;
	@FXML
	private TextField txtSenha;
	@FXML
	private Button btnAvancar;
	@FXML
	private Button btnFechar;
	@FXML
	private Pane panel;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	public void Fechar(){
		
	}
	public void Avancar() {
		
		 ModeloTelaLogin usuario = new ModeloTelaLogin();
		 
		usuario.setNome(txtUsuario.getText());
		usuario.setSenha(txtSenha.getText());
		
		if(usuario.getNome().equals("erik") & usuario.getSenha().equals("1234")){
			
			URL arquivoFXML;
			arquivoFXML = getClass().getResource("/visao/PaginaCadastro.fxml");
			Parent fxmlParent;
			try {
				fxmlParent = FXMLLoader.load(arquivoFXML);
				panel.getChildren().clear();
				panel.getChildren().add(fxmlParent);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
		
		}
			}
	
	}
		
		
	
	
	

