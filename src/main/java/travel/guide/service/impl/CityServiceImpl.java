package travel.guide.service.impl;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travel.guide.entities.City;
import travel.guide.repositories.CityRepository;
import travel.guide.service.CityService;
import travel.guide.service.CityService;

@Service
@Transactional
public class CityServiceImpl  implements CityService {
	@Autowired
    private CityRepository cityRepository;
    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public Boolean delete(int id) {
        if (cityRepository.existsById(id)) {
            cityRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public City update(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City findById(int id) {
        return cityRepository.findById(id).get();
    }

    @Override
    public Collection<City> findAll() {
        Iterable<City> itr = cityRepository.findAll();
        return (Collection<City>) itr;
    }

	@Override
	public City findByName(String name) {
        return cityRepository.findByName(name);
	}
}
