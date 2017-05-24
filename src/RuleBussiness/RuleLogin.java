package RuleBussiness;

import DAO.LoginDAO;
import Model.Login;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by lucas.pereira on 05/05/2017.
 */


public class RuleLogin {

    private LoginDAO loginDAO;


//    public String Salvar(Login usuario){
//        boolean usuarioValido = false;
//        boolean senhaValida = false;
//
//        StringBuilder sb = new StringBuilder();
//        usuarioValido = validaUsuario(loginDAO.getLogin());
//        if (!usuarioValido) {
//            sb.append("login precisa ter mais de 6 caracteres. \n");
//        }
//        senhaValida = validaSenha(usuario.getSenha());
//        if (!senhaValida) {
//            sb.append("Senha precisa ter mais de 6 caracteres. \n");
//        }
//        if (sb.toString().isEmpty()) {
//            try {
//                sb.append(usuarioDao.salvar(usuario));
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } else {
//            sb.append("falha");
//            return sb.toString();
//        }
//
//        return sb.toString();
//    }

    //VALIDANDO A CRIAÇÃO DE SENHA
    private boolean validaSenha(String senha) {
        if(senha.length() < 6){
            return false;
        }else {
            return true;
        }
    }

    //VALIDANDO A CRIAÇÃO DE USUARIO
    private boolean validaUsuario(String login) {
        if(login.length() < 6){
            return false;
        }else{
            return true;
        }

    }

    //BUSCA O USUARIO DE LOGIN NO BANCO DE DADOS
    public Login buscaPorLogin(String login) throws SQLException{
        loginDAO = new LoginDAO();
        Login usuario = new Login();
        try {
           usuario = loginDAO.getUsuario(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

}// FIM DA CLASSE RULELOGIN

