package vn.edu.t3h.employeemanager.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.employeemanager.dao.EmployeeDao;
import vn.edu.t3h.employeemanager.dao.impl.EmployeeDaoMysqlImpl;
import vn.edu.t3h.employeemanager.model.Employee;
import vn.edu.t3h.employeemanager.service.EmployeeService;
import vn.edu.t3h.employeemanager.service.imp.EmployeeServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static vn.edu.t3h.employeemanager.utils.RequestUtils.isSearchEmpty;

@WebServlet(name = "EmployeeServlet",value = "/employee/show")
public class EmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getAllEmployees(req, resp);

    }
    public  void getAllEmployees(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // input data from param
        String name = req.getParameter("name");
        String salary = req.getParameter("salary");
        String fromHireDate = req.getParameter("fromDate");
        String toHireDate = req.getParameter("toDate");
        String position = req.getParameter("position");
        String departmentId = req.getParameter("departmentId");
        // put map
        Map<String, String> search = new HashMap<>();
        search.put("name", name);
        search.put("salary", salary);
        search.put("fromHireDate", fromHireDate);
        search.put("toHireDate", toHireDate);
        search.put("position", position);
        search.put("departmentId", departmentId);
        //router
        EmployeeDao employeeDao = new EmployeeDaoMysqlImpl();
        EmployeeService employeeService = new EmployeeServiceImpl(employeeDao);
        List<Employee> employees = isSearchEmpty(search)
                ? employeeService.getAllEmployee()
                : employeeService.searchEmployee(search);
        req.setAttribute("employeeData",employees);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/employee/show.jsp");
        requestDispatcher.forward(req,resp);
    }

}
