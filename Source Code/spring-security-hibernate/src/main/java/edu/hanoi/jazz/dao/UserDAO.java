package edu.hanoi.jazz.dao;

import edu.hanoi.jazz.dao.model.Group;
import edu.hanoi.jazz.dao.model.User;

import java.util.List;

public interface UserDAO {
    public void insert(User user);
    public List<User> listUser();
    public List<User> listFilter(int group);

    public User getUser(String username);
    public void deleteUser(String name);

    public int avarageAge();

    public List<User> page(int page);

    public List<User> listUserByNativeSQL();

    public void addBatch();
}
