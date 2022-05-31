<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ex" uri="/WEB-INF/customNavBar.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: krikivay
  Date: 05.05.2022
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<html>
<head>
    <title>Book List</title>
</head>
<body>
<ex:NavBar/>
<div class="container">
    <table class="table">
        <thead class="table-dark">
        <tr>
            <th scope="col">Author</th>
            <th scope="col">Title</th>
            <th scope="col">Price</th>
            <th scope="col">Genre</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${sessionScope.all_books}">
            <tr>
                <td>${book.author}</td>
                <td>${book.title}</td>
                <td>${book.price}</td>
                <td>${book.genre.name}</td>
                <td><a href="buy?id=${book.id}" class="btn btn-primary">Buy the book</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
