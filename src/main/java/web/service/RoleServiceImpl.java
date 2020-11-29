package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.DaoRole;
import web.model.Role;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class RoleServiceImpl implements RoleService {
    private DaoRole daoRole;

    @Autowired
    public DaoRole getDaoRole() {
        return daoRole;
    }

    @Override
    public List<Role> listOfRoles() {
        return daoRole.listOfRoles();
    }
}
