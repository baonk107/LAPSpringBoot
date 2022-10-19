package edu.hanoi.jazz.service;

import edu.hanoi.jazz.controller.UserController;
import edu.hanoi.jazz.dao.UserDAO;
import edu.hanoi.jazz.dao.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAuthProvider implements AuthenticationProvider {

    private final static Logger LOGGER = Logger.getLogger(UserAuthProvider.class);
    @Autowired
    private UserDAO userDAO;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("Run funtion authenticate");

        String username = authentication.getName().toString();
        User user = userDAO.getUser(username);
        if(user == null) return null;
        LOGGER.info("========> Found " + user + " by " + username);
        
        if(!user.getPassword().equals(authentication.getCredentials())) return null;
        return successful(username, authentication.getCredentials().toString(), user.getPassword());
    }

    private UsernamePasswordAuthenticationToken successful(String username, String toString, String password) {
        System.out.println("Run funtion successful");

        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        System.out.println("Run funtion supports");

        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
