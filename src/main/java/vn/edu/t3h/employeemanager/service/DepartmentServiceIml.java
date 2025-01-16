package vn.edu.t3h.employeemanager.service;

import vn.edu.t3h.employeemanager.dao.DepartmentDao;
import vn.edu.t3h.employeemanager.model.Department;

import java.util.List;

public class DepartmentServiceIml implements DepartmentService {
    private DepartmentDao departmentDao;
    public DepartmentServiceIml(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }
    @Override
    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }
}
