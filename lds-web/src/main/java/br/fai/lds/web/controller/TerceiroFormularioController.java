package br.fai.lds.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.lds.model.TerceiroFormulario;
import br.fai.lds.web.service.TerceiroFormularioService;

@Controller
@RequestMapping("/terceiro-formulario")
public class TerceiroFormularioController {

	@Autowired
	private TerceiroFormularioService terceiroFormularioService;

	@GetMapping("/listar")
	public String getListPage(final Model model) {

		final List<TerceiroFormulario> formularios = terceiroFormularioService.readAll();
		model.addAttribute("terceiroFormulario", formularios);

		return "terceiro-formulario/list";
	}

	@GetMapping("/pagina-cadastro")
	public String getRegisterPage(final TerceiroFormulario terceiroFormulario) {

		return "terceiro-formulario/register";
	}

	@PostMapping("/create")
	public String create(final TerceiroFormulario terceiroFormulario) {

		final Long id = terceiroFormularioService.create(terceiroFormulario);

		if (id != -1) {
			return "redirect:/terceiro-formulario/listar";
		}

		return "redirect:/";
	}

}
