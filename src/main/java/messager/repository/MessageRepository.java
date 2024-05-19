package messager.repository;


import messager.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.LinkedList;


@Repository
public interface MessageRepository extends CrudRepository <Message,Long>{


   LinkedList<Message> getMessagesByChatId(Long chatId);


}
