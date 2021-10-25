package br.fai.lds.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.lds.api.service.TerceiroFormularioService;
import br.fai.lds.db.dao.TerceiroFormularioDao;
import br.fai.lds.model.TerceiroFormulario;

@Service
public class TerceiroFormularioServiceImpl implements TerceiroFormularioService {

	@Autowired
	private TerceiroFormularioDao dao;

	@Override
	public List<TerceiroFormulario> readAll() {

		return dao.readAll();
	}

	@Override
	public Long create(final TerceiroFormulario entity) {

		return dao.create(entity);
	}
}
