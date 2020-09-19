package travel.guide.repositories;

import org.springframework.data.repository.CrudRepository;

import travel.guide.entities.Business;

public interface BusinessRepository  extends CrudRepository<Business, Integer>{

}
