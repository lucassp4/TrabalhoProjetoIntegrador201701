package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RelatorioDeCadastro implements Initializable {


	@FXML
	private Pane painelPrincipal;
	
	@FXML
	private TableColumn<CadControllerUsuario, String> tabelaNome;

	@FXML
	private TableColumn<CadControllerUsuario, String> tabelaData;
	
	@FXML
	private TableColumn<CadControllerUsuario, String> tabelaHorario;

	@FXML
	private TableColumn<CadControllerUsuario, String> tabelaCadastradoPelo;
	

	@FXML
	private Button btnSair;

	@FXML
	private DatePicker date;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void btnSair(){
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
