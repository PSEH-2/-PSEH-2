package com.example.demo;

import com.example.demo.model.WeatherResponseDetails;
import com.example.demo.service.WeatherService;
import com.example.demo.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static com.example.demo.TestUtils.getAPIResponse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Mock
	private RestTemplate restTemplate;

	@Autowired
	WeatherService weatherService;


	@Test
	public void testWeatherService() throws IOException, ParseException {
		when(restTemplate.getForObject(anyString(),Mockito.any())).thenReturn(getAPIResponse());
		List<WeatherResponseDetails> weatherResponseDetails = weatherService.getWeatherDetails("city","countryCode");
    	System.out.println(weatherResponseDetails.size());
	}

	@Test
	public void testDateDiffWithGivenEpochAndCurrentDate() throws ParseException {
		Assertions.assertThat(!Utils.dateDiff(1546689600000l)).isEqualTo(false);
	}


}

