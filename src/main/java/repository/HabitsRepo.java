package repository;

import model.Habits;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.User;

@Repository
public interface HabitsRepo extends CrudRepository<Habits, Integer> {



}
