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
<%
    double random  = Math.random()*10000;
    String css = "/resources/css/commons.css?v=" + random;
%>
<t:signup.layout title="Features" css="<%=css%>">
    <div class="container-fluid">

        <form action=<c:url value="/auth/information-submit/${activationCode}" /> name="inputInformationForm"
              id="example-form" class="form-signup" method="post">
            <img src="/resources/img/logo.png" alt="" class="mb-4">
            <h1 class="h3 mb-3 font-weight-normal">Register Confirmation</h1>
            <input type="hidden"  name="${_csrf.parameterName}"  value="${_csrf.token}">
            <div class="steps" id="stepsInfoInput">
                <div class="step-item is-active is-success">
                    <div class="step-marker">1</div>
                    <div class="step-details">
                        <p class="step-title">Managing Type</p>
                    </div>
                </div>
                <div class="step-item">
                    <div class="step-marker">2</div>
                    <div class="step-details">
                        <p class="step-title">Project Type</p>
                    </div>
                </div>
                <div class="step-item">
                    <div class="step-marker">3</div>
                    <div class="step-details">
                        <p class="step-title">Account Information</p>
                    </div>
                </div>
                <div class="steps-content">
                    <div class="step-content has-text-centered is-active">
                        <div class="field is-horizontal">
                            <div class="field-label is-normal">
                                <label class="label">Managing projects from start to finish</label>
                            </div>
                            <div class="field-body">
                                <div class="field">
                                    <div class="control has-icon has-icon-right">
                                        <input type="radio" name="managingtype" value="starttofinish">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="field is-horizontal">
                            <div class="field-label is-normal">
                                <label class="label">Managing daily work tasks</label>
                            </div>
                            <div class="field-body">
                                <div class="field">
                                    <div class="control has-icon has-icon-right">
                                        <input type="radio" name="managingtype" value="dailyworktasks">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="field is-horizontal">
                            <div class="field-label is-normal">
                                <label class="label">Tracking time spent on work</label>
                            </div>
                            <div class="field-body">
                                <div class="field">
                                    <div class="control has-icon has-icon-right">
                                        <input type="radio" name="managingtype" value="timespentonwork">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="field is-horizontal">
                            <div class="field-label is-normal">
                                <label class="label">Creating & sending invoices to clients</label>
                            </div>
                            <div class="field-body">
                                <div class="field">
                                    <div class="control has-icon has-icon-right">
                                        <input type="radio" name="managingtype" value="invoicestoclient">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="step-content has-text-centered">
                        <div class="field is-horizontal">
                            <div class="field-label is-normal">
                                <label class="label">Generic Project</label>
                            </div>
                            <div class="field-body">
                                <div class="field">
                                    <div class="control">
                                        <input type="radio" name="projecttype" value="genegic">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="field is-horizontal">
                            <div class="field-label is-normal">
                                <label class="label">Website Design</label>
                            </div>
                            <div class="field-body">
                                <div class="field">
                                    <div class="control has-icon has-icon-right">
                                        <input type="radio" name="projecttype" value="websitedesign">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="field is-horizontal">
                            <div class="field-label is-normal">
                                <label class="label">Video Project</label>
                            </div>
                            <div class="field-body">
                                <div class="field">
                                    <div class="control has-icon has-icon-right">
                                        <input type="radio" name="projecttype" value="video">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="field is-horizontal">
                            <div class="field-label is-normal">
                                <label class="label">Development Project</label>
                            </div>
                            <div class="field-body">
                                <div class="field">
                                    <div class="control has-icon has-icon-right">
                                        <input type="radio" name="projecttype" value="development">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="field is-horizontal">
                            <div class="field-label is-normal">
                                <label class="label">Marketing Project</label>
                            </div>
                            <div class="field-body">
                                <div class="field">
                                    <div class="control has-icon has-icon-right">
                                        <input type="radio" name="projecttype" value="marketing">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="field is-horizontal">
                            <div class="field-label is-normal">
                                <label class="label">Consulting Project</label>
                            </div>
                            <div class="field-body">
                                <div class="field">
                                    <div class="control has-icon has-icon-right">
                                        <input type="radio" name="projecttype" value="consulting">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="field is-horizontal">
                            <div class="field-label is-normal">
                                <label class="label">Editorial Calendar</label>
                            </div>
                            <div class="field-body">
                                <div class="field">
                                    <div class="control has-icon has-icon-right">
                                        <input type="radio" name="projecttype" value="editorial">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="field is-horizontal">
                            <div class="field-label is-normal">
                                <label class="label">Social Media Project</label>
                            </div>
                            <div class="field-body">
                                <div class="field">
                                    <div class="control has-icon has-icon-right">
                                        <input type="radio" name="projecttype" value="socialmedia">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="field is-horizontal">
                            <div class="field-label is-normal">
                                <label class="label">SEO</label>
                            </div>
                            <div class="field-body">
                                <div class="field">
                                    <div class="control has-icon has-icon-right">
                                        <input type="radio" name="projecttype" value="seo">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="step-content has-text-centered">
                        <div class="field is-horizontal">
                            <div class="field-label is-normal">
                                <label class="label">Your full name *</label>
                            </div>
                            <div class="field-body">
                                <div class="field">
                                    <div class="control">
                                        <input type="text" class="form-control" placeholder="Your full name" id="inputFullname" name="fullname" required
                                               autofocus value="">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="field is-horizontal">
                            <div class="field-label is-normal">
                                <label class="label">Your password *</label>
                            </div>
                            <div class="field-body">
                                <div class="field">
                                    <div class="control has-icon has-icon-right">
                                        <input type="password" class="form-control" placeholder="Password" id="inputPassword" name="password" required
                                               value="">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="field is-horizontal">
                            <div class="field-label is-normal">
                                <label class="label">Company name *</label>
                            </div>
                            <div class="field-body">
                                <div class="field">
                                    <div class="control has-icon has-icon-right">
                                        <input type="text" class="form-control" placeholder="Company name" id="inputCompanyname" name="companyname" required
                                               autofocus value="">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="field is-horizontal">
                            <div class="field-label is-normal">
                                <label class="label">Company size *</label>
                            </div>
                            <div class="field-body">
                                <div class="field">
                                    <div class="control has-icon has-icon-right">
                                        <select name="companysize" id="inputCompanysize">
                                            <option value="justme">Just me</option>
                                            <option value="saab">1 - 5</option>
                                            <option value="fiat">6 - 20</option>
                                            <option value="audi">21 - 100</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="field is-horizontal">
                            <div class="field-body">
                                <div class="field">
                                    <div class="control has-icon has-icon-right">
                                        <input type="submit" value="Submit" />
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="steps-actions">
                    <div class="steps-action">
                        <a href="#" data-nav="previous" class="button is-light">Previous</a>
                    </div>
                    <div class="steps-action">
                        <a href="#" data-nav="next" class="button is-light">Next</a>
                    </div>
                </div>

                <div class="result-message">
                    ${message}
                </div>
            </div>


        </form>


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
                /*debugger;
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
                    }
                });*/

            });
        </script>
        <script src='https://wikiki.github.io/node_modules/bulma-extensions/bulma-steps/dist/js/bulma-steps.min.js'></script>
        <script>
            bulmaSteps.attach();
        </script>
    </div>
</t:signup.layout>