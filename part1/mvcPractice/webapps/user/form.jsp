<%@ page contentType="text/html;charset-UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>form</title>
    <%--파비콘 요청 무시--%>
    <link rel="shortcut icon" href="data:image/x-icon;," type="image/x-icon">
</head>

<body>
<%--IDE에서 절대 경로 추천 안함, 동적 컨텍스트 활용하도록 수정--%>
<form method="post" action="${pageContext.request.contextPath}/users">
    <div>
        <label for="userId">사용자 아이디</label>
        <input class="form-control" id="userId" name="userId" placeholder="User Id"/>
    </div>
    <div>
        <label for="name">이름</label>
        <input class="form-control" id="name" name="name" placeholder="Name"/>
    </div>
    <button type="submit">회원가입</button>
</form>

</body>
</html>
