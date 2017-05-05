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

public class CadastroSala implements Initializable{

	@FXML
	private Pane painelPrincipal;
	
	@FXML
	private ComboBox<String> comboUnidade; 
	@FXML
	private TextField txtNumero;
	@FXML
	private TextField txtCapacidade;
	@FXML
	private ComboBox<String> comboBloco;
	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnFechar;
	@FXML
	private Pane panielSala;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		preencherComboUnidade();
		
	}
	public void preencherComboUnidade(){
		
		comboUnidade.getItems().add("Bueno:");
		comboUnidade.getItems().add("Sao paulo:");
		comboUnidade.getItems().add("Perimetral:");
		
	}
	public void cancelar(){
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
