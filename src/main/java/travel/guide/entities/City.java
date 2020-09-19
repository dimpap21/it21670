package travel.guide.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cities")
public class City {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(unique=true,nullable = false)
	private String name;
	@Column(nullable = false)
	private int museums;
	@Column(nullable = false)
	private int cafesRestaurantsBars;
	@Column(nullable = false)
	private String weather;
	@Column(nullable = false)
	private double lat;
	@Column(nullable = false)
	private double lon;
	public City(String name, int museums, int cafesRestaurantsBars, String weather, double lat, double lon) {
		super();
		this.name = name;
		this.museums = museums;
		this.cafesRestaurantsBars = cafesRestaurantsBars;
		this.weather = weather;
		this.lat = lat;
		this.lon = lon;
	}
	public City() {
		
	}


@Override
public boolean equals(Object that){
  if(this == that) return true;//if both of them points the same address in memory
  if(!(that instanceof City)) return false; // if "that" is not a City or a childclass
  City city = (City)that; // than we can cast it to People safely
  return this.name.equals(city.name) ;// if they have the same name then the 2 objects are equal unless they're pointing to different memory adresses
}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMuseums() {
		return museums;
	}
	public void setMuseums(int museums) {
		this.museums = museums;
	}
	public int getCafesRestaurantsBars() {
		return cafesRestaurantsBars;
	}
	public void setCafesRestaurantsBars(int cafesRestaurantsBars) {
		this.cafesRestaurantsBars = cafesRestaurantsBars;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", museums=" + museums + ", cafesRestaurantsBars="
				+ cafesRestaurantsBars + ", weather=" + weather + ", lat=" + lat + ", lon=" + lon +  "]";
	}
	
	
	
	
}
