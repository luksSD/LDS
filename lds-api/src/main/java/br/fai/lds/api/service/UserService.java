package br.fai.lds.api.service;

import java.util.List;
import java.util.Map;

import br.fai.lds.model.Usuario;

public interface UserService {

	List<Usuario> readAll();

	List<Usuario> readByCriteria(Map<String, String> criteria);

	Usuario readById(Long id);

	boolean update(Usuario entity);

	boolean delete(Long id);

	Usuario validateLogin(String encodedData);

}
