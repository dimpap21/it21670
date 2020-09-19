package travel.guide.service;

import java.util.Collection;

import travel.guide.entities.User;

public interface UserService {
	User save(User user);

    Boolean delete(int id);

    User update(User user);

    User findById(int id);

     User findByUserName(String Username);

     User findByEmail(String email);

    Collection<User> findAll();
}
