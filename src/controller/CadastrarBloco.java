package controller;

import DAO.BlocoDao;
import Model.CadastroBloco;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import application.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class CadastrarBloco implements Initializable {

	@FXML
	private Pane painelPrincipal;
	@FXML
	private TextArea textDescricao;
	@FXML
	private ComboBox<String> comboUnidade;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtSalas;
	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnCancelar;

	Main main = null;
	CadastroBloco bloco = new CadastroBloco();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		   	preencherComboUnidade();
	}

	public void btnCancelar() {
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


	public void btnSalvar() throws SQLException {
		pegarvalores();
		BlocoDao blocoDao = new BlocoDao();
		blocoDao.salvar(bloco);

			exibeMensagem("Salvo com sucesso");

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


	public void pegarvalores() {


		bloco.setNome(txtNome.getText());
		bloco.setUnidade(comboUnidade.getValue());
		bloco.setNumeroSalas(Integer.parseInt(txtSalas.getText()));
		bloco.setDescricao(textDescricao.getText());


	}

	  public void preencherComboUnidade(){
	  		comboUnidade.getItems().add("Faculdade Alves Faria Bueno");
			comboUnidade.getItems().add("Faculdade Alves Faria Perimetral");
			comboUnidade.getItems().add("Faculdade Alves Faria SP");
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