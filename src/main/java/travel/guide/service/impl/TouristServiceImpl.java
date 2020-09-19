package travel.guide.service.impl;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travel.guide.entities.Tourist;
import travel.guide.repositories.TouristRepository;
import travel.guide.service.TouristService;

@Service
@Transactional
public class TouristServiceImpl  implements TouristService {
	@Autowired
    private TouristRepository touristRepository;
    @Override
    public Tourist save(Tourist tourist) {
        return touristRepository.save(tourist);
    }

    @Override
    public Boolean delete(int id) {
        if (touristRepository.existsById(id)) {
            touristRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Tourist update(Tourist tourist) {
        return touristRepository.save(tourist);
    }

    @Override
    public Tourist findById(int id) {
        return touristRepository.findById(id).get();
    }

    @Override
    public Collection<Tourist> findAll() {
        Iterable<Tourist> itr = touristRepository.findAll();
        return (Collection<Tourist>) itr;
    }

}
