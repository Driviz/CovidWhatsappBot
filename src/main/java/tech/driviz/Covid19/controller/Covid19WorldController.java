package tech.driviz.Covid19.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tech.driviz.Covid19.services.Covid19WorldDataService;

@RestController
public class Covid19WorldController {
	
	private final Covid19WorldDataService covid19WorldDataService;
	
	public Covid19WorldController(Covid19WorldDataService covid19WorldDataService) {
		this.covid19WorldDataService = covid19WorldDataService;
	}

	@GetMapping("/{caseType}")
	public String getActiveCasesByCountry(@PathVariable String caseType) {
		String res = covid19WorldDataService.getCases(caseType.toLowerCase());
		return res;
	}
	
}
