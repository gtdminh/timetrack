<%@ page import="com.fxrialab.timetrack.utils.SecurityUtils" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username  = SecurityContextHolder.getContext().getAuthentication().getName();
%>
<nav class="navbar navbar-expand-md fixed-top navbar-light bg-light" color-on-scroll="300">
    <a class="navbar-brand text-primary" href="<c:url value="/home" />">
            <img src="<c:url value="/resources/img/sand-clock2.png" />" width="24" height="24" class="time-logo">
            <span>timetrack</span>
        </a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler"
                aria-controls="navbarToggler" aria-expanded="false" aria-label="Navbar Toggler">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarToggler">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" id="featuresDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Features</a>
                    <div class="dropdown-menu" aria-labelledBy="featuresDropdown">
                        <a href="/pages/feature#overview" class="dropdown-item">Overview</a>
                        <a href="/pages/feature#reporting" class="dropdown-item">Timesheet + Reports</a>
                        <a href="/pages/feature#task-management" class="dropdown-item">Task Management</a>
                        <a href="/pages/feature#resource-plan" class="dropdown-item">Resource Planning</a>
                        <a href="/pages/feature#invoice" class="dropdown-item">Invoice</a>
                        <a href="/pages/feature#finance" class="dropdown-item">Finance Tracking</a>
                    </div>
                </li>
                <li class="nav-item"><a href="/pages/pricing" class="nav-link">Pricing</a></li>
                <li class="nav-item"><a href="/pages/oss" class="nav-link">Open Source</a></li>
                <li class="nav-item dropdown">
                <% if(SecurityUtils.hasRole("USER")) {%>
                    <a href="#" class="nav-link dropdown-toggle" role="button" id="accountDropdown"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fa fa-user"></i>
                        <%= username.toUpperCase()%>
                    </a>
                    <div class="dropdown-menu" aria-labelledBy="accountDropdown">
                        <div class="dropdown-menu-header">Account</div>
                        <a href="/account/edit" class="dropdown-item">Edit</a>
                        <a href="/logout" class="dropdown-item">SignOut</a>
                    </div>
                <% } else { %>
                    <a href="/login" class="btn btn-outline-primary" role="button">
                        <i class="fa fa-sign-in"></i> Sign In
                    </a>
                <% } %>
                </li>
            </ul>
        </div>

</nav>
