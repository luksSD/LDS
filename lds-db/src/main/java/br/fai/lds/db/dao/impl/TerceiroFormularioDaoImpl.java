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
import br.fai.lds.db.dao.TerceiroFormularioDao;
import br.fai.lds.model.TerceiroFormulario;

@Repository
public class TerceiroFormularioDaoImpl implements TerceiroFormularioDao {

	@Override
	public List<TerceiroFormulario> readAll() {

		final List<TerceiroFormulario> terceiroFormulario = new ArrayList<TerceiroFormulario>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			final String sql = "SELECT * FROM terceiro_formulario;";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				final TerceiroFormulario formulario = new TerceiroFormulario();
				formulario.setId(resultSet.getLong("id"));
				formulario.setNoticia(resultSet.getString("noticia"));
				formulario.setQuantidadePaginas(resultSet.getLong("quantidade_paginas"));
				formulario.setTitulo(resultSet.getString("titulo"));

				terceiroFormulario.add(formulario);
			}

		} catch (final Exception e) {

		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		return terceiroFormulario;
	}

	@Override
	public Long create(final TerceiroFormulario entity) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		final String sql = "INSERT INTO terceiro_formulario (noticia, quantidade_paginas, titulo) VALUES(?, ?, ?);";

		Long id = Long.valueOf(-1);

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, entity.getNoticia());
			preparedStatement.setLong(2, entity.getQuantidadePaginas());
			preparedStatement.setString(3, entity.getTitulo());

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
