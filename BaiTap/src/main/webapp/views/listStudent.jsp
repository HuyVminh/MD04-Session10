<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 11/27/2023
  Time: 3:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Danh sách USER</h1>
<a href="/student-list?action=create">Create User</a>
<form action="/student-list">
    <input type="text" name="search" value="${search}">
    <input type="submit" value="search" name="action">
    <input type="submit" value="sortByName" name="action">
</form>
<table border="1" cellspacing="0">
    <tr>
        <td>STT</td>
        <td>ID</td>
        <td>Name</td>
        <td>Email</td>
        <td>Birthday</td>
        <td>Address</td>
        <td colspan="2">Action</td>
    </tr>
    <c:forEach items='${list}' var="student" varStatus="loop">
        <tr>
            <td>${loop.index+1}</td>
            <td>${student.id}</td>
            <td>${student.userName}</td>
            <td>${student.email}</td>
            <td>${student.birthday}</td>
            <td>${student.address}</td>
            <td><a href="/student-list?action=edit&id=${student.id}">Edit</a></td>
            <td><a onclick="return confirm('ban co chac chan muon xoa khong?')"
                   href="/student-list?action=delete&id=${student.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
