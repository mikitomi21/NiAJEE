package com.company.hightechcomapny.authentication;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

@ApplicationScoped

//@BasicAuthenticationMechanismDefinition(realmName = "HighTechComapny")
@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/authentication/custom/login.xhtml",
                errorPage = "/authentication/custom/login_error.xhtml"
        )
)
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/HighTechComapny",
        callerQuery = "select password from employees where name = ?",
        groupsQuery = "select role from employees__roles where id = (select id from employees where name = ?)",
        hashAlgorithm = Pbkdf2PasswordHash.class
//        hashAlgorithmParameters = {
//                "Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA256",
//                "Pbkdf2PasswordHash.Iterations=2048",
//                "Pbkdf2PasswordHash.SaltSizeBytes=32",
//                "Pbkdf2PasswordHash.KeySizeBytes=32"
//        }
)

public class AuthenticationConfig {
}
