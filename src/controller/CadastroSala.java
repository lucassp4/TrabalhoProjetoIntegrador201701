package controller;

import application.Main;
import dao.BlocoDao;
import dao.SalaDao;
import javafx.animation.RotateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.CadastroBloco;
import model.Sala;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

public class CadastroSala implements Initializable{
	Sala sala = new Sala();
	@FXML
	private Pane painelPrincipal;
	
	@FXML
	private ComboBox<String> comboUnidade; 
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtCapacidade;
	@FXML
	private ComboBox<String> comboBloco;
	@FXML
	private ComboBox<String> comboTipo;


	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnFechar;
	@FXML
	private Pane panielSala;

	BlocoDao blocoDao = new BlocoDao();
	Main main = null;
	private ObservableList <String> listaBloco;

	public CadastroSala() throws SQLException {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		preencherComboUnidade();
		preencherComboSala();
		preencherComboBloco();



	}
	public void preencherComboUnidade(){
		
		comboUnidade.getItems().add("Bueno:");
		comboUnidade.getItems().add("Sao paulo:");
		comboUnidade.getItems().add("Perimetral:");

	}

	public void salvar() throws SQLException {

		boolean validar;
		pegarValores();
		validar =  validarCampos();
		SalaDao salaDao = new SalaDao();
		if (validar) {
			salaDao.salvar(sala);
			exibeMensagem("Salvo com sucesso");

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

		}}
	public void cancelar(){
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
	public void pegarValores(){
		sala.setBloco(comboBloco.getValue());
		sala.setCapacidade(txtCapacidade.getText());
		sala.setNome(txtNome.getText());
		sala.setUnidade(comboUnidade.getValue());
		sala.setTipo(comboTipo.getValue());

	}

	public void preencherComboSala(){

		comboTipo.getItems().add("Sala de aula");
		comboTipo.getItems().add("Laboratorio");
		comboTipo.getItems().add("Auditorio");

	}
	public void preencherComboBloco(){
			List<String> teste = new ArrayList<String>();
			List<CadastroBloco> salas = new ArrayList<CadastroBloco>();
			salas = blocoDao.listarClientes();
		salas.forEach( (CadastroBloco cliente) -> {
					teste.add(cliente.getNome());
		}


		);
		teste.forEach((String testes)->{
		comboBloco.getItems().add(testes);
		}
		);


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

		String nome =  sala.getNome();
		String unidade = sala.getUnidade();
		String capacidade = sala.getCapacidade();
		String tipo = sala.getTipo();
		String bloco = sala.getBloco();

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
		if(capacidade.equals("") || capacidade == null){
			sb.append("O Numero de sala não pode ser vazio!. \n");
			controls.add(txtCapacidade);
		}
		if(unidade == null){
			sb.append(" A por favor selecione uma unidade !. \n");
			controls.add(comboUnidade);
		}
		if(tipo == null){
			sb.append("O Numero de sala não pode ser vazio!. \n");
			controls.add(comboTipo);
		}
		if(bloco == null){
			sb.append("O Numero de sala não pode ser vazio!. \n");
			controls.add(comboBloco);
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


