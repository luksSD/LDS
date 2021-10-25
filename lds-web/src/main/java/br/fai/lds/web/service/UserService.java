package br.fai.lds.web.service;

import java.util.List;

import br.fai.lds.model.Usuario;

/**
 *
 * @author Lucas
 *
 *         Classe Service de Usuario com CRUD.
 *
 */

public interface UserService {

	List<Usuario> readAll();

	Usuario readById(Long id);

	boolean update(Usuario entity);

	boolean deleteById(Long id);

}
