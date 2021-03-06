package web.dao;

import org.springframework.stereotype.Service;
import web.model.Role;
import web.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface DaoUser {

    User createUser(User user);

    User readUser(Long id);

    User updateUser(User user);

    void deleteUser(Long id);

    List<User> listUsers();

    Optional<User> findUserByEmail(String email);

    Optional<User> getUserById(Long id);
}
