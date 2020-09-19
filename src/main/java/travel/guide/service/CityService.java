package travel.guide.service;

import java.util.Collection;

import travel.guide.entities.City;

public interface CityService {
	City save(City city);

    Boolean delete(int id);

    City update(City city);

    City findById(int id);
    City findByName(String name);
    Collection<City> findAll();
}
