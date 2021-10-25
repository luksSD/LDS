package br.fai.lds.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.lds.api.service.GameService;
import br.fai.lds.db.dao.GameDao;
import br.fai.lds.model.Jogo;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameDao dao;

	@Override
	public List<Jogo> readAll() {

		return dao.readAll();
	}

	@Override
	public Jogo readById(final Long id) {

		return dao.readById(id);
	}

	@Override
	public Long create(final Jogo entity) {

		return dao.create(entity);
	}

	@Override
	public boolean update(final Jogo entity) {

		return dao.update(entity);
	}

	@Override
	public boolean delete(final Long id) {

		return dao.delete(id);
	}

}
