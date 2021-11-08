package br.fai.lds.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.lds.model.Usuario;
import br.fai.lds.web.security.provider.LdsAuthenticationProvider;
import br.fai.lds.web.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private LdsAuthenticationProvider authenticationProvider;

	@PostMapping("/read-by-criteria")
	public String getByCriteria(final String value, final Model model) {

		final List<Usuario> users = userService.readByCriteria(value);

		model.addAttribute("listaUsuarios", users);

		return "user/list";
	}

	@GetMapping("/listar")
	public String getListPage(final Model model) {

		final List<Usuario> users = userService.readAll();
		model.addAttribute("listaUsuarios", users);

		return "user/list";
	}

	@GetMapping("/detalhes/{id}")
	public String getDetailPage(@PathVariable("id") final Long id, final Model model) {

		final Usuario userModel = userService.readById(id);
		model.addAttribute("usuario", userModel);

		return "user/detail";
	}

	@GetMapping("/editar/{id}")
	public String getEditPage(@PathVariable("id") final Long id, final Model model) {

		final Usuario userModel = userService.readById(id);
		model.addAttribute("usuario", userModel);

		return "user/edit";
	}

	@PostMapping("/update")
	public String update(final Usuario usuario, final Model model) {

		userService.update(usuario);

		return getDetailPage(usuario.getId(), model);

	}

	@GetMapping("/deletar/{id}")
	public String delete(@PathVariable("id") final Long id, final Model model) {

		userService.deleteById(id);

		return getListPage(model);

	}

	@GetMapping("/profile")
	public String getProfile(final Model model) {

		final Usuario user = authenticationProvider.getAuthenticatedUser();
		model.addAttribute("usuario", user);

		return "user/detail";
	}

}
