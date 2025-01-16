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
import vn.edu.t3h.employeemanager.service.EmployeeServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "EmployeeServlet",value = "/employee")
public class EmployeeServlet extends HttpServlet {

    private boolean isSearchEmpty(Map<String, String> search) {
        if (search == null || search.isEmpty()) {
            return true; // search null hoặc không có key nào
        }
        // Kiểm tra nếu tất cả các giá trị đều là "" hoặc null
        for (String value : search.values()) {
            if (value != null && !value.trim().isEmpty()) {
                return false; // Có ít nhất 1 giá trị hợp lệ
            }
        }
        return true; // Tất cả giá trị đều rỗng hoặc null
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // lấy data từ param
        String name = req.getParameter("name")==null?"":req.getParameter("name");

        String salary = req.getParameter("salary")==null?"":req.getParameter("salary");

        String fromHireDate = req.getParameter("fromHireDate")==null?"":req.getParameter("fromHireDate");

        String toHireDate = req.getParameter("toHireDate")==null?"":req.getParameter("toHireDate");

        String position = req.getParameter("position")==null?"":req.getParameter("position");

        String departmentId = req.getParameter("departmentId");
        String departmentName = req.getParameter("departmentName");

//        String search= name+salary+fromHireDate+toHireDate+position;
        Map<String, String> search = new HashMap<>();
        search.put("name", name);
        search.put("salary", salary);
        search.put("fromHireDate", fromHireDate);
        search.put("toHireDate", toHireDate);
        search.put("position", position);
        search.put("departmentId", departmentId);

        System.out.println(search);


        EmployeeDao employeeDao = new EmployeeDaoMysqlImpl();
        EmployeeService employeeService = new EmployeeServiceImpl(employeeDao);
//        List<Employee> employees = search==null? employeeService.getAllEmployee():employeeService.searchEmployee(search);
        List<Employee> employees = isSearchEmpty(search)
                ? employeeService.getAllEmployee()
                : employeeService.searchEmployee(search);
        req.setAttribute("employeeData",employees);

        // log kq
        System.out.println("name: " + name);
        System.out.println("salary: " + salary);
        System.out.println("fromHireDate: " + fromHireDate);
        System.out.println("toHireDate: " + toHireDate);
        System.out.println("position: " + position);
        System.out.println("departmentId: " + departmentId);
         //
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("employees.jsp");
        requestDispatcher.forward(req,resp);
    }

}
