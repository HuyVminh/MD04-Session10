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
<h1>Danh sách PRODUCT</h1>
<a href="category-list" class="btn btn-success m-5">Category List</a>
<a href="/product-list?action=create" class="btn btn-primary m-5">Create Product</a>
<table border="1" cellspacing="0">
    <tr>
        <td>STT</td>
        <td>ID</td>
        <td>Name</td>
        <td>Price</td>
        <td>Category</td>
        <td colspan="2">Action</td>
    </tr>
    <c:forEach items='${list}' var="product" varStatus="loop">
        <tr>
            <td>${loop.index+1}</td>
            <td>${product.productId}</td>
            <td>${product.productName}</td>
            <td>${product.price}</td>
            <td>${product.category.categoryId}</td>
            <td><a href="/product-list?action=edit&id=${product.productId}">Edit</a></td>
            <td><a onclick="return confirm('ban co chac chan muon xoa khong?')"
                   href="/product-list?action=delete&id=${product.productId}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
