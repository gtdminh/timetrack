<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    double random  = Math.random()*10000;
    String css = "/resources/css/commons.css?v=" + random;
%>
<t:layout title="Open Source" css="<%=css%>">
<div class="container-fluid">
    BODY
</div>
</t:layout>