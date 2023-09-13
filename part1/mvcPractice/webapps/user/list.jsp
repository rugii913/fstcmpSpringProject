<%@ page contentType="text/html;charset-UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>list</title>
    <%--파비콘 요청 무시--%>
    <link rel="shortcut icon" href="data:image/x-icon;," type="image/x-icon">
</head>

<body>

<table>
    <thead>
        <tr>
            <th>#</th>
            <th>userId</th>
            <th>name</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${users}" var="user" varStatus="status">
            <tr>
                <th scope="row">${status.count}</th>
                <td>${user.userId}</td>
                <td>${user.name}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
