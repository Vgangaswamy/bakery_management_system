package edu.iit.sat.itmd4515.vgangaswamy.security;

import edu.iit.sat.itmd4515.vgangaswamy.service.AbstractService;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class GroupService extends AbstractService<Group> {
    public GroupService() {
        super(Group.class);
    }

    public List<Group> readAll(){
        return super.readAll("Group.findAll");
    }


}


