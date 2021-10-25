package br.fai.lds.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fai.lds.api.service.TerceiroFormularioService;
import br.fai.lds.model.TerceiroFormulario;

@RestController
@RequestMapping("/api/v1/terceiro-formulario")
@CrossOrigin(origins = "*")
public class TerceiroFormularioRestController {

	@Autowired
	private TerceiroFormularioService service;

	@GetMapping("/read-all")
	public ResponseEntity<List<TerceiroFormulario>> readAll() {

		return ResponseEntity.ok(service.readAll());
	}

	@PostMapping("/create")
	public ResponseEntity<Long> create(@RequestBody final TerceiroFormulario terceiroFormulario) {

		return ResponseEntity.ok(service.create(terceiroFormulario));
	}

}
