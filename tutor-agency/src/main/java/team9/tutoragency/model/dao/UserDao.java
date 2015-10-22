package team9.tutoragency.model.dao;

import team9.tutoragency.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User,Long> {
}
