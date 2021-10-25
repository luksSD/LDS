package br.fai.lds.web.service;

import java.util.List;

import br.fai.lds.model.Animal;

public interface AnimalService {

	List<Animal> readAll();

	Animal readById(Long id);

	boolean update(Animal entity);

	boolean delete(Long id);

	Long create(Animal entity);

}
