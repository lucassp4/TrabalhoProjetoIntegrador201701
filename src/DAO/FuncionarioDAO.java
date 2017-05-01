package DAO;

import Model.Funcionario;
import daoUtil.ConnectionFactory;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas.pereira on 28/04/2017.
 */
public class FuncionarioDAO {

    private Connection con;
    private Statement stmt;
    private PreparedStatement stm;

    //conexao
    public FuncionarioDAO() throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        con = factory.getConnection();

    }


    // comandos
    private String INSERIR      = "INSERT INTO usuarios() VALUES(?, ?, ?))";
    private String ATUALIZAR    = "UPDATE usuarios SET =?, =?,  = STR_TO_DATE(?, '%d/%m/%Y') WHERE id = ?";
    private String BUSCAR       = "SELECT * FROM usuarios WHERE ID = ?";
    private String BUSCAR_TODOS = "SELECT * FROM usuarios";
    private String APAGAR       = "DELETE FROM usuarios WHERE id = ?";


    public List<Funcionario> buscarTodas() throws SQLException {
        //Criando um objeto de listas para guardar todos usuarios
        List<Funcionario> listaUser = new ArrayList<>();

        try {
            stm = con.prepareStatement(BUSCAR_TODOS);
            ResultSet resultadoBusca = stm.executeQuery();
            while (resultadoBusca.next()) {
                Funcionario user = extraiUsuario(resultadoBusca); // coleta do banco de dados a lista de usuários
                listaUser.add(user);

            }
            stm.close();//fecha o execute query
            con.close(); //fecha a conexão
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR BUSCANDO TODAS AS USUARIOS.");
            System.exit(0);
        }
        return listaUser;
    }

    // extrain o objeto Conta do result set
    private Funcionario extraiUsuario(ResultSet resultadoBusca) throws SQLException, ParseException {

        // instanciando o objeto
        Funcionario user = new Funcionario();

        // fazendo o get da tabela de usuários
        user.setId(resultadoBusca.getInt(1));
        user.setNome(resultadoBusca.getString(2));
        user.setSenha(resultadoBusca.getString(3));
        user.setTelefone(resultadoBusca.getString(4));
        user.setCelular(resultadoBusca.getString(5));
        user.setMatricula(resultadoBusca.getString(6));
        user.setEmail(resultadoBusca.getString(7));

        return user;
    }


    public Funcionario buscaPorId(int id) {
        Funcionario user = null;
        try {
            stm = con.prepareStatement(BUSCAR);
            stm.setInt(1, id);
            ResultSet resultadoBusca = stm.executeQuery();
            resultadoBusca.next();
            user = extraiUsuario(resultadoBusca);
            stm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR BUSCANDO USUÁRIO COM ID " + id);
            System.exit(0);
        }
        return user;
    }


    public void apagarUser(int id) {
        try {
            stm = con.prepareStatement(APAGAR);
            stm.setInt(1, id);
            stm.executeUpdate();
            stm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR APAGANDO USUÁRIO COM ID " + id);
            System.exit(0);
        }
    }


}
