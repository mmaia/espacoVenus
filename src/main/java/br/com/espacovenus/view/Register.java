package br.com.espacovenus.view;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.espacovenus.model.Role;
import br.com.espacovenus.model.User;
import br.com.espacovenus.service.UserService;


@Named
@RequestScoped
public class Register {
	
	Logger logger = Logger.getLogger(Register.class.getName());

    private User user;

    @EJB
    private UserService service;

    @PostConstruct
    public void init() {
        user = new User();
    }

    public void submit() {
        try {
        	if((user.getPassword()).equals(user.getPasswordConfirmation()))
            {
        		user.setRole(Role.PACIENTE);
        		service.create(user);
        		logger.info("User created ==>> " + user.getUsername());
            }
        	else 
        	{
        		logger.severe("Passwords don't match");
        	}
        }
        catch (RuntimeException e) {
            logger.severe("Registration failed: " + e.getMessage());
            e.printStackTrace(); 
        }
    }

    public User getUser() {
        return user;
    }

}