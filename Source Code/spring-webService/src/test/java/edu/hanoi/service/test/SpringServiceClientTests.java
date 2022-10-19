package edu.hanoi.service.test;

import edu.hanoi.service.model.Group;
import edu.hanoi.service.model.User;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@RunWith(JUnit4.class)
public class SpringServiceClientTests {

    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        HttpClientBuilder builder = HttpClientBuilder.create();
        CloseableHttpClient httpClent = builder.build();
        restTemplate = new RestTemplate(
                new HttpComponentsClientHttpRequestFactory(httpClent));
    }

    @Test
    public void testLoadGroup() {
        String addressGroups = "http://localhost:8080/list/groups/";
        ResponseEntity<Group[]> groupEntity = restTemplate.getForEntity(addressGroups, Group[].class);
        Group[] groups = groupEntity.getBody();
        Assert.assertTrue(groups.length > 0);

        for (int i = 0; i < groups.length; i++) {
            System.out.println(groups[i].getId());
        }
    }

    @Test
    public void testLoadUser() {
        String address = "http://localhost:8080/list/users/";
        ResponseEntity<List> entity = restTemplate.getForEntity(address, List.class);
        List<User> users = (List<User>) entity.getBody();
    }


    @Test
    public void testInsert() {
        //Test Insert User
        User user = new User();
        user.setUsername("test1");
        user.setPassword("123455");
        user.setAge(25);
        user.setGroupId(301);
        user.setEmail("test1@gmail.com");

        System.out.println(user);

        String addressAddUser = "http://localhost:8080/add/user";
        ResponseEntity<String> insertEntity = restTemplate.postForEntity(addressAddUser, user, String.class);
        Assert.assertEquals(user.getUsername(), insertEntity.getBody());
    }

    @Test
    public void testGet() {
        String addressGetUser = "http://localhost:8080/get/user/test1";
        ResponseEntity<User> getEntity = restTemplate.getForEntity(addressGetUser, User.class);
        Assert.assertEquals("123455", getEntity.getBody().getPassword());
    }

    @Test
    public void testDeleteUser() {
        String addressDelUser = "http://localhost:8080/delete/user/test3";
        ResponseEntity<Void> delEntity = restTemplate.getForEntity(addressDelUser, Void.class);

        String addressGetUser = "http://localhost:8080/get/user/test3";
        ResponseEntity<User> getEntity = restTemplate.getForEntity(addressGetUser, User.class);
        Assert.assertEquals(null, getEntity.getBody());
    }

    @Test
    public void testUpdateUser(){
        String addressGetUser = "http://localhost:8080/get/user/test1";
        ResponseEntity<User> getEntity = restTemplate.getForEntity(addressGetUser, User.class);
        User user = getEntity.getBody();
        Assert.assertNotNull(user);

        user.setPassword("654321");
        String addressUpdateUser = "http://localhost:8080/update/user";
        restTemplate.postForEntity(addressUpdateUser, user, Void.class);

        addressGetUser = "http://localhost:8080/get/user/test1";
        ResponseEntity<User> getEntity2 = restTemplate.getForEntity(addressGetUser, User.class);
        Assert.assertEquals(user.getPassword(), getEntity2.getBody().getPassword());
    }

}
