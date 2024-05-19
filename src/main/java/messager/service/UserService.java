package messager.service;


import messager.model.User;
import java.util.List;


public interface UserService {


   User add(String name, String password);


   User find(long id);


   List<User> find(String name);


   User login(String name, String password);


   User registration(String name, String password);


}
