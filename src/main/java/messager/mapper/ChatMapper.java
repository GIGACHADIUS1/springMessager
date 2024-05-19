package messager.mapper;


import messager.DTO.ChatDTO;
import messager.DTO.MessageDTO;
import messager.DTO.UserDTO;
import messager.model.Chat;
import messager.model.Message;
import messager.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ChatMapper {


   ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);


   UserDTO userToUserDTO(User user);


   User userDTOToUser(UserDTO userDTO);
   Message messageDTOToMessage(MessageDTO messageDTO);
   MessageDTO messageToMessageDTO(Message message);


   @Mapping(source = "user1", target = "user1DTO")
   @Mapping(source = "user2", target = "user2DTO")
   @Mapping(source = "messages", target = "messages")
   ChatDTO chatToChatDTO(Chat chat);


   @Mapping(source = "user1DTO", target = "user1")
   @Mapping(source = "user2DTO", target = "user2")
   @Mapping(source = "messages", target = "messages")
   Chat chatDTOToChat(ChatDTO chatDTO);


}
