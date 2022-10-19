package edu.hanoi.service.dao.impl;

import edu.hanoi.service.dao.GroupDAO;
import edu.hanoi.service.model.Group;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
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
    public List<Group> list() {
        Session session = (Session) sessionFactory.getObject().openSession();
        try {
            Query query = session.createQuery("from Group");
            return (List<Group>) query.list();
        } finally {
            session.close();
        }
    }
}
