<%--
  Created by IntelliJ IDEA.
  User: Sonpt
  Date: 8/18/2018
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Time-Tick Register</title>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="apple-touch-icon" href="<c:url value="/resources/img/apple-icon.png" />" size="76x76"/>
    <link rel="icon" type="image/png" href="<c:url value="/resources/img/favicon.ico" />"/>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons">
    <%-- bootstrap core --%>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-material-design.min.css" />" type="text/css"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery.steps.css" />" type="text/css"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css" />" type="text/css"/>
    <%-- font awesome --%>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'/>

    <link href="<c:url value="/resources/css/login.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body class="text-center bg-light">

<form action=<c:url value="/auth/information-submit/${activationCode}" /> name="inputInformationForm"
      id="example-form" class="form-signup" method="post">
    <img src="/resources/img/logo.png" alt="" class="mb-4">
    <h1 class="h3 mb-3 font-weight-normal">Register Confirmation</h1>
    <input type="hidden"  name="${_csrf.parameterName}"  value="${_csrf.token}">
    <div>
        <h3>Step 1</h3>
        <section>
            <input type="radio" name="managingtype" value="starttofinish"> Managing projects from start to finish<br>
            <input type="radio" name="managingtype" value="dailyworktasks"> Managing daily work tasks<br>
            <input type="radio" name="managingtype" value="timespentonwork"> Tracking time spent on work<br>
            <input type="radio" name="managingtype" value="invoicestoclient"> Creating & sending invoices to clients<br>
        </section>
        <h3>Step 2</h3>
        <section>
            <input type="radio" name="projecttype" value="genegic"> Generic Project<br>
            <input type="radio" name="projecttype" value="websitedesign"> Website Design<br>
            <input type="radio" name="projecttype" value="video"> Video Project<br>
            <input type="radio" name="projecttype" value="development"> Development Project<br>
            <input type="radio" name="projecttype" value="marketing"> Marketing Project<br>
            <input type="radio" name="projecttype" value="consulting"> Consulting Project<br>
            <input type="radio" name="projecttype" value="editorial"> Editorial Calendar<br>
            <input type="radio" name="projecttype" value="socialmedia"> Social Media Project<br>
            <input type="radio" name="projecttype" value="seo"> SEO<br>
        </section>
        <h3>Step 3</h3>
        <section>
            <div class="form-group">
                <label for="inputFullname" class="sr-only">Your full name *</label>
                <input type="text" class="form-control" placeholder="Your full name" id="inputFullname" name="fullname" required
                       autofocus value="">
            </div>
            <div class="form-group">
                <label for="inputPassword" class="sr-only">Your password *</label>
                <input type="password" class="form-control" placeholder="Password" id="inputPassword" name="password" required
                       value="">
            </div>
            <div class="form-group">
                <label for="inputCompanyname" class="sr-only">Company name *</label>
                <input type="text" class="form-control" placeholder="Company name" id="inputCompanyname" name="companyname" required
                       autofocus value="">
            </div>
            <div class="form-group">
                <label for="inputCompanysize" class="sr-only">Company size *</label>
                <select name="companysize" id="inputCompanysize">
                    <option value="justme">Just me</option>
                    <option value="saab">1 - 5</option>
                    <option value="fiat">6 - 20</option>
                    <option value="audi">21 - 100</option>
                </select>
            </div>
        </section>
        <div class="result-message">
            ${message}
        </div>
    </div>


</form>

<script src="<c:url value="/resources/js/jquery-3.3.1.min.js"/> "></script>
<script src="<c:url value="/resources/js/popper.min.js"/> "></script>
<script src="<c:url value="/resources/js/bootstrap-material-design.js"/> "></script>
<script src="<c:url value="/resources/js/bootstrap-material-design.js"/> "></script>
<script src="<c:url value="/resources/js/jquery.steps.min.js"/> "></script>
<script src="<c:url value="/resources/js/jquery.validate.min.js"/> "></script>
<script>$(document).ready(function () {
    $('body').bootstrapMaterialDesign();
});</script>
<script>
    $(function () {

        var form = $("#example-form");
        form.validate({
            errorPlacement: function errorPlacement(error, element) { element.before(error); },
            rules: {
                confirm: {
                    equalTo: "#password"
                }
            }
        });
        debugger;
        form.children("div").steps({
            headerTag: "h3",
            bodyTag: "section",
            transitionEffect: "slideLeft",
            onStepChanging: function (event, currentIndex, newIndex)
            {
                form.validate().settings.ignore = ":disabled,:hidden";
                return form.valid();
            },
            onFinishing: function (event, currentIndex)
            {
                form.validate().settings.ignore = ":disabled";
                return form.valid();
            },
            onFinished: function (event, currentIndex)
            {
                form.submit();
                alert("Submitted!");
            }
        });

/*

        $('form').submit(function (event) {
            event.preventDefault();
            debugger;
            $.ajax({
                method: 'POST',
                url: '/auth/registerNameAndPassword/<c:out value="${activationCode}"/>?${_csrf.parameterName}=${_csrf.token}',
                data: {fullname: $('#inputFullname').val(), password: $('#inputPassword').val()}
            })
                .done(function (data) {
                    switch (data['code']) {
                        case "SUCCESS":
                            window.location.href = '../../..';
                            break;
                        case "USER_HAS_BEEN_ACTIVATED":
                            $('.result-message').text('Your account has been activated.');
                            break;
                        case "NO_LEGAL_ACTIVATION_CODE":
                            $('.result-message').text('This is no legal activation code.');
                            break;
                        case "UNEXPECTED_CREATING_USER_ISSUE":
                            $('.result-message').text('There is a problem of creating user.');
                            break;
                    }}
                )
                .fail(function (e) {
                    alert(e);
                });


        });
        */
    });
</script>
</body>
</html>
