package edu.iit.sat.itmd4515.vgangaswamy.security;

import edu.iit.sat.itmd4515.vgangaswamy.service.AbstractService;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class UserService extends AbstractService<User> {
    protected UserService() {
        super(User.class);
    }

    public List<User> readAll(){
        return super.readAll("User.findAll");
    }

}

