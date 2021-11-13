package br.fai.lds.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.lds.api.service.JwtService;
import br.fai.lds.api.service.UserService;
import br.fai.lds.db.dao.UserDao;
import br.fai.lds.model.Usuario;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	
	@Autowired
	private JwtService jwtService;

	@Override
	public List<Usuario> readAll() {

		return dao.readAll();
	}

	@Override
	public Usuario readById(final Long id) {

		return dao.readById(id);
	}

	@Override
	public boolean update(final Usuario entity) {

		if (entity == null) {
			return false;
		}

		return dao.update(entity);
	}

	@Override
	public boolean delete(final Long id) {

		return dao.delete(id);
	}

	@Override
	public List<Usuario> readByCriteria(final Map<String, String> criteria) {

		if (criteria == null || criteria.size() == 0) {
			return new ArrayList<Usuario>();
		}

		return dao.readByCriteria(criteria);
	}

	@Override
	public Usuario validateLogin(final String encodedData) {

		final Map<CREDENCIAIS, String> credentialsMap = decodeAndGetUsernameAndPassword(encodedData);

		if (credentialsMap == null || credentialsMap.size() != 2) {
			return null;

		}

		final String username = credentialsMap.get(CREDENCIAIS.USUARIO);
		final String password = credentialsMap.get(CREDENCIAIS.SENHA);

		final Usuario user = dao.validadeUsernameAndPassword(username, password);
		
		if (user == null) {
			return null;
		}
		
		final String token = jwtService.getJWTToken(user);
		
		user.setSenha(null);
		user.setToken(token);
		
		return user;

	}

	private enum CREDENCIAIS {

		USUARIO, 
		SENHA,
	}

	private Map<CREDENCIAIS, String> decodeAndGetUsernameAndPassword(final String encodedData) {

		// Basic + dados
		final String[] splittedData = encodedData.split("Basic ");

		if (splittedData.length != 2) {
			return null;
		}

		// dados
		final byte[] decodedBytes = Base64.getDecoder().decode(splittedData[1]);

		try {

			// Username=nome_usuario;Password=senha
			final String decodedString = new String(decodedBytes, "utf-8");

			final String[] firstPart = decodedString.split("Username=");

			if (firstPart.length != 2) {
				return null;
			}

			// nome_usuario;Password=senha
			final String[] credentials = firstPart[1].split(";Password=");

			if (credentials.length != 2) {
				return null;
			}

			final Map<CREDENCIAIS, String> credentialsMap = new HashMap<CREDENCIAIS, String>();
			credentialsMap.put(CREDENCIAIS.USUARIO, credentials[0]);
			credentialsMap.put(CREDENCIAIS.SENHA, credentials[1]);

			return credentialsMap;

		} catch (final UnsupportedEncodingException e) {
			System.out.println(e.getMessage());

			return null;
		}
	}

}
