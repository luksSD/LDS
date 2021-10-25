package br.fai.lds.web.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.lds.model.Jogo;
import br.fai.lds.web.service.GameService;

@Service
public class GameServiceImpl implements GameService {

	@Override
	public List<Jogo> readAll() {

		final String endpoint = "http://localhost:8085/api/v1/game/read-all";

		List<Jogo> response = null;

		try {

			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<String> httpEntity = new HttpEntity<String>("");

			final ResponseEntity<Jogo[]> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity,
					Jogo[].class);

			response = Arrays.asList(requestResponse.getBody());

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public Long create(final Jogo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Jogo readById(final Long id) {
		final String endpoint = "http://localhost:8085/api/v1/game/read-by-id" + id;

		Jogo response = null;

		try {

			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<String> httpEntity = new HttpEntity<String>("");

			final ResponseEntity<Jogo> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity,
					Jogo.class);

			response = requestResponse.getBody();

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public boolean update(final Jogo entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(final Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
