package messager.mapper;


import messager.DTO.*;
import messager.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface MessageMapper {


   MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);


   UserDTO userToUserDTO(User user);


   User userDTOToUser(UserDTO userDTO);


   @Mapping(source = "sender", target = "sender")
   @Mapping(source = "recipient", target = "recipient")
   MessageDTO messageToMessageDTO(Message message);


   @Mapping(source = "sender", target = "sender")
   @Mapping(source = "recipient", target = "recipient")
   Message messageDTOToMessage(MessageDTO messageDTO);


}
