package tech.driviz.Covid19.services;

import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import tech.driviz.Covid19.models.backend.WorldData;
import tech.driviz.Covid19.transformers.WorldTransformer;

@Service
public class Covid19WorldDataService {

	private final RestTemplate covidRestTemplate;
	private final WorldTransformer worldTransformer;
	private final TwilioSenderService twilioSenderService;
	private final String WORLD_PATH = "/world/total";

	@Value("${backend.baseUrl}")
	private String baseUrl;

	public Covid19WorldDataService(RestTemplate covidRestTemplate, WorldTransformer worldTransformer,
			TwilioSenderService twilioSenderService) {
		this.covidRestTemplate = covidRestTemplate;
		this.worldTransformer = worldTransformer;
		this.twilioSenderService = twilioSenderService;
	}

	public String getCases(String caseType) {
		URI requestURI = createRequestURI();
		WorldData worldData = covidRestTemplate.getForObject(requestURI, WorldData.class);
		String transformedMessage = worldTransformer.transform(caseType, worldData);
		return twilioSenderService.sendMessage(transformedMessage);
	}

	private URI createRequestURI() {
		StringBuilder sb = new StringBuilder();
		sb.append(baseUrl);
		sb.append(WORLD_PATH);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(sb.toString());

		return builder.build().encode().toUri();
	}

}
