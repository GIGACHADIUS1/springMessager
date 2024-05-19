package messager.controller;


import messager.DTO.ChatDTO;
import messager.DTO.MessageDTO;
import messager.DTO.UserDTO;
import messager.mapper.ChatMapper;
import messager.mapper.MessageMapper;
import messager.mapper.UserMapper;
import messager.model.Chat;
import messager.model.Message;
import messager.model.User;
import messager.service.ChatService;
import messager.service.MessageService;
import messager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class SpringMessagerController {


   private final MessageService messageServiceImpl;
   private final UserService userService;
   private final ChatService chatService;


   @Autowired
   private SimpMessagingTemplate simpMessagingTemplate;


   @Autowired
   public SpringMessagerController(MessageService messageService, UserService userService, ChatService chatService) {
      this.messageServiceImpl = messageService;
      this.userService = userService;
      this.chatService = chatService;
   }


   @GetMapping("/showChatList/{id}")
   public ResponseEntity<List<ChatDTO>> showChatList(@PathVariable long id) {
      List<ChatDTO> chatDTOList = chatService.getChatDTOsByUserId(id);
      return ResponseEntity.ok(chatDTOList);
   }


   @PostMapping("/find/{id}")
   public ResponseEntity<UserDTO> searchUserRequest(@PathVariable String id) {
      User user;
      UserDTO userDTO;
      long userId = Long.parseLong(id);
      try {
         user = userService.find(userId);
         userDTO = UserMapper.INSTANCE.userToUserDTO(user);
      } catch (Exception e) {
         return null;
      }
      return ResponseEntity.ok(userDTO);
   }


   @PostMapping("/addchat/{id}/{chatId}")
   public ResponseEntity<ChatDTO> addChat(@PathVariable long id, @PathVariable long chatId) {
      Chat chat = chatService.addChatByMembersId(id, chatId);
      return ResponseEntity.ok(ChatMapper.INSTANCE.chatToChatDTO(chat));
   }


   @GetMapping("/openchat/{stringChatId}")
   public ResponseEntity<ChatDTO> getChatDTO(@PathVariable String stringChatId) {
      long chatId = Long.parseLong(stringChatId);
      return ResponseEntity.ok(ChatMapper.INSTANCE.chatToChatDTO(chatService.getChatById(chatId)));
   }


   @MessageMapping("/sendMessage/{chatId}")
   public void addMessage(@DestinationVariable long chatId, String json) {
      try {
         long recipientId;
         String[] parts = json.split("Text:|SenderId:|ChatId:");
         String text = parts[1].trim();
         long senderId = Long.parseLong(parts[2].trim());
         Chat chat = chatService.getChatById(chatId);
         if (chat.getUser1().getId() == senderId) {
            recipientId = chat.getUser2().getId();
         } else {
            recipientId = chat.getUser1().getId();
         }
         Message message = messageServiceImpl.addMessage(text, senderId, recipientId, chatId);
         MessageDTO messageDTO = MessageMapper.INSTANCE.messageToMessageDTO(message);
         String path = "/topic/chat/" + chatId;
         simpMessagingTemplate.convertAndSend(path, messageDTO);
      } catch (Exception e) {
         System.out.println("ERROR: " + e);
      }
   }


}
