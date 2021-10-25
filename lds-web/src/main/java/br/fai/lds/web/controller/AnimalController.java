package br.fai.lds.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.lds.model.Animal;
import br.fai.lds.web.service.AnimalService;

@Controller
@RequestMapping("/animal")
public class AnimalController {

	@Autowired
	private AnimalService animalService;

	@GetMapping("/register-page")
	public String getRegisterPage(final Animal animal) {

		return "animal/register";
	}

	@PostMapping("/create")
	public String create(final Animal animal) {

		final Long id = animalService.create(animal);

		if (id != -1) {
			return "redirect:/animal/detalhes/" + id;
		}

		return "redirect:/animal/listar";
	}

	@GetMapping("/listar")
	public String getListPage(final Model model) {

		final List<Animal> animals = animalService.readAll();
		model.addAttribute("listaAnimal", animals);

		return "animal/list";
	}

	@GetMapping("/detalhes/{id}")
	public String getDetailPage(@PathVariable("id") final Long id, final Model model) {

		final Animal animalModel = animalService.readById(id);
		model.addAttribute("animal", animalModel);

		return "animal/detail";
	}

	@GetMapping("/editar/{id}")
	public String getEditPage(@PathVariable("id") final Long id, final Model model) {

		final Animal animalModel = animalService.readById(id);
		model.addAttribute("animal", animalModel);

		return "animal/edit";
	}

	@PostMapping("/update")
	public String update(final Animal animal, final Model model) {

		animalService.update(animal);

		return getDetailPage(animal.getId(), model);

	}

	@GetMapping("/deletar/{id}")
	public String delete(@PathVariable("id") final Long id, final Model model) {

		animalService.delete(id);

		return getListPage(model);

	}

}
