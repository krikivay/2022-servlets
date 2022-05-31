<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ex" uri="/WEB-INF/customNavBar.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: krikivay
  Date: 03.05.2022
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<html>
<head>
    <title>My Profile</title>
</head>
<body>
<ex:NavBar/>
<div class="container profile">
    <img src="./resources/avatar.png" width="50%" height="50%" class="my-profile-inline-info avatar rounded float-start"
         alt="...">
    <div class="profile-content">
        <h1 class="my-profile-inline-info font-monospace">
            ${sessionScope.current_user_full_info.firstName} ${sessionScope.current_user_full_info.lastName}
        </h1>
        <div class="my-profile-inline-info badge bg-primary text-wrap" style="width: 6rem;">
            ${sessionScope.current_user_full_info.role.name}
        </div>
        <h3 class="my-profile-inline-info font-monospace">
            Login: ${sessionScope.current_user_full_info.login}
        </h3>
        <h3 class="my-profile-inline-info font-monospace">
            Email: ${sessionScope.current_user_full_info.email}
        </h3>
        <h3 class="my-profile-inline-info font-monospace">
            Birthday: ${sessionScope.current_user_full_info.birthday}
        </h3>
        <h3 class="my-profile-inline-info font-monospace">
            Books:
        </h3>
        <ul class="my-profile-inline-info font-monospace">
            <c:forEach var="book" items="${sessionScope.current_user_full_info.books}">
                <li class="my-profile-inline-info font-monospace">
                        ${book.author} ${book.title}, price: ${book.price}$, genre: ${book.genre.name}
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
