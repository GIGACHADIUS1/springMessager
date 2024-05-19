package messager.repository;


import messager.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {


   List<User> findUserByName(String name);


   @Query(value = "select * from users where id=:id", nativeQuery = true)
   User findUserById(long id);


}
