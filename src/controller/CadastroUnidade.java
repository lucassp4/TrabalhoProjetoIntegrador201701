package controller;

import dao.BlocoDao;
import dao.UnidadeDao;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Control;
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
import negocio.UnidadeNegocio;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
	private Button btnSalvar;
	
	@FXML
	private Button btnCancelar; 
	
	Unidade unidadeM = new Unidade();
	Main main = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public void btnSalvar() throws SQLException {
		UnidadeDao unidadeDao = new UnidadeDao();
		pegarValores();
		boolean validar;
		boolean validarCep;
		boolean validarCnpj;
		boolean validarTelefone;

		validar = validarCampos();
		UnidadeNegocio unidadeNegocio = new UnidadeNegocio();
		validarTelefone = unidadeNegocio.validarTelefone(unidadeM.getTelefone());
		validarCep = unidadeNegocio.validarCep(unidadeM.getCep());
		validarCnpj = unidadeNegocio.verificarCampo(unidadeM.getCnpj());

		if (validar) {
			if (validarCep) {
				if (validarCnpj) {
					if(validarTelefone) {


						unidadeDao.salvar(unidadeM);

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
						exibeMensagem("Por favor digite um telefone valido");
					}
				} else {
					exibeMensagem("Por favor digite um CNPJ valido!");
				}
			}else{
				exibeMensagem("Por favor digite um Cep valido!");
			}
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

		unidadeM.setNome(txtNomeUnidade.getText());
		unidadeM.setEndereco(txtEndereco.getText());
		unidadeM.setCnpj(txtCnpj.getText());
		unidadeM.setRazaoSocial(txtRazao.getText());
		unidadeM.setTelefone(txtTelefone.getText());
		unidadeM.setCep(txtCep.getText());
		unidadeM.setCidade(txtCidade.getText());
		unidadeM.setEstado(txtEstado.getText());
		unidadeM.setFantacia(txtFantacia.getText());


	}


	public boolean validarCampos(){

		String nome =unidadeM.getNome();
		String endereco= unidadeM.getEndereco();
		String cnpj =  unidadeM.getCnpj();
		String razao = unidadeM.getRazaoSocial();
		String telefone = unidadeM.getTelefone();
		String cep =  unidadeM.getCep();
		String cidade = unidadeM.getCidade();
		String estado =  unidadeM.getEstado();
		String fantacia = unidadeM.getFantacia();


		List<Control> controls = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("");
		if(nome.equals("") || nome == null){
			sb.append("O nome não pode ser vazio!. \n");
			controls.add(txtNomeUnidade);
		}
		if(endereco.equals("") || endereco== null){
			sb.append(" O Endereço nao pode ser vazio !. \n");
			controls.add(txtEndereco);
		}
		if(cnpj.equals("") || cnpj == null){
			sb.append("O CNPJ nao pode ser vazio!. \n");
			controls.add(txtCnpj);
		}
		if(razao.equals("") || razao == null){
			sb.append("A razão socil não pode ser vazio!. \n");
			controls.add(txtRazao);
		}
		if(telefone.equals("")|| telefone == null){
			sb.append(" O telefone nao pode ser vazio !. \n");
			controls.add(txtTelefone);
		}
		if(cep.equals("") || cep == null){
			sb.append("O Cep nao pode ser vazio!. \n");
			controls.add(txtCep);
		}
		if(cidade.equals("") || cidade == null){
			sb.append(" A cidade nao pode ser vazia  !. \n");
			controls.add(txtCidade);
		}
		if(estado.equals("") || estado == null){
			sb.append("O estado nao pode ser vazio!. \n");
			controls.add(txtEstado);
		}
		if(fantacia.equals("") || fantacia == null){
			sb.append("O nome fantacia nao pode ser vazio!. \n");
			controls.add(txtFantacia);
		}

		if(!sb.equals("")) {
			exibeMensagem(sb.toString());
			animaCamposValidados(controls);
		}

		return sb.toString().isEmpty();
	}




	public void exibeMensagem(String msg){
		Notifications.create()
				.title("Atensão")
				.text(String.valueOf(msg))
				.owner(main)
				.hideAfter(Duration.seconds(6))
				.darkStyle()
				.position(Pos.TOP_RIGHT)
				.showInformation();


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
