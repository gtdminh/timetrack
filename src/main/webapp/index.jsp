<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>TimeTrack</title>
	<link rel="stylesheet"
		  type="text/css"
		  href="<c:url value="/resources/style.css"  />" />
</head>
<body>
	<h1>Timetrack</h1>
	<a href="<c:url value="/login" />">Login</a> |
	<a href="<c:url value="/logout" />">logout</a>
</body>
</html>
