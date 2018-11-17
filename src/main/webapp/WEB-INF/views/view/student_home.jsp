<%--
  Created by IntelliJ IDEA.
  User: rathana
  Date: 3/24/18
  Time: 9:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student</title>
</head>
<body>
    <h1>This Page Under Develop</h1>
    <form action="logout" method="post">
        <input type="submit" value="logout" class="submit-btn">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
</body>
</html>
