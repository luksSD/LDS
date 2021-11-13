package br.fai.lds.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import br.fai.lds.api.security.ApiSecurityCostants;
import br.fai.lds.api.service.JwtService;
import br.fai.lds.model.Usuario;
import io.jsonwebtoken.Jwts;

@Service
public class JwtServiceImpl implements JwtService {

	@Override
	public String getJWTToken(final Usuario usuario) {
		
		try {
			
			final List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getTipo()));
			
			final String token = Jwts.builder()
					.setId("LDS_FAI")
					.setSubject(usuario.getNomeUsuario())
					.claim("authorities", grantedAuthorities.stream().map(GrantedAuthority::getAuthority)
					.collect(Collectors.toList()))
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + 600000))
					.signWith(ApiSecurityCostants.KEY).compact();
			
			return token;
			
			
		} catch (final Exception e) {
			System.err.println(e.getMessage());
			return ApiSecurityCostants.INVALID_TOKEN;
		}
		
		
	}

}
