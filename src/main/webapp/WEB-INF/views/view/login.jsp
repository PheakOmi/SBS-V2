<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id" content="934878702971-vhv1inbu6cg8m4cggh4itr8mfuuidoo9.apps.googleusercontent.com">
    <title>Shuttle Bus Management</title>

    <script src="https://apis.google.com/js/platform.js" async defer></script>

    <spring:url value="/resources/Bootstrap/css/style.css" var="loginStyle"/>
    <link rel="stylesheet"  type="text/css" href="${loginStyle}">
    <!--<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>-->
    <script type="text/javascript" src="/resources/Bootstrap/js/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>


    <!-- Bootstrap Core JavaScript -->
    <spring:url value="/resources/Bootstrap/js/bootstrap.min.js" var="JSCORE"/>
    <script src="${JSCORE}"></script>



    <spring:url value="/resources/Bootstrap/css/sweetalert.css" var="alertStyle"/>
    <spring:url value="/resources/Bootstrap/js/sweetalert.min.js" var="alertJS"/>
    <spring:url value="/resources/css/radio.css" var="Radio"/>
    <link rel="stylesheet" type="text/css" href="${Radio}">
    <style>
        .form input,.form{
            border-radius: 5px;
        }
        .login-page{
            padding: 4% 0 0;
        }
        .form{
            margin-bottom: unset;
        }
        form{
            text-align: left;
        }
        label.error{
            font-size: 12px;
            color:#e74c3c;
        }
        input[type="submit"]{
            cursor: pointer;
        }
        .switch{
            border-spacing: 0;
            width: 100%;
            text-align: center;
        }
        .switch td{
            padding: 20px;
            font-size: 17px;
            font-weight: bold;
            cursor: pointer;
            color:#7f8c8d;
        }
        .formdev{
            padding: 45px;
            padding-top: 0px;
        }
        .title-header{
            color:#636e72;
            font-size: 31px;
            margin-top: 35px;
        }
        .active-btn{
            background-color: #95a5a6;
            color:white !important;
            -webkit-transition: background-color 0.6s;
            transition: background-color 0.6s;
        }
        .active-btn:hover{
            background-color: #bdc3c7;
        }
        .forgort{
            font-size: 14px;
            color:#E71D36;
            cursor: pointer;
            width: 80%;
            -webkit-transition: color 0.4s;
        }
        .forgort:hover{
            color: #DE6449;
        }
        .submit-btn{
            background-color: #636e72 !important;
            color:white;
            font-size: 14px;
            font-weight: bold;
            -webkit-transition: background-color 0.4s;
        }
        .submit-btn:hover{
            background-color: #95a5a6 !important;
        }
        .abcRioButton{
            border-radius: 5px !important;
        }
        .googleSign{
            margin-right: 15%;
            margin-left: 15%;
        }
        .abcRioButtonBlue{
            width: 100% !important;
        }
        .loader {
            display: none;
            border: 8px solid #f3f3f3;
            border-radius: 50%;
            border-top: 8px solid #7f8c8d;
            width: 60px;
            height: 60px;
            top: calc(50% - 30px);
            z-index: 2;
            position: absolute;
            right: calc(50% - 30px);
            -webkit-animation: spin 1.5s linear infinite; /* Safari */
            animation: spin 1.5s linear infinite;
        }
        /* Safari */
        @-webkit-keyframes spin {
            0% { -webkit-transform: rotate(0deg); }
            100% { -webkit-transform: rotate(360deg); }
        }
        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }
        .loading-bg{
            opacity: 0.6;
        }
        .login-error{
            color: #ea534a;
            font-size: 17px;
            display: none;
        }
        @media only screen and (max-width: 992px) {
            .formdev{
                padding: 15px;
            }
            .title-header{
                margin-top: unset;
            }
        }
    </style>
</head>
<body class="row" style="background: linear-gradient(to left, #636e72, #636e72);">
<div >
    <div class="loader"></div>
    <div class="login-page" style="width: unset !important;">
        <ul class="form" style="padding: 0;padding-top:0px">
            <table class="switch">
                <tr>
                    <td id="signin-btn" style="border-top-left-radius: 4px">SIGN IN</td>
                    <td id="signup-btn" style="border-top-right-radius: 4px">SIGN UP</td>
                </tr>
            </table>
            <div class="formdev">
                <h1 class="title-header">vKIRIROM SHUTTLE BUS</h1>
                <form id="signupform" action="<c:url value='signup' />" onsubmit="return false" method="post">
                    <label for="email"></label>
                    <input type='text'placeholder="Email" name='email' id="email">
                    <label id="email-error" class="error" for="email"></label>
                    <input type="password" placeholder="Password" name="pass"  id="pass" autocomplete="new-password">
                    <input type='text'placeholder="Username" name='username' id="username">
                    <input type='text'placeholder="Phone" name="phone" id="phone">
                    <input type="submit" class="submit-btn" value="SIGNUP" >
                </form>
                <div id="devlogin">
                    <p class="login-error" id="login-error"></p>
                    <form id="loginform" action="<c:url value='login' />" method="post">
                        <input type='text'placeholder="Email" name='username' id="login-username" required>
                        <input type="password" placeholder="Password" name="password" id="login-password"
                               autocomplete="new-password" required/>
                        <input type="submit" value="LOGIN" class="submit-btn">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </form>
                    <table style="width: 100%;text-align: center !important;">
                        <tr>
                            <td>
                                <div class="g-signin2 googleSign" data-onsuccess="onSignIn" data-theme="dark"></div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a class="forgort" href="forget_password">Forgot Password?</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </ul>
    </div>
</div>
<input type="hidden" id="csrfToken" value="${_csrf.token}"/>
<input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
</body>
<script type="text/javascript">
    var isIE = /*@cc_on!@*/false || !!document.documentMode;
    if(isIE||(!isIE && !!window.StyleMedia)){
        window.location.href = "/sbs/ie"
    }
    $("#signupform").hide();
    $("#signup-btn").addClass('active-btn');
    $("#signup-btn").click(function () {
        $("#signin-btn").addClass('active-btn');
        $("#signup-btn").removeClass('active-btn');
        $("#signupform").slideDown(200)
        $("#devlogin").hide();
    })
    $("#signin-btn").click(function () {
        $("#signup-btn").addClass("active-btn");
        $("#signin-btn").removeClass("active-btn");
        $("#devlogin").slideDown(200)
        $("#signupform").hide()
    })
    function loading() {
        $(".loader").css("display","block");
        $(".login-page").css("opacity","0.6");
        document.getElementsByClassName(".login-page").disabled = true;
    }
    function finish() {
        $(".loader").css("display","none");
        $(".login-page").css("opacity","1");
        document.getElementsByClassName(".login-page").disabled = false;
    }
    function isExistUser(email) {
        return axios.get('isexist', {
            params: {
                email: email
            }
        }).then(function (response) {
            return response.data
        })
    }
    $(document).ready(function () {
        var token = $('#csrfToken').val();
        var header = $('#csrfHeader').val();
        axios.defaults.headers.common[header] = token;
        var url = window.location.href;
        if(url.includes("login?error")){
            $("#login-error").css("display","inline");
            document.getElementById("login-error").innerHTML = "email or password is incorrect";
        }


    })
    $.validator.addMethod("notKIT", function(value, element) {
        console.log(value)
        return !value.includes("@kit.edu.kh")
    }, "KIT email not allow to sign up");
    $(function() {
        $("#signupform").validate({
            rules: {
                email: {
                    required: true,
                    email: true,
                    notKIT:true
                },
                pass: {
                    required: true,
                    minlength: 8
                },
                username: {
                    required: true,
                    minlength: 3
                },
                phone: {
                    minlength: 9,
                    number:true
                }
            },
            messages: {
                email: {
                    required: "*required",
                    email: "invalid email"
                },
                username: {
                    required: "*required",
                    minlength: "username must be at least 3 characters long"
                },
                pass: {
                    required: "*required",
                    minlength: "password must be at least 8 characters long"
                },
                phone: {
                    number: "number only",
                    minlength: "invalid phone number"
                },
            },
            submitHandler: function (form) {
                loading()
                var username = $('#username').val();
                var email = $('#email').val()
                var phone = $('#phone').val()
                var password = $('#pass').val();
                if(phone == ""){
                    phone = "0"
                }
                isExistUser(email)
                    .then(function (value) {
                        if(value.status){
                            finish()
                            $("#email-error").css("display","block");
                            document.getElementById("email-error").innerHTML = "email is existed"
                        }
                        else {
                            axios.post('signup', {
                                email: email,
                                password: password,
                                username:username,
                                phone:phone
                            })
                                .then(function (response) {
                                    if(response.data.status){
                                        console.log("signup")
                                        $('#login-username').val(email)
                                        $('#login-password').val(password)
                                        $('#loginform').submit()

                                    }
                                })
                                .catch(function (error) {
                                    console.log(error);
                                });
                        }
                    })
                return false;
            }
        });
        $("#loginform").validate({
            rules: {
                username: {
                    required: true,
                    email: true
                },
                password: {
                    required: true,
                    minlength: 8
                }
            },
            messages: {
                username: {
                    required: "*required",
                    email: "invalid email"
                },
                password: {
                    required: "*required",
                    minlength: "password must be at least 8 characters long"
                },
            },
            submitHandler: function (form) {
                var username = $("#login-username").val();
                isExistUser(username).then(function (data) {
                    console.log(data)
                    if(data.status){
                        if(data.role=="ROLE_DRIVER"){
                            $("#login-error").css("display","inline");
                            document.getElementById("login-error").innerHTML = "driver can not login";
                            return false
                        }
                        else{
                            form.submit();
                        }
                    }
                    else{
                        $("#login-error").css("display","inline");
                        document.getElementById("login-error").innerHTML = "email or password is incorrect"
                        return false;
                    }
                }
            )
            }
        });
    });
    function googleSignin(data){
        axios({
            method: 'post',
            url: 'login',
            data: data
        })
            .then(function (response) {
                finish()
                console.log(response)
                var url = response.request.responseURL;
                if(!url.includes("login")){
                    window.location.replace(url);
                }
            })
    }
    function onSignIn(googleUser) {
        console.log("ll")
        var profile = googleUser.getBasicProfile();
        var auth2 = gapi.auth2.getAuthInstance();
        console.log(document.getElementsByClassName("abcRioButtonContents"))
        auth2.disconnect();
        loading()
        axios.post('check_signup', {
            email: profile.getEmail(),
            password: profile.getId(),
            username:profile.getName(),
            name:profile.getGivenName(),
            profile:profile.getImageUrl()
        })
            .then(function (response) {
                if(response.data.status){
                    data = 'username='+profile.getEmail()+"--google"+ '&password='+profile.getId()
                }
                googleSignin(data);
            })
    };
</script>
</html>