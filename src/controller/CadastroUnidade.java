package controller;

import dao.UnidadeDao;
import model.Unidade;
import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CadastroUnidade implements Initializable {
	
	@FXML
	private Pane painelPrincipal;
	
	@FXML
	private TextField txtNomeUnidade;

	@FXML
	private TextField txtFantacia;

	@FXML
	private TextField txtCep;
	@FXML
	private TextField txtEstado;
	@FXML
	private TextField txtCidade;

	@FXML
	private TextField txtEndereco;
	@FXML
	private TextField txtRazao;
	
	@FXML
	private TextField txtCnpj;
	
	@FXML
	private TextField txtTelefone;
	
	@FXML
	private TextField textBloco;
	
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnCancelar; 
	
	Unidade unidade = new Unidade();
	Main main = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public void btnSalvar() throws SQLException {
		UnidadeDao unidadeDao = new UnidadeDao();
	    validarCampos();
		unidadeDao.salvar(unidade);

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
		unidade.setBlocos(Integer.parseInt(textBloco.getText()));


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
	public void exibeMensagem(String msg){
		Notifications.create()
				.title("Atensão")
				.text(String.valueOf(msg))
				.owner(main)
				.hideAfter(Duration.seconds(3))
				.darkStyle()
				.position(Pos.TOP_RIGHT)
				.showInformation();


	}
}
