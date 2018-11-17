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
    <form class="col s12 l6">
      <h5 class="center-align">Sign Up</h5>
      <div class="row">
        <div class="input-field col s12">
          <input id="1" type="text" class="validate">
          <label for="1">Full Name</label>
        </div>
        <div class="input-field col s12">
          <input id="2" type="text" class="validate">
          <label for="2">Username</label>
        </div>
        <div class="input-field col s12">
          <input id="3" type="email" class="validate">
          <label for="3">Email</label>
        </div>
        <div class="input-field col s12">
          <input id="6" type="tel" class="validate">
          <label for="6">Telephone</label>
        </div>
        <div class="input-field col s12">
          <input id="4" type="password" class="validate">
          <label for="4">Password</label>
        </div>
        <div class="input-field col s12">
          <input id="5" type="password" class="validate">
          <label for="5">Confirm Password</label>
        </div>
        <div class="col s12">
         	<span>
		      <input name="group1" type="radio" id="test1" />
		      <label for="test1">Male</label>
		    </span>
		    <span>
		      <input name="group1" type="radio" id="test2" />
		      <label for="test2">Female</label>
		    </span>
        </div>   
        <div class="col s12">
        	<br>
        	<button id="sign_up_btn" class="btn" type="submit">Sign Up</button>
        </div>
        
      </div>
    </form>
  </div>
</body>
</html>