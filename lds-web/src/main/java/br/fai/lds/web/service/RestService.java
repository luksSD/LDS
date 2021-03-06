package br.fai.lds.web.service;

import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.fai.lds.model.Usuario;
import br.fai.lds.web.security.CustomUserDetails;

public class RestService {

	public static HttpHeaders getAuthenticationHeaders(final String username, final String password) {

		final String auth = "Username=" + username + ";Password=" + password;

		try {
			final byte[] encondedBytes = Base64.getEncoder().encode(auth.getBytes("utf-8"));

			System.out.println("Dados em formato base64: " + new String(encondedBytes));

			final String header = "Basic " + new String(encondedBytes);

			final HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", header);

			return headers;

		} catch (final Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		

	}
	
	public static HttpHeaders getRequestHeaders() {
		
		try {
			
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			final CustomUserDetails userDatails = (CustomUserDetails) authentication.getPrincipal();

			final Usuario usuarioLogado = userDatails.getUsuario();

			System.out.println("Cliente - Token do Usuario: " + usuarioLogado.getToken());
			
			final String authHeader = "Bearer " + usuarioLogado.getToken();
			
			final HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", authHeader);
			
			return headers;
			
		} catch (final Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}

}
