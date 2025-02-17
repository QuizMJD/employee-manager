<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Add Employee</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #f8f9fa;
    }
    .form-container {
      max-width: 600px;
      margin: 50px auto;
      background: #fff;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }
  </style>
</head>
<body>
<div class="container">
  <div class="form-container">

    <c:if test="${empty model.employeeId}">
      <h2 class="mb-4 text-center">Add New Employee</h2>
    </c:if>
    <c:if test="${not empty model.employeeId}">
      <h2 class="mb-4 text-center">Edit Employee</h2>
    </c:if>
    <form action="/employee/crud-employee" method="POST">
      <input type="text" class="form-control" id="employeeId" name="employeeId" hidden
             value="${model.employeeId}" >
      <div class="mb-3">
        <label for="name" class="form-label">Full Name</label>
        <!-- Hiển thị giá trị name từ model nếu có, nếu không sẽ để trống -->
        <input type="text" class="form-control" id="name" name="name" placeholder="Enter employee name"
               value="${model.name}" required>
      </div>
      <div class="mb-3">
        <label for="position" class="form-label">Position</label>
        <!-- Hiển thị giá trị position từ model nếu có -->
        <input type="text" class="form-control" id="position" name="position" placeholder="Enter position"
               value="${model.position}" required>
      </div>
      <div class="mb-3">
        <label for="salary" class="form-label">Salary</label>
        <!-- Hiển thị giá trị salary từ model nếu có -->
        <input type="number" class="form-control" id="salary" name="salary" placeholder="Enter salary"
               step="0.01" value="${model.salary}" required>
      </div>
      <div class="mb-3">
        <label for="departmentName" class="form-label">Department</label>
        <select class="form-select" id="departmentName" name="departmentName" required>
          <option value="">Select Department</option>
          <option value="1" ${model.departmentName == 'IT' ? 'selected' : ''}>IT</option>
          <option value="2" ${model.departmentName == 'HR' ? 'selected' : ''}>HR</option>
          <option value="3" ${model.departmentName == 'Finance' ? 'selected' : ''}>Finance</option>
          <option value="5" ${model.departmentName == 'AI' ? 'selected' : ''}>AI</option>
        </select>
      </div>
      <div class="mb-3">
        <label for="hireDate" class="form-label">Hire Date</label>
        <!-- Hiển thị giá trị hire_date từ model nếu có -->
        <input type="date" class="form-control" id="hireDate" name="hireDate"
               value="${model.hireDate}" required>
      </div>
      <div class="d-grid">
        <c:if test="${empty model.employeeId}">
          <button type="submit" class="btn btn-outline-primary" name="action" value="Create">Add Employee</button>
        </c:if>
        <c:if test="${not empty model.employeeId}">
          <button type="submit" class="btn btn-outline-warning" name="action" value="Update">Edit Employee</button>
        </c:if>
      </div>
    </form>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>