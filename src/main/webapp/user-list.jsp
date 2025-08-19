<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Employee Management Application</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<header>
  <nav class="navbar navbar-expand-md navbar-dark" style="background-color:blue">
    <a href="#" class="navbar-brand">Employee Management Application</a>
    <ul class="navbar-nav">
      <li><a href="${pageContext.request.contextPath}/list" class="nav-link">Employee List</a></li>
    </ul>
  </nav>
</header>

<br>
<div class="container">
  <div class="d-flex justify-content-between align-items-center">
    <h3 class="text-center">List Of Users</h3>
    <a href="${pageContext.request.contextPath}/new" class="btn btn-success">Add New User</a>
  </div>
  <hr>

  <table class="table table-bordered table-striped">
    <thead>
      <tr>
        <th>ID</th><th>Name</th><th>Email</th><th>Country</th><th>Actions</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="user" items="${listUser}">
        <tr>
          <td><c:out value="${user.id}"/></td>
          <td><c:out value="${user.name}"/></td>
          <td><c:out value="${user.email}"/></td>
          <td><c:out value="${user.country}"/></td>
          <td>
            <a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/edit?id=${user.id}">Edit</a>
            <a class="btn btn-sm btn-danger ms-2" href="${pageContext.request.contextPath}/delete?id=${user.id}">Delete</a>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>
