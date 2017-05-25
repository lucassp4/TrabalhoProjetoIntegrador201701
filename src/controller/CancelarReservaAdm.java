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

public class CancelarReservaAdm implements Initializable {
	@FXML
	private Pane painelPrincipal;
	@FXML
	private TextArea txtareaMotivo;
	
	@FXML
	private ComboBox<String> comboNome;
	@FXML
	private Button btnCancelarReserva;
	
	@FXML
	private Button btnCancelar;
	
	
	@FXML
	private TableView TabelaPrincipal;
	
	@FXML
	private TableColumn<CadControllerUsuario, String> reservaCTebela;
	
	@FXML
	private TableColumn<CadControllerUsuario, String> dataTabela;
	@FXML
	private TableColumn<CadControllerUsuario, String> horarioTabela;
	@FXML
	private TableColumn<CadControllerUsuario, String> periodoTabela;
	
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
	
	
	public void btnCancelarReserva(){
		
	}
}
