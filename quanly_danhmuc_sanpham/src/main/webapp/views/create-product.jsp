<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 11/28/2023
  Time: 4:42 PM
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
<div class="container">
    <div class="row">
        <div class="col-lg-6">
            <h1 class="text-center text-danger">Thêm mới PRODUCT</h1>
            <form action="<%=request.getContextPath()%>/product-list" method="POST">
                <div class="form-group">
                    <label>Tên sản phẩm</label>
                    <input type="text" class="form-control" name="productName">
                </div>
                <div class="form-group">
                    <label>Đơn giá</label>
                    <input type="number" class="form-control" name="price">
                </div>
                <div class="form-group">
                    <select class="form-control"  name="categoryId">
                        <option selected>Danh mục sản phẩm</option>
                        <c:forEach items="${listCategory}" var="item">
                            <option value="${item.categoryId}" name="categoryId">${item.categoryName}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Add</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
