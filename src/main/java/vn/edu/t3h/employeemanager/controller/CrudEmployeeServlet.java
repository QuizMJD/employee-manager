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

@WebServlet("/employee/crud-employee")
public class CrudEmployeeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        EmployeeDao employeeDao = new EmployeeDaoMysqlImpl();
        EmployeeService employeeService = new EmployeeServiceImpl(employeeDao);
    String action = req.getParameter("action");
    if(action!=null&&action.equals("Create")) {
        // lay thong tin tu request
        Employee emp = new Employee();
//        emp.setEmployeeId(Integer.valueOf(req.getParameter("employee_id")));
        String empIdStr = req.getParameter("employee_id");
        int empId = (empIdStr != null && !empIdStr.isEmpty()) ? Integer.parseInt(empIdStr) : 0; // Hoặc một giá trị mặc định
        emp.setEmployeeId(empId);
        if (empIdStr != null && !empIdStr.isEmpty()) {
            emp.setEmployeeId(Integer.parseInt(empIdStr));
        }
        emp.setName(req.getParameter("name"));
        emp.setPosition(req.getParameter("position"));
        emp.setSalary(Double.valueOf(req.getParameter("salary")));
        emp.setDepartmentName(req.getParameter("departmentName"));
        emp.setHireDate(req.getParameter("hireDate"));
        // them moi
        boolean result = employeeService.saveEmployee(emp);
        if (result) {
            System.out.println("Employee saved successfully!");
            resp.sendRedirect(req.getContextPath() + "/employee/show");
        } else {
            System.out.println("Failed to save employee!");
            // hiển thị thông báo lỗi trên trang JSP
        }
    }
    else if(action!=null&&action.equals("Update")) {
        Employee emp = new Employee();
        String empIdStr = req.getParameter("id");
        emp.setEmployeeId(Integer.parseInt(empIdStr));
        emp.setName(req.getParameter("name"));
        emp.setPosition(req.getParameter("position"));
        emp.setSalary(Double.valueOf(req.getParameter("salary")));
        emp.setDepartmentName(req.getParameter("departmentName"));
        emp.setHireDate(req.getParameter("hireDate"));
        boolean result = employeeService.updateEmployee(emp);
        if (result) {
            System.out.println("Employee updated successfully!");
            resp.sendRedirect(req.getContextPath() + "/employee/show");
        }
    }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeIdStr = req.getParameter("id");
        RequestDispatcher requestDispatcher;
        if (employeeIdStr == null || employeeIdStr.trim().isEmpty()) {
            // Không có ID => Chuyển sang trang tạo mới
            requestDispatcher = req.getRequestDispatcher("/employee/create.jsp");
        } else {
            // Có ID => Lấy thông tin nhân viên và chuyển đến trang cập nhật
            int employeeId = Integer.parseInt(employeeIdStr);
            EmployeeDao employeeDao = new EmployeeDaoMysqlImpl();
            Employee employee = employeeDao.getEmployeeById(employeeId);
            if (employee != null) {
                req.setAttribute("model", employee);
                requestDispatcher = req.getRequestDispatcher("/employee/create.jsp");
            } else {
                // Nếu không tìm thấy nhân viên, quay về trang danh sách
                resp.sendRedirect(req.getContextPath() + "/employee/show");
                return;
            }
        }

        requestDispatcher.forward(req, resp);
    }



}
