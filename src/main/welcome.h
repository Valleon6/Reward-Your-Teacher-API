<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="ISO-8859-1">
    <title>Welcome - Spring Boot Security Example</title>
</head>
<body>
<div style="text-align: center;">
    <h2 th:inline="text">Welcome [[${#httpServletRequest.remoteUser}]]</h2>

    <form th:action="@{/logout}" method="post">
        <input type="submit" value="Logout" />
    </form>
</div>
</body>
</html>