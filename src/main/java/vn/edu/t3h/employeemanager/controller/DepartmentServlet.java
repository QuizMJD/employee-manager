package vn.edu.t3h.employeemanager.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.t3h.employeemanager.dao.DepartmentDao;

import vn.edu.t3h.employeemanager.dao.impl.DepartmentDaoIml;

import vn.edu.t3h.employeemanager.model.Department;

import vn.edu.t3h.employeemanager.service.DepartmentService;
import vn.edu.t3h.employeemanager.service.DepartmentServiceIml;


import java.io.IOException;

import java.util.List;


@WebServlet(name = "DepartmentServlet",value = "/department")
public class DepartmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        DepartmentDao departmentDao = new DepartmentDaoIml();
        DepartmentService departmentService=new DepartmentServiceIml(departmentDao);

        List<Department> departments = departmentService.getAllDepartments();

        req.setAttribute("departments",departments);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("departments.jsp");
        requestDispatcher.forward(req,resp);
    }

}
