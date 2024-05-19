package messager.model;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {


   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;


   private String name;


   private String password;


   public User(String name, String password) {
      this.name = name;
      this.password = password;
   }


}
