<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 11/27/2023
  Time: 3:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CREATE STUDENT</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-6">
            <h1 class="text-center text-danger">Thêm mới sinh viên </h1>
            <form action="<%=request.getContextPath()%>/student-list" method="POST">
                <div class="form-group">
                    <label>Tên sinh viên</label>
                    <input type="text" class="form-control" name="studentName">
                </div>
                <div class="form-group">
                    <label>Email</label>
                    <input type="text" class="form-control" name="email">
                </div>
                <div class="form-group">
                    <label>Birthday</label>
                    <input type="date" class="form-control" name="birthday">
                </div>
                <div class="form-group">
                    <label>Địa chỉ</label>
                    <input type="text" class="form-control" name="address">
                </div>
                <button type="submit" class="btn btn-primary">Add</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
