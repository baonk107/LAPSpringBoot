package edu.hanoi.jazz.dao.impl;

import edu.hanoi.jazz.dao.UserDAO;
import edu.hanoi.jazz.dao.model.Group;
import edu.hanoi.jazz.dao.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("userDAO")
public class UserDAOImpl implements UserDAO {
    private final static Logger LOGGER = Logger.getLogger(UserDAOImpl.class);
    private final static int SIZE_OF_PAGE = 2;
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public void insert(User user) {
        Session session = (Session) sessionFactory.getObject().openSession();
        try {
            //Action Insert
            Transaction trans = session.beginTransaction();
            session.save(user);
            session.flush();
            trans.commit();

            LOGGER.info("Save group " + user.getUsername() + " done!");
        } finally {
            session.close();
        }
    }

    //List User
    @Override
    public List<User> listUser() {
        Session session = (Session) sessionFactory.getObject().openSession();
        try {

            Query query = session.createQuery("from User order by age desc");
            return (List<User>) query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> listFilter(int group) {
        Session session = (Session) sessionFactory.getObject().openSession();
        try {
            if (group < 0) {
                Query query = session.createQuery("from User");
                return (List<User>) query.list();
            }

            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("groupId", group));
            criteria.add(Restrictions.gt("age", 21));
            return new ArrayList<User>(criteria.list());
        } finally {
            session.close();
        }
    }

    @Override
    public User getUser(String username) {
        Session session = (Session) sessionFactory.getObject().openSession();
        User user = session.get(User.class, username);
        return user;
    }

    //Delete UserName
    @Override
    public void deleteUser(String name) {
        Session session = (Session) sessionFactory.getObject().openSession();
        try {
            //Action Delete
            Transaction trans = session.beginTransaction();
            String hql = "delete from User where username like :name";
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            int result = query.executeUpdate();
            System.out.println("Result " + result);
            LOGGER.info("Rows affected: " + result + "\n\n");

            session.flush();
            trans.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public int avarageAge() {
        Session session = (Session) sessionFactory.getObject().openSession();
        try {
            //Action Delete
            Criteria cr = session.createCriteria(User.class);
            cr.setProjection(Projections.avg("age"));
            List avgAge = cr.list();

            return ((Double) avgAge.get(0)).intValue();
        } finally {
            session.close();
        }
    }

    //============Paging==========
    @Override
    public List<User> page(int page) {
        Session session = (Session) sessionFactory.getObject().openSession();
        try {
            //Action Delete
            Criteria cr = session.createCriteria(User.class);
            int start = (page - 1) * SIZE_OF_PAGE;
            cr.setFirstResult(start);
            cr.setMaxResults(SIZE_OF_PAGE);
            return (List<User>) cr.list();
        } finally {
            session.close();
        }
    }

    //List User by Navative SQL
    @Override
    public List<User> listUserByNativeSQL() {
        Session session = (Session) sessionFactory.getObject().openSession();
        try {
            //Action Delete
//            String sql = "select * from hn_user";
            String sql = "select * from hn_user where age > :value";
            NativeQuery query = session.createSQLQuery(sql);
            query.setParameter("value", 20);
            query.addEntity(User.class);
            return query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public void addBatch() {
        Session session = (Session) sessionFactory.getObject().openSession();
        Transaction trans = null;
        try {
            //Action Delete
            trans = session.beginTransaction();
            for (int i = 0; i < 20; i++) {
                session.save(UserFactory.generate(i + 1));
            }

            session.flush();
            trans.commit();
        } catch (HibernateException e) {
            if(trans != null) trans.rollback();
            LOGGER.error(e, e);
        } finally {
            session.close();
        }
    }
}
