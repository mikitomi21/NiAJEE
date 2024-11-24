package com.company.hightechcomapny.employee.view;

import com.company.hightechcomapny.employee.service.EmployeeService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import static jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;

@RequestScoped
@Named
@Log
@NoArgsConstructor(force = true)
public class EmployeeLogin {
    private final HttpServletRequest request;

    /**
     * Security context.
     */
    private final SecurityContext securityContext;

    /**
     * Faces context.
     */
    private final FacesContext facesContext;

    /**
     * User service.
     */
    private EmployeeService employeeService;

    public EmployeeLogin(HttpServletRequest request, SecurityContext securityContext, FacesContext facesContext, EmployeeService employeeService) {
        this.request = request;
        this.securityContext = securityContext;
        this.facesContext = facesContext;
        this.employeeService = employeeService;
    }
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String password;


    @SneakyThrows
    public void loginAction() {
        Credential credential = new UsernamePasswordCredential(name, new Password(password));
        AuthenticationStatus status = securityContext.authenticate(request, extractResponseFromFacesContext(),
                withParams().credential(credential));
        if (status == AuthenticationStatus.SUCCESS) {
            //ATM there is no build-in CDI event to observe.
            System.out.println("Poprawnie zalogowany");
//            employeeService.updateCallerPrincipalLastLoginDateTime();
        }
        facesContext.responseComplete();
    }

    private HttpServletResponse extractResponseFromFacesContext() {
        return (HttpServletResponse) facesContext.getExternalContext().getResponse();
    }

}
