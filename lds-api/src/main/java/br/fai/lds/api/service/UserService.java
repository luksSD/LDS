package br.fai.lds.api.service;

import java.util.List;

import br.fai.lds.model.Usuario;

public interface UserService {

	List<Usuario> readAll();

	Usuario readById(Long id);

	boolean update(Usuario entity);

	boolean delete(Long id);

}
