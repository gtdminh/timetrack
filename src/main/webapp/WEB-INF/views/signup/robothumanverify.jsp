<%--
  Created by IntelliJ IDEA.
  User: Sonpt
  Date: 8/25/2018
  Time: 1:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Robot Human Verify</title>
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

    <link href="<c:url value="/resources/css/login.css"/>" rel="stylesheet" type="text/css"/>
    <script src='https://www.google.com/recaptcha/api.js'></script>

</head>
<body class="text-center bg-light">

<form action="/auth/email-submit" name="inputNameAndPasswordForm" class="form-signup" method="post">
    <img src="/resources/img/logo.png" alt="" class="mb-4">
    <h1 class="h3 mb-3 font-weight-normal">Register Confirmation</h1>
    <div class="form-group">
        <input  type="hidden"  name="email" value="${email}" >
        <div class="g-recaptcha" data-sitekey="6Le1RWwUAAAAALMNkuo_3IQJtzPlc5wZTknn9LSe"></div>
    </div>

    <div class="btn-group" role="group" aria-label="SignButtons">
        <button class="btn btn-primary" type="submit">Sign Up</button>
    </div>
    <div class="result-message">

    </div>

</form>

<script src="<c:url value="/resources/js/jquery-3.3.1.min.js"/> "></script>
<script src="<c:url value="/resources/js/popper.min.js"/> "></script>
<script src="<c:url value="/resources/js/bootstrap-material-design.js"/> "></script>
<script src="<c:url value="/resources/js/forge.min.js"/> "></script>
<script>$(document).ready(function () {
    $('body').bootstrapMaterialDesign();
});</script>

</body>
</html>
