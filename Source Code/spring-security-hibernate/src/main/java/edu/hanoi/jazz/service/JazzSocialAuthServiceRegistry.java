package edu.hanoi.jazz.service;

import org.springframework.social.security.SocialAuthenticationServiceRegistry;
import org.springframework.social.security.provider.SocialAuthenticationService;

import javax.annotation.PostConstruct;
import java.util.List;

public class JazzSocialAuthServiceRegistry extends SocialAuthenticationServiceRegistry {
    private List<SocialAuthenticationService<?>> authenticationServices;

    public JazzSocialAuthServiceRegistry(List<SocialAuthenticationService<?>> authenticationServices) {
        this.authenticationServices = authenticationServices;
    }

    @PostConstruct
    public void init(){
        System.out.println("Run funtion init");
        if(authenticationServices == null) return;;
        authenticationServices.forEach(auth -> super.addAuthenticationService(auth));
    }
}
