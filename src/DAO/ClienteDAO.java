package DAO;

import Model.Login;
import daoUtil.ConnectionFactory;

import java.sql.*;


/**
 * Created by lucas.pereira on 06/04/2017.
 */
public class ClienteDAO {

    private Connection con;
    private Statement  stmt;
    private PreparedStatement stm;


    private String sqlListaCliente ="Select * from cliente";

    public ClienteDAO() throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        con = (Connection) factory.getConnection();

    }


}
