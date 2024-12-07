package edu.iit.sat.itmd4515.vgangaswamy.web;

import edu.iit.sat.itmd4515.vgangaswamy.security.Group;
import edu.iit.sat.itmd4515.vgangaswamy.security.GroupService;
import edu.iit.sat.itmd4515.vgangaswamy.security.User;
import edu.iit.sat.itmd4515.vgangaswamy.security.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.logging.Logger;


@Named
@RequestScoped
public class UserController {

    private static final Logger LOG = Logger.getLogger(GroupService.class.getName());

    @Inject
    private UserService userService;

    @Inject
    private GroupService groupService;

    private User user = new User();
    private String selectedRole; // Role selected during sign-up

    public String register() {
        try {
            LOG.info("Registering user with role: " + selectedRole);
            Group group = groupService.findByName(selectedRole);

            if (group == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid role selected", "Role does not exist."));
                return null; // Stay on the same page
            }

            user.addGroup(group);
            userService.create(user);

            LOG.info("User registered successfully: " + user);
            return "/login.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration failed", e.getMessage()));
            return null; // Stay on the same page
        }
    }


    // Getters and setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(String selectedRole) {
        this.selectedRole = selectedRole;
    }
}


