<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>

<!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
  
  
  <!--  Scripts  -->
  <script src="https://momentjs.com/downloads/moment.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script> 
  <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
  <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
  <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
  <style type="text/css">
  #sign_up_btn{
 	width:100%; 
  }
  input[type=text]{
  	 margin: 0 0 10px 0;
  }
  </style>
</head>
<body>
<br>
<div class="row container">
    <div> class="col s12 l6">
      <h5 class="center-align">Sign Up</h5>
      <div class="row">
        <div class="input-field col s12">
          <input  type="text" id="full_name" class="validate">
          <label for="full_name">Full Name</label>
        </div>
        <div class="input-field col s12">
          <input type="text" id="username" class="validate">
          <label for="username">Username</label>
        </div>
        <div class="input-field col s12">
          <input type="email" id="email" class="validate">
          <label for="email">Email</label>
        </div>
        <div class="input-field col s12">
          <input  type="tel" id="phone" class="validate">
          <label for="phone">Telephone</label>
        </div>
        <div class="input-field col s12">
          <input  type="password" id="password" class="validate">
          <label for="password">Password</label>
        </div>
        <div class="input-field col s12">
          <input  type="password" id="confirm" class="validate">
          <label for="confirm">Confirm Password</label>
        </div>
        <div class="col s12">
         	<span>
		      <input name="gender" type="radio" value="male"/>
		      <label for="gender">Male</label>
		    </span>
		    <span>
		      <input name="gender" type="radio" id="test2" value="female"/>
		      <label for="gender">Female</label>
		    </span>
        </div>   
        <div class="col s12">
        	<br>
        	<button id="sign_up_btn" class="btn">Sign Up</button>
        </div>
        
      </div>
    </div>
  </div>
  <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
  <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
 
</body>
 <script type="text/javascript">
  	var token = $('#csrfToken').val();
	var header = $('#csrfHeader').val();

  	
  	axios.defaults.headers.common[header] = token;
  	$('#sign_up_btn').click(function(){
  		var full_name = $('#full_name').val();
  	var username = $('#username').val();
  	var email = $('#email').val()
  	var phone = $('#phone').val()
  	var password = $('#password').val();
  	var confirm = $('#confirm').val()
  	console.log(username)
  	var gender = $("input[name='gender']:checked").val();


  		axios.post('signup', {
            email: email,
            password: password,
            username:username,
        	gender:gender,
        	name:full_name
          })
          .then(function (response) {
            if(response.data.status){
            	window.location.replace("login")
            }
            
          })
          .catch(function (error) {
            console.log(error);
          });
          
  	})
  	




  </script>
</html>