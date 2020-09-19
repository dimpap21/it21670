package travel.guide.service;

import java.util.Collection;

import travel.guide.entities.Traveller;

public interface TravellerService {
	Traveller save(Traveller traveller);

    Boolean delete(int id);

    Traveller update(Traveller traveller);

    Traveller findById(int id);
    Collection<Traveller> findAll();
    Collection<Traveller> findByUser_idl(int User_id);
}   
    
