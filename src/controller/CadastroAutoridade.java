package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CadastroAutoridade implements Initializable{
	
	
	@FXML
	private Pane painelPrincipal;
	@FXML
	private Button btnCancelar;
	@FXML
	private ComboBox <String> comboUnidade;
	@FXML
	private ComboBox <String>comboNivel;
	@FXML
	private VBox vbox;
	@FXML
	private RadioButton radioCadastro;
	@FXML
	private RadioButton radioAlterar;
	@FXML
	private RadioButton radioRelatorio;
	@FXML
	private RadioButton radioReserva;
	
	@FXML
	private RadioButton radioHistorico;
	
	@FXML
	private Button btnSalvar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		preencherComboUnidade();
		preencherComboNivel();
	}
	
	public void btnSalvar(){
		
		
	}
	public void btnCancelar(){
		
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/PaginaPrincipal.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = FXMLLoader.load(arquivoFXML);
			painelPrincipal.getChildren().clear();
			painelPrincipal.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		}

	public void preencherComboUnidade(){
		comboUnidade.getItems().add("Faculdade Alves Faria Bueno");
		comboUnidade.getItems().add("Faculdade Alves Faria Perimetral");
		comboUnidade.getItems().add("Faculdade Alves Faria SSP");
	}
	

	public void preencherComboNivel(){
		comboNivel.getItems().add("Alto");
		comboNivel.getItems().add("Media");
		comboNivel.getItems().add("Baixa");
	}
		
	public void comboNivel(){
		String nivel = comboNivel.getValue();
		if(nivel.equals("Alto")){
			
		}
		if(nivel.equals("Media")){
			
		}
		if(nivel.equals("Baixa")){
			
		}
		
		
	}
}
