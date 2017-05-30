package dao;

import daoutil.ConnectionFactory;
import model.Sala;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ritchely on 23/05/2017.
 */
public class SalaDAO {
    ConnectionFactory connection = null;
    private Connection con;
    private Statement stm;
    private PreparedStatement stmt;

    public SalaDAO() {
        ConnectionFactory cf = new ConnectionFactory();
        con = cf.getConnection();
    }


    String sqlSalvar = "INSERT INTO projetoIntegrador.cadastroSala" +
            "(nome,tipo,capacidade, unidade,bloco)" +
            "VALUES(?,?,?,?,?)";

    String sqlEditar = "UPDATE clientes SET nome = ?, sobrenome = ?," +
            "cpf = ?, rg = ?, telefone = ?, celular = ?, endereco = ?," +
            "email = ?  WHERE id = ?";

    String sqlDeletar = "DELETE from clientes where id = ?";


    public String salvar(Sala cliente) throws SQLException {


        String salvo = "falha";


        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement(sqlSalvar);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTipo());
            stmt.setString(3, cliente.getUnidade());
            stmt.setString(4, cliente.getCapacidade());
            stmt.setString(5, cliente.getBloco());

            stmt.executeUpdate();

            //Grava as informações se caso de problema os dados não são gravados
            con.commit();
            salvo = "salvo";

        } catch (SQLException e) {
            if (con != null) {
                try {
                    System.err.print("Rollback efetuado na transação");
                    con.rollback();
                } catch (SQLException e2) {
                    System.err.print("Erro na transação!" + e2);
                    salvo = "\"Erro na transação!\"+e2";
                }
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            con.setAutoCommit(true);
        }

        return salvo;
    }

    public List<Sala> listarSalas() {
        List<Sala> list = new ArrayList<Sala>();
        ResultSet res = null;

        try {
            stm = con.createStatement();
            res = stm.executeQuery("SELECT * FROM cadastroSala");

            while (res.next()) {

                Sala cliente = new Sala();

                cliente.setId(res.getInt("id"));
                cliente.setNome(res.getString("nome"));
                cliente.setTipo(res.getString( "tipo"));
                cliente.setUnidade(res.getString("unidade"));
                cliente.setCapacidade(res.getString("capacidade"));
                cliente.setBloco(res.getString("bloco"));

                list.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta1:" + e.getMessage());
        }
        return list;
    }
    public String editar(Sala cliente) throws SQLException {
        String salvo = "falha";
        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement(sqlEditar);

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTipo());
            stmt.setString(3, cliente.getUnidade());
            stmt.setString(4, cliente.getCapacidade());
            stmt.setString(5, cliente.getBloco());

            stmt.executeUpdate();
            con.commit();
            salvo = "salvo";


        }catch (Exception e){
            System.out.println("erro ao atualizar " + e.getMessage());
            salvo = e.getMessage();
        }
        return salvo;
    }

    public String deletar(Sala cliente) throws SQLException {
        String salvo = "falha";
        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement(sqlEditar);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTipo());
            stmt.setString(3, cliente.getUnidade());
            stmt.setString(4, cliente.getCapacidade());
            stmt.setString(5, cliente.getBloco());

            stmt.executeUpdate();
            con.commit();
            salvo = "salvo";


        }catch (Exception e){
            System.out.println("erro ao atualizar " + e.getMessage());
            salvo = e.getMessage();
        }
        return salvo;
    }
}
