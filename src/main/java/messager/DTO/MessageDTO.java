package messager.DTO;


import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageDTO {


   private Long id;


   private String text;


   private UserDTO sender;


   private UserDTO recipient;


}
