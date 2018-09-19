<%--
  Created by IntelliJ IDEA.
  User: Sonpt
  Date: 8/8/2018
  Time: 10:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    double random  = Math.random()*10000;
    String css = "/resources/css/commons.css?v=" + random;
%>
<t:signup.layout title="Features" css="<%=css%>">
    <div class="container-fluid">
        <form action="<c:url value="/auth/signup" />" name="signupForm" class="form-signup" method="post">
            <img src="/resources/img/logo.png" alt="" class="mb-4">
            <h1 class="h3 mb-3 font-weight-normal">Sign Up</h1>
            <input type="hidden"  name="${_csrf.parameterName}"  value="${_csrf.token}">
            <div class="form-group">
                <label for="inputEmail" class="sr-only">Email</label>
                <input type="email" class="form-control" placeholder="Email" id="inputEmail" name="email" required
                       autofocus value="">
            </div>

            <div class="btn-group" role="group" aria-label="SignButtons">
                <button class="btn btn-primary" type="submit">Sign Up</button>
            </div>
            <div class="result-message">
                    ${message}
            </div>

        </form>
    </div>
</t:signup.layout>
