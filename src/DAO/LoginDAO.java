package DAO;

import daoUtil.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by lucas.pereira on 17/04/2017.
 */
public class LoginDAO {

    private Connection con;
    private Statement stmt;
    private PreparedStatement stm;


    private String sqlLogin = "Select * from login";

    public LoginDAO() throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        con = factory.getConnection();

    }



}
