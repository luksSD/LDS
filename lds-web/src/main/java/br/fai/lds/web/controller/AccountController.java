package br.fai.lds.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.lds.model.Usuario;
import br.fai.lds.web.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping("/register-page")
	public String getRegisterPage(final Usuario usuario) {

		return "account/register";
	}

	@PostMapping("/create-user")
	public String create(final Usuario usuario) {

		final Long id = accountService.create(usuario);

		if (id != -1) {
			return "redirect:/user/detalhes/" + id;
		}

		return "redirect:/user/listar";
	}

	@GetMapping("/login-page")
	public String getLoginPage() {
		return "account/login";
	}

}
