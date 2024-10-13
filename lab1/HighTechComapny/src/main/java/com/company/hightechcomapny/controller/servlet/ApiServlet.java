package com.company.hightechcomapny.controller.servlet;

import com.company.hightechcomapny.employee.controller.api.EmployeeController;
import com.company.hightechcomapny.employee.controller.simple.EmployeeSimpleController;
import com.company.hightechcomapny.employee.dto.PatchEmployeeRequest;
import com.company.hightechcomapny.employee.dto.PutEmployeeRequest;
import com.company.hightechcomapny.exception.EmployeeAlreadyExistsException;
import com.company.hightechcomapny.exception.EmployeeNotFoundException;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
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

    private EmployeeController employeeController;

    public static final class Paths {
        public static final String API = "/api";
    }

    public static final class Patterns {
        private static final Pattern UUID = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
        private static final Pattern EMPLOYEES = Pattern.compile("/employees");
        private static final Pattern EMPLOYEE = Pattern.compile("/employees/(%s)".formatted(UUID.pattern()));
        private static final Pattern EMPLOYEE_PICTURE = Pattern.compile("/employees/(%s)/picture".formatted(UUID.pattern()));
    }

    private final Jsonb jsonb = JsonbBuilder.create();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equals("PATCH")) {
            doPatch(request, response);
        } else {
            super.service(request, response);
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        employeeController = (EmployeeSimpleController) getServletContext().getAttribute("employeeController");
        System.out.println("INIT");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getPathInfo();
        String servletPath = req.getServletPath();

        System.out.println("GET");

        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.EMPLOYEES.pattern())) {
                try {
                    resp.getWriter().write(jsonb.toJson(employeeController.getEmployees()));
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.setContentType("application/json");
                } catch (EmployeeNotFoundException e) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
                return;

            } else if (path.matches(Patterns.EMPLOYEE.pattern())) {
                UUID uuid = extractUuid(Patterns.EMPLOYEE, path);
                try {
                    resp.getWriter().write(jsonb.toJson(employeeController.getEmployee(uuid)));
                    resp.setContentType("application/json");
                    resp.setStatus(HttpServletResponse.SC_OK);
                } catch (EmployeeNotFoundException e) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
                return;

            } else if (path.matches(Patterns.EMPLOYEE_PICTURE.pattern())) {
                resp.setContentType("image/jpg");
                UUID uuid = extractUuid(Patterns.EMPLOYEE_PICTURE, path);
                try {
                    byte[] portrait = employeeController.getEmployeePicture(uuid);
                    resp.setContentLength(portrait.length);
                    resp.getOutputStream().write(portrait);
                } catch (EmployeeNotFoundException e) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
                return;

            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        String servletPath = req.getServletPath();

        System.out.println("PUT");

        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.EMPLOYEE.pattern())) {
                System.out.println("PUT EMPLOYEE");
                UUID uuid = extractUuid(Patterns.EMPLOYEE, path);
                try {
                    employeeController.putEmployee(uuid, jsonb.fromJson(req.getReader(), PutEmployeeRequest.class));
                    resp.addHeader("Location", createUrl(req, Paths.API, "employees", uuid.toString()));
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } catch(EmployeeAlreadyExistsException e) {
                    resp.setStatus(HttpServletResponse.SC_CONFLICT);
                }
                return;
            } else if (path.matches(Patterns.EMPLOYEE_PICTURE.pattern())) {
                UUID uuid = extractUuid(Patterns.EMPLOYEE_PICTURE, path);
                System.out.println("PUT EMPLOYEE PICTURE");
                try {
                    String fileName = req.getPart("picture").getSubmittedFileName();
                    employeeController.putEmployeePicture(uuid, req.getPart("picture").getInputStream(), fileName);
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } catch (EmployeeAlreadyExistsException e) {
                    resp.setStatus(HttpServletResponse.SC_CONFLICT);
                }
                return;

            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @SuppressWarnings("RedundantThrows")
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        String servletPath = req.getServletPath();

        System.out.println("DELETE");

        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.EMPLOYEE.pattern())) {
                UUID uuid = extractUuid(Patterns.EMPLOYEE, path);
                try {
                    employeeController.deleteEmployee(uuid);
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } catch (EmployeeNotFoundException e) {
                    System.out.println("Employee not found");
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
                return;
            }
            if (path.matches(Patterns.EMPLOYEE_PICTURE.pattern())) {
                UUID uuid = extractUuid(Patterns.EMPLOYEE_PICTURE, path);
                try {
                    employeeController.deleteEmployeePicture(uuid);
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } catch (EmployeeNotFoundException e) {
                    System.out.println("Employee picture not found");
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
                return;

            }else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @SuppressWarnings("RedundantThrows")
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        String servletPath = req.getServletPath();

        System.out.println("PATCH");

        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.EMPLOYEE.pattern())) {
                System.out.println("PATCH EMPLOYEE");
                UUID uuid = extractUuid(Patterns.EMPLOYEE, path);
                employeeController.patchEmployee(uuid, jsonb.fromJson(req.getReader(), PatchEmployeeRequest.class));
                resp.addHeader("Location", createUrl(req, Paths.API, "employees", uuid.toString()));
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                return;
            }
            if (path.matches(Patterns.EMPLOYEE_PICTURE.pattern())){
                UUID uuid = extractUuid(Patterns.EMPLOYEE_PICTURE, path);
                String fileName = req.getPart("picture").getSubmittedFileName();
                employeeController.patchEmployeePicture(uuid, req.getPart("picture").getInputStream(), fileName);
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                return;

            }else {
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

    public static String createUrl(HttpServletRequest request, String... paths) {
        StringBuilder builder = new StringBuilder();
        builder.append(request.getScheme())
                .append("://")
                .append(request.getServerName())
                .append(":")
                .append(request.getServerPort())
                .append(request.getContextPath());
        for (String path : paths) {
            builder.append("/")
                    .append(path, path.startsWith("/") ? 1 : 0, path.endsWith("/") ? path.length() - 1 : path.length());
        }
        return builder.toString();
    }



}

