package io.bayrktlihn.employeecrudapp.controllers;

import io.bayrktlihn.employeecrudapp.entities.Employee;
import io.bayrktlihn.employeecrudapp.models.DeleteEmployeesRequestDTO;
import io.bayrktlihn.employeecrudapp.models.SaveEmployeeRequestDTO;
import io.bayrktlihn.employeecrudapp.services.EmployeeService;
import io.bayrktlihn.employeecrudapp.util.JSON;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;


@WebServlet(urlPatterns = {EmployeeController.CONTROLLER_PATH + "/*", EmployeeController.CONTROLLER_PATH})
public class EmployeeController extends HttpServlet {

    public static final String CONTROLLER_PATH = "/employees";

    private final EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employees = employeeService.findAll();

        req.setAttribute("employees", employees);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/employees.jsp");

        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String methodRequestPath = methodRequestPath(req);

        if (methodRequestPath.equals("")) {
            handleDeleteCheckedEmployeees(req);
        } else if (Pattern.compile("^/[0-9]+$").matcher(methodRequestPath).matches()) {
            String idPathVariable = methodRequestPath.substring("/".length());
            handleDeleteSelectedEmployee(req, idPathVariable);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestBody = getRequestBody(req);

        SaveEmployeeRequestDTO saveEmployeeRequestDTO = JSON.parse(requestBody, SaveEmployeeRequestDTO.class);

        employeeService.saveEmployee(saveEmployeeRequestDTO);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestBody = getRequestBody(req);

        SaveEmployeeRequestDTO saveEmployeeRequestDTO = JSON.parse(requestBody, SaveEmployeeRequestDTO.class);

        employeeService.saveEmployee(saveEmployeeRequestDTO);
    }

    private void handleDeleteSelectedEmployee(HttpServletRequest req, String idPathVariable) {
        employeeService.deleteById(Long.parseLong(idPathVariable));
    }

    private void handleDeleteCheckedEmployeees(HttpServletRequest req) {
        String requestBody = getRequestBody(req);

        DeleteEmployeesRequestDTO deleteEmployeesRequestDTO = JSON.parse(requestBody, DeleteEmployeesRequestDTO.class);


        employeeService.deleteByIds(deleteEmployeesRequestDTO.getToBeDeletedEmployeeIds());
    }

    private static String methodRequestPath(HttpServletRequest req) {
        String path = req.getRequestURI().substring(req.getContextPath().length());

        return path.substring(CONTROLLER_PATH.length());
    }


    public String getRequestBody(HttpServletRequest req) {

        try (BufferedReader br = req.getReader()) {
            StringBuilder sb = new StringBuilder();
            String s = br.readLine();
            while (s != null) {
                sb.append(s);
                s = br.readLine();
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
