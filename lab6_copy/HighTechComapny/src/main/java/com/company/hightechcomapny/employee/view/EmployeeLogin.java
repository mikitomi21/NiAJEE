package com.company.hightechcomapny.employee.view;

import com.company.hightechcomapny.employee.service.EmployeeService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
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

    private final SecurityContext securityContext;

    private final FacesContext facesContext;

    private final EmployeeService employeeService;

    @Inject
    public EmployeeLogin(HttpServletRequest request, @SuppressWarnings("CdiInjectionPointsInspection") SecurityContext securityContext, FacesContext facesContext, EmployeeService employeeService) {
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
        System.out.println(password);
        Credential credential = new UsernamePasswordCredential(name, new Password(password));
        System.out.println(credential);
        AuthenticationStatus status = securityContext.authenticate(request, extractResponseFromFacesContext(),
                withParams().credential(credential));
        System.out.println(status);
        if (status == AuthenticationStatus.SUCCESS) {
            System.out.println("SUCCESS");
            employeeService.updateCallerPrincipalLastLoginDateTime();
        } else if (status == AuthenticationStatus.SEND_CONTINUE) {
            System.out.println("SEND_CONTINUE");
            employeeService.updateCallerPrincipalLastLoginDateTime();
        } else if (status == AuthenticationStatus.SEND_FAILURE) {
            System.out.println("Authentication failed");
        }
        System.out.println(facesContext.getLifecycle());

        facesContext.responseComplete();
//        return "/task/task_list.xhtml?faces-redirect=true";

    }

    private HttpServletResponse extractResponseFromFacesContext() {
        System.out.println("Siema321");
        System.out.println(facesContext.getExternalContext().getResponse().getClass().toString());
        return (HttpServletResponse) facesContext.getExternalContext().getResponse();
    }

}
