package edu.hanoi.spring.dao;

import edu.hanoi.spring.model.Group;
import edu.hanoi.spring.model.User;

import java.util.List;

public interface GroupDAO {
    public void insert(Group group);

    public List<Group> list();

    public void delete(int id);

    public void update(Group group);

    public Group getGroup(int id);
}
