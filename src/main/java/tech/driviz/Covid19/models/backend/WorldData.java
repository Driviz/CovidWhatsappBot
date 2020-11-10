package tech.driviz.Covid19.models.backend;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorldData {

	@JsonProperty("TotalConfirmed")
	private Long TotalConfirmed;
	
	@JsonProperty("TotalDeaths")
	private Long TotalDeaths;
	
	@JsonProperty("TotalRecovered")
	private Long TotalRecovered;

	public Long getTotalConfirmed() {
		return TotalConfirmed;
	}

	public void setTotalConfirmed(Long totalConfirmed) {
		TotalConfirmed = totalConfirmed;
	}

	public Long getTotalDeaths() {
		return TotalDeaths;
	}

	public void setTotalDeaths(Long totalDeaths) {
		TotalDeaths = totalDeaths;
	}

	public Long getTotalRecovered() {
		return TotalRecovered;
	}

	public void setTotalRecovered(Long totalRecovered) {
		TotalRecovered = totalRecovered;
	}

	@Override
	public String toString() {
		return "WorldData [TotalConfirmed=" + TotalConfirmed + ", TotalDeaths=" + TotalDeaths + ", TotalRecovered="
				+ TotalRecovered + "]";
	}

	
}
