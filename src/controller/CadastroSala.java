package controller;

import application.Main;
import dao.BlocoDao;
import dao.SalaDao;
import dao.UnidadeDao;
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
import model.Unidade;
import negocio.SalaNegocio;
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
	SalaNegocio salaNegocio = new SalaNegocio();
	BlocoDao blocoDao = new BlocoDao();
	Main main = null;

	UnidadeDao unidadeDao = new UnidadeDao();
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

		List<String> teste = new ArrayList<String>();
		List<Unidade> unidade = new ArrayList<Unidade>();
		unidade = unidadeDao.listarClientes();
		unidade.forEach( (Unidade cliente) -> {
					teste.add(cliente.getNome());
				}


		);
		teste.forEach((String testes)->{
					comboUnidade.getItems().add(testes);
				}
		);



	}

	public void salvar() throws SQLException {
		boolean validar;
		boolean validarCapacidade;
		pegarValores();


		validar =  validarCampos();
		SalaDao salaDao = new SalaDao();
		if (validar) {
				salaDao.salvar(sala);
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
			}else{

			}
		}
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

		boolean nome =  salaNegocio.string(sala.getNome());
		String unidade = sala.getUnidade();
		boolean capacidade = salaNegocio.verificarCampo(sala.getCapacidade());
		String tipo = sala.getTipo();
		String bloco = sala.getBloco();

		List<Control> controls = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("");
		if(!nome){
			sb.append("Por favor digite somente três numeros! \n");
			controls.add(txtNome);
		}
		if(!capacidade){
			sb.append("Digite a capacidade valida! \n");
			controls.add(txtCapacidade);
		}
		if(unidade == null){
			sb.append("Selecione uma unidade !. \n");
			controls.add(comboUnidade);
		}
		if(tipo == null){
			sb.append("Selecione um tipo!. \n");
			controls.add(comboTipo);
		}
		if(bloco == null){
			sb.append("Selecione um bloco!. \n");
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


