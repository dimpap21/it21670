package travel.guide.service;

import java.util.Collection;

import travel.guide.entities.Tourist;

public interface TouristService {
	Tourist save(Tourist tourist);

    Boolean delete(int id);

    Tourist update(Tourist tourist);

    Tourist findById(int id);
    Collection<Tourist> findAll();
}
