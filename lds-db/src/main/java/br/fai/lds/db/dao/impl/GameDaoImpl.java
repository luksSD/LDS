package br.fai.lds.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.fai.lds.db.connection.ConnectionFactory;
import br.fai.lds.db.dao.GameDao;
import br.fai.lds.model.Jogo;

@Repository
public class GameDaoImpl implements GameDao {

	@Override
	public List<Jogo> readAll() {

		final List<Jogo> games = new ArrayList<Jogo>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			final String sql = "SELECT * FROM jogo Order by 1;";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				final Jogo game = new Jogo();
				game.setId(resultSet.getLong("id"));
				game.setNome(resultSet.getString("nome"));
				game.setDesenvolvidoPor(resultSet.getString("desenvolvido_por"));
				game.setGenero(resultSet.getString("genero"));
				game.setPlataforma(resultSet.getString("plataforma"));
				game.setPreco(resultSet.getFloat("preco"));
				game.setDataLancamento(resultSet.getTimestamp("data_lancamento"));
				game.setDescricao(resultSet.getString("descricao"));

				game.setImagemPrincipal(resultSet.getString("imagem_principal"));

				games.add(game);
			}

		} catch (final Exception e) {

			System.out.println(e.getMessage());
		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		return games;
	}

	@Override
	public Jogo readById(final Long id) {

		Jogo game = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			final String sql = "SELECT * FROM jogo WHERE id = ?;";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				game = new Jogo();
				game.setId(resultSet.getLong("id"));
				game.setNome(resultSet.getString("nome"));
				game.setDesenvolvidoPor(resultSet.getString("desenvolvido_por"));
				game.setGenero(resultSet.getString("genero"));
				game.setPlataforma(resultSet.getString("plataforma"));
				game.setDataLancamento(resultSet.getTimestamp("data_lancamento"));
				game.setDescricao(resultSet.getString("descricao"));
				game.setImagemPrincipal(resultSet.getString("imagem_principal"));

			}

		} catch (final Exception e) {

		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		return game;

	}

	@Override
	public Long create(final Jogo entity) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String sql = "INSERT INTO jogo";
		sql += " (nome, desenvoldido_por, genero, ";
		sql += "plataforma, data_lancamento, descricao, imagem_principal) ";
		sql += "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

		Long id = Long.valueOf(-1);

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, entity.getNome());
			preparedStatement.setString(2, entity.getDesenvolvidoPor());
			preparedStatement.setString(3, entity.getGenero());
			preparedStatement.setString(4, entity.getPlataforma());
			preparedStatement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			preparedStatement.setString(5, "USUARIO");
			preparedStatement.setString(6, entity.getDescricao());
			preparedStatement.setString(7, entity.getImagemPrincipal());

			preparedStatement.execute();

			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next())
				;
			{
				id = resultSet.getLong(1);
			}

			connection.commit();

		} catch (final Exception e) {

			try {
				connection.rollback();

			} catch (final SQLException e1) {

				System.out.println(e1.getMessage());
			} finally {
				ConnectionFactory.close(resultSet, preparedStatement, connection);

			}

		}

		return id;

	}

	@Override
	public boolean update(final Jogo entity) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE jogo Set nome = ?, ";
		sql += " desenvolvido_por = ?  ";
		sql += " genero = ?  ";
		sql += " plataforma = ?  ";
		sql += " data_lancamento = ?  ";
		sql += " descricao = ?  ";
		sql += " WHERE id = ?;  ";

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, entity.getNome());
			preparedStatement.setString(2, entity.getDesenvolvidoPor());
			preparedStatement.setString(3, entity.getGenero());
			preparedStatement.setString(4, entity.getPlataforma());
			preparedStatement.setTimestamp(5, entity.getDataLancamento());
			preparedStatement.setString(6, entity.getDescricao());
			preparedStatement.setLong(7, entity.getId());

			preparedStatement.execute();

			connection.commit();

			return true;
		} catch (final Exception e) {

			try {
				connection.rollback();
			} catch (final SQLException e1) {
				System.out.println(e1.getMessage());
			}

			return false;
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
	}

	@Override
	public boolean delete(final Long id) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		final String sql = "DELETE FROM jogo WHERE id = ?, ";

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setLong(1, id);

			preparedStatement.execute();

			connection.commit();

			return true;
		} catch (final Exception e) {

			try {
				connection.rollback();
			} catch (final SQLException e1) {
				System.out.println(e1.getMessage());
			}

			return false;
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}

	}

}
