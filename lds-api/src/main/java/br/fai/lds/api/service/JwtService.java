package br.fai.lds.api.service;

import br.fai.lds.model.Usuario;

public interface JwtService {
	
	String getJWTToken(Usuario usuario);

}
