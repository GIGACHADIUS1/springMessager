package messager.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class MainController {


   @GetMapping("/springmessager/{id}")
   public String getSpringMessager(@PathVariable Long id) {
      return "springMessager";
   }


   @GetMapping("/login")
   public String login() {
      return "login";
   }


}