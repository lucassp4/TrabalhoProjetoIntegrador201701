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
	private ComboBox<String> comboAutoridade;
	
	@FXML 
	private ComboBox<String> comboUnidade;
	
	@FXML 
	private ComboBox<String> comboFuncao;
	
	@FXML 
	private ComboBox<String> comboCurso;
	
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
	
	public void preenchercomboAutoridade(){
		comboAutoridade.getItems().add("Alta");
		comboAutoridade.getItems().add("Baixa");
		comboAutoridade.getItems().add("Total");
	}
	
	public void preenchercomboUnidade(){
		comboUnidade.getItems().add("Bueno");
		comboUnidade.getItems().add("Sao paulo");
		comboUnidade.getItems().add("Perimetral");
		
	}
	public void preenchercomboFuncao(){
		comboFuncao.getItems().add("Professor");
		comboFuncao.getItems().add("Cordenador");
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
