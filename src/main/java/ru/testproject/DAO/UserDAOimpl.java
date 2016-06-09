package ru.testproject.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.testproject.model.Role;
import ru.testproject.model.RoleUser;
import ru.testproject.model.User;

import java.util.List;

/**
 * Created by Анатолий on 08.06.2016.
 */
@Repository
public class UserDAOimpl implements UserDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDAOimpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession(){ return sessionFactory.getCurrentSession(); }

    @Override
    public List<User> getUsers() {
        return currentSession().createQuery("FROM User").list();
    }

    @Override
    public User getUser(int id) {
        return currentSession().get(User.class, id);
    }

    @Override
    public void deleteUser(int id) {
        currentSession().createQuery("DELETE FROM User WHERE id=:id").setInteger("id", id).executeUpdate();
        currentSession().createQuery("DELETE FROM RoleUser WHERE user.id=:id").setInteger("id", id).executeUpdate();
    }

    @Override
    public User saveUser(User user) {
        if (user.isNew()){
            user.setId((Integer) currentSession().save(user));
        } else {currentSession().update(user);}

        currentSession().createQuery("DELETE FROM RoleUser WHERE user.id=:id").setInteger("id", user.getId()).executeUpdate();
        if (user.getRoles()!=null&&user.getRoles().size()>0) {
            for (Role role : user.getRoles()) {
                currentSession().save(new RoleUser(user, role));
            }
        }

        return user;
    }
}
