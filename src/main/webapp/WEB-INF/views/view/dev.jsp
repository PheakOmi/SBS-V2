<%--
  Created by IntelliJ IDEA.
  User: rathana
  Date: 7/4/18
  Time: 2:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="display: flex;flex: 1;justify-content: space-around;align-items: center;flex-direction: column">
    <h2>We currently accept only student booking! Please sign in with Google using your KIT email. Thanks!</h2>

    <form action="/sbs/logout" method="post" id="logoutForm">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
    <button onclick="myFunction()">Logout</button>
    <script>
        function myFunction() {
            document.getElementById("logoutForm").submit();
        }
    </script>
</body>
</html>
