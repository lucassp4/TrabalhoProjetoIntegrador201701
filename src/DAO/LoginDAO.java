package DAO;


import Model.Funcionario;
import Model.Login;
import daoUtil.ConnectionFactory;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.sql.*;


/**
 * Created by lucas.pereira on 17/04/2017.
 */
public class LoginDAO {

    private Connection con;
    private Statement stmt;
    private PreparedStatement stm;


    public LoginDAO() throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        con = factory.getConnection();

    }


    //comando sql
    private String BUSCAR = "SELECT NOME, SENHA FROM FUNCIONARIO WHERE NOME=? AND SENHA=?";

    // metodo de pesquisar todos deparamentos
    public Boolean buscarUsuario(Login user) throws SQLException {

        Boolean log = null;

        try {
            stm = con.prepareStatement(BUSCAR);

            stm.setString(1, user.getNome());
            stm.setString(2, user.getSenha());

            ResultSet rs = stm.executeQuery();
            //rs.next();

            if(rs.next()){
                log = true;
            }else {
                JOptionPane.showMessageDialog(null, "Senha Incorreta!!");
                log = false;
            }

            stm.close();
            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return log;
    }
}
