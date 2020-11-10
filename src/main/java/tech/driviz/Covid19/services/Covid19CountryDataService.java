package tech.driviz.Covid19.services;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tech.driviz.Covid19.models.backend.CountryData;
import tech.driviz.Covid19.transformers.CountryTransformer;

@Service
public class Covid19CountryDataService {

	private final RestTemplate covidRestTemplate;
	private final CountryTransformer countryTransformer;
	private final TwilioSenderService twilioSenderService;

	private final String COUNTRY_PATH = "/live/country/";

	private final ObjectMapper mapper;

	private static final Logger logger = LoggerFactory.getLogger(Covid19CountryDataService.class);

	@Value("${backend.baseUrl}")
	private String baseUrl;

	public Covid19CountryDataService(RestTemplate covidRestTemplate, ObjectMapper mapper,
			CountryTransformer countryTransformer, TwilioSenderService twilioSenderService) {
		this.covidRestTemplate = covidRestTemplate;
		this.countryTransformer = countryTransformer;
		this.twilioSenderService = twilioSenderService;
		this.mapper = mapper;
	}

	public String getCasesByCountry(String country, String caseType) {
		URI requestURI = createRequestURI(country);
		String jsonResponse = covidRestTemplate.getForObject(requestURI, String.class);
		List<CountryData> responseList = null;
		String transformedMessage = null;

		try {
			responseList = mapper.readValue(jsonResponse, new TypeReference<List<CountryData>>() {
			});

			logger.info("Before Transformation: " + responseList.toString());

			transformedMessage = countryTransformer.transform(caseType, responseList);

			logger.info("After Transformation: " + transformedMessage);

		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return twilioSenderService.sendMessage(transformedMessage);
	}

	private URI createRequestURI(String country) {
		StringBuilder sb = new StringBuilder();
		sb.append(baseUrl);
		sb.append(COUNTRY_PATH);
		sb.append(country);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(sb.toString());

		return builder.build().encode().toUri();
	}

}
