package edu.hanoi.jazz.dao.impl;

import edu.hanoi.jazz.dao.model.User;

import java.util.Random;

public class UserFactory {
    public static User generate(int index){
        User user = new User();
        Random r = new Random();
        user.setUsername("username-random" + index);
        user.setPassword("password" + r.nextInt() + 1);
        user.setEmail("ten"+r.nextInt() + 1 + "@gmail.com");
        user.setAge(r.nextInt(100) + 1);
        user.setGroupId(1);
        return user;
    }
}
