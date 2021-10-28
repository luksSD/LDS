package br.fai.lds.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String getHomepage() {
		return "home";
	}

	@GetMapping("/not-found")
	public String getNotFoundPage() {

		return "exception/not-found";
	}

}
