package edu.iit.sat.itmd4515.vgangaswamy.web;

import edu.iit.sat.itmd4515.vgangaswamy.security.User;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

@Named
@RequestScoped
public class LoginController {
    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    @Inject FacesContext facesContext;
    @Inject SecurityContext securityContext;

    private User user;
    public LoginController() {
    }
    @PostConstruct
    private void postConstruct(){
        LOG.info("Inside LoginController.postConstruct()");
        user = new User();
    }

    public boolean isAdmin(){
        boolean isAdmin = securityContext.isCallerInRole("ADMIN_ROLE");
        LOG.info("User isAdmin: " + isAdmin);
        return isAdmin;
    }

    public boolean isBakery(){
        boolean isBakery = securityContext.isCallerInRole("BAKERY_ROLE");
        LOG.info("User isBakery: " + isBakery);
        return isBakery;
    }

    public boolean isOwner(){
        boolean isOwner = securityContext.isCallerInRole("OWNER_ROLE");
        LOG.info("User isOwner: " + isOwner);
        return isOwner;
    }


    //Helper methods
    public String getAuthenticatedUsername(){
        return securityContext.getCallerPrincipal().getName();
    }

    //action methods
    public String doLogin() throws IOException {

        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        Credential cred = new UsernamePasswordCredential(this.user.getUsername(), new Password(this.user.getPassword()));

        AuthenticationStatus status = securityContext.authenticate(request, response, AuthenticationParameters.withParams().credential(cred));

        if (status == AuthenticationStatus.SUCCESS) {
            LOG.info("Authentication successful");
            return "/welcome.xhtml?faces-redirect=true";
        }

        if (status == AuthenticationStatus.SEND_FAILURE || status == AuthenticationStatus.NOT_DONE) {
            LOG.info("Authentication failed, status: " + status);
            facesContext.getExternalContext().redirect("/error.xhtml");
            return null;
        }

        return "/welcome.xhtml?faces-redirect=true";
    }

    public String doLogout(){
        LOG.info("LoginController.doLogout()");
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        try {
            request.logout();
            // Invalidate session
            facesContext.getExternalContext().invalidateSession();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        return "/login.xhtml?faces-redirect=true";
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
