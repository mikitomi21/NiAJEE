package com.company.hightechcomapny.controller.servlet;

import com.company.hightechcomapny.employee.controller.api.EmployeeController;
import com.company.hightechcomapny.employee.controller.simple.EmployeeSimpleController;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdk.javadoc.doclet.DocletEnvironment;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@WebServlet(urlPatterns = {
        ApiServlet.Paths.API + "/*"
})
@MultipartConfig(maxFileSize = 200 * 1024)

public class ApiServlet extends HttpServlet {

    private EmployeeSimpleController employeeController;

    public static final class Paths {
        public static final String API = "/api";
    }

    public static final class Patterns {
        private static final Pattern UUID = Pattern.compile("/[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
        private static final Pattern EMPLOYEES = Pattern.compile("/employees");
        private static final Pattern EMPLOYEE = Pattern.compile("/employees/(%s)".formatted(UUID.pattern()));
        private static final Pattern EMPLOYEE_PICTURE = Pattern.compile("/employees/(%s)/picture".formatted(UUID.pattern()));
    }

    private final Jsonb jsonb = JsonbBuilder.create();
    @Override
    public void init() throws ServletException {
        super.init();
        employeeController = (EmployeeSimpleController) getServletContext().getAttribute("employeeController");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        String servletPath = req.getServletPath();
        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.EMPLOYEES.pattern())) {
                resp.setContentType("application/json");
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write(jsonb.toJson(employeeController.getEmployees()));
                return;

            } else if (path.matches(Patterns.EMPLOYEE.pattern())) {
                resp.setContentType("application/json");
                resp.setStatus(HttpServletResponse.SC_OK);
                UUID uuid = extractUuid(Patterns.EMPLOYEE, path);
                resp.getWriter().write(jsonb.toJson(employeeController.getEmployee(uuid)));
                return;

            } else if (path.matches(Patterns.EMPLOYEE_PICTURE.pattern())) {
                resp.setContentType("image/png");
                UUID uuid = extractUuid(Patterns.EMPLOYEE_PICTURE, path);
                byte[] portrait = employeeController.getEmployeePicture(uuid);
                resp.setContentLength(portrait.length);
                resp.getOutputStream().write(portrait);
                return;

            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    private static UUID extractUuid(Pattern pattern, String path) {
        Matcher matcher = pattern.matcher(path);
        if (matcher.matches()) {
            return UUID.fromString(matcher.group(1));
        }
        throw new IllegalArgumentException("No UUID in path.");
    }


}

