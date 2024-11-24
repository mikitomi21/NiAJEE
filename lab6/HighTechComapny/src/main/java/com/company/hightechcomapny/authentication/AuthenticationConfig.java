package com.company.hightechcomapny.authentication;

import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/authentication/custom/login.xhtml",
                errorPage = "/authentication/custom/login_error.xhtml"
        )
)
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/SimpleRpgCharacters",
        callerQuery = "select password from employees where name = ?",
        groupsQuery = "select role from employees__roles where id = (select id from employees where name = ?)",
        hashAlgorithm = Pbkdf2PasswordHash.class
)

public class AuthenticationConfig {
}
