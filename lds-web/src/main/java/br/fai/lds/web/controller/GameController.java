package br.fai.lds.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.lds.model.Jogo;
import br.fai.lds.web.service.GameService;

@Controller
@RequestMapping("/game")
public class GameController {

	@Autowired
	private GameService gameService;

	@GetMapping("/listar")
	public String getListPage(final Model model) {

		final List<Jogo> games = gameService.readAll();
		model.addAttribute("listaJogos", games);

		return "game/list";
	}

	@GetMapping("/detalhes")
	public String getDetailPage() {
		return "game/detail";
	}

	@GetMapping("/editar")
	public String getEditPage() {
		return "game/edit";
	}

	@GetMapping("/criar")
	public String getCreatePage() {
		return "game/create";
	}

}
