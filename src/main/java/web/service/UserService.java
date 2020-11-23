package web.service;

import org.springframework.stereotype.Service;
import web.model.User;
import web.validation.NotExistsUserException;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {

    User createUser(User user);

    User readUser(Long id);

    void updateUser(User user);

    void deleteUser(Long id);

    List<User> listUsers();

    User findUserByEmail(String email);

    User getUserById(Long id);
}
