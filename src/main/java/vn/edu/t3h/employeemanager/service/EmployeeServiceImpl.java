package vn.edu.t3h.employeemanager.service;

import vn.edu.t3h.employeemanager.dao.EmployeeDao;
import vn.edu.t3h.employeemanager.model.Employee;

import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> getAllEmployee() {
//        EmployeeDao employeeDao = new EmployeeDaoImpl();
        return employeeDao.getAllEmployee();
    }
    @Override
    public List<Employee> searchEmployee(Map<String, String> search) {
        return employeeDao.searchEmployee(search);
    }
}
