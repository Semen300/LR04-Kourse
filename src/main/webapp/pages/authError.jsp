<%--
  Created by IntelliJ IDEA.
  User: Account
  Date: 13.09.2024
  Time: 13:20
  To change this template use File | Settings | File Templates.

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Отказано в доступе</title>
</head>
<body>
    У вас нет доступа к данной странице <br>
    <a href="${pageContext.request.contextPath}/login?action=login">Вернуться на страницу авторизации</a>
</body>
</html>
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        <%@include file="/pages/errorStyles.css"%>
    </style>
    <title>Авторизация</title>
</head>
<body>
<div class="container">
    <div class="error-box">
        <h2>Ошибка!</h2>
        <div class="error-message" id="error-message">У вас нет доступа к данной странице</div>
        <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/login?action=login';">Возврат к регистрации</button>
    </div>
</div>
</body>
</html>

