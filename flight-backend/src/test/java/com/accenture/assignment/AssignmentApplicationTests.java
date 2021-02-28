package com.accenture.assignment;

import com.accenture.assignment.repository.AirportsRepository;
import com.accenture.assignment.repository.CountriesRepository;
import com.accenture.assignment.repository.RunwaysRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class AssignmentApplicationTests {

	ObjectMapper om = new ObjectMapper();

	@Autowired
	MockMvc mockMvc;

	@Autowired
	CountriesRepository countriesRepository;
	
	@Autowired
	AirportsRepository airportsRepository;
	
	@Autowired
	RunwaysRepository runwaysRepository;

	static Map<String, Integer> expectedTop10 = new LinkedHashMap<>();

	static {
		expectedTop10.put("United States",23820);
		expectedTop10.put("Brazil",5324);
		expectedTop10.put("Canada",2803);
		expectedTop10.put("Australia",2021);
		expectedTop10.put("Mexico",1531);
		expectedTop10.put("South Korea",1374);
		expectedTop10.put("United Kingdom",1267);
		expectedTop10.put("Russia",1123);
		expectedTop10.put("Germany",963);
		expectedTop10.put("France",903);

	}

	@Test
	void contextLoads() {
	}

	@Test
	public void getTop10Countries() throws Exception {

		final Map<String, Integer> actualTop10 = om.readValue(this.mockMvc.perform(get("/runways/topten")).andDo(print()).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString(), Map.class);

		Assert.assertEquals(expectedTop10, actualTop10);
	}

	@Test
	public void getEmptyRunwayForCountryCode() throws Exception {

		final String response = this.mockMvc.perform(
				get("/runways/code?code=ZZ")).andDo(print()).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		Assert.assertTrue(response.equals("{}"));
	}

	@Test
	public void getEmptyRunwayForCountryName() throws Exception {

		final String response = this.mockMvc.perform(
				get("/runways/name?name=Unknown or unassigned country"))
				.andDo(print()).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		Assert.assertTrue(response.equals("{}"));
	}

	@Test
	public void getRunwayForCountryName() throws Exception {
		String responseJson = "{\"Test Airstrip\":[{\"id\":269999,\"airportref\":307328,\"airport_ident\":\"00BC\",\"length_ft\":80,\"width_ft\":80,\"surface\":\"DEMO-G\",\"lighted\":1,\"closed\":0,\"le_ident\":\"H1\",\"le_latitude_deg\":null,\"le_longitude_deg\":null,\"le_elevation_ft\":null,\"le_heading_degT\":null,\"le_displaced_threshold_ft\":null,\"he_ident\":null,\"he_latitude_deg\":null,\"he_longitude_deg\":null,\"he_elevation_ft\":null,\"he_heading_degT\":null,\"he_displaced_threshold_ft\":null}]}";

		final String response = this.mockMvc.perform(
				get("/runways/name?name=LA LA LAND"))
				.andDo(print()).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		Assert.assertTrue(response.equals(responseJson));
	}

	@Test
	public void getRunwayForCountryCode() throws Exception {
		String responseJson = "{\"Test Airstrip\":[{\"id\":269999,\"airportref\":307328,\"airport_ident\":\"00BC\",\"length_ft\":80,\"width_ft\":80,\"surface\":\"DEMO-G\",\"lighted\":1,\"closed\":0,\"le_ident\":\"H1\",\"le_latitude_deg\":null,\"le_longitude_deg\":null,\"le_elevation_ft\":null,\"le_heading_degT\":null,\"le_displaced_threshold_ft\":null,\"he_ident\":null,\"he_latitude_deg\":null,\"he_longitude_deg\":null,\"he_elevation_ft\":null,\"he_heading_degT\":null,\"he_displaced_threshold_ft\":null}]}";

		final String response = this.mockMvc.perform(
				get("/runways/code?code=DEMO"))
				.andDo(print()).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		Assert.assertTrue(response.equals(responseJson));
	}

}
