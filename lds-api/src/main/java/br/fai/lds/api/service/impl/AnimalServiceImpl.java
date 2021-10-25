package br.fai.lds.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.lds.api.service.AnimalService;
import br.fai.lds.db.dao.AnimalDao;
import br.fai.lds.model.Animal;

@Service
public class AnimalServiceImpl implements AnimalService {

	@Autowired
	private AnimalDao dao;

	@Override
	public List<Animal> readAll() {

		return dao.readAll();
	}

	@Override
	public Animal readById(final Long id) {

		return dao.readById(id);
	}

	@Override
	public boolean update(final Animal entity) {

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
	public Long create(final Animal entity) {
		return dao.create(entity);
	}

}
