<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee List</title>

    <!-- CSS Styles -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .search-form {
            display: flex;
            flex-wrap: wrap;
            gap: 15px;
            margin-bottom: 20px;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .search-form label {
            font-weight: bold;
            margin-bottom: 5px;
        }
        .search-form input[type="text"],
        .search-form input[type="date"] {
            padding: 8px;
            width: 200px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .search-form input[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .search-form input[type="submit"]:hover {
            background-color: #45a049;
        }
        .add-employee-btn {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            text-decoration: none;
            display: inline-block;
            margin-bottom: 20px;
        }
        .add-employee-btn:hover {
            background-color: #0056b3;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #f8f9fa;
            font-weight: bold;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .action-buttons {
            display: flex;
            gap: 10px;
        }
        .action-buttons a {
            padding: 6px 12px;
            border-radius: 4px;
            text-decoration: none;
            color: white;
            transition: background-color 0.3s ease;
        }
        .action-buttons .btn-warning {
            background-color: #ffc107;
        }
        .action-buttons .btn-warning:hover {
            background-color: #e0a800;
        }
        .action-buttons .btn-danger {
            background-color: #dc3545;
        }
        .action-buttons .btn-danger:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>

<h1>Employee List</h1>

<!-- Nút thêm nhân viên -->
<a href="/employee/crud-employee" class="add-employee-btn">Thêm nhân viên</a>

<!-- Form tìm kiếm với các trường hiển thị ngang -->
<form class="search-form" action="/employee/show" method="get">
    <div>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${param.name}" placeholder="Search by Name"/>
    </div>

    <div>
        <label for="salary">Salary:</label>
        <input type="text" id="salary" name="salary" value="${param.salary}" placeholder="Search by Salary"/>
    </div>

    <div>
        <label for="fromDate">From Hire Date:</label>
        <input type="date" id="fromDate" name="fromDate" value="${param.fromDate}"/>
    </div>

    <div>
        <label for="toDate">To Hire Date:</label>
        <input type="date" id="toDate" name="toDate" value="${param.toDate}"/>
    </div>

    <div>
        <label for="position">Position:</label>
        <input type="text" id="position" name="position" value="${param.position}" placeholder="Search by Position"/>
    </div>

    <div>
        <input type="submit" value="Search"/>
    </div>
</form>

<!-- Bảng danh sách nhân viên -->
<table>
    <thead>
    <tr>
        <th>Employee ID</th>
        <th>Name</th>
        <th>Position</th>
        <th>Salary</th>
        <th>Department</th>
        <th>Hire Date</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="employeeModel" items="${employeeData}">
        <tr>
            <td>${employeeModel.employeeId}</td>
            <td>${employeeModel.name}</td>
            <td>${employeeModel.position}</td>
            <td>${employeeModel.salary}</td>
            <td>${employeeModel.departmentName}</td>
            <td>${employeeModel.hireDate}</td>
            <td>
                <div class="action-buttons">
                    <a href="/employee/crud-employee?id=${employeeModel.employeeId}" class="btn-warning">Update</a>
                    <a href="/employee/delete/${employeeModel.employeeId}" class="btn-danger">Delete</a>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>