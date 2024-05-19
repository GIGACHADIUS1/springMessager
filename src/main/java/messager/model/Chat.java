package messager.model;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "chats")
public class Chat {


   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;


   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "user_id1")
   private User user1;


   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "user_id2")
   private User user2;


   @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
   private List<Message> messages;


   public Chat(User user1, User user2) {
      this.user1 = user1;
      this.user2 = user2;
   }


}
