package travel.guide.entities;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
public class Tourist extends Traveller{
	public Tourist( String city,String preferedWeather,int preferedMuseums,int preferedCafesRestaurantsBars,ArrayList<String> preferedCities,User user) throws IOException {
		super(city,preferedWeather, preferedMuseums, preferedCafesRestaurantsBars,preferedCities,user);
		}
	public Tourist() {
		
	}
	@Override
	protected float Similarity(City city, Traveller traveller) {
		float min=0;
		min=traveller.getPreferedMuseums()*city.getMuseums();
		min+=traveller.getPreferedCafesRestaurantsBars()*city.getCafesRestaurantsBars();
		return min;
	}
	@Override
	public City CompareCities(ArrayList<City> cities) {
		City bestCity=null;
		float min=0;
		for(City city:cities) {
			if (Similarity(city,this)>min) {
				min=Similarity(city,this);
				 bestCity=city;
			}
			System.out.println(bestCity);
			System.out.println("The best prefered city is "+bestCity.getName() + "with similarity :"+min);
		}
		return bestCity;
	}
	@Override
	public City CompareCities(ArrayList<City> cities,boolean weather) {
		City bestCity=null;
		float min=0;
		for(City city:cities) {
			if((weather==true && !city.getWeather().equals("Rain"))||(weather==false)){
				if (Similarity(city,this)>min) {
					min=Similarity(city,this);
					 bestCity=city;
				}
			}
			System.out.println(bestCity);
			System.out.println("The best prefered city is "+bestCity.getName() + "with similarity :"+min);
		}
		return bestCity;
	}
}
