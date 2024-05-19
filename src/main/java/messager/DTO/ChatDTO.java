package messager.DTO;


import lombok.*;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChatDTO {


   private long id;


   private UserDTO user1DTO;


   private UserDTO user2DTO;


   List<MessageDTO> messages;


}
