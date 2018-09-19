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
<%
    double random  = Math.random()*10000;
    String css = "/resources/css/commons.css?v=" + random;
%>
<t:signup.layout title="Robot Human Verify" css="<%=css%>">
    <div class="container-fluid">

        <form action=<c:url value="/auth/email-submit" /> name="robotHumanVerifyForm" class="form-signup" method="post">
            <img src="/resources/img/logo.png" alt="" class="mb-4">
            <h1 class="h3 mb-3 font-weight-normal">Sign Up</h1>
            <input type="hidden"  name="${_csrf.parameterName}"  value="${_csrf.token}">
            <div class="form-group">
                <input  type="hidden"  name="email" value="${email}" >
                <div class="g-recaptcha" data-callback="onSubmit" data-sitekey="6Le1RWwUAAAAALMNkuo_3IQJtzPlc5wZTknn9LSe"></div>
            </div>
            <div class="result-message">

            </div>

        </form>

        <script src='https://www.google.com/recaptcha/api.js'></script>
        <script>

            function onSubmit( token ) {
                $("form").submit();
            }
        </script>
    </div>
</t:signup.layout>

