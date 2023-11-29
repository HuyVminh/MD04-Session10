<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 11/28/2023
  Time: 4:59 PM
  To change this template use File | Settings | File Templates.
--%>
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
            <h1 class="text-center text-danger">Thêm mới CATEGORY</h1>
            <form action="<%=request.getContextPath()%>/category-list" method="POST">
                <div class="form-group">
                    <label>Tên category</label>
                    <input type="text" class="form-control" name="categoryName">
                </div>
                <div class="form-group">
                    <label for="Active">Status </label>
                    <input type="radio" id="Active" checked value="true">
                    <label for="Active">Active</label>
                    <input type="radio" id="IsActive" value="false">
                    <label for="IsActive">InActive</label><br>
                </div>
                <button type="submit" class="btn btn-primary">Add</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
