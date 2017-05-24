package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CadControllerUsuario implements Initializable{
	
	
	
	@FXML
	private TextField txtNome;
	@FXML  
	private TextField txtTelefone;
	@FXML 
	private TextField txtEndereco;
	@FXML 
	private TextField txtMatricula;
	@FXML 
	private TextField txtCelular;
	@FXML 
	private TextField txtEmail;
	@FXML 
	private ComboBox<String> comboFuncao;
	@FXML
	private Button btnSalvar;
	@FXML 
	private Button btnCancelar;
	@FXML 
	private Pane painelPrincipal;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		preenchercomboFuncao();

	}
	
	public void preenchercomboFuncao(){
		comboFuncao.getItems().add("Professor");
		comboFuncao.getItems().add("Coordenador");
		comboFuncao.getItems().add("Audio visual");
		
	}
	public void btnSalvar(){
		
	}
		public void btnCancelar(){
			URL arquivoFXML;
			arquivoFXML = getClass().getResource("/View/PaginaPrincipal.fxml");
			Parent fxmlParent;
			try {
				fxmlParent = FXMLLoader.load(arquivoFXML);
				painelPrincipal.getChildren().clear();
				painelPrincipal.getChildren().add(fxmlParent);
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
		
	
}
