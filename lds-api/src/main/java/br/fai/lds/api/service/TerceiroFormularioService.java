package br.fai.lds.api.service;

import java.util.List;

import br.fai.lds.model.TerceiroFormulario;

public interface TerceiroFormularioService {

	List<TerceiroFormulario> readAll();

	Long create(TerceiroFormulario entity);

}
