
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html><html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="theme-color" content="#000000"><link rel="manifest" href="/resources/build/manifest.json">
    <link rel="apple-touch-icon" sizes="180x180" href="/resources/icon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="/resources/icon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="/resources/icon/favicon-16x16.png">
    <link rel="manifest" href="/resources/icon/site.webmanifest">
    <link rel="mask-icon" href="/resources/icon/safari-pinned-tab.svg" color="#5bbad5">
    <meta name="msapplication-TileColor" content="#da532c">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <title>Shuttle Bus Booking</title>
    <link rel="stylesheet" href="https://unpkg.com/react-md@1.2.13/dist/react-md.indigo-pink.min.css">


</head>
<body>
<jsp:include page='./payment.jsp'/>
<input type="hidden" id="csrfToken" value="${_csrf.token}"/>
<input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
<script>
    window.token = document.getElementById("csrfToken").value
    window.headerName = document.getElementById("csrfHeader").value
    sessionStorage.setItem("path","/")
    if(!sessionStorage.getItem("intro")){
        sessionStorage.setItem("intro",'true');
    }

</script>
<form action="/logout" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<noscript>You need to enable JavaScript to run this app.</noscript>
<div id="root"></div>
<!--<script type="text/javascript" src="/resources/build/static/js/bundle.js"></script>-->
<!--<script type="text/javascript" src="/resources/build/static/js/comp.js"></script>
<script type="text/javascript" src="/resources/build/static/js/compressed.js"></script>-->
<script type="text/javascript" src="/resources/build/static/js/main.651269d9.js"></script>
<link rel="stylesheet" href="/resources/build/static/css/main.18dc56c1.css">
<!--<script type="text/javascript" src="http://localhost:3000/static/js/bundle.js"></script>-->
</body>

</html>
