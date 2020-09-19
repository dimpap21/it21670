package travel.guide.repositories;

import org.springframework.data.repository.CrudRepository;

import travel.guide.entities.Tourist;

public interface TouristRepository  extends CrudRepository<Tourist, Integer> {

}
