package edu.iit.sat.itmd4515.vgangaswamy.security;

import edu.iit.sat.itmd4515.vgangaswamy.service.AbstractService;
import jakarta.ejb.Stateless;

import java.util.List;
import java.util.logging.Logger;

@Stateless
public class GroupService extends AbstractService<Group> {

    private static final Logger LOG = Logger.getLogger(GroupService.class.getName());


    public GroupService() {
        super(Group.class);
    }

    public Group findByName(String groupName) {
        List<Group> results = getEntityManager()
                .createQuery("SELECT g FROM Group g WHERE g.groupName = :groupName", Group.class)
                .setParameter("groupName", groupName)
                .getResultList();

        if (results.isEmpty()) {
            LOG.warning("No group found with name: " + groupName);
            return null; // Or throw a custom exception
        }

        return results.get(0); // Return the first result if found
    }

}
