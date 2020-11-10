package tech.driviz.Covid19.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.driviz.Covid19.services.Covid19CountryDataService;

@RestController
@RequestMapping("/country")
public class Covid19CountryController {

	private final Covid19CountryDataService covid19CountryDataService;
    private static final Logger logger = LoggerFactory.getLogger(Covid19CountryController.class);
	
	public Covid19CountryController(Covid19CountryDataService covid19CountryDataService) {
		this.covid19CountryDataService = covid19CountryDataService;
	}

	@GetMapping("/{country}/{caseType}")
	public String getActiveCasesByCountry(@PathVariable String country, @PathVariable String caseType) {
		logger.info("country = "+country+" caseType = "+caseType);
		String res = covid19CountryDataService.getCasesByCountry(country, caseType.toLowerCase());
		return res;
	}
}
