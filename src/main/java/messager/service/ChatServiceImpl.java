package messager.service;


import messager.DTO.ChatDTO;
import messager.mapper.ChatMapper;
import messager.model.Chat;
import messager.model.User;
import messager.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class ChatServiceImpl implements ChatService {


   private final ChatRepository chatRepository;
   private final UserServiceImpl userServiceImpl;


   @Autowired
   public ChatServiceImpl(ChatRepository chatRepository, UserServiceImpl userServiceImpl) {
      this.chatRepository = chatRepository;
      this.userServiceImpl = userServiceImpl;
   }


   @Override
   public Chat getChatById(long chatId) {
      Chat chat = chatRepository.getChatById(chatId);
      return chat;
   }


   @Override
   public List<ChatDTO> getChatDTOsByUserId(long userId) {
      List<Chat> chatList = chatRepository.getChatsByUserId(userId);
      List<ChatDTO> chatDTOList = new ArrayList<>();
      for (Chat c : chatList) {
         chatDTOList.add(ChatMapper.INSTANCE.chatToChatDTO(c));
      }
      return chatDTOList;
   }


   @Override
   public Chat addChatByMembersId(long id1, long id2) {
      User user1 = userServiceImpl.find(id1);
      User user2 = userServiceImpl.find(id2);
      Chat chat = new Chat(user1, user2);
      chatRepository.save(chat);
      return chat;
   }


}
