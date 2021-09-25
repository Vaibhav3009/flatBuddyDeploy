package repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.User;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {

	User findByUserId(String userId);
	User findByEmail(String email);



  
}
