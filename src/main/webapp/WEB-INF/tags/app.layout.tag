<%@tag description="basic layout" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="title" type="java.lang.String" required="true" %>
<%
    String appName = "app";
%>
<html>
<head>
    <title>Time-Tick - ${title}</title>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="apple-touch-icon" href="<c:url value="/resources/img/apple-icon.png" />" size="76x76"/>
    <link rel="icon" type="image/png" href="<c:url value="/resources/img/favicon.ico" />"/>

    <%-- font awesome --%>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

</head>
<body>
<jsp:doBody/>
<script type="text/javascript" src="<c:url value="/resources/<%=appName%>/runtime.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/<%=appName%>/polyfills.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/<%=appName%>/styles.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/<%=appName%>/vendor.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/<%=appName%>/main.js"/>"></script>
</body>

</body>
</html>