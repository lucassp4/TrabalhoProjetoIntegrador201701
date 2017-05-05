package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Login;

import javax.swing.*;

import dao.LoginDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LongSummaryStatistics;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControleLogin implements Initializable{

	
	
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



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

	public void Login(ActionEvent event) throws SQLException{

		Login usuario = new Login();
		LoginDAO userDAO = new LoginDAO();
		Boolean log = null;

		try{

			if(txtUsuario.getText().equals("")|| txtUsuario.getText().equals("") && txtSenha.getText().equals(null)||txtSenha.getText().equals("")){
				//JOptionPane.showMessageDialog(null,"O campo de usuário e senha estão vazios!!!");
				msgLogin.setText("O campo de usuário e senha estão vazios!!!");
				msgLogin.setVisible(true);
			}else{
				usuario.setNome(txtUsuario.getText());
				usuario.setSenha(txtSenha.getText());
				log = userDAO.buscarUsuario(usuario);
				if (log	== true ){
					Stage stage = new Stage();
					Parent root	= null;
					try{
						root = FXMLLoader.load(getClass().getResource("../View/PaginaPrincipal.fxml"));
					}catch (IOException e){
						Logger.getLogger(ControleLogin.class.getName()).log(Level.SEVERE, null, e);
					}
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
					stage.setTitle("Sistema Gestão de Recursos Audiovisuais - UNIALFA");

					btnLogin.getScene().getWindow().hide();
				}
			}

		}catch (Exception e1){

		}

	}
	public void limpaMsg(){
		msgLogin.setText("Login incorreto!");
		msgLogin.setVisible(false);
	}
	
}
		
		
	
	
	

