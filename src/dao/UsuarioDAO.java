package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import daoutil.ConnectionFactory;
import model.Usuario;

/**
 * Created by lucas.pereira on 28/04/2017.
 */
public class UsuarioDAO {

	public boolean create(Usuario usuario) {
		Connection conn = new ConnectionFactory().getConnection();

		String sql = "insert into usuario " + "nome, telefone, celular, matricula, email, funcao "
				+ "values(?, ?, ?, ?, ?, ?);";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getTelefone());
			stmt.setString(3, usuario.getCelular());
			stmt.setString(4, usuario.getMatricula());
			stmt.setString(5, usuario.getEmail());
			stmt.setString(6, usuario.getFuncao());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			try {
				conn.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return true;
	}

	public static Usuario getByName(String nome) {

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
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return usuario;
	}

	public boolean update(Usuario usuario) throws SQLException {
		Connection conn = new ConnectionFactory().getConnection();

		String sql = "update usuario set " + "where nome = ?;";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getTelefone());
			stmt.setString(3, usuario.getCelular());
			stmt.setString(4, usuario.getMatricula());
			stmt.setString(5, usuario.getEmail());
			stmt.setString(6, usuario.getFuncao());

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			conn.close();
		}
		return true;
	}

	public boolean delete(Usuario usuario) throws SQLException {
		Connection conn = new ConnectionFactory().getConnection();

		String sql = "delete from usuario where nome = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			conn.close();
		}
		return true;
	}

	public static ArrayList<Usuario> getAll() {
		
		ArrayList<Usuario> usuarios = new ArrayList();
		
		Connection conn = new ConnectionFactory().getConnection();
		
		String sql = "select * from usuario";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()){
				Usuario usuario = new  Usuario();
				
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setCelular(rs.getString("celular"));
				usuario.setMatricula(rs.getString("matricula"));
				usuario.setEmail(rs.getString("email"));
				usuario.setFuncao(rs.getString("funcao"));
			
				usuarios.add(usuario);
			}
		}catch (SQLException e){
			System.out.println();
		}
		return usuarios;
	}

}
