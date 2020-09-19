package travel.guide.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.CrudRepository;

import travel.guide.entities.Traveller;


public interface TravellerRepository  extends CrudRepository<Traveller, Integer> {
	Collection<Traveller> findByUser_id(int User_id);
	
}
