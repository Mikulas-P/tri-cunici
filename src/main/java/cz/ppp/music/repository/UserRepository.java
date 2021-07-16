package cz.ppp.music.repository;

import cz.ppp.music.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);
    User findByUsername(String username);
    List<User> findAllByRole(String role);
}
