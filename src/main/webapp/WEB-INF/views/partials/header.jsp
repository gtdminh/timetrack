<%@ page import="com.fxrialab.timetrack.utils.SecurityUtils" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
%>

<div class="hero-head">
    <nav class="navbar">
        <div class="container">
            <div class="navbar-brand">
                <a href="/" class="navbar-item">
                    <img src="<c:url value="/resources/img/timetrack_logo.png"/>" alt="timetrack logo" width="112"
                         height="28">
                </a>

                <a role="button" class="navbar-burger burger" aria-expanded="false" aria-label="menu"
                   data-target="site-menu">
                    <span aria-hidden="true"></span>
                    <span aria-hidden="true"></span>
                    <span aria-hidden="true"></span>
                </a>
            </div>
            <div class="navbar-menu" id="site-menu">
                <div class="navbar-end">
                    <span class="navbar-item">
                        <a class="button is-black is-outlined" href="#">
                            <span class="icon">
                                <i class="fa fa-home"></i>
                            </span>
                            <span>Home</span>
                        </a>
                    </span>
                    <span class="navbar-item">
                        <a class="button is-black is-outlined" href="#">
                            <span class="icon">
                                <i class="fa fa-cogs"></i>
                            </span>
                            <span>Features</span>
                        </a>
                    </span>
                    <span class="navbar-item">
                        <a class="button is-black is-outlined" href="#">
                            <span class="icon">
                                <i class="fa fa-dollar-sign"></i>
                            </span>
                            <span>Pricing</span>
                        </a>
                    </span>
                    <span class="navbar-item">
                        <a class="button is-black is-outlined" href="#">
                            <span class="icon">
                                <i class="fa fa-code-branch"></i>
                            </span>
                            <span>Open Source</span>
                        </a>
                    </span>
                    <span class="navbar-item">
                        <a class="button is-black is-outlined" href="/auth/login">
                            <span class="icon">
                                <i class="fa fa-sign-in-alt"></i>
                            </span>
                            <span>Login</span>
                        </a>
                    </span>
                </div>
            </div>
        </div>
    </nav>
</div>