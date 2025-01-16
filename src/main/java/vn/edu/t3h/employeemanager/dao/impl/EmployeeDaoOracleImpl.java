package vn.edu.t3h.employeemanager.dao.impl;

import vn.edu.t3h.employeemanager.dao.EmployeeDao;
import vn.edu.t3h.employeemanager.model.Employee;

import java.util.List;
import java.util.Map;

public class EmployeeDaoOracleImpl implements EmployeeDao {
    @Override
    public List<Employee> getAllEmployee() {
        return null;
    }

    @Override
    public List<Employee> searchEmployee(Map<String, String> search) {
        return List.of();
    }
}
