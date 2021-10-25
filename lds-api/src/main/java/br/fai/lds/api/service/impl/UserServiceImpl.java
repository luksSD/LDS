package br.fai.lds.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.lds.api.service.UserService;
import br.fai.lds.db.dao.UserDao;
import br.fai.lds.model.Usuario;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

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

}
