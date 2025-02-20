package vn.edu.t3h.employeemanager.dao.impl;

import vn.edu.t3h.employeemanager.dao.EmployeeDao;
import vn.edu.t3h.employeemanager.model.Employee;
import vn.edu.t3h.employeemanager.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static vn.edu.t3h.employeemanager.utils.DatabaseConnection.getConnection;


public class EmployeeDaoMysqlImpl implements EmployeeDao {

    @Override
    public List<Employee> getAllEmployee() {

        // tạo connection đến database
        Connection connection = getConnection();
        List<Employee> employeesResult = new ArrayList<>();
        // tạo câu query
        String sql = "SELECT * FROM employees emp " +
                "INNER JOIN departments dept ON emp.department_id = dept.department_id " +
                "ORDER BY emp.employee_id ASC;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            // thực thi câu query
            ResultSet resultSet = statement.executeQuery();
            // lấy ra dữ liệu từ câu query đưa vào object java
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setName(resultSet.getString("name"));
                employee.setPosition(resultSet.getString("position"));
                employee.setSalary(resultSet.getDouble("salary"));
                employee.setDepartmentName(resultSet.getString("department_name"));
                employee.setHireDate(resultSet.getString("hire_date"));
                employeesResult.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        // trả về kết quả là danh sach employee
        return employeesResult;
    }

    @Override
    public List<Employee> searchEmployee(Map<String, String> search) {
        List<Employee> employeesResult = new ArrayList<>();
        String name = search.get("name");
        String salary = search.get("salary");
        String fromHireDate = search.get("fromHireDate");
        String toHireDate = search.get("toHireDate");
        String position = search.get("position");
        String departmentId = search.get("departmentId");

        StringBuilder sql = new StringBuilder(
                "SELECT e.employee_id, e.name, e.position, e.salary, d.department_name, e.hire_date " +
                        "FROM employees e LEFT JOIN departments d ON e.department_id = d.department_id " +
                        "WHERE 1=1 ");

        if (name != null && !name.isEmpty()) {
            sql.append(" AND e.name LIKE ? ");
        }
        if (salary != null && !salary.isEmpty()) {
            sql.append(" AND e.salary >= ? ");
        }
        if (fromHireDate != null && !fromHireDate.isEmpty() && toHireDate != null && !toHireDate.isEmpty()) {
            sql.append(" AND e.hire_date BETWEEN ? AND ? ");
        }
        if (position != null && !position.isEmpty()) {
            sql.append(" AND e.position LIKE ? ");
        }
        if (departmentId != null && !departmentId.isEmpty()) {
            sql.append(" AND e.department_id = ? ");
        }

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            int paramIndex = 1;

            if (name != null && !name.isEmpty()) {
                statement.setString(paramIndex++, "%" + name + "%");
            }
            if (salary != null && !salary.isEmpty()) {
                statement.setDouble(paramIndex++, Double.parseDouble(salary));
            }
            if (fromHireDate != null && !fromHireDate.isEmpty() && toHireDate != null && !toHireDate.isEmpty()) {
                statement.setString(paramIndex++, fromHireDate);
                statement.setString(paramIndex++, toHireDate);
            }
            if (position != null && !position.isEmpty()) {
                statement.setString(paramIndex++, "%" + position + "%");
            }
            if (departmentId != null && !departmentId.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(departmentId));
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Employee employee = new Employee();
                    employee.setEmployeeId(resultSet.getInt("employee_id"));
                    employee.setName(resultSet.getString("name"));
                    employee.setPosition(resultSet.getString("position"));
                    employee.setSalary(resultSet.getDouble("salary"));
                    employee.setDepartmentName(resultSet.getString("department_name"));
                    employee.setHireDate(resultSet.getString("hire_date"));
                    employeesResult.add(employee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return employeesResult;
    }

    public boolean saveEmployee(Employee employee) {
        Connection conn = null;
        CallableStatement callSt = null;
        Boolean result = true;
        try {
            conn = getConnection();
            callSt =conn.prepareCall("{call InsertEmployee(?,?,?,?,?,?)}");
            callSt.setInt(1, employee.getEmployeeId());
            // thuc hien set gia tri cho tham so vao
            callSt.setString(2, employee.getName());
            callSt.setString(3, employee.getPosition());
            callSt.setDouble(4, employee.getSalary());
            callSt.setString(5, employee.getDepartmentName());
            callSt.setString(6, employee.getHireDate());
            // covert java.util.date ---> java.sql.date
//            callSt.setDate(6,new Date(employee.getHireDate().getTime()));
            callSt.execute();
            result = true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        return result;

    }

    @Override
    public boolean updateEmployee(Employee employee) {
        Connection conn = null;
        CallableStatement callSt = null;
        Boolean result = true;
        try {
            conn = getConnection();
            callSt =conn.prepareCall("{call update_mployee(?,?,?,?,?,?)}");
            callSt.setInt(1, employee.getEmployeeId());
            // thuc hien set gia tri cho tham so vao
            callSt.setString(2, employee.getName());
            callSt.setString(3, employee.getPosition());
            callSt.setDouble(4, employee.getSalary());
            callSt.setString(5, employee.getDepartmentName());
            callSt.setString(6, employee.getHireDate());
            // covert java.util.date ---> java.sql.date
//            callSt.setDate(6,new Date(employee.getHireDate().getTime()));
            callSt.executeUpdate();
            result = true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        return result;
    }

    @Override
    public boolean deleteEmployee(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean result = true;

        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            // Xóa nhân viên theo employee_id
            String sql = "DELETE FROM employees WHERE employee_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id); // Sử dụng tham số id đã truyền vào

            // Thực thi câu lệnh và lấy số dòng bị ảnh hưởng
            int rowsAffected = statement.executeUpdate();
            connection.commit();

            // Nếu xóa thành công (số dòng bị ảnh hưởng > 0) thì result = true
            result = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Rollback nếu có lỗi
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            // Đóng PreparedStatement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Đóng Connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Employee employee = null;
        try {
            conn = getConnection();
            String query = "SELECT e.employee_id, e.name, e.position, e.salary, e.hire_date, d.department_name " +
                    "FROM employees e " +
                    "LEFT JOIN departments d ON e.department_id = d.department_id WHERE e.employee_id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setName(rs.getString("name"));
                employee.setPosition(rs.getString("position"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setDepartmentName(rs.getString("department_name"));
                employee.setHireDate(rs.getString("hire_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return employee;
    }


}
