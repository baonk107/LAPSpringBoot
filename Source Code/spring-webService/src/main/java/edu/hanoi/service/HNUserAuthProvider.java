package edu.hanoi.service;

import edu.hanoi.service.dao.UserDAO;
import edu.hanoi.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class HNUserAuthProvider implements AuthenticationProvider {
    @Autowired
    private UserDAO userDAO;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName().toString();
        System.out.println("User name: " + username);
        System.out.println("Password: " + authentication.getCredentials());
        User user = userDAO.getUser(username);
        System.out.println("User: " + user);
        if (user == null) return null;
        System.out.println("========> Found " + user + " by " + username);

        if (!user.getPassword().equals(authentication.getCredentials())) return null;
        return successful(username, authentication.getCredentials().toString(), "ROLE_USER");
    }

    private UsernamePasswordAuthenticationToken successful(String username, String password, String role) {
        ArrayList<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority(role));

        return new UsernamePasswordAuthenticationToken(username,
                password,
                grantedAuths);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
