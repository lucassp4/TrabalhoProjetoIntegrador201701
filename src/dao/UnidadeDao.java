package dao;

import model.CadastroBloco;
import model.Unidade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import daoutil.ConnectionFactory;

/**
 * Created by Ritchely on 04/05/2017.
 */
public class UnidadeDao {


    ConnectionFactory connection = null;
    private Connection con;
    private Statement stm;
    private PreparedStatement stmt;

    public UnidadeDao() throws SQLException {
        ConnectionFactory cf = new ConnectionFactory();
        con = cf.getConnection();
    }

    String sqlSalvar = "INSERT INTO projetoIntegrador.cadastrounidade" +
            "(nome,endereco,telefone,cnpj,razaoSocial,fantacia,cidade,cep,estado)" +
            "VALUES(?,?,?,?,?,?,?,?,?)";

    String sqlEditar = "update cadastrounidade set nome = ?, endereco = ?," +
            "telefone = ?, cnpj = ?, razaoSocial = ?, fantacia = ?, cidade = ?," +
            "cep = ? , estado = ?  WHERE id = ?;";

    String sqlDeletar = "DELETE from cadastrounidade where id = ?";

    public String salvar(Unidade unidade) throws SQLException {

        String salvo = "falhar";

        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement(sqlSalvar);

            stmt.setString(1, unidade.getNome());
            stmt.setString(2, unidade.getEndereco());
            stmt.setString(3, unidade.getTelefone());
            stmt.setString(4, unidade.getCnpj());
            stmt.setString(5, unidade.getRazaoSocial());
            stmt.setString(6, unidade.getFantacia());
            stmt.setString(7, unidade.getCidade());
            stmt.setString(8, unidade.getCep());
            stmt.setString(9, unidade.getEstado());


            stmt.executeUpdate();
            con.commit();
            salvo = "salvo";
        } catch (SQLException e) {
            if (con != null) {
                try {
                    System.err.print("Rollbacl efetuado na transão" + e.getMessage());
                    con.rollback();
                } catch (SQLException e2) {
                    System.err.print("erro na transão e2" + e2.getMessage());
                    salvo = "\n erro na transão  " + e2.getMessage();
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

    public List<Unidade> listarClientes() {
        List<Unidade> list = new ArrayList<Unidade>();
        ResultSet res = null;

        try {
            stm = con.createStatement();
            res = stm.executeQuery("SELECT * FROM cadastrounidade");

            while (res.next()) {

                Unidade unidade = new Unidade();

                unidade.setId(res.getInt("id"));
                unidade.setNome(res.getString("nome"));
                unidade.setTelefone(res.getString("telefone"));
                unidade.setEndereco(res.getString("endereco"));
                unidade.setCep(res.getString("cep"));
                unidade.setFantacia(res.getString("fantacia"));
                unidade.setEstado(res.getString("estado"));
                unidade.setCidade(res.getString("cidade"));
                unidade.setCnpj(res.getString("cnpj"));
                unidade.setRazaoSocial(res.getString("razaoSocial"));
                list.add(unidade);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta1:" + e.getMessage());
        }
        return list;
    }

    public String editar(Unidade unidade) throws SQLException {

        String salvo = "falhar";

        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement(sqlEditar);

            stmt.setString(1, unidade.getNome());
            stmt.setString(2, unidade.getEndereco());
            stmt.setString(3, unidade.getTelefone());
            stmt.setString(4, unidade.getCnpj());
            stmt.setString(5, unidade.getRazaoSocial());
            stmt.setString(6, unidade.getFantacia());
            stmt.setString(7, unidade.getCidade());
            stmt.setString(8, unidade.getCep());
            stmt.setString(9, unidade.getEstado());
            stmt.setInt(10, unidade.getId());

            stmt.executeUpdate();
            con.commit();
            salvo = "salvo";


        } catch (Exception e) {
            System.out.println("erro ao atualizar " + e.getMessage());
            salvo = e.getMessage();
        }
        return salvo;
    }

    public String excluir(Unidade unidade) {
        String deletado = "falha";
        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement(sqlDeletar);

            stmt.setInt(1, unidade.getId());

            stmt.executeUpdate();
            con.commit();
            deletado = "deletado";

        } catch (SQLException e) {
            System.out.println("Erro na exclusão :" + e.getMessage());
            deletado = e.getMessage();
        }
        return deletado;
    }
}

