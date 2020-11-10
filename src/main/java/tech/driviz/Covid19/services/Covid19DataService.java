package tech.driviz.Covid19.services;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.stereotype.Service;

import tech.driviz.Covid19.models.backend.TwilioMessage;
import tech.driviz.Covid19.transformers.TwilioMessageTransformer;

@Service
public class Covid19DataService {
	
	private static final String TOTAL = "Total";
	
	private final Covid19WorldDataService covid19WorldDataService;
	private final Covid19CountryDataService covid19CountryDataService;
	
	public Covid19DataService(Covid19WorldDataService covid19WorldDataService,
			Covid19CountryDataService covid19CountryDataService) {
		this.covid19WorldDataService = covid19WorldDataService;
		this.covid19CountryDataService = covid19CountryDataService;
	}

	public String getMessage(String encodedMessage) throws UnsupportedEncodingException {
		String decodedMessage = URLDecoder.decode(encodedMessage, "UTF-8");
		TwilioMessage twilioMessage = TwilioMessageTransformer.transform(decodedMessage);
		String caseType = twilioMessage.getCaseType();
		String country = twilioMessage.getCountry();
		String messageString = null;
		if(country.equalsIgnoreCase(TOTAL))
			messageString = covid19WorldDataService.getCases(caseType, twilioMessage);
		else
			messageString = covid19CountryDataService.getCasesByCountry(country, caseType, twilioMessage);
		
		return messageString;
	}
}
