package travel.guide.repositories;

import org.springframework.data.repository.CrudRepository;

import travel.guide.entities.City;

public interface CityRepository extends CrudRepository<City, Integer>  {
	City findByName(String name);
}
