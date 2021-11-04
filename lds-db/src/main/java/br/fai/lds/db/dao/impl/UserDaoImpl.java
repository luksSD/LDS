package br.fai.lds.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.fai.lds.db.connection.ConnectionFactory;
import br.fai.lds.db.dao.UserDao;
import br.fai.lds.model.Usuario;

/**
 *
 * @author Lucas Dias
 *
 */

@Repository
public class UserDaoImpl implements UserDao {

	@Override
	public List<Usuario> readAll() {

		final List<Usuario> users = new ArrayList<Usuario>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			final String sql = "SELECT * FROM usuario;";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				final Usuario user = new Usuario();
				user.setId(resultSet.getLong("id"));
				user.setNomeUsuario(resultSet.getString("nome_usuario"));
				user.setSenha(resultSet.getString("senha"));
				user.setNomeCompleto(resultSet.getString("nome_completo"));
				user.setEmail(resultSet.getString("email"));
				user.setTipo(resultSet.getString("tipo"));
				user.setEstaAtivo(resultSet.getBoolean("esta_ativo"));
				user.setDataNascimento(resultSet.getTimestamp("data_nascimento"));
				user.setUltimoAcesso(resultSet.getTimestamp("ultimo_acesso"));
				user.setCriadoEm(resultSet.getTimestamp("criado_em"));
				user.setAvatar(resultSet.getBytes("avatar"));

				users.add(user);
			}

		} catch (final Exception e) {

		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		return users;
	}

	@Override
	public Usuario readById(final Long id) {

		Usuario user = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			final String sql = "SELECT * FROM usuario WHERE id = ?;";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				user = new Usuario();
				user.setId(resultSet.getLong("id"));
				user.setNomeUsuario(resultSet.getString("nome_usuario"));
				user.setSenha(resultSet.getString("senha"));
				user.setNomeCompleto(resultSet.getString("nome_completo"));
				user.setEmail(resultSet.getString("email"));
				user.setTipo(resultSet.getString("tipo"));
				user.setEstaAtivo(resultSet.getBoolean("esta_ativo"));
				user.setDataNascimento(resultSet.getTimestamp("data_nascimento"));
				user.setUltimoAcesso(resultSet.getTimestamp("ultimo_acesso"));
				user.setCriadoEm(resultSet.getTimestamp("criado_em"));
				user.setAvatar(resultSet.getBytes("avatar"));

			}

		} catch (final Exception e) {

		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		return user;
	}

	@Override
	public boolean update(final Usuario entity) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE usuario Set nome_completo = ?, ";
		sql += " email = ?  ";
		sql += " WHERE id = ?;  ";

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, entity.getNomeCompleto());
			preparedStatement.setString(2, entity.getEmail());
			preparedStatement.setLong(3, entity.getId());

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

		final String sql = "DELETE FROM usuario WHERE id = ?; ";

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
	public List<Usuario> readByCriteria(final Map<String, String> criteria) {

		final List<Usuario> users = new ArrayList<Usuario>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM usuario ";
			sql += " WHERE TRUE ";

			for (final String key : criteria.keySet()) {
				sql += "  " + key + " '" + criteria.get(key) + "'";
			}

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				final Usuario user = new Usuario();
				user.setId(resultSet.getLong("id"));
				user.setNomeUsuario(resultSet.getString("nome_usuario"));
				user.setSenha(resultSet.getString("senha"));
				user.setNomeCompleto(resultSet.getString("nome_completo"));
				user.setEmail(resultSet.getString("email"));
				user.setTipo(resultSet.getString("tipo"));
				user.setEstaAtivo(resultSet.getBoolean("esta_ativo"));
				user.setDataNascimento(resultSet.getTimestamp("data_nascimento"));
				user.setUltimoAcesso(resultSet.getTimestamp("ultimo_acesso"));
				user.setCriadoEm(resultSet.getTimestamp("criado_em"));
				user.setAvatar(resultSet.getBytes("avatar"));

				users.add(user);
			}

		} catch (final Exception e) {

			System.out.println(e.getMessage());

		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		return users;
	}

	@Override
	public Usuario validadeUsernameAndPassword(final String username, final String password) {
		Usuario user = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			final String sql = "SELECT * FROM usuario WHERE nome_usuario = ? AND senha = ?;";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				user = new Usuario();
				user.setId(resultSet.getLong("id"));
				user.setNomeUsuario(resultSet.getString("nome_usuario"));
				user.setSenha(resultSet.getString("senha"));
				user.setNomeCompleto(resultSet.getString("nome_completo"));
				user.setEmail(resultSet.getString("email"));
				user.setTipo(resultSet.getString("tipo"));
				user.setEstaAtivo(resultSet.getBoolean("esta_ativo"));
				user.setDataNascimento(resultSet.getTimestamp("data_nascimento"));
				user.setUltimoAcesso(resultSet.getTimestamp("ultimo_acesso"));
				user.setCriadoEm(resultSet.getTimestamp("criado_em"));
				user.setAvatar(resultSet.getBytes("avatar"));

			}

		} catch (final Exception e) {

			System.out.println(e.getMessage());

		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		return user;
	}

}
