<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 11/28/2023
  Time: 3:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<h1>Danh sách CATEGORY</h1>
<a href="product-list" class="btn btn-success m-5">Product List</a>
<a href="/category-list?action=create" class="btn btn-primary m-5">Create Category</a>
<table border="1" cellspacing="0">
    <tr>
        <td>STT</td>
        <td>ID</td>
        <td>Name</td>
        <td>Status</td>
        <td>Quantity Product</td>
        <td colspan="2">Action</td>
    </tr>
    <c:forEach items='${list}' var="category" varStatus="loop">
        <tr>
            <td>${loop.index+1}</td>
            <td>${category.categoryId}</td>
            <td>${category.categoryName}</td>
            <td>${category.status}</td>
            <td>${category.quantity_product}</td>
            <td><a href="/category-list?action=edit&id=${category.categoryId}">Edit</a></td>
            <td><a onclick="return confirm('ban co chac chan muon xoa khong?')"
                   href="/category-list?action=delete&id=${category.categoryId}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
