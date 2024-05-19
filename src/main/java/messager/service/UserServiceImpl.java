package messager.service;


import messager.model.User;
import messager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {


   private final UserRepository userRepository;


   @Autowired
   public UserServiceImpl(UserRepository userRepository) {
      this.userRepository = userRepository;
   }


   @Override
   public User add(String name, String password) {
      User user = new User(name, password);
      userRepository.save(user);
      return user;
   }


   @Override
   public User find(long id) {
      User user = userRepository.findUserById(id);
      return user;
   }


   @Override
   public List<User> find(String name) {
      return userRepository.findUserByName(name);
   }


   @Override
   public User login(String name, String password) {
      List<User> userList = find(name);
      for (User user : userList) {
         if (user.getPassword().equals(password)) {
            return user;
         }
      }
      return null;
   }


   @Override
   public User registration(String name, String password) {
      if (!name.isEmpty() && !password.isEmpty()) {
         if (find(name).isEmpty()) {
            User user = add(name, password);
            return user;
         }
      }
      return null;
   }


}
