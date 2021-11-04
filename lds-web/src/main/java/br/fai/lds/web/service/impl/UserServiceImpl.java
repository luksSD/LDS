package br.fai.lds.web.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.lds.model.Usuario;
import br.fai.lds.web.service.RestService;
import br.fai.lds.web.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public List<Usuario> readAll() {

		final String endpoint = "http://localhost:8085/api/v1/user/read-all";

		List<Usuario> response = null;

		try {

			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<String> httpEntity = new HttpEntity<String>("");

			final ResponseEntity<Usuario[]> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET,
					httpEntity, Usuario[].class);

			response = Arrays.asList(requestResponse.getBody());

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public Usuario readById(final Long id) {

		final String endpoint = "http://localhost:8085/api/v1/user/read-by-id/" + id;

		Usuario response = null;

		try {

			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<String> httpEntity = new HttpEntity<String>("");

			final ResponseEntity<Usuario> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity,
					Usuario.class);

			response = requestResponse.getBody();

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public boolean update(final Usuario entity) {

		boolean response = false;

		final String endpoint = "http://localhost:8085/api/v1/user/update";

		try {
			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<Usuario> httpEntity = new HttpEntity<Usuario>(entity);

			final ResponseEntity<Boolean> responseEntity = restTemplate.exchange(endpoint, HttpMethod.PUT, httpEntity,
					Boolean.class);

			response = responseEntity.getBody();

		} catch (final Exception e) {

			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public boolean deleteById(final Long id) {

		boolean response = false;

		final String endpoint = "http://localhost:8085/api/v1/user/delete/" + id;

		try {

			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<String> httpEntity = new HttpEntity<String>("");

			final ResponseEntity<Boolean> requestResponse = restTemplate.exchange(endpoint, HttpMethod.DELETE,
					httpEntity, Boolean.class);

			response = requestResponse.getBody();

		} catch (final Exception e) {

			System.out.println(e.getMessage());
		}

		return response;

	}

	@Override
	public List<Usuario> readByCriteria(final String value) {

		if (value.isEmpty()) {
			return new ArrayList<Usuario>();
		}

		final String endpoint = "http://localhost:8085/api/v1/user/read-by-criteria";

		List<Usuario> response = null;

		try {
			final Map<String, String> criteria = new HashMap<String, String>();
			criteria.put("AND nome_completo ilike", "%" + value + "%");

			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<Map<String, String>> httpEntity = new HttpEntity<Map<String, String>>(criteria);

			final ResponseEntity<Usuario[]> requestResponse = restTemplate.exchange(endpoint, HttpMethod.POST,
					httpEntity, Usuario[].class);

			response = Arrays.asList(requestResponse.getBody());

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public Usuario validateUserNameAndPassword(final String username, final String password) {

		Usuario usuario = null;

		final String endpoint = "http://localhost:8085/api/account/login";

		final RestTemplate restTemplate = new RestTemplate();

		try {
			final HttpHeaders httpHeaders = RestService.getAuthenticationHeaders(username, password);
			final HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);

			final ResponseEntity<Usuario> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity,
					Usuario.class);

			if (responseEntity.getStatusCode() != HttpStatus.OK) {
				return null;
			}

			usuario = responseEntity.getBody();

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return usuario;
	}

}
