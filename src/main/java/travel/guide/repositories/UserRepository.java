package travel.guide.repositories;

import org.springframework.data.repository.CrudRepository;

import travel.guide.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);
}
