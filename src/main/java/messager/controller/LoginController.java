package messager.controller;


import messager.model.User;
import messager.service.ChatService;
import messager.service.MessageService;
import messager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {


   private final MessageService messageServiceImpl;
   private final UserService userService;
   private final ChatService chatService;


   @Autowired
   public LoginController(MessageService messageService, UserService userService, ChatService chatService) {
      this.messageServiceImpl = messageService;
      this.userService = userService;
      this.chatService = chatService;
   }


   @GetMapping("/login/{loginData}")
   public ResponseEntity<Long> login(@PathVariable String loginData) {
      System.out.println(loginData);
      if (!loginData.isEmpty()) {
         String[] parts = loginData.split("\\|");
         String userName = parts[0].trim();
         String userPassword = parts[1].trim();
         User user;
         if (!userName.isEmpty() && !userPassword.isEmpty()) {
            try {
               user = userService.find(userName).get(0);
            } catch (Exception e) {
               long error = 0;
               return ResponseEntity.ok(error);
            }
            if (userPassword.equals(user.getPassword())) {
               return ResponseEntity.ok(user.getId());
            }
         }
      }
      long error = 0;
      return ResponseEntity.ok(error);
   }


   @GetMapping("/registration/{regData}")
   public ResponseEntity<Long> registration(@PathVariable String regData) {
      String[] parts = regData.split(" \\| ");
      String userName = parts[0].trim();
      String userPassword = parts[1].trim();
      User user = userService.registration(userName, userPassword);
      if (user != null) {
         return ResponseEntity.ok(user.getId());
      }
      return null;
   }


}
