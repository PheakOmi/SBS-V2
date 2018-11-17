<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><tiles:insertAttribute name="title" /></title>

<!--  jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<!--  jQuery Validation -->
	<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.js"></script> 
<!-- Bootstrap Core JavaScript -->
    <spring:url value="/resources/Bootstrap/js/bootstrap.min.js" var="JSCORE"/>
    <script src="${JSCORE}"></script>
	

    	
<!-- Isolated Version of Bootstrap, not needed if your site already uses Bootstrap -->
<link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css" />
<!-- Bootstrap Core CSS -->	
  <spring:url value="/resources/Bootstrap/css/bootstrap.min.css" var="bootatrapCore"/>
  <spring:url value="/resources/Bootstrap/css/sb-admin.css" var="CustomCSS"/>
  <spring:url value="/resources/Bootstrap/font-awesome/css/font-awesome.min.css" var="customfontCSS"/>
  
 		<link href="${bootatrapCore}" rel="stylesheet">
   		<!-- Custom CSS -->
    	<link href="${CustomCSS}" rel="stylesheet">
    	<!-- Morris Charts CSS -->
    	<link href="${morrisCSS }" rel="stylesheet">
    	<!-- Custom Fonts -->
   	    <link href="${customfontCSS }" rel="stylesheet" type="text/css">

<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<spring:url value="/resources/Bootstrap/css/reset_pw.css" var="reset_pw"/>
<link href="${reset_pw}" rel="stylesheet">
<!-- Sweet alert -->
<spring:url value="/resources/Bootstrap/css/sweetalert.css" var="alertStyle"/>
      <link rel="stylesheet" href="${alertStyle}">
<spring:url value="/resources/Bootstrap/js/sweetalert.min.js" var="alertJS"/>
      <script src="${alertJS}"></script>

</head>
<body>
 <tiles:insertAttribute name="body" />
</body>
</html>