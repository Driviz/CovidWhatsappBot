package tech.driviz.Covid19.transformers;

import java.util.List;

import org.springframework.stereotype.Component;

import tech.driviz.Covid19.models.backend.CountryData;

@Component
public class CountryTransformer {

	private static final String ACTIVE_CASES = " Active Cases ";
	private static final String DEATHS = " Deaths ";
	private static final String NOT_FOUND = "Data Not Found";

	public String transform(String typeOfMessage, List<CountryData> responseList) {
		CountryData latestData = getLatestData(responseList);

		if (latestData == null) {
			return NOT_FOUND;
		}
		if (typeOfMessage == null)
			return NOT_FOUND;

		if (typeOfMessage.equalsIgnoreCase("cases"))
			return latestData.getCountryCode() + ACTIVE_CASES + latestData.getActive();
		else if (typeOfMessage.equalsIgnoreCase("deaths"))
			return latestData.getCountryCode() + DEATHS + latestData.getDeaths();

		return NOT_FOUND;
	}

	private CountryData getLatestData(List<CountryData> responseList) {
		if (responseList == null || responseList.isEmpty())
			return null;
		int size = responseList.size();
		return responseList.get(size - 1);
	}
}
