package br.fai.lds.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.lds.api.service.AccountService;
import br.fai.lds.db.dao.AccountDao;
import br.fai.lds.model.Usuario;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao dao;

	@Override
	public Long create(final Usuario entity) {

		return dao.create(entity);
	}

}
