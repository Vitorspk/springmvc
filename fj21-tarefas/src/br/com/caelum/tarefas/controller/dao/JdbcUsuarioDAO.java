package br.com.caelum.tarefas.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.caelum.tarefas.controller.Usuario;

@Repository
public class JdbcUsuarioDAO extends DAOException{
	private static final long serialVersionUID = 1L;
	private Connection connection;
	
	@Autowired
	public JdbcUsuarioDAO(DataSource dataSource) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		try {
		    this.connection = dataSource.getConnection();
		  } catch (SQLException e) {
		    throw new RuntimeException(e);
		  }
	}

	public boolean existeUsuario(Usuario usuario) {
		String sql = "select * from usuarios where login = ?  and senha = ?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = null;
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			stmt.executeQuery();
			rs = stmt.getResultSet();
			// verifica se existe retorno na consulta
			if (rs.next()) {
				stmt.close();
				return true;
			} else {
				stmt.close();
				return false;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
