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

import br.fai.lds.api.service.GameService;
import br.fai.lds.model.Jogo;

@RestController
@RequestMapping("/api/v1/game")
@CrossOrigin(origins = "*")
public class GameRestController {

	@Autowired
	private GameService service;

	@GetMapping("/read-all")
	public ResponseEntity<List<Jogo>> readAll() {

		return ResponseEntity.ok(service.readAll());
	}

	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Jogo> readById(@PathVariable("id") final Long id) {

		return ResponseEntity.ok(service.readById(id));

	}

	public ResponseEntity<Long> Create(@RequestBody final Jogo game) {

		return ResponseEntity.ok(service.create(game));
	}

	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody final Jogo game) {

		return ResponseEntity.ok(service.update(game));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") final Long id) {

		return ResponseEntity.ok(service.delete(id));
	}

}
