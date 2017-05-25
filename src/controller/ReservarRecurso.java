package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReservarRecurso implements Initializable{

	@FXML
	private Pane painelPrincipal;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private ComboBox<String> comboEquipamento;
	
	@FXML
	private ComboBox<String> comboTurno;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnCancelar;
	
	@FXML
	private DatePicker dataFim;
	
	@FXML
	private DatePicker dataInicial;
	
	@FXML
	private TextField horaFim;
	
	@FXML
	private TextField horaInicial;
	
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

	public void btnSalvar(){
		
	}

	
	
}
