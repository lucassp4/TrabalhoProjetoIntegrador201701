package dao;

import daoutil.ConnectionFactory;
import model.CadEquipamento;
import model.Unidade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoDAO {

	ConnectionFactory connection = null;
	private Connection con;
	private Statement stm;
	private PreparedStatement stmt;

	public EquipamentoDAO() {
		ConnectionFactory cf = new ConnectionFactory();
		con = cf.getConnection();
	}

	String sqlSalvar = "INSERT INTO projetoIntegrador.equipamento" + "(tipo,modelo, marca, dataCadastro, unidade)"
			+ "VALUES(?,?,?,?,?)";

	String sqlEditar = "UPDATE equipamento SET tipo = ?, modelo = ?," + "marca = ?, dataCadastro = ?, unidade = ?";


	String sqlDeletar = "DELETE from equipamento where id = ?";

	public String salvar(CadEquipamento equipamento) throws SQLException {
		Date date = java.sql.Date.valueOf(equipamento.getDataCadastro());
		String salvo = "falha";

		try {
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sqlSalvar);
			stmt.setString(1, equipamento.getTipo());
			stmt.setString(2, equipamento.getModelo());
			stmt.setString(3, equipamento.getMarca());
			stmt.setDate(4, (date));
			stmt.setString(5, equipamento.getUnidade());

			stmt.executeUpdate();

			con.commit();
			salvo = "salvo";

		} catch (SQLException e) {
			if (con != null) {
				try {
					System.err.print("Rollback efetuado na transa��o"+e.getMessage());
					con.rollback();
				} catch (SQLException e2) {
					System.err.print("Erro na transa��o!" + e2);
					salvo = "\"Erro na transa��o!\"+e2";
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

	
	public List<CadEquipamento> listarEquipamento() {
        List<CadEquipamento> list = new ArrayList<CadEquipamento>();
        ResultSet res = null;

        try {
            stm = con.createStatement();
            res = stm.executeQuery("SELECT * FROM Equipamento");

            while (res.next()) {

            	CadEquipamento equipamento = new CadEquipamento();

                equipamento.setId(res.getInt("id"));
                equipamento.setTipo(res.getString("tipo"));
                equipamento.setModelo(res.getString("modelo"));
                equipamento.setMarca(res.getString("marca"));
                Date dataCadastro = res.getDate("dataCadastro");
                equipamento.setUnidade(res.getString("unidade"));


                list.add(equipamento);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta:" + e.getMessage());
        }
        return list;
    }
	 
	
	public String editarr(CadEquipamento equipamento) throws SQLException {
		String salvo = "falha";
		try {
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sqlEditar);

			stmt.setString(1, equipamento.getTipo());
			stmt.setString(2, equipamento.getModelo());
			stmt.setString(3, equipamento.getMarca());
			stmt.setDate(4, Date.valueOf(equipamento.getDataCadastro()));
			stmt.setString(5, equipamento.getUnidade());

			stmt.executeUpdate();
			con.commit();
			salvo = "salvo";

		} catch (Exception e) {
			System.out.println("erro ao atualizar " + e.getMessage());
			salvo = e.getMessage();
		}
		return salvo;
	}
	public String excluir(CadEquipamento unidade) {
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