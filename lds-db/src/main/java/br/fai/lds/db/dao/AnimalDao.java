package br.fai.lds.db.dao;

import java.util.List;

import br.fai.lds.model.Animal;

public interface AnimalDao {

	List<Animal> readAll();

	Animal readById(Long id);

	boolean update(Animal entity);

	boolean delete(Long id);

	Long create(Animal entity);

}
