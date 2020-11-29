package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DaoRoleImp implements DaoRole {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<Role> listOfRoles() {
        TypedQuery<Role> query =
                entityManager.createQuery("SELECT u FROM Role u", Role.class);
        return query.getResultList();
    }
}
