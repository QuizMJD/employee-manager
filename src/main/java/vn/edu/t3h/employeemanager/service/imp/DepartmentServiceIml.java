package vn.edu.t3h.employeemanager.service.imp;

import vn.edu.t3h.employeemanager.dao.DepartmentDao;
import vn.edu.t3h.employeemanager.model.Department;
import vn.edu.t3h.employeemanager.service.DepartmentService;

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
