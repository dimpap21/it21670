package travel.guide.service.impl;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travel.guide.entities.Traveller;
import travel.guide.repositories.TravellerRepository;
import travel.guide.service.TravellerService;
@Service
@Transactional
public class TravellerServiceImpl implements TravellerService{
	@Autowired
    private TravellerRepository travellerRepository;
    @Override
    public Traveller save(Traveller traveller) {
        return travellerRepository.save(traveller);
    }

    @Override
    public Boolean delete(int id) {
        if (travellerRepository.existsById(id)) {
            travellerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Traveller update(Traveller traveller) {
        return travellerRepository.save(traveller);
    }

    @Override
    public Traveller findById(int id) {
        return travellerRepository.findById(id).get();
    }

    @Override
    public Collection<Traveller> findAll() {
        Iterable<Traveller> itr = travellerRepository.findAll();
        return (Collection<Traveller>) itr;
    }

	@Override
	public Collection<Traveller> findByUser_idl(int User_id) {
        Iterable<Traveller> itr = travellerRepository.findByUser_id(User_id);
        return (Collection<Traveller>) itr;
	}

	
}
