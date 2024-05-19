package messager.mapper;


import messager.DTO.UserDTO;
import messager.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {


   UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


   UserDTO userToUserDTO(User user);


   User userDTOToUser(UserDTO userDTO);


}
