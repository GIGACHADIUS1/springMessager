package messager.repository;


import messager.model.Chat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ChatRepository extends CrudRepository<Chat, Long> {


   @Query(value = "select * from chats where id=:id", nativeQuery = true)
   Chat getChatById(long id);


   @Query(value = "select * from chats where user_id1=:userId or user_id2=:userId", nativeQuery = true)
   List<Chat> getChatsByUserId(long userId);


}
