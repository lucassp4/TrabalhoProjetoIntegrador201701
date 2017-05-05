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

public class AlterarAuditorio implements Initializable {
	
	@FXML
	private Pane painelPrincipal;
	
	@FXML
	private TextField txtNumero;

	@FXML
	private TextField txtCapacidade;
	
	@FXML
	private ComboBox<String> comboBloco;
	
	@FXML
	private ComboBox<String> comboUnidade;
	
	@FXML
	private Button btnAlterar;
	@FXML
	private Button btnCancelar;
	@FXML
	private javafx.scene.control.TextArea txtareaDescricao;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		preencherComboUnidade();
		
		
	}
	public void btnAlterar(){
		
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
	

	public void preencherComboUnidade(){
		comboUnidade.getItems().add("Faculdade Alves Faria Bueno");
		comboUnidade.getItems().add("Faculdade Alves Faria Perimetral");
		comboUnidade.getItems().add("Faculdade Alves Faria SSP");
	}

	
	public void preencherComboBloco(){
		
	}
		
	}

