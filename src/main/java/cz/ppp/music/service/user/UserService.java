package cz.ppp.music.service.user;

import cz.ppp.music.entity.User;
import java.util.List;

@SuppressWarnings("unused")
public interface UserService {
    void save(User user);
    User findByUsername(String username);
    boolean checkIfUserExists(String username);
    User findById(int id);
    List<User> findAllUsers(String role);
    void remove(User employee);
}
