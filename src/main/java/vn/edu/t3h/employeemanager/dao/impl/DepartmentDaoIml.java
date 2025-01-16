package vn.edu.t3h.employeemanager.dao.impl;

import vn.edu.t3h.employeemanager.dao.DepartmentDao;
import vn.edu.t3h.employeemanager.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoIml implements DepartmentDao {
    @Override
    public List<Department> getAllDepartments() {
        // tạo connection đến database
        Connection connection = getConnection();
        List<Department> departmentResult = new ArrayList<>();
        // tạo câu query
        String sql = "select * from departments;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            // thực thi câu query
            ResultSet resultSet = statement.executeQuery();
            // lấy ra dữ liệu từ câu query đưa vào object java
            while (resultSet.next()){
                Department department = new Department();
                department.setDepartmentId(resultSet.getInt("department_id"));
                department.setDepartmentName(resultSet.getString("department_name"));
                department.setLocation(resultSet.getString("location"));
                departmentResult.add(department);
            }
            System.out.println("get department success");
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
        return departmentResult;

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

