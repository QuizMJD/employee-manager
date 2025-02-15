<%--
  Created by IntelliJ IDEA.
  User: GIANG VIEN T3H
  Date: 1/9/2025
  Time: 7:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page T3H</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(135deg, #f5f7fa, #c3cfe2);
            color: #333;
        }

        header {
            background-color: #3f51b5;
            color: white;
            padding: 20px 0;
            text-align: center;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        header h1 {
            margin: 0;
            font-size: 2.5rem;
        }

        nav {
            margin: 20px auto;
            text-align: center;
        }

        nav a {
            text-decoration: none;
            color: white;
            background-color: #3f51b5;
            padding: 10px 20px;
            margin: 10px;
            border-radius: 5px;
            font-size: 1rem;
            transition: background-color 0.3s ease;
        }

        nav a:hover {
            background-color: #303f9f;
        }

        .content {
            max-width: 800px;
            margin: 50px auto;
            text-align: center;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .content h2 {
            margin-bottom: 20px;
            font-size: 2rem;
            color: #3f51b5;
        }

        .footer {
            text-align: center;
            padding: 10px 0;
            margin-top: 20px;
            background-color: #3f51b5;
            color: white;
        }

    </style>
</head>
<body>
<header>
    <h1>Welcome to T3H Home Page</h1>
</header>

<div class="content">
    <h2>Explore Our Features</h2>
    <nav>
        <a href="home-servlet">Home Servlet</a>
        <a href="employee/show">Danh sách nhân viên</a>
        <a href="department">Danh sách phòng ban</a>
    </nav>
</div>

<div class="footer">
    © 2025 T3H | All Rights Reserved
</div>
</body>
</html>
