package messager.service;


import messager.DTO.ChatDTO;
import messager.model.Chat;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ChatService {


   Chat getChatById(long chatId);


   Chat addChatByMembersId(long id1, long id2);


   List<ChatDTO> getChatDTOsByUserId(long userId);


}
