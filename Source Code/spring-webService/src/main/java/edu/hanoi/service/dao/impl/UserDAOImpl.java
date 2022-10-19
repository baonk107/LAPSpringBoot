package edu.hanoi.service.dao.impl;

import edu.hanoi.service.dao.UserDAO;
import edu.hanoi.service.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("userDAO")
public class UserDAOImpl implements UserDAO {
    private final static Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    @Qualifier(value = "sessionFactory")
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public LocalSessionFactoryBean getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public List<User> list() {
        Session session = sessionFactory.getObject().openSession();
        try {

            Query query = session.createQuery("from User order by age desc");
            return (List<User>) query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public String insert(User user) {
        Session session = sessionFactory.getObject().openSession();
        try {
            Transaction trans = session.beginTransaction();
            Serializable value = session.save(user);
            session.flush();
            trans.commit();

            System.out.println("Save user " + user.getUsername() + " done!" + value);
            return value.toString();
        } finally {
            session.close();
        }
    }

    @Override
    public User getUser(String username) {
        Session session = sessionFactory.getObject().openSession();
        try {
            return session.get(User.class, username);
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(String username) {
        Session session = sessionFactory.getObject().openSession();
        try {
            //Action Delete
            Transaction trans = session.beginTransaction();
            User user = session.get(User.class, username);
            if (user != null) session.delete(user);
            System.out.println("Delete " + username + " successful");
            session.flush();
            trans.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getObject().openSession();
        try {
            //Action Delete
            Transaction trans = session.beginTransaction();
            user = (User) session.merge(user);
            session.save(user);
            System.out.println("Update " + user.getUsername() + " successful");
            session.flush();
            trans.commit();
        } finally {
            session.close();
        }
    }
}
