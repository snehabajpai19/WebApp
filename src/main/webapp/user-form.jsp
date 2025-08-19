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
<div class="container col-md-5">
  <div class="card">
    <div class="card-body">
      <h2 class="mb-3">
        <c:if test="${user != null}">Edit User</c:if>
        <c:if test="${user == null}">Add New User</c:if>
      </h2>

      <form method="post" action="${pageContext.request.contextPath}<c:out value='${user != null ? "/update" : "/insert"}'/>">
        <c:if test="${user != null}">
          <input type="hidden" name="id" value="<c:out value='${user.id}'/>"/>
        </c:if>

        <div class="mb-3">
          <label class="form-label">User Name</label>
          <input type="text" class="form-control" name="name" required
                 value="<c:out value='${user != null ? user.name : ""}'/>"/>
        </div>

        <div class="mb-3">
          <label class="form-label">User Email</label>
          <input type="email" class="form-control" name="email"
                 value="<c:out value='${user != null ? user.email : ""}'/>"/>
        </div>

        <div class="mb-3">
          <label class="form-label">User Country</label>
          <input type="text" class="form-control" name="country"
                 value="<c:out value='${user != null ? user.country : ""}'/>"/>
        </div>

        <button type="submit" class="btn btn-success">Save</button>
        <a href="${pageContext.request.contextPath}/list" class="btn btn-secondary ms-2">Cancel</a>
      </form>
    </div>
  </div>
</div>
</body>
</html>
