package dao;

import model.Auditorio;
import model.CadastroBloco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import daoutil.ConnectionFactory;

/**
 * Created by Ritchely on 04/05/2017.
 */
public class AuditorioDAO {
    ConnectionFactory connection = null;
    private Connection con;
    private Statement stm;
    private PreparedStatement stmt;

    public AuditorioDAO() throws SQLException {
        ConnectionFactory cf = new ConnectionFactory();
        con = cf.getConnection();
    }
    String sqlSalvar =  "INSERT INTO projetoIntegrador.cadastroAuditorio" + "(nome,capacidade,unidade,descricao)" +
            "VALUES(?,?,?,?)";

    public String salvar(Auditorio auditorio) throws SQLException{

        String salvo = "falhar";

        try{
            con.setAutoCommit(false);
            stmt = con.prepareStatement(sqlSalvar);
            stmt.setString(1, auditorio.getNome());
            stmt.setInt    (2, auditorio.getCapacidade());
            stmt.setString(3, auditorio.getUnidade());
            stmt.setString(4,auditorio.getDescricao());
            stmt.executeUpdate();
            con.commit();
            salvo = "salvo";
        }catch (SQLException e){
            if(con !=null){
                try{
                    System.err.print("Rollbacl efetuado na transão" +e.getMessage());
                    con.rollback();
                }catch(SQLException e2){
                    System.err.print("erro na transão e2" +e2.getMessage());
                    salvo = "\n erro na transão  "+e2.getMessage();
                }
            }
        }
        finally{
            if(stmt !=null){
                stmt.close();

            }
            con.setAutoCommit(true);
        }
        return salvo;
    }

    public List<CadastroBloco> listarClientes() {
        List<CadastroBloco> list = new ArrayList<CadastroBloco>();
        ResultSet res = null;

        try {
            stm = con.createStatement();
            res = stm.executeQuery("SELECT * FROM cadastroAuditorio");

            while (res.next()){

                CadastroBloco bloco = new CadastroBloco();

                bloco.setId(res.getInt("id"));
                bloco.setNome(res.getString("nome"));
                bloco.setDescricao(res.getString("descricao"));
                bloco.setUnidade(res.getString("unidade"));
                bloco.setNumeroSalas(res.getString("sala"));

                list.add(bloco);
            }
        }
        catch (SQLException e){
            System.out.println("Erro na consulta1:" + e.getMessage());
        }
        return list;
    }
}


