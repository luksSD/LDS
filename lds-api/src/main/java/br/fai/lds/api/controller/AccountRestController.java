package br.fai.lds.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fai.lds.api.service.AccountService;
import br.fai.lds.api.service.UserService;
import br.fai.lds.model.Usuario;

@RestController
@RequestMapping("/api/v1/account")
@CrossOrigin(origins = "*")
public class AccountRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private AccountService service;

	@PostMapping("/create-user")
	public ResponseEntity<Long> create(@RequestBody final Usuario usuario) {

		return ResponseEntity.ok(service.create(usuario));
	}

	@PostMapping("/login")
	public ResponseEntity<Usuario> login(@RequestHeader("Authorization") final String encodedData) {

		final Usuario usuario = userService.validateLogin(encodedData);

		if (usuario == null) {
			return ResponseEntity.badRequest().build();

		}

		return ResponseEntity.ok(usuario);

	}

}
