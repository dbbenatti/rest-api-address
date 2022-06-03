package br.com.address.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import br.com.address.domain.Address;
import org.junit.Before;
import  org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

public class GetAddressByAPIServiceImplTest {
	@Mock
	RestTemplate rest;
	@InjectMocks
	GetAddressByAPIServiceImpl getAddressByAPIServiceImpl;
	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void test() {
		String API_KEY = "AIzaSyCj0cY2yEvVfYhAaTz3-P2MW-YRKmhz5Uw";	
		Address address = new Address(1, "Rua Boaventura do Amaral", 760, "AP 3", "Bosque", "Campinas", "SP", "Brasil",
				"13015191", null, null);
		Address expectedAddress = new Address(1, "Rua Boaventura do Amaral", 760, "AP 3", "Bosque", "Campinas", "SP",
				"Brasil", "13015191", "-22.9067672", "-47.0539181");
		String uri = "https://maps.googleapis.com/maps/api/geocode/json?address="+address.getNumber()+"+"+address.getStreetName()+"+"+address.getNeighbourhood()+"+"+address.getCity()+"&key="+API_KEY;
	
		when(rest.getForObject(uri,String.class)).thenReturn(responseAPI);
		
		
		Address returnedAddress = getAddressByAPIServiceImpl.getLatLng(address);
		
		
		assertEquals(expectedAddress,returnedAddress);
	}
	
	private String responseAPI = "{\r\n" + 
			"   \"results\" : [\r\n" + 
			"      {\r\n" + 
			"         \"address_components\" : [\r\n" + 
			"            {\r\n" + 
			"               \"long_name\" : \"760\",\r\n" + 
			"               \"short_name\" : \"760\",\r\n" + 
			"               \"types\" : [ \"street_number\" ]\r\n" + 
			"            },\r\n" + 
			"            {\r\n" + 
			"               \"long_name\" : \"Rua Boaventura do Amaral\",\r\n" + 
			"               \"short_name\" : \"R. Boaventura do Amaral\",\r\n" + 
			"               \"types\" : [ \"route\" ]\r\n" + 
			"            },\r\n" + 
			"            {\r\n" + 
			"               \"long_name\" : \"Centro\",\r\n" + 
			"               \"short_name\" : \"Centro\",\r\n" + 
			"               \"types\" : [ \"political\", \"sublocality\", \"sublocality_level_1\" ]\r\n" + 
			"            },\r\n" + 
			"            {\r\n" + 
			"               \"long_name\" : \"Campinas\",\r\n" + 
			"               \"short_name\" : \"Campinas\",\r\n" + 
			"               \"types\" : [ \"administrative_area_level_2\", \"political\" ]\r\n" + 
			"            },\r\n" + 
			"            {\r\n" + 
			"               \"long_name\" : \"São Paulo\",\r\n" + 
			"               \"short_name\" : \"SP\",\r\n" + 
			"               \"types\" : [ \"administrative_area_level_1\", \"political\" ]\r\n" + 
			"            },\r\n" + 
			"            {\r\n" + 
			"               \"long_name\" : \"Brazil\",\r\n" + 
			"               \"short_name\" : \"BR\",\r\n" + 
			"               \"types\" : [ \"country\", \"political\" ]\r\n" + 
			"            },\r\n" + 
			"            {\r\n" + 
			"               \"long_name\" : \"13015-191\",\r\n" + 
			"               \"short_name\" : \"13015-191\",\r\n" + 
			"               \"types\" : [ \"postal_code\" ]\r\n" + 
			"            }\r\n" + 
			"         ],\r\n" + 
			"         \"formatted_address\" : \"R. Boaventura do Amaral, 760 - Centro, Campinas - SP, 13015-191, Brazil\",\r\n" + 
			"         \"geometry\" : {\r\n" + 
			"            \"location\" : {\r\n" + 
			"               \"lat\" : -22.9067672,\r\n" + 
			"               \"lng\" : -47.0539181\r\n" + 
			"            },\r\n" + 
			"            \"location_type\" : \"ROOFTOP\",\r\n" + 
			"            \"viewport\" : {\r\n" + 
			"               \"northeast\" : {\r\n" + 
			"                  \"lat\" : -22.90541821970849,\r\n" + 
			"                  \"lng\" : -47.0525691197085\r\n" + 
			"               },\r\n" + 
			"               \"southwest\" : {\r\n" + 
			"                  \"lat\" : -22.9081161802915,\r\n" + 
			"                  \"lng\" : -47.0552670802915\r\n" + 
			"               }\r\n" + 
			"            }\r\n" + 
			"         },\r\n" + 
			"         \"place_id\" : \"ChIJzeXq_TXPyJQRsVLrXz57-Ow\",\r\n" + 
			"         \"types\" : [ \"establishment\", \"premise\" ]\r\n" + 
			"      }\r\n" + 
			"   ],\r\n" + 
			"   \"status\" : \"OK\"\r\n" + 
			"}\r\n" + 
			"";

}
