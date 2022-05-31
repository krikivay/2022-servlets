<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: krikivay
  Date: 24.05.2022
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="./styles.css">
<html>
<head>
    <title>Add new user</title>
</head>
<body>
<div class="my-form">
    <div class="col-md-12 text-center">
        ${sessionScope.add_edit_mode.equals("ADD") ? "<h1>Add new user</h1>" : "<h1>Edit user</h1>"}
    </div>
    <form action=${sessionScope.add_edit_mode.equals("ADD") ? "adduser" : "edituser"} method="post">
        <input hidden
               id="id"
               name="id"
               value="${sessionScope.edit_user.id}"
        >
        <div class="my-form form-group">
            <label for="login" class="control-label required">Login</label>
            <input type="text"
                   required
                   name="login"
                   class="form-control"
                   id="login"
                   placeholder="Enter the login"
                   value=${sessionScope.add_edit_mode == "EDIT" ? sessionScope.edit_user.login : ""}
            ${sessionScope.add_edit_mode == "EDIT" ? "readonly" : ""}
            >
            <h6 class="error">${sessionScope.login_error}</h6>
        </div>
        <div class="my-form form-group">
            <label for="password" class="control-label required">Password</label>
            <input type="password"
                   required
                   name="password"
                   class="form-control"
                   id="password"
                   placeholder="Enter the password"
                   value=${sessionScope.add_edit_mode == "EDIT" ? sessionScope.edit_user.password : ""}
            >
            <h6 class="error">${sessionScope.password_length_error}</h6>
            <h6 class="error">${sessionScope.password_match_error}</h6>
        </div>
        <div class="my-form form-group">
            <label for="confirm-password" class="control-label required">Confirm password</label>
            <input type="password"
                   required
                   name="confirm-password"
                   class="form-control"
                   id="confirm-password"
                   placeholder="Please confirm the password"
                   value=${sessionScope.add_edit_mode == "EDIT" ? sessionScope.edit_user.password : ""}
            >
            <h6 class="error">${sessionScope.password_match_error}</h6>
        </div>
        <div class="my-form form-group">
            <label for="email" class="control-label required">Email</label>
            <input type="text"
                   required
                   name="email"
                   class="form-control"
                   id="email"
                   placeholder="Enter the email"
                   value=${sessionScope.add_edit_mode == "EDIT" ? sessionScope.edit_user.email : ""}
            >
            <h6 class="error">${sessionScope.email_unique_error}</h6>
            <h6 class="error">${sessionScope.email_pattern_error}</h6>
        </div>
        <div class="my-form form-group">
            <label for="first-name" class="control-label required">First name</label>
            <input type="text"
                   required
                   name="first-name"
                   class="form-control"
                   id="first-name"
                   placeholder="Enter the first name"
                   value=${sessionScope.add_edit_mode == "EDIT" ? sessionScope.edit_user.firstName : ""}
            >
        </div>
        <div class="my-form form-group">
            <label for="last-name" class="control-label required">Last name</label>
            <input type="text"
                   required
                   name="last-name"
                   class="form-control"
                   id="last-name"
                   placeholder="Enter the last name"
                   value=${sessionScope.add_edit_mode == "EDIT" ? sessionScope.edit_user.lastName : ""}
            >
        </div>
        <div class="my-form form-group">
            <jsp:useBean id="now" class="java.util.Date"/>
            <fmt:formatDate var="currentDate" value="${now}" pattern="yyyy-MM-dd"/>
            <label for="birthday" class="control-label required">Birthday</label>
            <input type="date"
                   required
                   name="birthday"
                   class="form-control"
                   id="birthday"
                   placeholder="Choose your birthday"
                   pattern="yyyy-MM-dd"
                   min="1970-01-01"
                   max="${currentDate}"
                   value=${sessionScope.add_edit_mode == "EDIT" ? sessionScope.edit_user.birthday : currentDate}
            >
        </div>
        <div class="my-form form-group">
            <label class="control-label required">Role</label>
            <select id="role" name="role">
                <c:forEach var="role" items="${sessionScope.roles}">
                    <option
                            label="${role.getName()}"
                        ${sessionScope.add_edit_mode == "EDIT"
                                ?
                                (role.equals(sessionScope.edit_user.role) ? "selected" : "")
                                :
                                (role.equals(sessionScope.roles.get(0)) ? "selected" : "")
                                }
                    >
                            ${role.getName()}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div align="center">
            <button type="submit" class="btn btn-block mybtn btn-primary tx-tfm">Save</button>
            <a type="button" href="userlist" class="btn btn-block mybtn btn-primary tx-tfm">Cancel</a>
        </div>
</div>
</form>
</body>
</html>
