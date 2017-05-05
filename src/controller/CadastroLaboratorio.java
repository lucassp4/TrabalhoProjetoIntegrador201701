package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CadastroLaboratorio implements Initializable{

	@FXML
	private Pane painelPrincipal;
	
	@FXML
	private Button buttonSalvar;
	

	@FXML
	private	Button buttonCancelar;
	

	@FXML
	private TextField txtNome;
	

	@FXML
	private TextField txtCapacidade;
	

	@FXML
	private ComboBox <String> comboUnidade;
	

	@FXML
	private TextArea txtAria;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public void buttonSalvar(){
		
	}
	public void buttonCancelar(){
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
