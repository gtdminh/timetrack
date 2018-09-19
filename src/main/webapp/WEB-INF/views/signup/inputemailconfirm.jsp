<%--
  Created by IntelliJ IDEA.
  User: Sonpt
  Date: 8/25/2018
  Time: 3:23 PM
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
        Please check your email
        We've sent your confirmation email to
        ose_support1@gmail.com
        (just to make sure that you are the owner of this email address).
        If you haven't received it, check the junk/spam folder or the "Updates" tab (for Gmail accounts), or click here to send the confirmation email again. If the email address is misspelled, click back in your browser and enter the correct one.
        Already have an account? <a href="/auth/login">Login</a>
        Contact Fxrialab Support
    </div>
</t:signup.layout>
