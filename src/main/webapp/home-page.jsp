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
            background: linear-gradient(135deg, #f0f4f8, #dfe6ed);
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .window {
            background: white;
            border-radius: 10px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
            width: 90%;
            max-width: 800px;
            overflow: hidden;
        }

        .window-header {
            background-color: #6c5ce7; /* Màu tím pastel */
            color: white;
            padding: 15px 20px;
            text-align: center;
            font-size: 1.5rem;
            font-weight: bold;
            border-bottom: 1px solid rgba(0, 0, 0, 0.1);
        }

        .window-content {
            padding: 20px;
            text-align: center;
        }

        .window-content h2 {
            margin-bottom: 20px;
            font-size: 1.8rem;
            color: #6c5ce7; /* Màu tím pastel */
        }

        .window-nav {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 15px;
            margin-bottom: 20px;
        }

        .window-nav a {
            text-decoration: none;
            color: white;
            background-color: #00b894; /* Màu xanh lá pastel */
            padding: 10px 20px;
            border-radius: 5px;
            font-size: 1rem;
            transition: background-color 0.3s ease, transform 0.3s ease;
            flex: 1 1 auto;
            max-width: 200px;
            text-align: center;
        }

        .window-nav a:hover {
            background-color: #00cec9; /* Màu xanh ngọc pastel */
            transform: translateY(-2px);
        }

        .footer {
            text-align: center;
            padding: 10px;
            background-color: #6c5ce7; /* Màu tím pastel */
            color: white;
            font-size: 0.9rem;
            border-top: 1px solid rgba(0, 0, 0, 0.1);
        }

        .footer p {
            margin: 0;
        }

        /* Responsive Design */
        @media (max-width: 600px) {
            .window-nav a {
                flex: 1 1 100%;
                max-width: 100%;
            }
        }
    </style>
</head>
<body>
<div class="window">
    <div class="window-header">
        <h1>Welcome to T3H Home Page</h1>
    </div>
    <div class="window-content">
        <h2>Explore Our Features</h2>
        <div class="window-nav">
            <a href="home-servlet">Home Servlet</a>
            <a href="employee/show">Danh sách nhân viên</a>
            <a href="department">Danh sách phòng ban</a>
        </div>
    </div>
    <div class="footer">
        <p>© 2025 T3H | All Rights Reserved</p>
    </div>
</div>
</body>
</html>