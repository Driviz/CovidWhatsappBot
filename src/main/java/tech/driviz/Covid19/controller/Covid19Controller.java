package tech.driviz.Covid19.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.driviz.Covid19.services.Covid19DataService;

@RestController
public class Covid19Controller {

	private final Covid19DataService covid19DataService;

	public Covid19Controller(Covid19DataService covid19DataService) {
		this.covid19DataService = covid19DataService;
	}

	@PostMapping("/covid")
	public String covid(@RequestBody String request) throws Exception {

		return covid19DataService.getMessage(request);
	}
}
