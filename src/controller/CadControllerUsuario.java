package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.Usuario;
import negocio.UsuarioNegocio;

public class CadControllerUsuario implements Initializable {

	@FXML
	private TableView<Usuario> tabelaUsuario;
	@FXML
	private TableColumn<Usuario, String> colunaNome;
	@FXML
	private TableColumn<Usuario, String> colunaTelefone;
	@FXML
	private TableColumn<Usuario, String> colunaEndereco;
	@FXML
	private TableColumn<Usuario, String> colunaMatricula;
	@FXML
	private TableColumn<Usuario, String> colunaCelular;
	@FXML
	private TableColumn<Usuario, String> colunaEmail;
	@FXML
	private TableColumn<Usuario, String> colunaFuncao;
	@FXML
	private TableColumn<Usuario, Integer> colunaId;

	@FXML
	private TextField txtId;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtTelefone;
	@FXML
	private TextField txtEndereco;
	@FXML
	private TextField txtMatricula;
	@FXML
	private TextField txtCelular;
	@FXML
	private TextField txtEmail;
	@FXML
	private ComboBox<String> comboFuncao;
	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnCancelar;
	@FXML
	private Pane painelPrincipal;

	List<Usuario> usuarios = new ArrayList();
	ObservableList<Usuario> usuariosView = null;
	UsuarioNegocio usuarioNegocio = new UsuarioNegocio();

	static Usuario usuario = new Usuario();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if(usuario != null){
			
		}
		preenchercomboFuncao();
	}

	public void preenchercomboFuncao() {
		comboFuncao.getItems().add("Professor");
		comboFuncao.getItems().add("Coordenador");
		comboFuncao.getItems().add("Audio visual");
	}
	
	public List<Usuario> listarUsuarios(){
		usuarios = usuarioNegocio.listarUsuario();
		return usuarios;
	}
	
	@FXML
	public void btnSalvar() {
		usuario.setNome(txtNome.getText());
		usuario.setTelefone(txtTelefone.getText());
		usuario.setCelular(txtCelular.getText());
		usuario.setMatricula(txtMatricula.getText());
		usuario.setEmail(txtEmail.getText());
		usuario.setFuncao(comboFuncao.getPromptText());

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
	
	
	
	public boolean validarCampos(){
		
		String nome = txtNome.getText();
		String telefone = txtTelefone.getText();
		String celular = txtCelular.getText();
		String matricula = txtMatricula.getText();
		String email = txtEmail.getText();
		String funcao = comboFuncao.getValue();
		
		if(nome.equals("") || nome == null ||
				telefone.equals("") || telefone == null ||
				celular.equals("") || celular == null ||
				matricula.equals("") || matricula == null ||
				email.equals("") || email == null ||
				funcao.equals("") || funcao == null){
			return false;
		}		
		return true;
	}

}
