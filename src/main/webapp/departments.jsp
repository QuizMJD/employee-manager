<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Department List</title>

    <!-- CSS Styles -->
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .search-form {
            display: flex;
            flex-wrap: wrap;
            gap: 15px;
            margin-bottom: 20px;
        }
        .search-form label {
            font-weight: bold;
        }
        .search-form input[type="text"],
        .search-form input[type="date"] {
            padding: 5px;
            width: 200px;
        }
        .search-form input[type="submit"] {
            padding: 8px 15px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        .search-form input[type="submit"]:hover {
            background-color: #45a049;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
    </style>
</head>
<body>

<h1>Employee List</h1>

<!-- Form tìm kiếm với các trường hiển thị ngang -->
<form class="search-form" action="/department" method="get">
    <div>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${param.name}" placeholder="Search by Name"/>
    </div>





    <div>
        <label for="position">Location:</label>
        <input type="text" id="position" name="position" value="${param.loaction}" placeholder="Search by Location"/>
    </div>

    <div>
        <input  type="submit" value="Search"/>
        <%--        <a href="/admin/user/create" class="btn btn-primary">Create a user</a>--%>
    </div>
</form>

<!-- Bảng danh sách nhân viên -->
<table>
    <thead>
    <tr>
        <th>department ID</th>
        <th>department Name</th>
        <th>location</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach var="department" items="${departments}">
        <tr>
            <td>${department.departmentId}</td>
            <td>${department.departmentName}</td>
            <td>${department.location}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
