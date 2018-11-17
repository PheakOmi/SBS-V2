$(document).ready(function() {
	$('.dropdown-button').dropdown();
	  // ======== User Information ==========
		 $.ajax({
				async: false,
				cache: false,
				type: "GET",
				url: "user_info",
				contentType: "application/json",
				timeout: 100000,
				success: function(data) {
					document.getElementById('fullname').innerHTML=data.username;
				},
				error: function(e) {
					console.log("ERROR: ", e);
				},
				done: function(e) {
					console.log("DONE");
				}
		});
	
	  
	// ======== History Booking ==========
		 $.ajax({
				async: false,
				cache: false,
				type: "GET",
				url: "customer_booking_history",
				contentType: "application/json",
				timeout: 100000,
				success: function(data) {
					
					var bh_form='';
					if(data.length>0){
						bh_form+='<h5 class="center">Booking History</h5>'
							+'<ul id="bh_collapsible" class="collapsible" data-collapsible="accordion">';
						for(i=0;i<data.length;i++){
							var dept_date_time=convert_date(data[i].dept_date)+' '+data[i].dept_time;
							console.log("dept_date_time: "+dept_date_time)
							if(data[i].notification=="Cancelled"){
									bh_form+='<li><div class="collapsible-header"><i class="material-icons rejected">cancel</i>&nbsp&nbsp'
										+ '<span class="rejected">'+convert_date(data[i].dept_date)+',  '+convert_time(data[i].dept_time)
										+';&nbsp&nbsp&nbsp&nbsp&nbsp'+data[i].scource
										+' to '+data[i].destination+'</span>'
										+'<span class="right"><i class="material-icons">keyboard_arrow_down</i></span></div>'	
									+'<div class="collapsible-body">'
									+'<table><col width="50%"><col width="50%"><tr><th>Booking Code</th><td><b>:</b>&nbsp&nbsp'+data[i].booking_code+'</td></tr>'
							        +'<tr><th>Departure Date</th><td><b>:</b>&nbsp&nbsp'+convert_date(data[i].dept_date)+' </td></tr>'  
							        +'<tr><th>Departure Time</th><td><b>:</b>&nbsp&nbsp'+convert_time(data[i].dept_time)+'</td></tr>'
							        +'<tr><th>Source</th><td><b>:</b>&nbsp&nbsp'+data[i].scource+'</td></tr>'
							        +'<tr><th>Pickup Location</th><td><b>:</b>&nbsp&nbsp'+data[i].pick_up+'</td></tr>'
							        +'<tr><th>Destination</th><td><b>:</b>&nbsp&nbsp'+data[i].destination +'</td></tr>'
							        +'<tr><th>Drop-off Location</th><td><b>:</b>&nbsp&nbsp'+data[i].drop_off+'</td></tr>'
							        +'<tr><th>Number of Ticket</th><td><b>:</b>&nbsp&nbsp'+data[i].number_of_ticket +'</td></tr>'
							        +'<tr><th>Bus Information</th><td><b>:</b>&nbsp&nbsp<a href="#!" class="bus_info" data="'+data[i].id +'">'+data[i].bus_model +'</a></td></tr>'
//							        +'<tr><th>Bus Model</th><td><b>:</b>&nbsp&nbsp'+data[i].bus_model +'</td></tr>'
//							        +'<tr><th>Bus Plate Number</th><td><b>:</b>&nbsp&nbsp'+data[i].plate_number +'</td></tr>'
							        if(data[i].diver_name=="no_driver"){
									    bh_form+='<tr><th>Driver\'s Name</th><td><b>:</b>&nbsp&nbsp To be decided</td></tr>'
									 }else{
										 bh_form+='<tr><th>Driver Information</th><td><b>:</b>&nbsp&nbsp<a href="#!" class="driver_info" data="'+data[i].id +'">'+data[i].diver_name +'</a></td></tr>'
									 } 
							}else{
								if(compared_tomorrow(dept_date_time)){
					
										bh_form+='<li><div class="collapsible-header"><i class="material-icons future_icon">schedule</i>&nbsp&nbsp'
											+'<span class="future_icon">'+ convert_date(data[i].dept_date)+',  '+convert_time(data[i].dept_time)
											+';&nbsp&nbsp&nbsp&nbsp&nbsp'+data[i].scource
											+' to '+data[i].destination+'</span>'
											+'<span class="right"><i class="material-icons">keyboard_arrow_down</i></span></div>'	
											+'<div class="collapsible-body">'
											+'<table><col width="50%"><col width="50%"><tr><th>Booking Code</th><td><b>:</b>&nbsp&nbsp'+data[i].booking_code+'</td></tr>'
									        +'<tr><th>Departure Date</th><td><b>:</b>&nbsp&nbsp'+convert_date(data[i].dept_date)+' </td></tr>'  
									        +'<tr><th>Departure Time</th><td><b>:</b>&nbsp&nbsp'+convert_time(data[i].dept_time)+'</td></tr>'
									        +'<tr><th>Source</th><td><b>:</b>&nbsp&nbsp'+data[i].scource+'</td></tr>'
									        +'<tr><th>Pickup Location</th><td><b>:</b>&nbsp&nbsp'+data[i].pick_up+'</td></tr>'
									        +'<tr><th>Destination</th><td><b>:</b>&nbsp&nbsp'+data[i].destination +'</td></tr>'
									        +'<tr><th>Drop-off Location</th><td><b>:</b>&nbsp&nbsp'+data[i].drop_off+'</td></tr>'
									        +'<tr><th>Number of Ticket</th><td><b>:</b>&nbsp&nbsp'+data[i].number_of_ticket +'</td></tr>'
									        +'<tr><th>Bus Information</th><td><b>:</b>&nbsp&nbsp<a href="#!" class="bus_info" data="'+data[i].id +'">'+data[i].bus_model +'</a></td></tr>'
//									        +'<tr><th>Bus Model</th><td><b>:</b>&nbsp&nbsp'+data[i].bus_model +'</td></tr>'
//									        +'<tr><th>Bus Plate Number</th><td><b>:</b>&nbsp&nbsp'+data[i].plate_number +'</td></tr>'
								        if(data[i].diver_name=="no_driver"){
										    bh_form+='<tr><th>Driver\'s Name</th><td><b>:</b>&nbsp&nbsp To be decided</td></tr>'
										 }else{
											 bh_form+='<tr><th>Driver Information</th><td><b>:</b>&nbsp&nbsp<a href="#!" class="driver_info" data="'+data[i].id +'">'+data[i].diver_name +'</a></td></tr>'
										 } 
									bh_form+='<tr><td><a href="#!" class="btn red lighten-3 cancel_booking_modal" booking="'+data[i].id +'">Cancel</a></td>'
											+'<td><a href="#!" class="view_qrcode btn green lighten-1" value="' 
											+ data[i].id  + '">Ticket QR-Code</a></td></tr>'
								}else{
									bh_form+='<li><div class="collapsible-header"><i class="material-icons history_icon">history</i>&nbsp&nbsp'
										+ '<span class="history_icon">'+convert_date(data[i].dept_date)+',  '+convert_time(data[i].dept_time)
										+';&nbsp&nbsp&nbsp&nbsp&nbsp'+data[i].scource
										+' to '+data[i].destination+'</span>'
										+'<span class="right"><i class="material-icons">keyboard_arrow_down</i></span></div>'
								        +'<div class="collapsible-body">'
								        +'<table><col width="50%"><col width="50%"><tr><th>Booking Code</th><td><b>:</b>&nbsp&nbsp'+data[i].booking_code+'</td></tr>'
								        +'<tr><th>Departure Date</th><td><b>:</b>&nbsp&nbsp'+convert_date(data[i].dept_date)+' </td></tr>'  
								        +'<tr><th>Departure Time</th><td><b>:</b>&nbsp&nbsp'+convert_time(data[i].dept_time)+'</td></tr>'
								        +'<tr><th>Source</th><td><b>:</b>&nbsp&nbsp'+data[i].scource+'</td></tr>'
								        +'<tr><th>Pickup Location</th><td><b>:</b>&nbsp&nbsp'+data[i].pick_up+'</td></tr>'
								        +'<tr><th>Destination</th><td><b>:</b>&nbsp&nbsp'+data[i].destination +'</td></tr>'
								        +'<tr><th>Drop-off Location</th><td><b>:</b>&nbsp&nbsp'+data[i].drop_off+'</td></tr>'
								        +'<tr><th>Number of Ticket</th><td><b>:</b>&nbsp&nbsp'+data[i].number_of_ticket +'</td></tr>'
								        +'<tr><th>Bus Information</th><td><b>:</b>&nbsp&nbsp<a href="#!" class="bus_info" data="'+data[i].id +'">'+data[i].bus_model +'</a></td></tr>'
//								        +'<tr><th>Bus Model</th><td><b>:</b>&nbsp&nbsp'+data[i].bus_model +'</td></tr>'
//								        +'<tr><th>Bus Plate Number</th><td><b>:</b>&nbsp&nbsp'+data[i].plate_number +'</td></tr>'
								        if(data[i].diver_name=="no_driver"){
										    bh_form+='<tr><th>Driver\'s Name</th><td><b>:</b>&nbsp&nbsp To be decided</td></tr>'
										 }else{
											 bh_form+='<tr><th>Driver Information</th><td><b>:</b>&nbsp&nbsp<a href="#!" class="driver_info" data="'+data[i].id +'">'+data[i].diver_name +'</a></td></tr>'
										 }  
								}
							}   
							bh_form+='</table></div></li>';
						}
					}
					bh_form+='</ul>'
					document.getElementById('all_booking_history').innerHTML=bh_form;
					
				},
				error: function(e) {
					console.log("ERROR: ", e);
				},
				done: function(e) {
					console.log("DONE");
				}
		});
		 $(".driver_info").click(function(){
			 console.log("KK");
			 var id=$(this).attr('data');
			 console.log(id);
			 $.ajax({
					async: false,
					cache: false,
					type: "GET",
					url: "get_sch_driver_info",
					data :{'id':id},
					timeout: 100000,
					success: function(data) {
						console.log(data);
						var data='<tr><th>Driver\'s Name</th><td><b>:</b>  &nbsp&nbsp '+data[0].name+'</td></tr>'
						  +'<tr><th>Phone Number</th><td><b>:</b>  &nbsp&nbsp '+ data[0].phone_number+'</td></tr>'
						  +'<tr><th>Email</th><td><b>:</b> &nbsp&nbsp '+data[0].email +'</td></tr>'
						  document.getElementById('get_driver_info').innerHTML=data;
						$('#driver_info_modal').modal();
						$('#driver_info_modal').modal('open');
						
					},
					error: function(e) {
						console.log("ERROR: ", e);
					},
					done: function(e) {
						console.log("DONE");
					}
				});
		 });
		 $(".bus_info").click(function(){
			 console.log("KK");
			 var id=$(this).attr('data');
			 console.log(id);
			 $.ajax({
					async: false,
					cache: false,
					type: "GET",
					url: "get_sch_bus_info",
					data :{'id':id},
					timeout: 100000,
					success: function(data) {
						console.log(data);
						var data='<tr><th>Bus Model</th><td><b>:</b>  &nbsp&nbsp '+data[0].bus_model+'</td></tr>'
						  +'<tr><th>Bus Plate Number</th><td><b>:</b>  &nbsp&nbsp '+ data[0].plate_number+'</td></tr>'
						  +'<tr><th>Total Seats</th><td><b>:</b> &nbsp&nbsp '+data[0].number_of_seat +'</td></tr>'
						  document.getElementById('get_bus_info').innerHTML=data;
						$('#bus_info_modal').modal();
						$('#bus_info_modal').modal('open');
						
					},
					error: function(e) {
						console.log("ERROR: ", e);
					},
					done: function(e) {
						console.log("DONE");
					}
				});
		 });
		// View QR-Code 
		 $('.view_qrcode').click(function() {
			$('#rqcodeForm').modal();
			$('#rqcodeForm').modal('open');
			var qrcode = $(this).attr('value');
			console.log(qrcode);
			$.ajax({
				async: false,
				cache: false,
				type: "GET",
				url: "get_qrcode",
				data :{'id':qrcode},
				timeout: 100000,
				success: function(data) {
					console.log(data);
					
					var qrcode_form = '<br><label>Route: ' 
					+ data[0].scource 
					+ ' to ' + ' ' 
					+ data[0].destination
					+ '</label><br>'
					+ '<img class="img_rqcode center" src="data:image/png;base64,' 
					+ data[0].qrcode + '"/><br><label>Departure Date: ' 
					+ data[0].dept_date+'; Time: '+ data[0].dept_time+ '</label>' 
					document.getElementById('rqcode').innerHTML = qrcode_form;
					
				},
				error: function(e) {
					console.log("ERROR: ", e);
				},
				done: function(e) {
					console.log("DONE");
				}
			});
			

		})
		//Cancel Booking
		$('.cancel_booking_modal').click(function(){
				var id=$(this).attr("booking") ;
				console.log(id);
				$('#cancel_confirm_model').modal();
				$('#cancel_confirm_model').modal('open');
				var form1='<button id="confirm_cancel_booking_btn" booking="'+id
							+'" class="modal-action waves-effect waves-green btn-flat">Confirm</button>';
				document.getElementById('get_cancel_book_footer').innerHTML=form1;
				console.log(form1)
				$('#confirm_cancel_booking_btn').click(function(){
					var id1=$(this).attr("booking") ;
					console.log(id1);
					$.ajax({
						async: false,
						cache: false,
						type: "GET",
						url: "cancel_booking_ticket",
						data :{'id':id1},
						timeout: 100000,
						success: function(data) {
							
							var text;
		   					if(data=="success"){
		   						text='<div class="modal-content center-align">'
				   					+'<i class="material-icons large success_icon">done</i>'
				   					+'<h6><b>Congrats!</b></h6>'
				   					+'<p>You have just cancelled successfully.</p></div>'
				   					+'<div class="modal-footer">'
							   	    +'<a href="#!" class="modal-action modal-close btn green lighten-1">OK</a>'
							   	    +'</div>';
				   				
		   					}else if(data=="no_record"){
		   						text='<div class="modal-content center-align">'
				   					+'<i class="material-icons large error_icon">highlight_off</i>'
				   					+'<h6><b>Sorry, No record available!</b></h6>'
				   					+'<p>Please Try Again!</p></div>'
				   					+'<div class="modal-footer">'
							   	    +'<a href="#!" class="modal-action modal-close btn green lighten-1">OK</a>'
							   	    +'</div>';
		   					}else{
		   						text='<div class="modal-content center-align">'
				   					+'<i class="material-icons large error_icon">highlight_off</i>'
				   					+'<h6><b>Sorry, Connection Error!</b></h6>'
				   					+'<p>Please refresh the page and try again!</p></div>'
				   					+'<div class="modal-footer">'
							   	    +'<a href="#!" class="modal-action modal-close btn green lighten-1">OK</a>'
							   	    +'</div>';
		   					}
		   					document.getElementById('confirm').innerHTML=text;
							$('#cancel_confirm_model').modal('close');
							
							
							$('#confirm').modal({
								onCloseEnd: function() {
										window.location.replace("booking_history");
								} 
							});

							$('#confirm').modal('open');
							
						},
						error: function(e) {
							console.log("ERROR: ", e);
						},
						done: function(e) {
							console.log("DONE");
						}
				});

				})
			})
	
	
		 
		 
		// ======== Request Booking Form==========
		 $.ajax({
				async: false,
				cache: false,
				type: "GET",
				url: "get_request_booking",
				contentType: "application/json",
				timeout: 100000,
				success: function(data) {
					console.log("get_request_booking");
					console.log(data);
					var mobile='<h5 class="center">Booking Request</h5>'
								+'<ul id="rb_collapsible" class="collapsible" data-collapsible="accordion">';
					var bh_form='';
					if(data.length>0){					
						for(i=0;i<data.length;i++){
							if(data[i].time_status=='future'){
								if(data[i].status=='Confirmed'){
									
									mobile+='<li><div class="collapsible-header"><i class="material-icons confirmed_request">beenhere</i>&nbsp&nbsp'
										+'<span class="confirmed_request">'+convert_date(data[i].dept_date)+'; '+ convert_time(data[i].dept_time)
										+';&nbsp&nbsp&nbsp&nbsp&nbsp'+data[i].scource
											+' to '+data[i].destination+';&nbsp&nbsp '+data[i].status+'</span>'
										+'<span class="right"><i class="material-icons">keyboard_arrow_down</i></span></div>'
										+'<div class="collapsible-body"><table>'
								        +'<tr><th>Departure Date</th><td><b>:</b>  &nbsp&nbsp '+convert_date(data[i].dept_date)+'</td></tr>'
								        +'<tr><th>Time Allowance</th><td><b>:</b>  &nbsp&nbsp '+ convert_time(data[i].dept_time)+'</td></tr>'
								        +'<tr><th>Source</th><td><b>:</b> &nbsp&nbsp '+data[i].scource +'</td></tr>'
								        +'<tr><th>Destination</th><td><b>:</b> &nbsp&nbsp '+ data[i].destination +'</td></tr>'
								        +'<tr><th>Number of Ticket</th><td><b>:</b> &nbsp&nbsp '+data[i].number_of_ticket +'</td></tr>'
								        +'<tr><th>Status</th><td><b>:</b> &nbsp&nbsp '+data[i].status +'</td></tr>'
								        +'<tr><th colspan="2"><a href="#!" class="btn confirm_booking_request_model green lighten-1" request="'+data[i].id +'">Book Now</a></th></tr>'
										+'</table></div></li>'
									
								}else if(data[i].status=='Pending'){
									
									mobile+='<li><div class="collapsible-header"><i class="material-icons future_icon">schedule</i>&nbsp&nbsp'
										+'<span class="future_icon">'+convert_date(data[i].dept_date)+'; '+ convert_time(data[i].dept_time)
										+';&nbsp&nbsp&nbsp&nbsp&nbsp'+data[i].scource
										+' to '+data[i].destination+';&nbsp&nbsp '+data[i].status+'</span>'
										+'<span class="right"><i class="material-icons">keyboard_arrow_down</i></span></div>'
										+'<div class="collapsible-body"><table>'
								        +'<tr><th>Departure Date</th><td><b>:</b>  &nbsp&nbsp '+convert_date(data[i].dept_date)+'</td></tr>'
								        +'<tr><th>Time Allowance</th><td><b>:</b>  &nbsp&nbsp '+ convert_time(data[i].dept_time)+'</td></tr>'
								        +'<tr><th>Source</th><td><b>:</b> &nbsp&nbsp '+data[i].scource +'</td></tr>'
								        +'<tr><th>Destination</th><td><b>:</b> &nbsp&nbsp '+ data[i].destination +'</td></tr>'
								        +'<tr><th>Number of Ticket</th><td><b>:</b> &nbsp&nbsp '+data[i].number_of_ticket +'</td></tr>'
								        +'<tr><th>Status</th><td><b>:</b> &nbsp&nbsp '+data[i].status +'</td></tr>'
										+'</table></div></li>'
								}else{
									
									mobile+='<li><div class="collapsible-header"><i class="material-icons rejected">cancel</i>&nbsp&nbsp'
										+'<span class="rejected">'+convert_date(data[i].dept_date)+'; '+ convert_time(data[i].dept_time)
										+';&nbsp&nbsp&nbsp&nbsp&nbsp'+data[i].scource
										+' to '+data[i].destination+';&nbsp&nbsp '+data[i].status+'</span>'
										+'<span class="right"><i class="material-icons">keyboard_arrow_down</i></span></div>'
										+'<div class="collapsible-body"><table>'
								        +'<tr><th>Departure Date</th><td><b>:</b>  &nbsp&nbsp '+convert_date(data[i].dept_date)+'</td></tr>'
								        +'<tr><th>Time Allowance</th><td><b>:</b>  &nbsp&nbsp '+ convert_time(data[i].dept_time)+'</td></tr>'
								        +'<tr><th>Source</th><td><b>:</b> &nbsp&nbsp '+data[i].scource +'</td></tr>'
								        +'<tr><th>Destination</th><td><b>:</b> &nbsp&nbsp '+ data[i].destination +'</td></tr>'
								        +'<tr><th>Number of Ticket</th><td><b>:</b> &nbsp&nbsp '+data[i].number_of_ticket +'</td></tr>'
								        +'<tr><th>Status</th><td><b>:</b> &nbsp&nbsp '+data[i].status +'</td></tr>'
										+'</table></div></li>'
								}
							}else if(data[i].time_status="past"){
								mobile+='<li><div class="collapsible-header"><i class="material-icons rejected">history</i>&nbsp&nbsp'
									+'<span class="rejected">'+convert_date(data[i].dept_date)+'; '+ convert_time(data[i].dept_time)
									+';&nbsp&nbsp&nbsp&nbsp&nbsp'+data[i].scource
									+' to '+data[i].destination+';&nbsp&nbsp '+data[i].status+'</span>'
									+'<span class="right"><i class="material-icons">keyboard_arrow_down</i></span></div>'
									+'<div class="collapsible-body"><table>'
							        +'<tr><th>Departure Date</th><td><b>:</b>  &nbsp&nbsp '+convert_date(data[i].dept_date)+'</td></tr>'
							        +'<tr><th>Time Allowance</th><td><b>:</b>  &nbsp&nbsp '+ convert_time(data[i].dept_time)+'</td></tr>'
							        +'<tr><th>Source</th><td><b>:</b> &nbsp&nbsp '+data[i].scource +'</td></tr>'
							        +'<tr><th>Destination</th><td><b>:</b> &nbsp&nbsp '+ data[i].destination +'</td></tr>'
							        +'<tr><th>Number of Ticket</th><td><b>:</b> &nbsp&nbsp '+data[i].number_of_ticket +'</td></tr>'
							        +'<tr><th>Status</th><td><b>:</b> &nbsp&nbsp '+data[i].status +'</td></tr>'
									+'</table></div></li>'
							}

						}
						mobile+='</ul>';
					}else{
						mobile='';
					}
					document.getElementById('get_booking_request').innerHTML=mobile;
//					$('.collapsible').collapsible();

				},
				error: function(e) {
					console.log("ERROR: ", e);
				},
				done: function(e) {
					console.log("DONE");
				}
		});
		 
		 // confirm booking request
		$('.confirm_booking_request_model').click(function(){
			var id=$(this).attr("request") ;
			console.log(id);
			$.ajax({
				async: false,
				cache: false,
				type: "GET",
				url: "get_request_booking_id",
				data :{'id':id},
				timeout: 100000,
				success: function(data) {
					console.log(data)
					var form='<button id="confirm_booking_request_btn" request="'+id
								+'" class="modal-action waves-effect waves-green btn-flat">Confirm</button>';
					document.getElementById('get_req_book_footer').innerHTML=form;
					
					 var data='<tr><th>Departure Date</th><td><b>:</b>  &nbsp&nbsp '+convert_date(data[0].dept_date)+'</td></tr>'
							  +'<tr><th>Time Allowance</th><td><b>:</b>  &nbsp&nbsp '+ convert_time(data[0].dept_time)+'</td></tr>'
							  +'<tr><th>Source</th><td><b>:</b> &nbsp&nbsp '+data[0].scource +'</td></tr>'
							  //+'<tr><th>Pick-up</th><td><b>:</b> &nbsp&nbsp '+data[0].pick_source_name +'</td></tr>'
							  +'<tr><th>Destination</th><td><b>:</b> &nbsp&nbsp '+ data[0].destination +'</td></tr>'
							  //+'<tr><th>Drop-off</th><td><b>:</b> &nbsp&nbsp '+ data[0].drop_dest_name +'</td></tr>'
							  +'<tr><th>Number of Ticket</th><td><b>:</b> &nbsp&nbsp '+data[0].number_of_ticket +'</td></tr>'
					document.getElementById('get_request_detail').innerHTML=data;
					
					console.log(form)
					$('#confirm_booking_request').modal();
					$('#confirm_booking_request').modal('open');
					
				},
				error: function(e) {
					console.log("ERROR: ", e);
				},
				done: function(e) {
					console.log("DONE");
				}
			});
			
			$('#confirm_booking_request_btn').click(function(){
				var id1=$(this).attr("request") ;
				console.log(id1);
				$.ajax({
					async: false,
					cache: false,
					type: "GET",
					url: "request_book_now",
					data :{'id':id1},
					timeout: 100000,
					success: function(data) {
						
						var text;
	   					if(data=="success"){
	   						text='<div class="modal-content center-align">'
			   					+'<i class="material-icons large success_icon">done</i>'
			   					+'<h6><b>Congrats!</b></h6>'
			   					+'<p>You have just booked successfully.</p>'
			   					+'<p>Enjoy your trip! </p></div>'
			   					+'<div class="modal-footer">'
						   	    +'<a href="#!" class="modal-action modal-close btn green lighten-1">OK</a>'
						   	    +'</div>';
			   				
	   					}else if(data=="no_bus_available"){
	   						text='<div class="modal-content center-align">'
			   					+'<i class="material-icons large error_icon">highlight_off</i>'
			   					+'<h6><b>Sorry, No bus available!</b></h6>'
			   					+'<p>Please try another departure date or time! </p></div>'
			   					+'<div class="modal-footer">'
						   	    +'<a href="#!" class="modal-action modal-close btn green lighten-1">OK</a>'
						   	    +'</div>';
	   					}else if(data=="over_bus_available"){
	   						text='<div class="modal-content center-align">'
			   					+'<i class="material-icons large error_icon">highlight_off</i>'
			   					+'<h6><b>Sorry, No bus available!</b></h6>'
			   					+'<p>Please try another departure date or time! </p></div>'
			   					+'<div class="modal-footer">'
						   	    +'<a href="#!" class="modal-action modal-close btn green lighten-1">OK</a>'
						   	    +'</div>';
	   					}else{
	   						text='<div class="modal-content center-align">'
			   					+'<i class="material-icons large error_icon">highlight_off</i>'
			   					+'<h6><b>Sorry, Connection Error!</b></h6>'
			   					+'<p>Please refresh the page and try again!</p></div>'
			   					+'<div class="modal-footer">'
						   	    +'<a href="#!" class="modal-action modal-close btn green lighten-1">OK</a>'
						   	    +'</div>';
	   					}
	   					document.getElementById('confirm').innerHTML=text;
						$('#confirm_booking_request').modal('close');
						$('#confirm').modal({
							onCloseEnd: function() {
									window.location.replace("booking_history");
							} 
						});
						$('#confirm').modal('open');
						
					},
					error: function(e) {
						console.log("ERROR: ", e);
					},
					done: function(e) {
						console.log("DONE");
					}
			});

			})
		})
		
		
		
		
		
		function compared_tomorrow(dept_date_time){
			 var current_date;
			 $.ajax({
					async: false,
					cache: false,
					type: "GET",
					url: "today",
					timeout: 100000,
					success: function(data) {
						current_date=data.slice(0, 19);
					},
					error: function(e) {
						console.log("ERROR: ", e);
					},
					done: function(e) {
						console.log("DONE");
					}
				});
			 var date = new Date(moment.utc(current_date, "YYYY-MM-DD  HH:mm:ss"));
			 date.setDate(date.getDate() + 1);
			 var tomorrow = String(date.getFullYear()+ "-" + (date.getMonth()+1) + "-" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
			 var tmr = moment.utc(tomorrow, "YYYY-MM-DD  HH:mm:ss");
   		 var dept_dt = moment.utc(dept_date_time, "YYYY-MM-DD  HH:mm:ss");
   		 
   		 console.log(tmr);
   		 console.log(dept_dt);
    		 console.log(moment(dept_dt).isAfter(tmr));
			 if(moment(dept_dt).isAfter(tmr)){
	   			 return true;
	   		 }else{
	   			 return false;
	   		 }
		 }
	
		// ======== Convert Time 13:00:00 to 01:00 PM ==========
		 function convert_time(time_input){
			  console.log(time_input);
			  var h=time_input.slice(0, 2);
			  var m=time_input.slice(3, 5);
			  var format=null;
			  if(h>=12){
			    h-=12;
			    format='PM';
			    if(h<10){
			      h='0'+h;
			    }
			  }else{
			    format='AM';
			  }
			  return time=h+':'+m+' '+format;
			}
		// ======== Convert Time 2018-02-01 00:00:00 to 2018-02-01 ==========
		 function convert_date(date_input){
			  console.log(date_input);
			  return date_input.slice(0, 10);;
		}
		 var elem = document.querySelector('#rb_collapsible');
		 var instance = M.Collapsible.init(elem, {
		      onOpenEnd: function(el){
		        console.log("onOpenEnd");
		        //console.log(this)
		        for(i=0;i<this.el.children.length;i++){
		          if(this.el.children[i].className=='active'){
		            for(j=0;j<this.el.children[i].children.length;j++){
		              if(this.el.children[i].children[j].className=='collapsible-header'){
		            	var n=this.el.children[i].children[j].children.length-1;
		                this.el.children[i].children[j].children[n].outerHTML='<i class="material-icons right">keyboard_arrow_right</i>';
		                break;
		              }
		            }
		            break;
		          }
		        }
		      },
		      onCloseEnd: function(){
		        console.log("onCloseEnd");
		        for(i=0;i<this.el.children.length;i++){
		          if(this.el.children[i].className!='active'){
		            for(j=0;j<this.el.children[i].children.length;j++){
		              if(this.el.children[i].children[j].className=='collapsible-header'){
		            	  var n=this.el.children[i].children[j].children.length-1;
		                  this.el.children[i].children[j].children[n].outerHTML='<i class="material-icons right">keyboard_arrow_down</i>';
		                  break;
		              }
		            }
		          }
		        }
		      }
		    });
		 
		 var elem1 = document.querySelector('#bh_collapsible');
		 var instance = M.Collapsible.init(elem1, {
		      onOpenEnd: function(el){
		        console.log("onOpenEnd");
		        //console.log(this)
		        for(i=0;i<this.el.children.length;i++){
		          if(this.el.children[i].className=='active'){
		            for(j=0;j<this.el.children[i].children.length;j++){
		              if(this.el.children[i].children[j].className=='collapsible-header'){
		            	var n=this.el.children[i].children[j].children.length-1;
		                this.el.children[i].children[j].children[n].outerHTML='<i class="material-icons right">keyboard_arrow_right</i>';
		                break;
		              }
		            }
		            break;
		          }
		        }
		      },
		      onCloseEnd: function(){
		        console.log("onCloseEnd");
		        for(i=0;i<this.el.children.length;i++){
		          if(this.el.children[i].className!='active'){
		            for(j=0;j<this.el.children[i].children.length;j++){
		              if(this.el.children[i].children[j].className=='collapsible-header'){
		            	  var n=this.el.children[i].children[j].children.length-1;
		                  this.el.children[i].children[j].children[n].outerHTML='<i class="material-icons right">keyboard_arrow_down</i>';
		                  break;
		              }
		            }
		          }
		        }
		      }
		    });
	  });