package travel.guide.service;

import java.util.Collection;

import travel.guide.entities.Business;

public interface BusinessService {
	Business save(Business business);

    Boolean delete(int id);

    Business update(Business business);

    Business findById(int id);
    Collection<Business> findAll();
}
