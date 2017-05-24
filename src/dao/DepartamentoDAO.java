package dao;

import model.Departamento;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import daoUtil.ConnectionFactory;

/**
 * Created by lucas.pereira on 01/05/2017.
 */
public class DepartamentoDAO {


    private Connection con;
    private Statement stmt;
    private PreparedStatement stm;

    //conexao
    public DepartamentoDAO() throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        con = factory.getConnection();

    }


    // comandos SQL
    private String INSERIR      = "INSERT INTO departamento() VALUES(?, ?, ?))";
    private String ATUALIZAR    = "UPDATE departamento SET =? WHERE id = ?";
    private String BUSCAR       = "SELECT * FROM departamento WHERE ID = ?";
    private String BUSCAR_TODOS = "SELECT * FROM departamento";
    private String APAGAR       = "DELETE FROM departamento WHERE id = ?";


    // metodo de pesquisar todos deparamentos
    public List<Departamento> buscarTodas() throws SQLException {
        //Criando um objeto de listas para guardar todos usuarios
        List<Departamento> listaDepartamento = new ArrayList<>();

        try {
            stm = con.prepareStatement(BUSCAR_TODOS);
            ResultSet resultadoBusca = stm.executeQuery();
            while (resultadoBusca.next()) {
                Departamento dpto = extraiDepartamento(resultadoBusca); // coleta do banco de dados a lista de usuários
                listaDepartamento.add(dpto);

            }
            stm.close();//fecha o execute query
            con.close(); //fecha a conexão
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR BUSCANDO TODAS AS DEPARTAMENTO.");
            System.exit(0);
        }
        return listaDepartamento;
    }


    // extrain o objeto Conta do result set
    private Departamento extraiDepartamento(ResultSet resultadoBusca) throws SQLException, ParseException {

        // instanciando o objeto
        Departamento dpto = new Departamento();

        // fazendo o get da tabela de usuários
        dpto.setId(resultadoBusca.getInt(1));
        dpto.setNome(resultadoBusca.getString(2));

        return dpto;
    }


    //metodo de busca por id deparamento
    public Departamento buscaPorId(int id) {
        Departamento dpto = null;
        try {
            stm = con.prepareStatement(BUSCAR);
            stm.setInt(1, id);
            ResultSet resultadoBusca = stm.executeQuery();
            resultadoBusca.next();
            dpto = extraiDepartamento(resultadoBusca);
            stm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR BUSCANDO DEPARTAMENTO COM ID " + id);
            System.exit(0);
        }
        return dpto;
    }


    //metodo para apagar deparatamento
    public void apagarDepartamento(int id) {
        try {
            stm = con.prepareStatement(APAGAR);
            stm.setInt(1, id);
            stm.executeUpdate();
            stm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR APAGANDO DEPARTAMENTO COM ID " + id);
            System.exit(0);
        }
    }

    //metodo para atualizar um departamento
    public void atualizar(Departamento dpto) {
        try {
            stm = con.prepareStatement(ATUALIZAR);
            //stm.setInt(1, dpto.getId());
            stm.setString(2, dpto.getNome());
            stm.executeUpdate();
            stm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR ATUALIZANDO DEPARTAMENTO COM ID " + dpto.getId());
            System.exit(0);
        }

    }


}
