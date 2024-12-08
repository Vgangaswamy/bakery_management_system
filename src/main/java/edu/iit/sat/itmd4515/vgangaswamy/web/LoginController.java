package edu.iit.sat.itmd4515.vgangaswamy.web;

import edu.iit.sat.itmd4515.vgangaswamy.security.User;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
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

    public boolean isCustomer(){
        boolean isCustomer = securityContext.isCallerInRole("CUSTOMER_ROLE");
        LOG.info("User isCustomer: " + isCustomer);
        return isCustomer;
    }

    public boolean isBakeryOwner() {
        boolean isBakeryOwner = securityContext.isCallerInRole("BAKERY_ROLE");
        LOG.info("User isBakeryOwner: " + isBakeryOwner);
        return isBakeryOwner;
    }

    //Helper methods
    public String getAuthenticatedUsername(){
        return securityContext.getCallerPrincipal().getName();
    }

    //action methods
    public String doLogin() throws IOException {
        LOG.info("Inside LoginController.doLogin()");
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        Credential credential = new UsernamePasswordCredential(user.getUsername(), new Password(user.getPassword()));

        AuthenticationStatus status = securityContext.authenticate(request, response,
                AuthenticationParameters.withParams().credential(credential));

        LOG.info("Authentication status: " + status);

        if (status == AuthenticationStatus.SUCCESS) {
            LOG.info("Authentication successful. Checking user roles...");
            if (isAdmin()) {
                LOG.info("User is admin. Redirecting to admin welcome page.");
                return "/admin/welcome.xhtml?faces-redirect=true"; // Admin dashboard
            } else if (isCustomer()) {
                LOG.info("User is customer. Redirecting to customer welcome page.");
                return "/customer/welcome.xhtml?faces-redirect=true"; // Customer homepage
            } else if (isBakeryOwner()) {
                LOG.info("User is bakery owner. Redirecting to bakery welcome page.");
                return "/bakery/welcome.xhtml?faces-redirect=true"; // Bakery homepage
            } else {
                LOG.info("User role not recognized. Redirecting to default welcome page.");
                return "/welcome.xhtml?faces-redirect=true"; // Default welcome page
            }
        } else if (status == AuthenticationStatus.SEND_FAILURE || status == AuthenticationStatus.NOT_DONE) {
            LOG.warning("Authentication failed.");
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", "Invalid credentials"));
            return null; // Stay on login page
        }

        return null;
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
