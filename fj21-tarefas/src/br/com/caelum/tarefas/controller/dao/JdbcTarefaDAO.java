package br.com.caelum.tarefas.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.caelum.tarefas.controller.Tarefa;

@Repository
public class JdbcTarefaDAO extends DAOException {
	private static final long serialVersionUID = 1L;

	// a conexão com o banco de dados
	private Connection connection;
	
	@Autowired
	public JdbcTarefaDAO(DataSource dataSource) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		try {
		    this.connection = dataSource.getConnection();
		  } catch (SQLException e) {
		    throw new RuntimeException(e);
		  }
	}

	public void adiciona(Tarefa tarefa) {
		String sql = "insert into tarefas " + "(descricao,finalizado)" + " values (?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt =  this.connection.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
//			stmt.setDate(3, new Date(tarefa.getDataFinalizacao().getTimeInMillis()));

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Tarefa> getLista() throws SQLException {
		PreparedStatement stmt =  this.connection.prepareStatement("select * from tarefas");
		ResultSet rs = stmt.executeQuery();
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		while (rs.next()) {
			// criando o objeto Tarefa
			Tarefa tarefa = new Tarefa();
			tarefa.setId(rs.getLong("id"));
			tarefa.setDescricao(rs.getString("descricao"));
			tarefa.setFinalizado(rs.getBoolean("finalizado"));
//			Calendar data = Calendar.getInstance();
//			data.setTime(rs.getDate("dataFinalizacao"));
//			tarefa.setDataFinalizacao(data);

			tarefas.add(tarefa);
		}
		rs.close();
		stmt.close();
		return tarefas;
	}
	
	public Tarefa getTarefa(Tarefa tarefa) throws SQLException {
		String sql = "select * from tarefas where id = ?";
		Tarefa tarefaSelecionada = new Tarefa();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, tarefa.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// criando o objeto Contato
				tarefaSelecionada.setId(rs.getLong("id"));
				tarefaSelecionada.setDescricao(rs.getString("descricao"));
				tarefaSelecionada.setFinalizado(rs.getBoolean("finalizado"));

//				Calendar data = Calendar.getInstance();
//				data.setTime(rs.getDate("dataFinalizacao"));
//				tarefaSelecionada.setDataFinalizacao(data);
			}			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return tarefaSelecionada;
	}
	
	
	//altera a tarefa adicionada.
	public void altera(Tarefa tarefa) {
	     String sql = "update tarefas set descricao=?, finalizado=? " +
	             " where id=?";
	     try {
	    	 PreparedStatement stmt =  connection.prepareStatement(sql);
	         stmt.setString(1, tarefa.getDescricao());
	         stmt.setBoolean(2, tarefa.isFinalizado());
//	         stmt.setDate(3, new Date(tarefa.getDataFinalizacao().getTimeInMillis()));
	         stmt.setLong(3, tarefa.getId());
	         stmt.execute();
	         stmt.close();
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	 }
	
	
	//Deleta uma tarefa pelo id
	 public void remove(Tarefa tarefa) {
	     try {
	    	 PreparedStatement stmt =  connection.prepareStatement("delete" +
	                 " from tarefas where id=?");
	         stmt.setLong(1, tarefa.getId());
	         stmt.execute();
	         stmt.close();
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	 }
	 
	 public Tarefa buscaPorId(Long id) throws SQLException {
			String sql = "select * from tarefas where id = ?";
			Tarefa tarefaSelecionada = new Tarefa();
			try {
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setLong(1, id);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					// criando o objeto Contato
					tarefaSelecionada.setId(rs.getLong("id"));
					tarefaSelecionada.setDescricao(rs.getString("descricao"));
					tarefaSelecionada.setFinalizado(rs.getBoolean("finalizado"));
//
//					Calendar data = Calendar.getInstance();
//					data.setTime(rs.getDate("dataFinalizacao"));
//					tarefaSelecionada.setDataFinalizacao(data);
				}			
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			return tarefaSelecionada;
		}

	public Tarefa finaliza(Long id) throws SQLException {
		String sql = "select * from tarefas where id = ?";
		Tarefa tarefaSelecionada = new Tarefa();
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			tarefaSelecionada.setId(rs.getLong("id"));
			tarefaSelecionada.setDescricao(rs.getString("descricao"));
			tarefaSelecionada.setFinalizado(rs.getBoolean("finalizado"));
		}
		stmt.execute();
		stmt.close();
		return tarefaSelecionada;
	}
}
