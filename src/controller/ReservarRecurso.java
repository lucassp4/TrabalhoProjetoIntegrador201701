package controller;

import dao.EquipamentoDAO;
import dao.SalaDao;
import dao.UnidadeDao;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.CadEquipamento;
import model.ReservaEquipamento;
import model.Sala;
import model.Unidade;
import negocio.EquipamentoNegocio;
import org.controlsfx.control.Notifications;
import sun.applet.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReservarRecurso implements Initializable{

	@FXML
	private TableColumn<ReservaEquipamento, String> colunaSala;
	@FXML
	private TableColumn<ReservaEquipamento, String>  colunaUnidade;
	@FXML
	private TableColumn<ReservaEquipamento, LocalDate>  colunaDataIncial;
	@FXML
	private TableColumn<ReservaEquipamento, LocalDate>  colunaDataFinal;
	@FXML
	private TableColumn<ReservaEquipamento, String>  calunaUnidade;
	@FXML
	private ComboBox<String> comboUnidade;
	@FXML
	private ComboBox <String> comboSala;
	@FXML
	private TableView tabelaVewer;
	@FXML
	private TableColumn colunaEquiepamento;
	@FXML
	private Pane painelPrincipal;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private ComboBox<String> comboEquipamento;
	
	@FXML
	private ComboBox<String> comboTurno;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnCancelar;
	
	@FXML
	private DatePicker dataFim;
	
	@FXML
	private DatePicker dataInicial;
	
	@FXML
	private TextField horaFim;
	
	@FXML
	private TextField horaInicial;
	List<ReservaEquipamento> reservaEquipamentoList = new ArrayList<ReservaEquipamento>();
	CadEquipamento equipamento = new CadEquipamento();
	EquipamentoDAO equipamentoDAO = new EquipamentoDAO();

	Main main = null;
	EquipamentoNegocio equipamentoNegocio = new EquipamentoNegocio();
	ReservaEquipamento reserva = new ReservaEquipamento();
	Sala sala = new Sala();
	SalaDao salaDao = new SalaDao();
	UnidadeDao unidadeDao = new UnidadeDao();
	ObservableList<ReservaEquipamento> equipamentoView = null;

	public ReservarRecurso() throws SQLException {
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

	public void btnSalvar(){

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

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		preencherComboUnidade();
		preencherSala();
		preencherTurno();
		preencherComboEquipamento();
		populaView(reservaEquipamentoList);


	}

	public void populaView(List<ReservaEquipamento> equipamento){

		colunaEquiepamento.setCellValueFactory(new PropertyValueFactory<ReservaEquipamento, String>("tipo"));
		colunaSala.setCellValueFactory(new PropertyValueFactory<ReservaEquipamento, String>("sala"));
		colunaDataFinal.setCellValueFactory(new PropertyValueFactory<ReservaEquipamento, LocalDate>("dataFinal"));
		colunaDataIncial.setCellValueFactory(new PropertyValueFactory<ReservaEquipamento, LocalDate>("dataInicial"));
		colunaUnidade.setCellValueFactory(new PropertyValueFactory<ReservaEquipamento, String>("unidade"));

		equipamentoView = FXCollections.observableArrayList(reservaEquipamentoList);
		tabelaVewer.setItems(equipamentoView);
	}

	public void preencherComboEquipamento(){

		List<String> teste = new ArrayList<String>();
		List<CadEquipamento> unidade = new ArrayList<CadEquipamento>();
		unidade = equipamentoDAO.listarEquipamento();
		unidade.forEach( (CadEquipamento cliente) -> {
					teste.add(cliente.getTipo());
				}


		);
		teste.forEach((String testes)->{
					comboEquipamento.getItems().add(testes);
				}
		);
	}


	public void pegarValores(){
		reserva.setNome("Usuario");
		reserva.setEquipamento(comboEquipamento.getValue());
		reserva.setUnidade(comboUnidade.getValue());
		reserva.setDataInicial(dataInicial.getValue());
		reserva.setDataFinal(dataFim.getValue());
		reserva.setTurno(comboTurno.getValue());
		reserva.setHoraInicial(horaInicial.getText());
		reserva.setHoraFinal(horaFim.getText());
		reserva.setSala(comboSala.getValue());

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
	public void setarValor(ReservaEquipamento reserva){
		comboTurno.setValue(reserva.getTurno());
		comboSala.setValue(reserva.getSala());
		comboEquipamento.setValue(reserva.getEquipamento());
		comboUnidade.setValue(reserva.getUnidade());
		dataFim.setValue(reserva.getDataFinal());
		dataInicial.setValue(reserva.getDataInicial());


	}
	public boolean validarCampos(){
		String sala = reserva.getSala();
		String unidade = reserva.getUnidade();
		String turno = reserva.getTurno();
		String equipamento = reserva.getEquipamento();
		LocalDate inicial = reserva.getDataInicial();
		LocalDate  fim = reserva.getDataFinal();

		List<Control> controls = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("");
		if(equipamento == null){
			sb.append("Selecione um Equipamento!. \n");
			controls.add(comboEquipamento);
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
		if(inicial == null){
			sb.append("Selecione a data Inicial!. \n");
			controls.add(dataInicial);
		}
		if(fim == null){
			sb.append("Selecione a data final!. \n");
			controls.add(dataFim);
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
