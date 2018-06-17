<%@tag description="basic layout" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="title" type="java.lang.String" required="true" %>
<%@attribute name="css" type="java.lang.String" required="false" %>

<html>
<head>
    <title>Time-Tick - ${title}</title>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="apple-touch-icon" href="<c:url value="/resources/img/apple-icon.png" />" size="76x76"/>
    <link rel="icon" type="image/png" href="<c:url value="/resources/img/favicon.ico" />" />

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons">
    <%-- bootstrap core --%>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-material-design.min.css" />" type="text/css"/>

    <%-- font awesome --%>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'/>

    <c:if test="${css != null}">
        <link href="<c:url value="${css}"/>" rel="stylesheet" type="text/css"/>
    </c:if>

</head>
<body>
<jsp:include page="/WEB-INF/views/partials/header.jsp"/>
<jsp:doBody/>
<jsp:include page="/WEB-INF/views/partials/footer.jsp"/>

<script src="<c:url value="/resources/js/jquery-3.2.1.slim.min.js"/> "></script>
<script src="<c:url value="/resources/js/popper.min.js"/> "></script>
<script src="<c:url value="/resources/js/bootstrap-material-design.js"/> "></script>
<script>$(document).ready(function() { $('body').bootstrapMaterialDesign(); });</script>
</body>
</html>