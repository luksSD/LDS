package br.fai.lds.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fai.lds.api.service.UserService;
import br.fai.lds.model.Usuario;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserRestController {

	@Autowired
	private UserService service;

	@GetMapping("/read-all")
	public ResponseEntity<List<Usuario>> readAll() {

		return ResponseEntity.ok(service.readAll());
	}

	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Usuario> readById(@PathVariable("id") final Long id) {

		return ResponseEntity.ok(service.readById(id));
	}

	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody final Usuario usuario) {

		return ResponseEntity.ok(service.update(usuario));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") final Long id) {

		return ResponseEntity.ok(service.delete(id));
	}

}
