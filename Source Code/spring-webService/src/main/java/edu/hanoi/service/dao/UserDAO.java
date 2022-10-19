package edu.hanoi.service.dao;

import edu.hanoi.service.model.User;

import java.util.List;

public interface UserDAO {
    public List<User> list();
    public String insert(User user);
    public User getUser(String username);

    public void delete(String username);

    public void update(User user);
}
