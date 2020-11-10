package tech.driviz.Covid19.transformers;

import org.springframework.stereotype.Component;

import tech.driviz.Covid19.models.backend.WorldData;

@Component
public class WorldTransformer {

	private static final String TOTAL_ACTIVE_CASES = "Total Active Cases ";
	private static final String TOTAL_DEATHS = "Total Deaths ";

	public String transform(String typeOfMessage, WorldData worldData) {
		if (worldData == null) {
			return "Not Found";
		}

		if (typeOfMessage == null)
			return "Not Found";

		if (typeOfMessage.equalsIgnoreCase("cases")) {
			Long activeCases = getActiveCases(worldData);
			return TOTAL_ACTIVE_CASES + activeCases;
		} else if (typeOfMessage.equalsIgnoreCase("deaths"))
			return TOTAL_DEATHS + worldData.getTotalDeaths();

		return "Not Found";
	}

	private Long getActiveCases(WorldData worldData) {
		Long totalConfirmed = worldData.getTotalConfirmed();
		Long totalDeaths = worldData.getTotalDeaths();
		Long totalRecovered = worldData.getTotalRecovered();
		return totalConfirmed - totalDeaths - totalRecovered;
	}
}
