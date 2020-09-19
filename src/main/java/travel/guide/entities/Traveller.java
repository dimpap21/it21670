package travel.guide.entities;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import travel.guide.Api.Weather.OpenWeatherMap;
import travel.guide.GetDataFromApi.GetData;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name="travellers")
public class Traveller {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(length = 45, nullable = false)
	private String city;
	@Column(nullable = false)
	private String preferedWeather;
	@Column(nullable = false)
	private double currentlat;
	@Column(nullable = false)
	private double currentlon;
	@Column(nullable = false)
	private int preferedMuseums;
	@Column(nullable = false)
	private int preferedCafesRestaurantsBars;
	@Column(nullable = false)
	public ArrayList<String> preferedCities;
	@Column(nullable = true)
	public String visit;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "User_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;

	public Traveller(String city, String preferedWeather, int preferedMuseums, int preferedCafesRestaurantsBars,
			ArrayList<String> preferedCities, User user) throws IOException {
		super();
		this.city = city;
		this.preferedWeather = preferedWeather;
		this.preferedMuseums = preferedMuseums;
		this.preferedCafesRestaurantsBars = preferedCafesRestaurantsBars;
		this.preferedCities = preferedCities;
		this.user = user;
	}

	public Traveller() {
		// TODO Auto-generated constructor stub
	}

	public Traveller freeTicket(Collection<Traveller> travellers,City city) {
		float best_sim=0;
		Traveller traveller_winner=null;
		for(Traveller traveller : travellers) {
			if(traveller.Similarity(city, traveller)>best_sim) {
				best_sim=traveller.Similarity(city, traveller);
				System.out.println(best_sim);
				System.out.println(traveller);
				traveller_winner=traveller;
			}
		}
		return traveller_winner;
	}
	protected float Similarity(City city, Traveller traveller) {
		float min = 0;
		if( traveller.getPreferedMuseums()<traveller.getPreferedCafesRestaurantsBars()) {
			float diff=traveller.getPreferedCafesRestaurantsBars()-traveller.getPreferedMuseums();
			min = (traveller.getPreferedMuseums() * city.getMuseums())/diff;
			min += traveller.getPreferedCafesRestaurantsBars() * city.getCafesRestaurantsBars();
		}else {
		float diff=traveller.getPreferedMuseums()-traveller.getPreferedCafesRestaurantsBars();
		min = traveller.getPreferedMuseums() * city.getMuseums();
		min += (traveller.getPreferedCafesRestaurantsBars() * city.getCafesRestaurantsBars())/diff;
		}
		if (city.getWeather().equals(traveller.getPreferedWeather())) {
			min += 100;
		}
		return min;
	}

	public City CompareCities(ArrayList<City> cities) {
		City bestCity = null;
		float min = 0;
		for (City city : cities) {
			if (Similarity(city, this) > min) {
				min = Similarity(city, this);
				bestCity = city;
			}
			System.out.println(bestCity);
			System.out.println("The best prefered city is " + bestCity.getName() + "with similarity :" + min);
		}
		return bestCity;
	}

	public City CompareCities(ArrayList<City> cities, boolean weather) {
		City bestCity = null;
		float min = 0;
		for (City city : cities) {
			System.out.println(city);
			if((weather==true && !city.getWeather().equals("Rain"))||(weather==false)){
				if (Similarity(city, this) > min) {
					System.out.println(city);
					min = Similarity(city, this);
					bestCity = city;
				}
			}
			System.out.println(bestCity);
			System.out.println("The best prefered city is " + bestCity.getName() + "with similarity :" + min);
		}
		return bestCity;
	}
	public Traveller GetTravellersCityCoordinates() throws IOException {
		ObjectMapper mapper = new ObjectMapper(); 
		 OpenWeatherMap weather_obj=null; 
		try {
		weather_obj = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q="+this.getCity()+"&APPID=a9414d4e18e890688dc1c0ab7d4db7ba"),OpenWeatherMap.class);
		double lat= weather_obj.getCoord().getLat();
		double lon= weather_obj.getCoord().getLon();
		this.setCurrentlat(lat);
		this.setCurrentlon(lon);
		System.out.println(lat);
		return this;
		}
		 catch(java.io.FileNotFoundException e) {
		System.out.println("null");
			return null;
		}
		
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPreferedWeather() {
		return preferedWeather;
	}

	public void setPreferedWeather(String preferedWeather) {
		this.preferedWeather = preferedWeather;
	}

	public double getCurrentlat() {
		return currentlat;
	}

	public void setCurrentlat(double currentlat) {
		this.currentlat = currentlat;
	}

	public double getCurrentlon() {
		return currentlon;
	}

	public void setCurrentlon(double currentlon) {
		this.currentlon = currentlon;
	}

	public int getPreferedMuseums() {
		return preferedMuseums;
	}

	public void setPreferedMuseums(int preferedMuseums) {
		this.preferedMuseums = preferedMuseums;
	}

	public int getPreferedCafesRestaurantsBars() {
		return preferedCafesRestaurantsBars;
	}

	public void setPreferedCafesRestaurantsBars(int preferedCafesRestaurantsBars) {
		this.preferedCafesRestaurantsBars = preferedCafesRestaurantsBars;
	}

	public ArrayList<String> getPreferedCities() {
		return preferedCities;
	}

	public void setPreferedCities(ArrayList<String> preferedCities) {
		this.preferedCities = preferedCities;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getVisit() {
		return visit;
	}

	public void setVisit(String visit) {
		this.visit = visit;
	}

	@Override
	public String toString() {
		return "Traveller [ id=" + id + ", city=" + city + ", preferedWeather=" + preferedWeather + ", currentlat="
				+ currentlat + ", currentlon=" + currentlon + ", preferedMuseums=" + preferedMuseums
				+ ", preferedCafesRestaurantsBars=" + preferedCafesRestaurantsBars + ", preferedCities="
				+ preferedCities + ", visit=" + visit + ", user=" + user + "]";
	}

}
