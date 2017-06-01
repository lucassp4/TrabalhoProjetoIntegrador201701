package controller;

import dao.BlocoDAO;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import model.CadastroBloco;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import application.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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


	public void btnSalvar() throws SQLException {
		boolean validar;
		pegarvalores();
		validar =  validarCampos();
		BlocoDAO blocoDao = new BlocoDAO();
		if (validar) {
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
	}


	public void pegarvalores() {


		bloco.setNome(txtNome.getText());
		bloco.setUnidade(comboUnidade.getValue());
		bloco.setNumeroSalas(txtSalas.getText());
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

	public boolean validarCampos(){

		String nome =  txtNome.getText();
		String unidade = bloco.getUnidade();
		String nSala = bloco.getNumeroSalas();

		List<Control> controls = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("");
		if(nome.equals("") || nome == null){
			sb.append("O nome não pode ser vazio!. \n");
			controls.add(txtNome);
		}
		if(unidade == null){
			sb.append(" A por favor selecione uma unidade !. \n");
			controls.add(comboUnidade);
		}
		if(nSala.equals("") || nSala == null){
			sb.append("O Numero de sala não pode ser vazio!. \n");
			controls.add(txtSalas);
		}

		if(!sb.equals("")) {
			exibeMensagem(sb.toString());
			animaCamposValidados(controls);
		}

		return sb.toString().isEmpty();
	}

	public void animaCamposValidados(List<Control> controls) {
		controls.forEach(control -> {
			RotateTransition rotateTransition = new RotateTransition(Duration.millis(60), control);
			rotateTransition.setFromAngle(-4);
			rotateTransition.setToAngle(4);
			rotateTransition.setCycleCount(8);
			rotateTransition.setAutoReverse(true);
			rotateTransition.setOnFinished((ActionEvent event1) ->{
				control.setRotate(0);
			});
			rotateTransition.play();
		});
		if(!controls.isEmpty()){
			controls.get(0).requestFocus();
		}
	}

		}


