package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import daoutil.ConnectionFactory;
import model.Usuario;

/**
 * Created by lucas.pereira on 28/04/2017.
 */
public class UsuarioDAO {
	
	public boolean create(Usuario usuario){
		Connection conn = new ConnectionFactory().getConnection();
		
		String sql = "insert into usuario"
				+ "values(?);";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, usuario.getId());
			
			stmt.execute();
			stmt.close();
		}catch (SQLException e){
			System.out.println(e.getMessage());
			return false;
		}finally{
			try {
				conn.close();
			}catch (SQLException ex){
				System.out.println(ex.getMessage());
			}
		}
		return true;
	}

	public static Usuario getByName(String nome){
		
		Usuario usuario = null;
		
		Connection conn = new ConnectionFactory().getConnection();
		
		String sql = "select * from usuario where nome = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nome);
			
			ResultSet rs = stmt.executeQuery();
			
			rs.getString("nome");
			rs.close();
			stmt.close();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally{
			try{
				conn.close();
			}catch(SQLException ex){
				System.out.println(ex.getMessage());
			}
		}
		return usuario;
	}
	
	public boolean update(Usuario usuario)  {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sql = "update usuario set"
				+ "where nome = ?;";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			
			stmt.execute();
			stmt.close();
		}catch (SQLException e){
			System.out.println(e.getMessage());
			return false;
		}finally{
			try {
				conn.close();
			}catch (SQLException ex){
				System.out.println(ex.getMessage());
			}
		}
		return true;
	}
	
	public boolean delete(Usuario usuario){
		Connection conn = new ConnectionFactory().getConnection();
		
		String sql = "delete from usuario where nome = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e){
			System.out.println(e.getMessage());
			return false;
		}finally{
			try{
				conn.close();
			}catch(SQLException ex){
				System.out.println(ex.getMessage());
			}
		}
		return true;
	}
	

}
