package br.fai.lds.db.dao;

import java.util.List;

import br.fai.lds.model.TerceiroFormulario;

public interface TerceiroFormularioDao {

	List<TerceiroFormulario> readAll();

	Long create(TerceiroFormulario entity);

}
