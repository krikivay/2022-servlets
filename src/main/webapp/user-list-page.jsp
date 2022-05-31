<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ex" uri="/WEB-INF/customNavBar.tld" %>
<%@ taglib prefix="userlist" uri="/WEB-INF/customUserList.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: krikivay
  Date: 05.05.2022
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="deleteWindow.js"></script>
<html>
<head>
    <title>User List</title>
</head>
<body>
<ex:NavBar/>
<div class="container">
    <a type="button" class="btn btn-success" href="adduser">Add new User</a>
    <userlist:UserList/>
</div>
</body>
</html>
