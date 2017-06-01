package controller;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Login;

import javax.swing.*;

import dao.LoginDAO;
import model.Usuario;
import negocio.UsuarioNegocio;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControleLogin implements Initializable {
//	InicialController inicialController = new InicialController();
	Usuario usuario = new Usuario();
	UsuarioNegocio usuarioNegocio = new UsuarioNegocio();



	@FXML
	private TextField txtUsuario;
	@FXML
	private PasswordField pswSenha;
	@FXML
	private Label lbUsuarioInvalido;
	@FXML
	private Label lbSenhaInvalida;
	@FXML
	private Pane panePrincipal;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	@FXML
	// METODO PARA LOGIN
	public void Login() throws IOException {
		Boolean validacao = false;
		validacao = validarCampos();
		Usuario usuarioBanco = new Usuario();
		if(validacao) {
			usuario.setLogin(txtUsuario.getText());
			usuario.setSenha(pswSenha.getText());
			usuarioBanco = usuarioNegocio.buscaPorLogin(usuario.getLogin());
		}
		if(usuarioBanco.getId() != 0){
			if(!(usuarioBanco.getLogin().equals(usuario.getLogin()))){
				lbUsuarioInvalido.setVisible(true);
			}else {
				if (!(usuarioBanco.getSenha().equals(usuario.getSenha()))){
					lbSenhaInvalida.setVisible(true);
				}else{
					usuario =  setarUsuarioLogado(usuarioBanco);
//					inicialController.setarUsuario(usuarioBanco);
					URL arquivoFXML;
					arquivoFXML = getClass().getResource("/Visao/inicial.fxml");
					Parent fxmlParent =(Parent) FXMLLoader.load(arquivoFXML);
					panePrincipal.getChildren().clear();
					panePrincipal.getChildren().add(fxmlParent);
					this.usuario = usuarioBanco;



				}
			}
		}else{
			lbUsuarioInvalido.setVisible(true);
		}

	}

	// METODO PARA VALIDAR OS CAMPOS DE LOGIN
	public boolean validarCampos(){

		String usuario = txtUsuario.getText();
		String senha = pswSenha.getText();


		List<Control> controls = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("");
		if(usuario.equals("") || usuario == null){
			sb.append("O usuário não pode ser vazio!. \n");
			controls.add(txtUsuario);
		}
		if(senha.equals("") || senha == null){
			sb.append("A senha não pode ser vazia!. \n");
			controls.add(pswSenha);
		}
		if(!sb.toString().equals("")) {
			exibeMensagem(sb.toString());
			animaCamposValidados(controls);
		}
		return sb.toString().isEmpty();
	}

	//METODO QUE ANIMA OS CAMPOS NA VALIDAÇÃO
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

	//METODO PARA EXIBIR MENSAGENS
	public void exibeMensagem(String msg){
		Notifications.create()
				.title("Atenção")
				.text(String.valueOf(msg))
				.owner(panePrincipal)
				.hideAfter(Duration.seconds(3))
				.darkStyle()
				.position(Pos.TOP_RIGHT)
				.showInformation();


	}

	// METODO QUE IRÁ VALIDAR O USUÁRIO LOGADO NO SISTEMA.
	public Usuario setarUsuarioLogado(Usuario usuario){
		usuario = this.usuario;
//		inicialController.SetarUsuarioSessao(usuario);
		return usuario;
	}

	}


	
	
	

