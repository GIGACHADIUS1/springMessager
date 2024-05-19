package messager.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "messages")
public class Message {


   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;


   private String text;


   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "sender_id")
   private User sender;


   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "recipient_id")
   private User recipient;


   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "chat_id")
   private Chat chat;


   public Message(String text, User sender, User recipient) {
      this.text = text;
      this.sender = sender;
      this.recipient = recipient;
   }


   public Message(String text, User sender, User recipient, Chat chat) {
      this.text = text;
      this.sender = sender;
      this.recipient = recipient;
      this.chat = chat;
   }


}
