package edu.hanoi.service;

import edu.hanoi.service.controller.UserRestServiceController;
import edu.hanoi.service.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringWebServiceApplication.class)
@WebAppConfiguration
@TestExecutionListeners(listeners = {ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class})
public class SpringWebServiceApplicationTest {

    @Autowired
    UserRestServiceController userRestServiceController;

    @Test
    @WithMockUser(username = "admin", password = "654321", roles = {"USER"})
    public void contextLoad() {
        List<User> users = userRestServiceController.listUser();
        Assert.assertTrue(users.size() > 0);

        users.forEach(user -> {
            Assert.assertTrue(user.getAge() > 50);
        });
    }
}