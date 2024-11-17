package edu.iit.sat.itmd4515.vgangaswamy.config;

import jakarta.annotation.security.DeclareRoles;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

@DeclareRoles({"ADMIN_ROLE","BAKERY_ROLE","CUSTOMER_ROLE"})
@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml",
                errorPage = "/error.xhtml"
        )
)
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:app/jdbc/itmd4515DS",
        callerQuery = "select password from SEC_USER where USERNAME = ?",
        groupsQuery = "select GROUPNAME from SEC_USER_GROUPS where USERNAME = ?"
)
public class ITMD4515SecurityConfig {
}
