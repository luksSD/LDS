package br.fai.lds.db.dao;

import java.util.List;

import br.fai.lds.model.Jogo;

public interface GameDao {

	List<Jogo> readAll();

	Jogo readById(Long id);

	Long create(Jogo entity);

	boolean update(Jogo entity);

	boolean delete(Long id);

}
