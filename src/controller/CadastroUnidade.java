package controller;

import Model.Unidade;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CadastroUnidade implements Initializable {
	
	@FXML
	private Pane painelPrincipal;
	
	@FXML
	private TextField txtNomeUnidade;

	@FXML
	private TextField txtEndereco;
	
	@FXML
	private TextField txtRazao;
	
	@FXML
	private TextField txtCnpj;
	
	@FXML
	private TextField txtTelefone;
	
	@FXML
	private TextField txtBloco;
	
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnCancelar; 
	
	Unidade unidade = new Unidade();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public void btnSalvar(){

	    validarCampos();


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
	
	public void pegarValores(){

		unidade.setNome(txtNomeUnidade.getText());
		unidade.setEndereco(txtEndereco.getText());
		unidade.setCnpj(txtCnpj.getText());
		unidade.setRazaoSocial(txtRazao.getText());
		unidade.setTelefone(txtTelefone.getText());
		unidade.setBlocos(Integer.parseInt(txtBloco.getText()));


	}


	public void validarCampos(){

		pegarValores();

		if(!unidade.getNome().equals("")|| unidade.getNome() != null){
			if(!unidade.getEndereco().equals("") ||unidade.getEndereco() != null );
			if(!unidade.getTelefone().equals("") || unidade.getTelefone() != null);
			if(!unidade.getCnpj().equals("") || unidade.getCnpj() != null);
			if(!unidade.getRazaoSocial().equals("") || unidade.getRazaoSocial() !=null);
			if(unidade.getBlocos() != 0);
					}



	}
}
