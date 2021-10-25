package br.fai.lds.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.fai.lds.db.connection.ConnectionFactory;
import br.fai.lds.db.dao.AnimalDao;
import br.fai.lds.model.Animal;

@Repository
public class AnimalDaoImpl implements AnimalDao {

	@Override
	public List<Animal> readAll() {

		final List<Animal> animals = new ArrayList<Animal>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			final String sql = "SELECT * FROM animal;";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				final Animal animal = new Animal();
				animal.setId(resultSet.getLong("id"));
				animal.setNome(resultSet.getString("nome"));
				animal.setDescricao(resultSet.getString("descricao"));
				animal.setIdade(resultSet.getLong("idade"));

				animals.add(animal);
			}

		} catch (final Exception e) {

		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		return animals;
	}

	@Override
	public Animal readById(final Long id) {

		Animal animal = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			final String sql = "SELECT * FROM animal WHERE id = ?;";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				animal = new Animal();

				animal.setId(resultSet.getLong("id"));
				animal.setNome(resultSet.getString("nome"));
				animal.setDescricao(resultSet.getString("descricao"));
				animal.setIdade(resultSet.getLong("idade"));

			}

		} catch (final Exception e) {

		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		return animal;
	}

	@Override
	public boolean update(final Animal entity) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE animal Set nome = ?, ";
		sql += " descricao = ?  ";
		sql += " idade = ?  ";
		sql += " WHERE id = ?;  ";

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, entity.getNome());
			preparedStatement.setString(2, entity.getDescricao());
			preparedStatement.setLong(3, entity.getIdade());
			preparedStatement.setLong(4, entity.getId());

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

		final String sql = "DELETE FROM animal WHERE id = ?; ";

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

	@Override
	public Long create(final Animal entity) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String sql = "INSERT INTO animal";
		sql += " (nome, descricao, idade) ";
		sql += "VALUES(?, ?, ?);";

		Long id = Long.valueOf(-1);

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, entity.getNome());
			preparedStatement.setString(2, entity.getDescricao());
			preparedStatement.setLong(3, entity.getIdade());

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

}
