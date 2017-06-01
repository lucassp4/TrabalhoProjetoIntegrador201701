package controller;

import dao.BlocoDao;
import dao.SalaDao;
import dao.UnidadeDao;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.CadastroBloco;
import model.ReservaSala;
import model.Sala;
import model.Unidade;
import org.controlsfx.control.Notifications;
import sun.applet.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ReservarSala implements Initializable {
	@FXML
	private Pane painelPrincipal;
		
	@FXML
	private TextArea textAriaDescricao;
	
	@FXML
	private ComboBox<String> comboBloco;
	@FXML
	private ComboBox<String> comboTurno;
	@FXML
	private ComboBox<String> comboSala;
	@FXML
	private ComboBox<String>  comboUnidade;
	@FXML
	private ComboBox<String> comboTipo;
	@FXML
	private DatePicker dateInicio;
	@FXML
	private DatePicker dateFim;
	
	@FXML
	private TextField horaIncio;

	@FXML
	private TextField horaFim;
	
	@FXML
	private Button btcSalvar;
	
	@FXML
	private Button btnCancelar;

	Main main = null;

	ReservaSala reserva = new ReservaSala();
	Sala sala = new Sala();
	SalaDao salaDao = new SalaDao();
	UnidadeDao unidadeDao = new UnidadeDao();
	BlocoDao blocoDao = new BlocoDao();
	public ReservarSala() throws SQLException {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		usuarioPesquisa(sala);
		preencherComboUnidade();
		preencherSala();
		preencherTurno();
		preencherComboTipo();
		preencherComboBloco();

		setarValor(sala);



		
	}
	public void setarValor(Sala sala){

		comboSala.setValue(sala.getNome());
		comboBloco.setValue(sala.getBloco());
		comboTipo.setValue(sala.getTipo());
		comboUnidade.setValue(sala.getUnidade());

	}



	public void pegarValores(){
		reserva.setNome("Usuario");
		reserva.setTipo(comboTipo.getValue());
		reserva.setUnidade(comboUnidade.getValue());
		reserva.setBloco(comboBloco.getValue());
		reserva.setDataInicial(dateInicio.getValue());
		reserva.setDataFinal(dateFim.getValue());
		reserva.setTurno(comboTurno.getValue());
		reserva.setHoraInicial(horaIncio.getText());
		reserva.setHoraFinal(horaFim.getText());
		reserva.setSala(comboSala.getValue());

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
	

	public void btcSalvar(){
	pegarValores();
		boolean validar;

	validar = validarCampos();
	if(validar) {
		exibeMensagem("Reserva refetuada com sucesso!");
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
	}

	public void preencherTurno(){
		comboTurno.getItems().add("Matunino");
		comboTurno.getItems().add("Noturno");
	}

	public void preencherSala(){
		List<String> teste = new ArrayList<String>();
		List<Sala> sala = new ArrayList<Sala>();
		sala = salaDao.listarSalas();
		sala.forEach( (Sala cliente) -> {
					teste.add(cliente.getNome());
				}


		);
		teste.forEach((String testes)->{
					comboSala.getItems().add(testes);
				}
		);
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
	public void preencherComboTipo(){

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

	public void usuarioPesquisa(Sala se){
		sala = se;
	}

	public boolean validarCampos(){
		String sala = reserva.getSala();
		String unidade = reserva.getUnidade();
		String turno = reserva.getTurno();
		String tipo = reserva.getTipo();
		String bloco = reserva.getBloco();
		LocalDate inicial = reserva.getDataInicial();
		LocalDate  fim = reserva.getDataFinal();

		List<Control> controls = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("");
		if(tipo == null){
			sb.append("Selecione um tipo!. \n");
			controls.add(comboTipo);
		}
		if(turno == null){
			sb.append("Selecione um turno!. \n");
			controls.add(comboTurno);
		}
		if(unidade == null){
			sb.append(" Selecione uma unidade !. \n");
			controls.add(comboUnidade);
		}
		if(sala == null){
			sb.append("Selecione uma sala!. \n");
			controls.add(comboSala);
		}
		if(bloco == null){
			sb.append(" Selecione um bloco !. \n");
			controls.add(comboBloco);
		}
		if(inicial == null){
			sb.append("Selecione a data Inicial!. \n");
			controls.add(dateInicio);
		}
		if(fim == null){
			sb.append("Selecione a data final!. \n");
			controls.add(dateFim);
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

