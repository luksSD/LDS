package br.fai.lds.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.springframework.stereotype.Repository;

import br.fai.lds.db.connection.ConnectionFactory;
import br.fai.lds.db.dao.AccountDao;
import br.fai.lds.model.Usuario;

@Repository
public class AccountDaoImpl implements AccountDao {

	@Override
	public Long create(final Usuario entity) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String sql = "INSERT INTO usuario";
		sql += " (nome_usuario, senha, nome_completo, ";
		sql += "email, tipo, esta_ativo, data_nascimento, ";
		sql += "ultimo_acesso, criado_em) ";
		sql += "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

		Long id = Long.valueOf(-1);

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, entity.getNomeUsuario());
			preparedStatement.setString(2, entity.getSenha());
			preparedStatement.setString(3, entity.getNomeCompleto());
			preparedStatement.setString(4, entity.getEmail());
			preparedStatement.setString(5, "USUARIO");
			preparedStatement.setBoolean(6, true);
			preparedStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			preparedStatement.setTimestamp(9, new Timestamp(System.currentTimeMillis()));

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
