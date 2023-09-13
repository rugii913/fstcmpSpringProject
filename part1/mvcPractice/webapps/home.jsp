<%@ page contentType="text/html;charset-UTF-8" pageEncoding="UTF-8" language="java" %>
<%--pageEncoding="UTF-8" 부분 없으면 한글 깨져서 나옴--%>
<%--cf. 현재 request에서 /favicon.ico까지 같이 날라와서 exception 터지는 상태--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--여기 빨간줄 뜨는 건 뭐가 문제인지는 모르겠음--%>
<%--몇 가지 시도해보려고 했는데 잘 안 됨, 톰캣, 서블릿, IntelliJ 프로젝트 구조에 대해서 더 생각해볼 것--%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>home</title>
    <%--파비콘 요청 무시--%>
    <%--(참고) https://ryusmtistory.com/9 https://blog.naver.com/dsz08082/222569239010--%>
    <link rel="shortcut icon" href="data:,">
    <%--<link rel="shortcut icon" href="data:image/x-icon;," type="image/x-icon">--%>
</head>
<body>
페이지 Home
</body>
</html>
