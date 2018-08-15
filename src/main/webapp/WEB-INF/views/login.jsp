<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Time-Tick - Login</title>
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

</head>
<body class="text-center bg-light">
<%--content here--%>
<form action="<c:url value="/auth/login?${_csrf.parameterName}=${_csrf.token} " />" name="loginForm" class="form-signin" method="post">
    <img src="/resources/img/logo.png" alt="" class="mb-4">
    <h1 class="h3 mb-3 font-weight-normal">Sign In</h1>
    <div class="form-group">
        <label for="inputUsername" class="sr-only">UserName</label>
        <input type="username" class="form-control" placeholder="Username" id="inputUsername" name="username" required
               autofocus value="gtdminh">
    </div>
    <div class="form-group">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" class="form-control" placeholder="Password" id="inputPassword" name="password" required
               value="password">
    </div>
    <div class="form-check">
        <input type="checkbox" class="form-check-input" id="rememberCheckbox" value="remember-me">
        <label for="rememberCheckbox" class="form-check-label">Remember Me</label>
    </div>
    <div class="btn-group" role="group" aria-label="SignButtons">
        <a href="/signup" class="btn btn-danger" role="button">Sign Up</a>
        <button class="btn btn-primary" type="submit">Sign In</button>
    </div>

</form>

<script src="<c:url value="/resources/js/jquery-3.3.1.min.js"/> "></script>
<script src="<c:url value="/resources/js/popper.min.js"/> "></script>
<script src="<c:url value="/resources/js/bootstrap-material-design.js"/> "></script>
<script src="<c:url value="/resources/js/forge.min.js"/> "></script>
<script>$(document).ready(function () {
    $('body').bootstrapMaterialDesign();
});</script>
<script>
    $(function () {
        debugger;
        $('form').submit(function (event) {
            event.preventDefault();

            $.ajax({
                method: 'GET',
                url: '/auth/salt/' + $('#inputUsername').val()
            })
                .done(function (data) {
                    const salt = data.salt;
                    const iteration = data.iteration || 1000;
                    const hash_length = data.length || 256;
                    const password = $('#inputPassword').val();
                    forge.pkcs5.pbkdf2(password, salt, iteration, hash_length, function(err, newHash){
                        const hex = forge.util.bytesToHex(newHash);
                        $('#inputPassword').val(hex);
                        $('form').off('submit').submit();
                    });

                })
                .fail(function (e) {
                    alert(e);
                });
        });
    });
</script>
</body>
</html>