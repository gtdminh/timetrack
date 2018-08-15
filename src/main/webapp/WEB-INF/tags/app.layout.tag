<%@tag description="basic layout" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="title" type="java.lang.String" required="true" %>
<%
    //String path = request.getPathInfo();
    String appName = "app";//path.split("/")[1];
%>
<html>
<head>
    <title>Time-Tick - ${title}</title>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="apple-touch-icon" href="<c:url value="/resources/img/apple-icon.png" />" size="76x76"/>
    <link rel="icon" type="image/png" href="<c:url value="/resources/img/favicon.ico" />"/>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons">
    <%-- bootstrap core --%>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-material-design.min.css" />" type="text/css"/>

    <%-- font awesome --%>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'/>

    <link href="<c:url value="/resource/css/commons.css"/>" rel="stylesheet" type="text/css"/>

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