package br.fai.lds.web.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.lds.model.Usuario;
import br.fai.lds.web.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Override
	public Long create(final Usuario entity) {

		Long id = Long.valueOf(-1);

		final String endpoint = "http://localhost:8085/api/v1/account/create-user";

		try {
			final RestTemplate restTemplate = new RestTemplate();

			final HttpEntity<Usuario> httpEntity = new HttpEntity<>(entity);

			final ResponseEntity<Long> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity,
					Long.class);

			id = responseEntity.getBody();

		} catch (final Exception e) {

			System.out.println(e.getMessage());
		}

		return id;
	}

}
