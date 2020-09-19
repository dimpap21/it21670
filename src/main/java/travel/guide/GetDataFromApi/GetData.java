package travel.guide.GetDataFromApi;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import travel.guide.Api.OpenTripMap.*;
import travel.guide.Api.Weather.*;
import travel.guide.ImagesApi.Example;
import travel.guide.entities.*;

public class GetData {
	public static int GetCitysDay(String city) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		OpenWeatherMap weather_obj = null;
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
			weather_obj = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city
					+ "&APPID=a9414d4e18e890688dc1c0ab7d4db7ba"), OpenWeatherMap.class);
		int sunrise=weather_obj.getSys().getSunrise();
		int sunset =weather_obj.getSys().getSunset();
		int dayLength=sunset-sunrise;
		return dayLength;
	}
	public static City RetrieveData(String city) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		OpenWeatherMap weather_obj = null;
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		try {
			weather_obj = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city
					+ "&APPID=a9414d4e18e890688dc1c0ab7d4db7ba"), OpenWeatherMap.class);
		} catch (java.io.FileNotFoundException e) {
			return null;
		}
		System.out.println(weather_obj.toString());
		String weather = weather_obj.getWeather().get(0).getMain();
		double lat = weather_obj.getCoord().getLat();
		double lon = weather_obj.getCoord().getLon();
		System.out.println(lat + "  " + lon);
		List<CityJson> museumsJson = mapper.readValue(
				new URL("https://api.opentripmap.com/0.1//en/places/radius?radius=1000&lat=" + lat + "&lon=" + lon
						+ "&kinds=museums&format=json&apikey=5ae2e3f221c38a28845f05b613bd5411ce9bdae39d816b9728f9f18b"),
				new TypeReference<List<CityJson>>() {
				});
		List<CityJson> cafesRestaurantsBarsJson = mapper.readValue(
				new URL("https://api.opentripmap.com/0.1//en/places/radius?radius=1000&lat=" + lat + "&lon=" + lon
						+ "&kinds=cafes,restaurants,bars&format=json&apikey=5ae2e3f221c38a28845f05b613bd5411ce9bdae39d816b9728f9f18b"),
				new TypeReference<List<CityJson>>() {
				});
		int museums = museumsJson.size();
		int cafeRestaurantBar = cafesRestaurantsBarsJson.size();
		City city_info = new City(city, museums, cafeRestaurantBar, weather, lat, lon);
		return city_info;
	}

	public static String GetCityImage(String city) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		city = city.substring(0, 1).toLowerCase() + city.substring(1).toLowerCase();
		Example photoObject = null;
		try {
			photoObject = mapper.readValue(
					new URL("https://api.teleport.org/api/urban_areas/slug:" + city + "/images/"), Example.class);
			String image = photoObject.getPhotos().get(0).getImage().getWeb();
			System.out.println(image.toString());
			return image;
		} catch (java.io.FileNotFoundException e) {
			return null;
		}
	}
}