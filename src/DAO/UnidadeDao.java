package dao;

import model.CadastroBloco;
import model.Unidade;
import daoutil.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    String sqlSalvar =  "INSERT INTO projetoIntegrador.cadastroUnidade" + "(nome,endereco,telefone,cnpj,razaoSocial,blocos)" +
            "VALUES(?,?,?,?,?,?)";

    public String salvar(Unidade unidade) throws SQLException{

        String salvo = "falhar";

        try{
            con.setAutoCommit(false);
            stmt = con.prepareStatement(sqlSalvar);
            stmt.setString(1, unidade.getNome());
            stmt.setString(2, unidade.getEndereco());
            stmt.setString(3, unidade.getTelefone());
            stmt.setString(4,unidade.getCnpj());
            stmt.setString(5, unidade.getRazaoSocial());
            stmt.setInt    (6, unidade.getBlocos());
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
                bloco.setNumeroSalas(res.getInt("sala"));

                list.add(bloco);
            }
        }
        catch (SQLException e){
            System.out.println("Erro na consulta1:" + e.getMessage());
        }
        return list;
    }
}


