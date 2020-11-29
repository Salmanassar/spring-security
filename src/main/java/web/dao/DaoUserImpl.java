package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@Transactional
public class DaoUserImpl implements DaoUser {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public User readUser(Long id) {
        return (User) entityManager.createQuery("select u from User u where u.id=:id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void deleteUser(Long id) {
        User user = readUser(id);
        entityManager.remove(user);
    }

    @Override
    public List<User>listUsers() {
        TypedQuery<User> query =
                entityManager.createQuery("select u from User u", User.class);
        return query.getResultList();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.email = :email",
                User.class);
        query.setParameter("email", email);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public User createUser(User user){
        if(!isNotEmpty(user)){
            throw new RuntimeException("There is an empty field(s): ");
        }
        final String passwordEncoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        user.setPasswordConfirmation(passwordEncoded);
        return entityManager.merge(user);
    }

    @Override
    public User updateUser(User user){
        User updateUser = getUserById(user.getId()).get();
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getFirstName());
        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(user.getPassword());
        entityManager.flush();
        return user;
    }

    private boolean isNotEmpty(User user) {
        return Stream.of(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword())
                .noneMatch(String::isEmpty);
    }
}
