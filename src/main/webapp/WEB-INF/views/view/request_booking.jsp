<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>KIT Admin</title>
  <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
      
  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/css/materialize.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
  <spring:url value="/resources/css/custom_booking.css" var="custom_booking" />
  <link href="${custom_booking}" rel="stylesheet"/>
  
  
  <!--  Scripts  -->
  <script src="https://momentjs.com/downloads/moment.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>
  <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
  <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
  <spring:url value="/resources/js/request_booking.js" var="request_bookingjs"/>	
  <script src="${request_bookingjs}" type="text/javascript"></script>

  <style type="text/css">
	.input-field {
	    margin-top: 0rem;
	}
	h5 {
	    font-size: 1.64rem;
	    line-height: 110%;
	    margin: 2.5rem 0 1.5rem 0;
	}
	#book_now{
	    width: 100%;
   		height: 100%;
	}
	@media only screen and (min-width: 993px){
	.container {
		    width: 60%;
		}
	}
	nav .brand-logo {
    	left: 15%;
    }	
    .btn , .btn:hover, .btn-large:hover, .btn:focus, .btn-large:focus, .btn-floating:focus{
	    background-color: #ee6e73;
	}
	
	@media screen and (min-width: 401px) {
		.booking_history1{
				display:none;
			}
		
	}
	@media screen and (max-width: 400px) {
		.booking_history{
			display:none;
		}
	}
	.select-wrapper span.caret {
	    margin: 15px 0!important;
    }
    .confirm_success{
    	color:white;
    	background-color: green;
    }
    .confirm_error{
    	color:white;
    	background-color: red;
    }

    #source_name-error,#destination_name-error,#departure_time-error,#departure_date-error,#number_of_seat-error{
    	color:red;
    }
    @media screen and (max-width: 350px) {
		.flatpickr-calendar.animate.open{
			top: 12%!important;
			left: 2%!important;
		}
		.flatpickr-calendar.open {
		    z-index: 995!important;
		}
	}
	.rb{
		margin-bottom: 0px!important;
	}
	.select-wrapper .caret {
	    margin: 10px 0!important;
    }
    #confirm {
	    width: 350px;
	    height: 300px;
	}
	#confirm .modal-footer{
	    border-top: 1px solid rgba(0,0,0,0.1)!important;
	    position: absolute;
	    bottom: 0;
	  }  
	  	.error_icon{
		color:red!important;
	}
	.success_icon{
		color:green!important;
	}
  </style>
</head>
<body>
  
<!-- Dropdown Structure -->
<ul id="dropdown1" class="dropdown-content">
  	<li class="booking_history1 hide-on-med-and-up"><a href="booking_history">History</a></li>
 	<li><a href="#!">Profile</a></li>
  <li><a href="#!" onclick="formSubmit()">Logout</a></li>
</ul>
<nav>
  <div class="nav-wrapper">
    <a href="customer_home" class="brand-logo">Logo</a>
      <ul class="right">
      <li><a class="dropdown-button" href="#!" data-target='dropdown1'><span id="fullname"></span><i class="material-icons right">arrow_drop_down</i></a></li>
    </ul>
  </div>
</nav>
<div class="container">
<form id="form_booking_request" class="col s12">
		<h5 class="center">Bus Booking Request</h5>
		<div class="row rb">
			<div class="input-field col s12 m6">
			    <select id="source_name" name="source_name" required></select>
			    <span id="source_name_error" class="red-text" hidden>*Required</span>
			  </div>
			    <div class="input-field col s12 m6">
			    <select id="destination_name" name="destination_name" required>
			    	<option disabled selected>Destination Location</option>
			    </select>
			    <span id="destination_name_error" class="red-text" hidden>*Required</span>
			  </div>
		</div>
		<div class="row rb">
			  <div class="input-field col s12 m6">
			    	<div class="input-field s6 flatpickr">
						<input type="text" placeholder="Select Departure Date" id="departure_date" data-input class="input flatpickr-input active" name="departure_date" required> 					
					</div>
			  </div>
			  <div class="input-field col s12 m6">
			    	<div class="input-field s6 flatpickr">
						<input type="text" placeholder="Select Departure Time" id="departure_time" data-input class="input flatpickr-input active" name="departure_time" required> 					
					</div>
			  </div>
			    
		  </div>
		  <div class="row rb">
			   <div class="input-field col s12">
			       <input id="number_of_seat" type="number" class="validate" name="number_of_seat" >
			       <label for="last_name">Number of Ticket</label>
			  </div> 
			  <div class="input-field col s12">
			    <button id="book_now" class="btn" name="action">Request Now</button>
			  </div>
		</div>
	</form>
</div>

	<!-- Confirm Modal -->
	<div id="confirm" class="modal">
	</div>
	<form action="logout" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
  </body>
  </body>
  <script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
</script>
</html>