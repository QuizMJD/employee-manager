package vn.edu.t3h.employeemanager.dao.impl;

import vn.edu.t3h.employeemanager.dao.EmployeeDao;
import vn.edu.t3h.employeemanager.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class EmployeeDaoMysqlImpl implements EmployeeDao {

    @Override
    public List<Employee> getAllEmployee() {

        // tạo connection đến database
        Connection connection = getConnection();
        List<Employee> employeesResult = new ArrayList<>();
        // tạo câu query
        String sql = "select * from employees emp " +
                "inner join departments dept on emp.department_id = dept.department_id;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
        // thực thi câu query
            ResultSet resultSet = statement.executeQuery();
        // lấy ra dữ liệu từ câu query đưa vào object java
            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setName(resultSet.getString("name"));
                employee.setPosition(resultSet.getString("position"));
                employee.setSalary(resultSet.getDouble("salary"));
                employee.setDepartmentName(resultSet.getString("department_name"));
                employee.setHireDate(resultSet.getString("hire_date"));

                employeesResult.add(employee);
            }
            System.out.println("get employee success");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if (connection != null){
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

        // tạo connection đến database
        Connection connection = getConnection();
        List<Employee> employeesResult = new ArrayList<>();
        // Sử dụng các giá trị trong Map để xây dựng query.
        String name = search.get("name");
        String salary = search.get("salary");
        String fromHireDate = search.get("fromHireDate");
        String toHireDate = search.get("toHireDate");
        String position = search.get("position");
        String departmentId = search.get("departmentId");


        // tạo câu query
//        String sql = "SELECT e.employee_id, e.name, e.position, e.salary, d.department_name, e.hire_date " +
//                "FROM employees e LEFT JOIN departments d ON e.department_id = d.department_id " +
//                "WHERE 1=1 AND (e.name LIKE ? OR ? IS NULL) " +
//                "AND (e.salary >= ? OR ? IS NULL) " +
//                "AND (e.hire_date BETWEEN ? AND ? OR ? IS NULL OR ? IS NULL) " +
//                "AND (e.position LIKE ? OR ? IS NULL) " +
//                "AND (e.department_id = ? OR ? IS NULL);\n";
        String sql = "SELECT e.employee_id, e.name, e.position, e.salary, d.department_name, e.hire_date " +
                "FROM employees e LEFT JOIN departments d ON e.department_id = d.department_id " +
                "WHERE 1=1 " +
                (name != null && !name.isEmpty() ? " AND e.name LIKE ? " : "") +
                (salary != null && !salary.isEmpty() ? " AND e.salary >= ? " : "") +
                (fromHireDate != null && toHireDate != null ? " AND e.hire_date BETWEEN ? AND ? " : "") +
                (position != null && !position.isEmpty() ? " AND e.position LIKE ? " : "") +
                (departmentId != null && !departmentId.isEmpty() ? " AND e.department_id = ? " : "");


        if (fromHireDate == null || fromHireDate.isEmpty() || toHireDate == null || toHireDate.isEmpty()) {
            // Loại bỏ điều kiện liên quan đến hire_date nếu một trong hai giá trị bị rỗng hoặc null
            sql = sql.replace("AND e.hire_date BETWEEN ? AND ?", "");
            System.out.println("Final SQL query: " + sql);
        }
        try {
            PreparedStatement statement = connection.prepareStatement(sql.toString());
//            ResultSet resultSet = statement.executeQuery();
            int paramIndex = 1;

            if (name != null && !name.isEmpty()) {
                statement.setString(paramIndex++, "%" + name + "%");
            }
            if (salary != null && !salary.isEmpty()) {
                statement.setDouble(paramIndex++, Double.parseDouble(salary));
            }
            if (fromHireDate != null && toHireDate != null) {
                statement.setString(paramIndex++, fromHireDate);
                statement.setString(paramIndex++, toHireDate);
            }
            if (position != null && !position.isEmpty()) {
                statement.setString(paramIndex++, "%" + position + "%");
            }
            if (departmentId != null && !departmentId.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(departmentId));
            }


            // thực thi câu query
            ResultSet resultSet = statement.executeQuery();
            // lấy ra dữ liệu từ câu query đưa vào object java
            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setName(resultSet.getString("name"));
                employee.setPosition(resultSet.getString("position"));
                employee.setSalary(resultSet.getDouble("salary"));
                employee.setDepartmentName(resultSet.getString("department_name"));
                employee.setHireDate(resultSet.getString("hire_date"));

                employeesResult.add(employee);
            }
            System.out.println("get employee success");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if (connection != null){
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

    public Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/quanlynhansu";
        String username = "root";
        String password = "12345678";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,password);
            System.out.println("get connection success");
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
