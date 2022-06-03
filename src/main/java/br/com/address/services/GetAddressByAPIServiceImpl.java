package br.com.address.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.address.domain.Address;

@Service
public class GetAddressByAPIServiceImpl implements  GetAddressByAPIService{
	
	
	private static final String API_KEY = "AIzaSyCj0cY2yEvVfYhAaTz3-P2MW-YRKmhz5Uw";	
	
	
	public Address getLatLng(Address address) {
		RestTemplate rest =  new RestTemplate();
		String uri = "https://maps.googleapis.com/maps/api/geocode/json?address="+address.getNumber()+"+"+address.getStreetName()+"+"+address.getNeighbourhood()+"+"+address.getCity()+"&key="+API_KEY;
		String response = rest.getForObject(uri,String.class);
		JSONObject json = new JSONObject(response);
		JSONArray arr = json.getJSONArray("results");
		json = arr.getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
		
		String latitude = String.valueOf(json.getDouble("lat"));
		String longitude = String.valueOf(json.getDouble("lng"));
		
		address.setLatitude(latitude);
		address.setLongitude(longitude);
		
		
		return address;
	}
	

}
