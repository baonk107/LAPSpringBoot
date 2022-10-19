package edu.hanoi.spring.dao.impl;

import edu.hanoi.spring.dao.GroupDAO;
import edu.hanoi.spring.model.Group;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("groupDAO")
public class GroupDAOImpl implements GroupDAO {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public void insert(Group group) {

    }

    @Override
    public List<Group> list() {
        Session session = (Session) sessionFactory.getObject().openSession();
        try {
            Query query = session.createQuery("from Group");
            return (List<Group>) query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = (Session) sessionFactory.getObject().openSession();
        try {
            Group group = session.get(Group.class, id);
            if (group == null) return;
            //Action Delete
            Transaction trans = session.beginTransaction();
            session.delete(group);
            session.flush();
            trans.commit();

            System.out.println("Delete group " + group.getName() + " successful!");
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Group group) {
        Session session = (Session) sessionFactory.getObject().openSession();
        try {
            //Action Update
            Transaction trans = session.beginTransaction();
            group = (Group) session.merge(group);
            session.save(group);
            session.flush();
            trans.commit();

            System.out.println("Update group " + group.getName() + " successful!");
        } finally {
            session.close();
        }
    }

    @Override
    public Group getGroup(int id) {
        Session session = sessionFactory.getObject().openSession();
        Group group = session.get(Group.class, id);
        System.out.println("new Group " + group);
        return group;
    }
}
