package repository;

import model.Hobbies;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HobbiesRepo extends CrudRepository<Hobbies, Integer> {



}
