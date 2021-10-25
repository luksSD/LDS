package br.fai.lds.web.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.lds.model.TerceiroFormulario;
import br.fai.lds.web.service.TerceiroFormularioService;

@Service
public class TerceiroFormularioServiceImpl implements TerceiroFormularioService {

	@Override
	public List<TerceiroFormulario> readAll() {
		final String endpoint = "http://localhost:8085/api/v1/terceiro-formulario/read-all";

		List<TerceiroFormulario> response = null;

		try {

			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<String> httpEntity = new HttpEntity<String>("");

			final ResponseEntity<TerceiroFormulario[]> requestResponse = restTemplate.exchange(endpoint, HttpMethod.GET,
					httpEntity, TerceiroFormulario[].class);

			response = Arrays.asList(requestResponse.getBody());

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}

		return response;
	}

	@Override
	public Long create(final TerceiroFormulario entity) {
		Long id = Long.valueOf(-1);

		final String endpoint = "http://localhost:8085/api/v1/terceiro-formulario/create";

		try {
			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<TerceiroFormulario> httpEntity = new HttpEntity<>(entity);

			final ResponseEntity<Long> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity,
					Long.class);

			id = responseEntity.getBody();

		} catch (final Exception e) {

			System.out.println(e.getMessage());
		}

		return id;
	}

}
