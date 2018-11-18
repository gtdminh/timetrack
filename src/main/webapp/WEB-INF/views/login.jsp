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

    <%-- font awesome --%>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'/>

    <!-- Bulma -->
    <link rel="stylesheet" href="<c:url value="/resources/css/bulma.min.css" />"
          type="text/css"/>

    <link rel="stylesheet" href="<c:url value="https://unpkg.com/bulma-modal-fx/dist/css/modal-fx.min.css" />"
          type="text/css"/>

    <link href="<c:url value="/resources/css/login.css"/>" rel="stylesheet" type="text/css"/>

</head>
<body class="text-center bg-light">
<%--content here--%>
<section class="hero is-success is-fullheight">
    <div class="hero-body">
        <div class="container has-text-centered">
            <div class="column is-4 is-offset-4">
                <h3 class="title has-text-grey">Login</h3>
                <p class="subtitle has-text-grey">Please login to proceed.</p>
                <div class="box">
                    <figure class="avatar">
                        <img src="https://placehold.it/128x128">
                    </figure>
                    <form action="<c:url value="/auth/login?${_csrf.parameterName}=${_csrf.token} " />" name="loginForm"
                          class="form-signin" method="post">
                        <div class="result-message">
                            ${message}
                        </div>
                        <div class="field">
                            <div class="control">
                                <input class="input is-large" type="username" placeholder="Username" autofocus=""
                                       id="inputUsername" name="username" required
                                       autofocus value="gtdminh">
                            </div>
                        </div>

                        <div class="field">
                            <div class="control">
                                <input class="input is-large" type="password" placeholder="Your Password"
                                       id="inputPassword" name="password" required
                                       value="password">
                            </div>
                        </div>
                        <div class="field">
                            <label class="checkbox">
                                <input type="checkbox" class="form-check-input" id="rememberCheckbox"
                                       value="remember-me">
                                Remember me
                            </label>
                        </div>
                        <div>
                            <button class="button is-block is-info is-large is-fullwidth" type="submit">Sign In</button>
                        </div>
                    </form>
                </div>
                <p class="has-text-grey">
                    <a href="/auth/signup">Sign Up</a> &nbsp;·&nbsp;
                    <a href="/auth/password-recovery">Forgot Password</a> &nbsp;·&nbsp;
                    <a href="../">Need Help?</a>
                </p>
            </div>
        </div>
    </div>
</section>

<script src="<c:url value="/resources/js/jquery-3.2.1.slim.min.js"/> "></script>
<script src="<c:url value="/resources/js/forge.min.js"/> "></script>
<script>
    $(function () {
        //debugger;
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
                    forge.pkcs5.pbkdf2(password, salt, iteration, hash_length, function (err, newHash) {
                        const hex = forge.util.bytesToHex(newHash);
                        debugger;
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