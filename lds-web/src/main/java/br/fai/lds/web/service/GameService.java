package br.fai.lds.web.service;

import java.util.List;

import br.fai.lds.model.Jogo;

public interface GameService {

	List<Jogo> readAll();

	Long create(Jogo entity);

	Jogo readById(Long id);

	boolean update(Jogo entity);

	boolean deleteById(Long id);

}