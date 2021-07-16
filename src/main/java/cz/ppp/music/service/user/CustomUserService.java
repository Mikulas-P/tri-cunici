package cz.ppp.music.service.user;

import cz.ppp.music.entity.User;
import cz.ppp.music.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomUserService implements UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean checkIfUserExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAllUsers(String role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }
}
