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
    <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
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
        .forget-page{
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
            margin-top: 35px;
            padding-top: 25px;
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
        p{
        	color: #a09e9e;
  			font-size: 14px;
        }
    </style>
</head>
<body class="row" style="background: linear-gradient(to left, #636e72, #636e72);">
<div >
    <div class="loader"></div>
    <div class="forget-page" style="width: unset !important;">
        <ul class="form" style="padding: 0;padding-top:0px">
            <div class="formdev" id="return_form">
                <h3 class="title-header">Reset your password</h3>
                <p>Enter your email address below. We'll look for your account and send you a password reset email.</p>
                <div id="devlogin">
                    <form id="email_submit"  method="post" onsubmit="return false;">
                        <input type='text'placeholder="Email" name='username' id="login-username" required>
                        <input type="submit" class="submit-btn" value="RESET PASSWORD" >
                    </form>
                </div>
            </div>
        </ul>
    </div>
</div>
<input type="hidden" id="csrfToken" value="${_csrf.token}"/>
<input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
</body>
<script type="text/javascript">
$(document).ready(function () {
    var token = $('#csrfToken').val();
    var header = $('#csrfHeader').val();
    axios.defaults.headers.common[header] = token;
})

  $("#email_submit").validate({
            rules: {
                username: {
                    required: true,
                    email: true
                }
            },
            messages: {
                username: {
                    required: "*Required",
                    email: "*Invalid Email"
                }
            },
            submitHandler: function (form) {
            	loading();
                var email = $("#login-username").val();
                console.log("Email: "+email)
                axios.post('submit_reset_password_email', {
                    email:email,
                }).then(function (response) {
                	console.log(response);
                	var form="";
                	if(response.data.status){
                		form='<h3 class="title-header">Reset your password</h3>'
                       		+'<p>We just sent a password reset email to <b>'
                       		+ response.data.email+'</b></p>'
                       		+ '<p>When you receive the email, click on the link inside to reset your password. '
                       		+ 'If you don\'t see the email after a few minutes, check your spam folder. </p>'
                       	
                	}else{
                		if(response.data.email=== undefined){
                			form='<h3 class="title-header">Reset your password</h3>'
                           		+ "<p>Sorry your email: <b>"+ email+"</b> doesn't exist with our system yet"
                           		+ '<p>Please kindly sign up a new account or login with google account, thank you. </p>'
                		}else{
                			form='<h3 class="title-header">Reset your password</h3>'
                           		+'<p>Sorry your email problem occur while sent a password reset email to <b>'
                           		+ email+'</b></p>'
                           		+ '<p>Please kindly try again, thank you. </p>'
                		}
                		
                	}
                	finish();
                	document.getElementById("return_form").innerHTML = form;
                }).catch(function (error) {
                   console.log(error);
                   finish()
                });
            }
        });
        
	function loading() {
	    $(".loader").css("display","block");
	    $(".forget-page").css("opacity","0.6");
	    document.getElementsByClassName(".forget-page").disabled = true;
	}
	function finish() {
	    $(".loader").css("display","none");
	    $(".forget-page").css("opacity","1");
	    document.getElementsByClassName(".forget-page").disabled = false;
	}

</script>
</html>