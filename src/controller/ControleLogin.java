package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Login;

import javax.swing.*;

import dao.LoginDAO;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LongSummaryStatistics;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControleLogin implements Initializable {


	@FXML
	private TextField txtUsuario;
	@FXML
	private TextField txtSenha;
	@FXML
	private Button btnLogin;
	@FXML
	private Pane panel;
	@FXML
	private Label msgSenha;
	@FXML
	private Label msgLogin;

	Login login = new Login();

	Main main = null;

	public void setarValor(){
		login.setNome(txtSenha.getText());
		login.setSenha(txtSenha.getText());
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


	}

	public void Login(ActionEvent event) throws SQLException {
		setarValor();

		if (login.getNome().equals("admin") && login.getNome().equals("admin")){

		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/view/PaginaPrincipal.fxml");
		Parent fxmlParent;
		try {
			fxmlParent = FXMLLoader.load(arquivoFXML);
			panel.getChildren().clear();
			panel.getChildren().add(fxmlParent);
		} catch (IOException e) {
			e.printStackTrace();
		}
}else{
			exibeMensagem("Usuario e senha incorreta");
		}


	}

		public void limpaMsg() {
			msgLogin.setText("Login incorreto!");
			msgLogin.setVisible(false);
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


	
	
	

