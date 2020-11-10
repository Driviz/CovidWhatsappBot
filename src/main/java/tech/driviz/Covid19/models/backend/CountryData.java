package tech.driviz.Covid19.models.backend;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryData {

	@JsonProperty("Deaths")
	private int Deaths;

	@JsonProperty("Active")
	private int Active;

	@JsonProperty("CountryCode")
	private String CountryCode;

	public int getDeaths() {
		return Deaths;
	}

	public String getCountryCode() {
		return CountryCode;
	}

	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}

	public void setDeaths(int deaths) {
		Deaths = deaths;
	}

	public int getActive() {
		return Active;
	}

	public void setActive(int active) {
		Active = active;
	}

	@Override
	public String toString() {
		return "CountryData [Deaths=" + Deaths + ", Active=" + Active + ", CountryCode=" + CountryCode + "]";
	}

}
