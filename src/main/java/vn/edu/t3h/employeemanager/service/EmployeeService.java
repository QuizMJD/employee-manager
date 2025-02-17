package vn.edu.t3h.employeemanager.service;

import vn.edu.t3h.employeemanager.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    List<Employee> getAllEmployee();
   List<Employee> searchEmployee(Map<String, String> search);
    boolean saveEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    boolean deleteEmployee(Integer id);
    Employee getEmployeeById(Integer id);
}
