package edu.hanoi.jazz.service;

import edu.hanoi.jazz.controller.GroupController;
import edu.hanoi.jazz.dao.UserDAO;
import edu.hanoi.jazz.dao.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

public class JazzConnectionSignUp implements ConnectionSignUp {
    private final static Logger LOGGER = Logger.getLogger(JazzConnectionSignUp.class);
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private FBService fbService;

    @Override
    public String execute(Connection<?> connection) {
        System.out.println("Run funtion execute");
//        UserProfile userProfile = connection.fetchUserProfile();
        UserProfile userProfile = fbService.getProfile("me", connection.createData().getAccessToken());

        LOGGER.info("========> id: " + userProfile.getId() + " name: " + userProfile.getName());
        User user = userDAO.getUser(userProfile.getId());
        if(user != null) return user.getUsername();

        user = new User();
        user.setUsername(userProfile.getId());
        user.setPassword("123");
        user.setGroupId(1);
        user.setEmail(user.getEmail());
        user.setAge(65);
        userDAO.insert(user);

        return user.getUsername();
    }
}
