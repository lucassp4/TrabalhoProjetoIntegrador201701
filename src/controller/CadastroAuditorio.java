package controller;

import DAO.AuditorioDao;
import DAO.BlocoDao;
import Model.Auditorio;
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
import sun.applet.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CadastroAuditorio implements Initializable{
	Auditorio auditorio = new Auditorio();
	Main main = new Main();
	AuditorioDao auditorioDao = new AuditorioDao();
	BlocoDao blocoDao = new BlocoDao();
	CadastroBloco cadastroBloco = new CadastroBloco();

	@FXML
	private Pane painelPrincipal;
	
	@FXML
	private TextField txtNumero;

	@FXML
	private TextField txtCapacidade;
	
	@FXML
	private ComboBox<String> comboUnidade;
	
	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnCancelar;
	@FXML
	private TextArea txtDescricao;

	public CadastroAuditorio() throws SQLException {
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		preencherComboUnidade();

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
		
	
	public void btnSalvar() throws SQLException {

		validarCampo();
		auditorioDao.salvar(auditorio);

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





		public void pegarValores(Auditorio auditorio){

			auditorio.setNome(txtNumero.getText());
			auditorio.setUnidade(comboUnidade.getValue());
			auditorio.setCapacidade(Integer.parseInt(txtCapacidade.getText()));
			auditorio.setDescricao(txtDescricao.getText());
		}

		public void validarCampo(){
			pegarValores(auditorio);

			if(!auditorio.getBloco().equals("") || auditorio.getBloco() != null ){
				if(auditorio.getCapacidade()!= 0 )
					if(!auditorio.getNome().equals("") || auditorio.getNome() != null){
					if(!auditorio.getUnidade().equals("") || auditorio.getUnidade()!= null){
						exibeMensagem("Cadastro efetuado com sucesso");
					}

					}

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







