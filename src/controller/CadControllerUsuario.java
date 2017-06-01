package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.UsuarioDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import model.Usuario;

public class CadControllerUsuario implements Initializable {

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
	@FXML
	private Label errorMessage;
	@FXML
	private Label salvoMessage;

	ObservableList<Usuario> usuariosView = null;

	static Usuario usuario = new Usuario();
	UsuarioDAO usuarioDAO = new UsuarioDAO();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		usuario = new Usuario();
		preenchercomboFuncao();
	}

	public void preenchercomboFuncao() {
		comboFuncao.getItems().add("Professor");
		comboFuncao.getItems().add("Coordenador");
		comboFuncao.getItems().add("Audio visual");
	}

	@FXML
	public void btnSalvar() {
		if (validarCampos() == true) {
			usuario.setNome(txtNome.getText());
			usuario.setTelefone(txtTelefone.getText());
			usuario.setCelular(txtCelular.getText());
			usuario.setMatricula(txtMatricula.getText());
			usuario.setEmail(txtEmail.getText());
			usuario.setFuncao(comboFuncao.getSelectionModel().getSelectedItem().toString());
			usuario.save();
			salvoMessage.setText("Cadastro realizado com sucesso!");
			errorMessage.setText("");
			limpaCampos();
		} else {
			errorMessage.setText("Os campos com (*) devem ser preenchidos obrigatoriamente");
		}
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

	public boolean validarCampos() {

		String nome = txtNome.getText();
		String telefone = txtTelefone.getText();
		String celular = txtCelular.getText();
		String matricula = txtMatricula.getText();
		String email = txtEmail.getText();
		String funcao = comboFuncao.getValue();

		if (nome.equals("") || nome == null || telefone.equals("") || telefone == null || celular.equals("")
				|| celular == null || matricula.equals("") || matricula == null || email.equals("") || email == null
				|| funcao.equals("") || funcao == null) {
			return false;
		}
		return true;
	}

	public void limpaCampos() {
		txtNome.setText("");
		txtTelefone.setText("");
		txtCelular.setText("");
		txtMatricula.setText("");
		txtEmail.setText("");
		comboFuncao.getSelectionModel().clearSelection();
	}

}
