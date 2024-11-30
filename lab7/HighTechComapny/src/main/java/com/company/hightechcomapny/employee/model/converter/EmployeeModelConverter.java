package com.company.hightechcomapny.employee.model.converter;

import com.company.hightechcomapny.component.ModelFunctionFactory;
import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.model.EmployeeModel;
import com.company.hightechcomapny.employee.service.EmployeeService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

import java.util.Optional;
import java.util.UUID;

@FacesConverter(value = "employeeConverter", forClass = EmployeeModel.class, managed = true)
public class EmployeeModelConverter implements Converter<EmployeeModel> {
    private final EmployeeService service;
    private final ModelFunctionFactory factory;

    @Inject
    public EmployeeModelConverter(EmployeeService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public EmployeeModel getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("EmployeeModelConverter.getAsObject");
        if (value == null || value.isBlank()) {
            return null;
        }
        Optional<Employee> employee = service.find(UUID.fromString(value));

        System.out.println("EmployeeModelConverter.getAsObject");
        return employee.map(factory.employeeToModel()).orElse(null);

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, EmployeeModel value) {
        System.out.println("EmployeeModelConverter.getAsString");
        return value == null ? "" : value.getId().toString();
    }
}
