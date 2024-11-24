package com.company.hightechcomapny.configuration.singleton;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.entity.EmployeeRoles;
import com.company.hightechcomapny.employee.repository.api.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;
import java.util.UUID;

@Singleton
@Startup
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
@NoArgsConstructor(force = true)
public class InitializeAdminService {
    private final EmployeeRepository employeeRepository;
    private final Pbkdf2PasswordHash passwordHash;

    @Inject
    public InitializeAdminService(EmployeeRepository employeeRepository, Pbkdf2PasswordHash passwordHash) {
        this.employeeRepository = employeeRepository;
        this.passwordHash = passwordHash;
    }
    @PostConstruct
    @SneakyThrows
    private void init() {
        if (employeeRepository.findByName("admin-service").isEmpty()) {

            Employee admin = Employee.builder()
                    .id(UUID.fromString("14d59f3a-057c-44d5-825a-19295a6600a8"))
                    .name("admin-service")
                    .password(passwordHash.generate("siema".toCharArray()))
                    .roles(List.of(EmployeeRoles.ADMIN, EmployeeRoles.USER))
                    .build();

            System.out.println("Admin utowrzony!!!");
            employeeRepository.create(admin);
        }
    }


}
