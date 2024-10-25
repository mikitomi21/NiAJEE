package com.company.hightechcomapny.controller.servlet;

import com.company.hightechcomapny.employee.controller.api.EmployeeController;
import com.company.hightechcomapny.employee.dto.PatchEmployeeRequest;
import com.company.hightechcomapny.employee.dto.PutEmployeeRequest;
import com.company.hightechcomapny.project.controller.api.ProjectController;
import com.company.hightechcomapny.project.dto.PatchProjectRequest;
import com.company.hightechcomapny.project.dto.PutProjectRequest;
import com.company.hightechcomapny.task.controller.api.TaskController;
import com.company.hightechcomapny.task.dto.PatchTaskRequest;
import com.company.hightechcomapny.task.dto.PutTaskRequest;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@WebServlet(urlPatterns = {
        ApiServlet.Paths.API + "/*"
})
@MultipartConfig(maxFileSize = 200 * 1024)

public class ApiServlet extends HttpServlet {

    private final EmployeeController employeeController;
    private final TaskController taskController;
    private final ProjectController projectController;

    public static final class Paths {
        public static final String API = "/api";
    }

    public static final class Patterns {
        private static final Pattern UUID = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
        private static final Pattern EMPLOYEES = Pattern.compile("/employees");
        private static final Pattern EMPLOYEE = Pattern.compile("/employees/(%s)".formatted(UUID.pattern()));
        private static final Pattern EMPLOYEE_PICTURE = Pattern.compile("/employees/(%s)/picture".formatted(UUID.pattern()));
        private static final Pattern TASK = Pattern.compile("/tasks/(%s)".formatted(UUID.pattern()));
        private static final Pattern TASKS = Pattern.compile("/tasks");
        private static final Pattern PROJECT = Pattern.compile("/projects/(%s)".formatted(UUID.pattern()));
        private static final Pattern PROJECTS = Pattern.compile("/projects");
    }

    private final Jsonb jsonb = JsonbBuilder.create();

    @Inject
    public ApiServlet(EmployeeController employeeController, TaskController taskController, ProjectController projectController) {
        this.employeeController = employeeController;
        this.taskController = taskController;
        this.projectController = projectController;
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equals("PATCH")) {
            doPatch(request, response);
        } else {
            super.service(request, response);
        }
    }

    @SuppressWarnings("RedundantThrows")
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
                } catch (RuntimeException e) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }

            } else if (path.matches(Patterns.EMPLOYEE.pattern())) {
                UUID uuid = extractUuid(Patterns.EMPLOYEE, path);
                try {
                    resp.getWriter().write(jsonb.toJson(employeeController.getEmployee(uuid)));
                    resp.setContentType("application/json");
                    resp.setStatus(HttpServletResponse.SC_OK);
                } catch (RuntimeException e) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }

            } else if (path.matches(Patterns.EMPLOYEE_PICTURE.pattern())) {
                resp.setContentType("image/jpg");
                UUID uuid = extractUuid(Patterns.EMPLOYEE_PICTURE, path);
                try {
                    byte[] portrait = employeeController.getEmployeePicture(uuid);
                    resp.setContentLength(portrait.length);
                    resp.getOutputStream().write(portrait);
                } catch (RuntimeException e) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }

            } else if (path.matches(Patterns.TASK.pattern())){
                UUID uuid = extractUuid(Patterns.TASK, path);
                try {
                    resp.getWriter().write(jsonb.toJson(taskController.getTask(uuid)));
                    resp.setContentType("application/json");
                    resp.setStatus(HttpServletResponse.SC_OK);
                } catch (RuntimeException e) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }

            } else if (path.matches(Patterns.TASKS.pattern())){
                try {
                    resp.getWriter().write(jsonb.toJson(taskController.getTasks()));
                    resp.setContentType("application/json");
                    resp.setStatus(HttpServletResponse.SC_OK);
                } catch (RuntimeException e) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }

            } else if (path.matches(Patterns.PROJECT.pattern())) {
                UUID uuid = extractUuid(Patterns.PROJECT, path);
                try {
                    resp.getWriter().write(jsonb.toJson(projectController.getProject(uuid)));
                    resp.setContentType("application/json");
                    resp.setStatus(HttpServletResponse.SC_OK);
                } catch (RuntimeException e) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } else if (path.matches(Patterns.PROJECTS.pattern())) {
                try {
                    resp.getWriter().write(jsonb.toJson(projectController.getProjects()));
                    resp.setContentType("application/json");
                    resp.setStatus(HttpServletResponse.SC_OK);
                } catch (RuntimeException e) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Override
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
                } catch(Exception e) {
                    resp.setStatus(HttpServletResponse.SC_CONFLICT);
                }
            } else if (path.matches(Patterns.EMPLOYEE_PICTURE.pattern())) {
                UUID uuid = extractUuid(Patterns.EMPLOYEE_PICTURE, path);
                System.out.println("PUT EMPLOYEE PICTURE");
                try {
                    String fileName = req.getPart("picture").getSubmittedFileName();
                    employeeController.putEmployeePicture(uuid, req.getPart("picture").getInputStream(), fileName);
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } catch (RuntimeException e) {
                    resp.setStatus(HttpServletResponse.SC_CONFLICT);
                }

            } else if (path.matches(Patterns.TASK.pattern())) {
                UUID uuid = extractUuid(Patterns.TASK, path);
                try {
                    taskController.putTask(uuid, jsonb.fromJson(req.getReader(), PutTaskRequest.class));
                    resp.addHeader("Location", createUrl(req, Paths.API, "tasks", uuid.toString()));
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } catch (RuntimeException e) {
                    resp.setStatus(HttpServletResponse.SC_CONFLICT);
                }

            } else if (path.matches(Patterns.PROJECT.pattern())) {
                UUID uuid = extractUuid(Patterns.PROJECT, path);
                try {
                    projectController.putProject(uuid, jsonb.fromJson(req.getReader(), PutProjectRequest.class));
                    resp.addHeader("Location", createUrl(req, Paths.API, "projects", uuid.toString()));
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } catch (RuntimeException e) {
                    resp.setStatus(HttpServletResponse.SC_CONFLICT);
                }

            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            }
        }
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        String servletPath = req.getServletPath();

        System.out.println("DELETE");
        System.out.println(path);
        System.out.println(req);
        System.out.println(resp);

        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.EMPLOYEE.pattern())) {
                UUID uuid = extractUuid(Patterns.EMPLOYEE, path);
                try {
                    employeeController.deleteEmployee(uuid);
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } catch (RuntimeException e) {
                    System.out.println("Employee not found");
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            }
            else if (path.matches(Patterns.EMPLOYEE_PICTURE.pattern())) {
                UUID uuid = extractUuid(Patterns.EMPLOYEE_PICTURE, path);
                try {
                    employeeController.deleteEmployeePicture(uuid);
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } catch (RuntimeException e) {
                    System.out.println("Employee picture not found");
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }

            } else if (path.matches(Patterns.TASK.pattern())) {
                UUID uuid = extractUuid(Patterns.TASK, path);
                try {
                    taskController.deleteTask(uuid);
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } catch (RuntimeException e) {
                    System.out.println("Task not found");
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }

            } else if (path.matches(Patterns.PROJECT.pattern())) {
                UUID uuid = extractUuid(Patterns.PROJECT, path);
                try {
                    projectController.deleteProject(uuid);
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } catch (RuntimeException e) {
                    System.out.println("Project not found");
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }

            } else {
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
                try {
                    employeeController.patchEmployee(uuid, jsonb.fromJson(req.getReader(), PatchEmployeeRequest.class));
                    resp.addHeader("Location", createUrl(req, Paths.API, "employees", uuid.toString()));
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } catch (RuntimeException e) {
                    resp.setStatus(HttpServletResponse.SC_CONFLICT);
                }

            } else if (path.matches(Patterns.EMPLOYEE_PICTURE.pattern())){
                UUID uuid = extractUuid(Patterns.EMPLOYEE_PICTURE, path);
                try {
                    String fileName = req.getPart("picture").getSubmittedFileName();
                    employeeController.patchEmployeePicture(uuid, req.getPart("picture").getInputStream(), fileName);
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } catch (RuntimeException e) {
                    resp.setStatus(HttpServletResponse.SC_CONFLICT);
                }

            } else if (path.matches(Patterns.TASK.pattern())) {
                UUID uuid = extractUuid(Patterns.TASK, path);
                try {
                    taskController.patchTask(uuid, jsonb.fromJson(req.getReader(), PatchTaskRequest.class));
                    resp.addHeader("Location", createUrl(req, Paths.API, "tasks", uuid.toString()));
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } catch (RuntimeException e) {
                    resp.setStatus(HttpServletResponse.SC_CONFLICT);
                }

            } else if (path.matches(Patterns.PROJECT.pattern())) {
                UUID uuid = extractUuid(Patterns.PROJECT, path);
                try {
                    projectController.patchProject(uuid, jsonb.fromJson(req.getReader(), PatchProjectRequest.class));
                    resp.addHeader("Location", createUrl(req, Paths.API, "projects", uuid.toString()));
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } catch (RuntimeException e) {
                    resp.setStatus(HttpServletResponse.SC_CONFLICT);
                }

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

