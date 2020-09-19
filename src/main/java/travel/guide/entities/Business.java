package travel.guide.entities;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class Business extends Traveller {

	public Business(String city, String preferedWeather, int preferedMuseums, int preferedCafesRestaurantsBars,
			ArrayList<String> preferedCities, User user) throws IOException {
		super(city, preferedWeather, preferedMuseums, preferedCafesRestaurantsBars, preferedCities, user);
	}

	public Business() {

	}

	public static double distance(double lat1, double lon1, double lat2, double lon2) {
		lon1 = Math.toRadians(lon1);
		lon2 = Math.toRadians(lon2);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);
		double dlon = lon2 - lon1;
		double dlat = lat2 - lat1;
		double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
		double c = 2 * Math.asin(Math.sqrt(a));
		double r = 6371;
		return (c * r);
	}

	@Override
	protected float Similarity(City city, Traveller traveller) {
		double km = distance(city.getLat(), city.getLon(), traveller.getCurrentlat(), traveller.getCurrentlon());
		return (float) km;
	}

	@Override
	public City CompareCities(ArrayList<City> cities) {
		float min = 10000000;
		City nearestcity = null;
		for (City city : cities) {
			if (Similarity(city, this) < min) {
				min = Similarity(city, this);
				System.out.println(min + " " + city);
				System.out.println(min + " " + this);
				nearestcity = city;
			}
		}
		System.out.println("alalalalong");
		return nearestcity;
	}

	@Override
	public City CompareCities(ArrayList<City> cities, boolean weather) {
		float min = 1000000;
		City nearestcity = null;
		for (City city : cities) {
			if (Similarity(city, this) < min && !city.getWeather().equals("Rain")) {
				min = Similarity(city, this);
				nearestcity = city;
			}
		}
		return nearestcity;
	}
}
