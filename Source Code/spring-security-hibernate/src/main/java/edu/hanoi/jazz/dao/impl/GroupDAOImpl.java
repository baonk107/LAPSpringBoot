package edu.hanoi.jazz.dao.impl;

import edu.hanoi.jazz.dao.GroupDAO;
import edu.hanoi.jazz.dao.model.Group;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component("groupDAO")
public class GroupDAOImpl implements GroupDAO {

    private final static Logger LOGGER = Logger.getLogger(GroupDAOImpl.class);
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public void insert(Group group) {
        Session session = (Session) sessionFactory.getObject().openSession();
        try {
            //Action Insert
            Transaction trans = session.beginTransaction();
            session.save(group);
            session.flush();
            trans.commit();

            LOGGER.info("Save group " + group.getName() + " done!");
        } finally {
            session.close();
        }
        System.out.println(sessionFactory + ": Insert group " + group);
    }

    //List
    @Override
    public List<Group> list(String pattern) {
        Session session = (Session) sessionFactory.getObject().openSession();
        try {
            if (pattern == null || pattern.length() < 1) {
                Query query = session.createQuery("from Group");
                return (List<Group>) query.list();
            }
            Criteria criteria = session.createCriteria(Group.class);
            criteria.add(Restrictions.like("name", "%" + pattern + "%", MatchMode.ANYWHERE));
            return new ArrayList<Group>(criteria.list());
        }finally {
            session.close();
        }
    }

    //Delete
    @Override
    public void delete(int id) {
        Session session = (Session) sessionFactory.getObject().openSession();
        Group group = session.get(Group.class, id);
        if (group == null) return;
        //Action Delete
        Transaction trans = session.beginTransaction();
        session.delete(group);
        session.flush();
        trans.commit();

        LOGGER.info("Delete group " + group.getName() + " successful!");
        session.close();
    }

    @Override
    public void update(Group group) {
        Session session = (Session) sessionFactory.getObject().openSession();

        //Action Update
        Transaction trans = session.beginTransaction();
        group = (Group) session.merge(group);
        session.save(group);
        session.flush();
        trans.commit();

        LOGGER.info("Update group " + group.getName() + " successful!");
        session.close();
    }

    @Override
    public Group getGroup(int id) {
        Session session = (Session) sessionFactory.getObject().openSession();
        Group group = session.get(Group.class, id);
        System.out.println("new Group " + group);
        return group;
    }

    //Serch By Name
    public List<Group> searchName(String name){
        Session session = (Session) sessionFactory.getObject().openSession();
        Query query = session.createQuery("from Student where name like :studentName");
        query.setParameter("studentName", "%" + name + "%");
        return (List<Group>) query.list();
    }
}
