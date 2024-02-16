package com.dietify.v1.Controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.dietify.v1.DTO.Day.Day;
import com.dietify.v1.DTO.Day.DayResponse;
import com.dietify.v1.DTO.Week.Week;
import com.dietify.v1.DTO.Week.WeekResponse;


@RestController
@RequestMapping("/mealplanner")
public class MealController {

	// Add base for the unchanging part of your web address.
	@Value("${spoonacular.urls.baseurl}")
	private String baseURL;

	@Value("${apikey}")
	private String apiKey;

	@GetMapping("/day")
	public ResponseEntity<DayResponse> getDayMeals(
			@RequestParam Optional<String> targetCalories,
			@RequestParam Optional<String> diet,
			@RequestParam Optional<String> exclusions) {
		RestTemplate rt = new RestTemplate();

		URI uri = UriComponentsBuilder.fromHttpUrl(baseURL)
				.queryParam("timeFrame", "day")
				.queryParamIfPresent("targetCalories", targetCalories)
				.queryParamIfPresent("diet", diet)
				.queryParamIfPresent("exclude", exclusions)
				.queryParam("apiKey", apiKey)
				.build()
				.toUri();

		ResponseEntity<DayResponse> response = rt.getForEntity(uri, DayResponse.class);
		if (response.getStatusCode().is2xxSuccessful()) {
			DayResponse dayResponse = response.getBody();
			if (dayResponse != null && dayResponse.getMeals() != null) {
				dayResponse.getMeals().forEach(meal -> {
					int id = meal.getId();
					String imageURL = "https://spoonacular.com/recipeImages/" + id + "-312x231.jpg";
					meal.setSourceUrl(imageURL);
				});
			}
		}
		return response;
	}

	@GetMapping("/week")
	public ResponseEntity<WeekResponse> getWeekMeals(
			@RequestParam(required = false) String targetCalories,
			@RequestParam(required = false) String diet,
			@RequestParam(required = false) String exclusions) {

		RestTemplate restTemplate = new RestTemplate();

		URI uri = UriComponentsBuilder.fromHttpUrl(baseURL)
				.queryParam("timeFrame", "week")
				.queryParamIfPresent("targetCalories", Optional.ofNullable(targetCalories))
				.queryParamIfPresent("diet", Optional.ofNullable(diet))
				.queryParamIfPresent("exclude", Optional.ofNullable(exclusions))
				.queryParam("apiKey", apiKey)
				.build()
				.toUri();

		ResponseEntity<WeekResponse> responseEntity = restTemplate.getForEntity(uri, WeekResponse.class);
		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			WeekResponse weekResponse = responseEntity.getBody();
			if (weekResponse != null && weekResponse.getWeek() != null) {
				updateMealSourceUrls(weekResponse.getWeek());
			}
		}

		return responseEntity;
	}

	private void updateMealSourceUrls(Week week) {
		Day[] days = { week.getMonday(), week.getTuesday(), week.getWednesday(),
				week.getThursday(), week.getFriday(), week.getSaturday(), week.getSunday() };
		for (Day day : days) {
			if (day != null) {
				day.getMeals().forEach(meal -> {
					int id = meal.getId();
					String imageURL = "https://spoonacular.com/recipeImages/" + id + "-312x231.jpg";
					meal.setSourceUrl(imageURL);
				});
			}
		}
	}
}