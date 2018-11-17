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
 
  
  
  <!--  Scripts  -->
  <script src="https://momentjs.com/downloads/moment.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>
  <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
  <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
  <spring:url value="/resources/js/booking_history.js" var="booking_history"/>	
  <script src="${booking_history}" type="text/javascript"></script>


  <style type="text/css">

	h5 {
	    line-height: 110%;
	    margin: 2rem 0 1.5rem 0;
	}

	.container {
		    width: 90%;
		}
	nav .brand-logo {
    	left: 15%;
    }	
	 .modal-content .input-field .select-wrapper input.select-dropdown {
	    margin: 0!important;
	}
    .confirm_success{
    	color:white;
    	background-color: green;
    }
    .confirm_error{
    	color:white;
    	background-color: red;
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

	#cancel_confirm_model{
		width: 300px;
	}
	#confirm_booking_request,#bus_info_modal,#driver_info_modal{
	    width: 340px;
	}
	.confirm_booking_request_model{
		width:100%
	}
	.cancel_booking_modal,.view_qrcode{
		width:100%!important;
	}
	.img_rqcode{
		width: 250px;
    	height: 230px;
	}
	.h5_qr{
		line-height: 100%!important;
   		margin:0!important;
	}
	#rqcodeForm{
		width: 320px;
   		height: 425px;
	}
	.collapsible-header {
		display: block!important;
	}
	input.select-dropdown {
	    -webkit-user-select:none;
	    -moz-user-select:none;
	    -ms-user-select:none;
	    -o-user-select:none;
	    user-select:none;
	}
	.bk {
	    margin-bottom: 0px!important;
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
	.history_icon,.confirmed_request{
		color: green;
	}
	.future_icon{
		color:#fbc02d ;
	}
	.rejected{
		color:red;
	}
	tr {
	    border-bottom: 0px!important;
	}

	.confirm_h6{
		padding: 15px!important;
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
  <li class="booking_history1"><a href="booking_history">History</a></li>
  <li><a href="#!">Profile</a></li>
  <li><a onclick="formSubmit()">Logout</a></li>
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
	<div id="get_booking_request" class="row container"></div> 
	<div id="all_booking_history" class="row container"></div> 
</div>
<!-- Confirm Modal -->
<div id="confirm" class="modal">
</div>
 <!-- View Ticket Modal-->
	<div id="rqcodeForm" class="modal modal-fixed-footer ">
		<div class="modal-content center">
			<h6 class="h5_qr center light-blue-text">Bus Ticket QR-Code</h6>
			<div id="rqcode"></div>
		</div>
	  <div class="modal-footer">
		<a class="modal-action modal-close waves-effect waves-green btn-flat ">Close</a>
	  </div>
	</div>
<!-- Ask for confirm booking request -->
<div id="cancel_confirm_model" class="modal">
    <div class="modal-content container center">
	    <h5 class="center">Confirm</h5>
		<p> Do you want to cancel your shuttle bus booking now?
	</div>
	<div class="modal-footer">
		<span id="get_cancel_book_footer"></span>
	    <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Cancel</a>
	 </div>
</div>
<!-- Bus Info -->
<div id="bus_info_modal" class="modal">
    <div class="modal-content center">
	    <h6 class="center light-blue-text">Bus Information</h6>
		<table id="get_bus_info"></table>
	</div>
</div>
<!-- Driver Info -->
<div id="driver_info_modal" class="modal">
    <div class="modal-content center">
	    <h6 class="center light-blue-text">Driver Information</h6>
		<table id="get_driver_info"></table>
	</div>
</div>
<!-- Ask for confirm booking request -->
<div id="confirm_booking_request" class="modal">
    <div class="modal-content center">
	    <h6 class="center light-blue-text confirm_h6">Confirm Booking Information</h6>
		<table id="get_request_detail"></table>
	</div>
	<div class="modal-footer">
		<span id="get_req_book_footer"></span>
	    <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Cancel</a>
	 </div>
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