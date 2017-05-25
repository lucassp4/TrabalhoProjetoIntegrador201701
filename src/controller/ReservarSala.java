package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReservarSala implements Initializable {
	@FXML
	private Pane painelPrincipal;
		
	@FXML
	private TextArea textAriaDescricao;
	
	@FXML
	private ComboBox<String> comboBloca;
	@FXML
	private ComboBox<String> comboTurma;
	@FXML
	private ComboBox<String> comboSala;
	@FXML
	private ComboBox<String>  comboUnidade;
	
	@FXML
	private DatePicker dateInicio;
	@FXML
	private DatePicker dateFim;
	
	@FXML
	private TextField horaIncio;

	@FXML
	private TextField horaFim;
	
	@FXML
	private Button btcSalvar;
	
	@FXML
	private Button btnCancelar;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
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
	

	public void btcSalvar(){
		
	}
}
