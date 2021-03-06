<%@tag description="basic layout" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="title" type="java.lang.String" required="true" %>
<%@attribute name="css" type="java.lang.String" required="false" %>

<%--<html class="has-navbar-fixed-top">--%>
<html>
<head>
    <title>Time-Tick - ${title}</title>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="apple-touch-icon" href="<c:url value="/resources/img/apple-icon.png" />" size="76x76"/>
    <link rel="icon" type="image/png" href="<c:url value="/resources/img/favicon.ico" />"/>

    <%--<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons">--%>
    <%-- bootstrap core --%>
    <%--<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-material-design.min.css" />" type="text/css"/>--%>
    <link rel="stylesheet" href="<c:url value="/resources/css/bulma.min.css" />"
          type="text/css"/>

    <link rel="stylesheet" href="<c:url value="https://unpkg.com/bulma-modal-fx/dist/css/modal-fx.min.css" />"
          type="text/css"/>

    <link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>"
          type="text/css"/>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/hero.css" />"
          type="text/css"/>

    <link rel="stylesheet" href="<c:url value="https://fonts.googleapis.com/css?family=Open+Sans" />"
          type="text/css"/>

    <script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"
            integrity="sha384-kW+oWsYx3YpxvjtZjFXqazFpA7UP/MbiY4jvs+RWZo2+N94PFZ36T6TFkc9O3qoB"
            crossorigin="anonymous"></script>

    <c:if test="${css != null}">
        <link href="<c:url value="${css}"/>" rel="stylesheet" type="text/css"/>
    </c:if>

</head>
<body>
<jsp:include page="/WEB-INF/views/partials/header.jsp"/>
<jsp:doBody/>
<jsp:include page="/WEB-INF/views/partials/footer.jsp"/>

<script src="<c:url value="/resources/js/jquery-3.2.1.slim.min.js"/> "></script>
</body>
</html>
