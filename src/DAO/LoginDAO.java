package DAO;


import Model.Login;
import daoUtil.ConnectionFactory;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by lucas.pereira on 17/04/2017.
 */
public class LoginDAO {

    private Connection con;
    private PreparedStatement stmt;

    //CONEXÃO DO DANCO DE DADOS + FABRICA DE CONEXÃO
    public LoginDAO() throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        con = factory.getConnection();

    }


    //COMANDO SQL
    private String BUSCAR = "SELECT ID, NOME, SENHA FROM FUNCIONARIO WHERE NOME=?";

    // BUCA USUARIO DE LOGIN
    public Login getUsuario(String user) throws SQLException {

        List<Login> list = new ArrayList<Login>();

        try {
            stmt = con.prepareStatement(BUSCAR);

            stmt.setString(1, user);
           // stmt.setString(2, user.getSenha());

            ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    Login usuario = new Login();

                    usuario.setId(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setSenha(rs.getString("senha"));

                    list.add(usuario);

                }//FIM DO ENQUANTO

        }//FIIM TRY
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }//FIM CATCH

        if (!list.isEmpty()){
            return list.get(0);
        }
        else return new Login();

    }// FIM DO GETUSUARIO

}// FIM DA CLASSE
