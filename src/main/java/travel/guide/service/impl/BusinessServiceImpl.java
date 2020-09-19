package travel.guide.service.impl;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travel.guide.entities.Business;
import travel.guide.repositories.BusinessRepository;
import travel.guide.service.BusinessService;

@Service
@Transactional
public class BusinessServiceImpl  implements BusinessService{
	@Autowired
    private BusinessRepository businessRepository;
    @Override
    public Business save(Business business) {
        return businessRepository.save(business);
    }

    @Override
    public Boolean delete(int id) {
        if (businessRepository.existsById(id)) {
            businessRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Business update(Business business) {
        return businessRepository.save(business);
    }

    @Override
    public Business findById(int id) {
        return businessRepository.findById(id).get();
    }

    @Override
    public Collection<Business> findAll() {
        Iterable<Business> itr = businessRepository.findAll();
        return (Collection<Business>) itr;
    }

}
