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

    <%-- bootstrap core --%>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" type="text/css"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/paper-kit.css" />" type="text/css"/>

    <%-- font awesome --%>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'/>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,300,700" rel="stylesheet" type="text/css" />

    <c:if test="${css != null}">
    <link href="<c:url value="${css}"/>" rel="stylesheet" type="text/css"/>
    </c:if>
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js">
    </script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js">
    </script>
    <![endif]-->
</head>
<body>
<jsp:include page="/WEB-INF/views/partials/header.jsp"/>
<jsp:doBody/>
<jsp:include page="/WEB-INF/views/partials/footer.jsp"/>
</body>
<script href="<c:url value="/resources/js/jquery-3.3.1.min.js"/> " type="text/javascript"></script>
<script href="<c:url value="/resources/js/bootstrap.bundle.min.js"/> " type="text/javascript"></script>
</html>
