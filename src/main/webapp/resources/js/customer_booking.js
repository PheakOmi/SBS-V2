$(document).ready(function() {
	 $('select').formSelect();
	 $('#destination_name').formSelect();
	 $('.dropdown-button').dropdown();
	 		
	 $(".flatpickr input").flatpickr({
			mode: "single",
			minDate: new Date().fp_incr(1),
			dateFormat: "Y-m-d",
			disableMobile: "true"
	});
	 
	 // ======== User Information ==========
	 var phone;
	 $.ajax({
			async: false,
			cache: false,
			type: "GET",
			url: "user_info",
			contentType: "application/json",
			timeout: 100000,
			success: function(data) {
				console.log("KK");
				console.log(data);
				phone=data.phone_number;
				document.getElementById('fullname').innerHTML=data.username;
				
			},
			error: function(e) {
				console.log("ERROR: ", e);
			},
			done: function(e) {
				console.log("DONE");
			}
	});

	// ======== Source Information ==========
	 $.ajax({
			async: false,
			cache: false,
			type: "GET",
			url: "location_data",
			contentType: "application/json",
			timeout: 100000,
			success: function(data) {
				console.log("location data:");
				console.log(data);
				var location= Object.keys( data.location );
				var l_form='<option disabled selected>Source Location</option>';
				var custom_pl_form='<option disabled selected>Source Location</option>';
				for(i=0;i<location.length;i++){
					console.log(location[i])
					if(data.location[location[i]].length!=0){
						custom_pl_form+='<option value="'+data.location[location[i]][0].location_id+'">'+location[i]+'</option>';
						l_form+='<optgroup label="'+location[i]+'">';
						for(j=0;j<data.location[location[i]].length;j++){
							console.log(data.location[location[i]][j]);
							l_form+='<option value="'+data.location[location[i]][j].id+'">'+data.location[location[i]][j].name+'   ('+location[i]+')</option>';
						}
						l_form+='</optgroup>';	
					}	
				}
				l_form+='<option value="custom_pickup"><span class="red">**Custom Pick Up Location</span></option>'
				console.log(l_form)
				document.getElementById('source_name').innerHTML=l_form;
				document.getElementById('source_loc_id').innerHTML=custom_pl_form;
				$('#source_name').formSelect();
				var element = $( "span:contains('Custom Pick Up Location')" );
				element.css("color","#000000");
//				var elem = document.querySelector('#source_name');
//				var instance = M.FormSelect.getInstance(elem);
//				console.log("instance")
//				var n=instance.$selectOptions.length-1;
//				console.log(instance.$selectOptions[n])
//				console.log(instance)
			},
			error: function(e) {
				console.log("ERROR: ", e);
			},
			done: function(e) {
				console.log("DONE");
			}
	});
	 function selected_source(id){
		 $.ajax({
				async: false,
				cache: false,
				type: "GET",
				url: "check_location",
				data:{'id':id},
				contentType: "application/json",
				timeout: 100000,
				success: function(data) {
					var location= Object.keys( data.location );
					var l_form='';
					var custom_dropoff='<option disabled selected>Destination Location</option>';
					for(i=0;i<location.length;i++){
						console.log(location[i])
						if(data.location[location[i]].length>0){
							l_form+='<optgroup label="'+location[i]+'">';
							custom_dropoff+='<option value="'+data.location[location[i]][0].location_id+'">'+location[i]+'</option>';
							for(j=0;j<data.location[location[i]].length;j++){
								console.log(data.location[location[i]][j]);
								l_form+='<option value="'+data.location[location[i]][j].id+'">'+data.location[location[i]][j].name+'   ('+location[i]+')</option>';
							}
							l_form+='</optgroup>';	
						}	
					}
					l_form+='<option value="custom_dropoff">Custom Drop-off Location</option>'
					document.getElementById('destination_name').innerHTML=l_form;
					document.getElementById('select_dest_id').innerHTML=custom_dropoff;
					
					$('#select_dest_id').formSelect();
					$('#destination_name').formSelect();
					var element = $( "span:contains('Custom Pick Up Location')" );
					element.css("color","#000000");
				},
				error: function(e) {
					console.log("ERROR: ", e);
				},
				done: function(e) {
					console.log("DONE");
				}
		});
	 }
	 
	// ========Select Source Information ==========
	 $('#destination_name').change(function() {
			var id = $("#destination_name").val();
			console.log(id);
			if(id=="custom_dropoff"){
				$('#coustom_dropoff').modal();
				$('#coustom_dropoff').modal('open');
				$('#select_dest_id').formSelect();
			}
	});
	 
	// ========Select Destination Information ==========
	 $('#source_name').change(function() {
			var id = $("#source_name").val();
			console.log(id);
			if(id=="custom_pickup"){
				$('#coustom_source_pl').modal();
				$('#coustom_source_pl').modal('open');
				$('#source_loc_id').formSelect();
			}else{
				selected_source(id);
			}
	});
	 
	// ======== Departure time Information ==========
	 $.ajax({
			async: false,
			cache: false,
			type: "GET",
			url: "departure_time_info",
			contentType: "application/json",
			timeout: 100000,
			success: function(data) {
				console.log("departure_time_info");
				console.log(data);
				var option='<option value="" disabled selected>Select Departure Time</option>';
					for(i=0;i<data.length;i++){
						var get_time=convert_time(data[i].dept_time);
						option+='<option value="'+data[i].dept_time+'">'+get_time+'</option>';
					}
				document.getElementById('departure_time').innerHTML=option;
				$('#departure_time').formSelect();
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
				console.log("KKK")
				console.log(data)
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
//								        +'<tr><th>Bus Model</th><td><b>:</b>&nbsp&nbsp'+data[i].bus_model +'</td></tr>'
//								        +'<tr><th>Bus Plate Number</th><td><b>:</b>&nbsp&nbsp'+data[i].plate_number +'</td></tr>'
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
//							        +'<tr><th>Bus Model</th><td><b>:</b>&nbsp&nbsp'+data[i].bus_model +'</td></tr>'
//							        +'<tr><th>Bus Plate Number</th><td><b>:</b>&nbsp&nbsp'+data[i].plate_number +'</td></tr>'
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
				
				var qrcode_form = '<br><label>Date of travel: ' 
				+ data[0].scource 
				+ ',</label>' + '&nbsp&nbsp&nbsp&nbsp<label>' 
				+ data[0].destination
				+ '</label><br>'
				+ '<img class="img_rqcode center" src="data:image/png;base64,' 
				+ data[0].qrcode + '"/><br><label>Departure Date: ' 
				+ data[0].dept_date+'; '+ data[0].dept_time+ '</label>' 
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
			var form='<button id="confirm_cancel_booking_btn" booking="'+id
						+'" class="modal-action waves-effect waves-green btn-flat">Confirm</button>';
			document.getElementById('get_cancel_booking_footer').innerHTML=form;
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
									window.location.replace("customer_home");
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
//				$('.collapsible').collapsible();

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
				 var data='<tr><th>Departure Date</th><td><b>:</b>  &nbsp&nbsp '+convert_date(data[0].dept_date)+'</td></tr>'
						  +'<tr><th>Departure Time</th><td><b>:</b>  &nbsp&nbsp '+ convert_time(data[0].dept_time)+'</td></tr>'
						  +'<tr><th>Source</th><td><b>:</b> &nbsp&nbsp '+data[0].scource +'</td></tr>'
						  //+'<tr><th>Pick-up</th><td><b>:</b> &nbsp&nbsp '+data[0].pick_source_name +'</td></tr>'
						  +'<tr><th>Destination</th><td><b>:</b> &nbsp&nbsp '+ data[0].destination +'</td></tr>'
						  //+'<tr><th>Drop-off</th><td><b>:</b> &nbsp&nbsp '+ data[0].drop_dest_name +'</td></tr>'
						  +'<tr><th>Number of Ticket</th><td><b>:</b> &nbsp&nbsp '+data[0].number_of_ticket +'</td></tr>'
				document.getElementById('get_request_detail').innerHTML=data;
				var form='<button id="confirm_booking_request_btn" request="'+id
							+'" class="modal-action waves-effect waves-green btn-flat">Confirm</button>';
				document.getElementById('get_req_book_footer').innerHTML=form;
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
		
		$('#confirm_booking_request_btn').click(function(event){
			event.preventDefault();
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
								window.location.replace("customer_home");
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
	 
	// ======== Convert Time 13:00:00 to 01:00 PM ==========
	 function convert_time(time_input){
		  console.log(time_input);
		  var h=time_input.slice(0, 2);
		  var m=time_input.slice(3, 5);
		  var format=null;
		  if(h>=12){
		    h=h-12;
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
					console.log("Current Date:"+current_date);
				},
				error: function(e) {
					console.log("ERROR: ", e);
				},
				done: function(e) {
					console.log("DONE");
				}
			});
		 var date = new Date(moment(current_date, "YYYY-MM-DD  HH:mm:ss"));
		 console.log("1 Date:"+date);
		 date.setDate(date.getDate() + 1);
		 console.log("2 Date:"+date);
		 var tomorrow = String(date.getFullYear()+ "-" + (date.getMonth()+1) + "-" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
		 var tmr = moment(tomorrow, "YYYY-MM-DD  HH:mm:ss");
		 var dept_dt = moment(dept_date_time, "YYYY-MM-DD  HH:mm:ss");
		 console.log("tomorrow Date:"+tomorrow);
		 console.log("dept_date_time Date:"+dept_date_time);
		 console.log("TMR Date: "+tmr);
		 console.log("Dept DT: "+dept_dt);
 		 console.log(moment(dept_dt).isAfter(tmr));
		 if(moment(dept_dt).isAfter(tmr)){
   			 return true;
   		 }else{
   			 return false;
   		 }
	 }
	 
	 
//============================== Validation Part ===========================//
	 $.validator.addMethod("valueNotEquals", function(value, element, arg){
		 console.log(value);
		  return arg !== value;
		 }, "Value must not equal arg.");
	 $.validator.addMethod("SourceValueNothing", function(value, element, arg){
		  return arg !== value;
		 }, "*Required");
	 $.validator.addMethod("DestinationValueNothing", function(value, element, arg){
		  return arg !== value;
		 }, "*Required");

	//============================== Booking ===========================
	 $('#book_now').click(function(event){
		 
		 console.log("kk mm");
		 var source = $("#source_name").val();
   		 var destination = $("#destination_name").val();
   		 var time=$("#departure_time").val();
   		 console.log("source: "+source)
   		 console.log("destination: "+destination)
   		 console.log("time: "+time)
   		 //Validation select tag
   		if(source=="undefined"||source==null||source=="custom_pickup"){
	   		console.log(1);
	   			 //source *Required
	   			$("#source_name_error").show();
	   			 if(destination=="undefined"||destination==null||destination=="custom_pickup"){
	   				 //destination *Required
	   				$("#destination_name_error").show();
	   				console.log(11)
	   			 }else{
	   				$("#destination_name_error").hide();
	   			 }
	   			 if(time=="undefined"||time==null){
	   				 //time *Required
	   				$("#departure_time_error").show();
	   				console.log(12)
	   			 }else{
	   				$("#departure_time_error").hide();
	   			 }
	   	}else if(destination=="undefined"||destination==null||destination=="custom_pickup"){
	   			console.log(2);
	   			$("#source_name_error").hide();
	   			$("#destination_name_error").show();
	   			 //destination *Required
	   			if(time=="undefined"||time==null){
	   				 //time *Required
	   				$("#departure_time_error").show();
	   				console.log(21)
	   			 }else{
	   				$("#departure_time_error").hide();
	   			 }
	   	}else if(time=="undefined"||time==null){
	   		console.log(3);
	   			$("#source_name_error").hide();
	   			$("#destination_name_error").hide();
	   			$("#departure_time_error").show();
				//time *Required
		}else{
			$("#source_name_error").hide();
	   		$("#destination_name_error").hide();
	   		$("#departure_time_error").hide();
		}
	   	$('#form_book_now').valid();
	   	console.log("finsish");
	 });
	 $("#form_book_now").validate({
	        rules: {
	            departure_date: {
	        		 valueNotEquals: "none" 
	            },
	            number_of_seat: {
			         required: true,
			         min: 1,
			         max: 10
			     }

	        },
	        messages: {
	            departure_date: {
	            	required: "*Required" 
	            },
	            number_of_seat: {
			         required: "*Required",
			         min: "Manimun 1 ticket each time booking",
			         max: "Maximun 10 tickets each time booking"
			     }

	        },
	        errorElement: 'span',
	        errorPlacement: function(error, element) {
	        	
	            var placement = $(element).data('error');
	            if (placement) {
	                $(placement).append(error)
	                console.log("correct");
	            } else {
	                error.insertAfter(element);
	                console.log("incorrect");
	            }
	        },
	        submitHandler: function() {
	        	console.log("ALL Correct")
	        	
		   		 var source = $("#source_name").val();
		   		 var destination = $("#destination_name").val();
		   		 var time=$("#departure_time").val();
		   		 var date=$("#departure_date").val();
		   		 var number_of_seat=$("#number_of_seat").val();
		   		 var adult=number_of_seat-1;
		   		 var child=1;
		   		 var dept_dt=date+" "+time;
		   		 if(time!=="undefined"&&time!==null&&destination!=="undefined"
		   			 &&destination!==null&&destination!=="custom_pickup"
		   			 &&source!=="undefined"&&source!==null&&source!=="custom_pickup"){
					 if(compared_tomorrow(dept_dt)){
						 event.preventDefault();
					 	if(phone=='0'){
				   				$('#confirm_phone_number_modal').modal({
				   					onCloseEnd: function() {
								    	$.ajax({
							   				async: false,
							   				cache: false,
							   				type: "GET",
							   				url: "customer_booking",
							   				data :	{
							   						 source:source,
							   						 destination:destination,
							   						 time:time,
							   						 date:date,
							   						 number_of_seat:number_of_seat
							   				},
							   				timeout: 100000,
							   				success: function(data) {
							   					console.log(data);
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
									   					+'<p>Please click green button below to make a request. '
									   					+'We will get back to you very soon. </p></div>'
									   					+'<div class="modal-footer">'
												   	    +'<a href="#!" id="confirm_request_booking" class="modal-action modal-close btn green lighten-1">Request Now</a>'
												   	    +'</div>';
							   					}else if(data=="over_bus_available"){
							   						text='<div class="modal-content center-align">'
									   					+'<i class="material-icons large error_icon">highlight_off</i>'
									   					+'<h6><b>Sorry, No bus available!</b></h6>'
									   					+'<p>Please click green button below to make a request. '
									   					+'We will get back to you very soon. </p></div>'
									   					+'<div class="modal-footer">'
												   	    +'<a href="#!" id="confirm_request_booking" class="modal-action modal-close btn green lighten-1">Request Now</a>'
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
							   					$('#confirm').modal({
							   						onCloseEnd: function() {
															window.location.replace("customer_home");
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
									} 
								});
				   				$('#confirm_phone_number_modal').modal('open');
				   				$('#confirm_phone_number_btn').click(function(){
				   					var phone=$('#user_confirm_phone_number').val();
				   					console.log('phone: '+phone)
				   					$.ajax({
						   				async: false,
						   				cache: false,
						   				type: "GET",
						   				url: "confirm_phone_number",
						   				data :	{phone:phone},
						   				timeout: 100000,
						   				success: function(data) {
						   					console.log(data);
						   				},
						   				error: function(e) {
						   					console.log("ERROR: ", e);
						   				},
						   				done: function(e) {
						   					console.log("DONE");
						   				}
							    	});
				   				})
				        	}else{
				        		var submit=[];
									submit[0] = {
											"source":source,
					   						'destination':destination,
					   						 'time':time,
					   						 'date':date,
					   						 'number_of_seat':number_of_seat,
					   						 'adult':adult,
					   						 'child':child
										
									};
//									submit[1]={'source':destination,
//					   						 'destination':source,
//					   						 'time':time,
//					   						 'date':"2018-05-26",
//					   						 'number_of_seat':number_of_seat,
//					   						 'adult':adult,
//					   						 'child':child}
									console.log(submit)
									axios.defaults.headers.common[window.headerName]=window.token
					        		axios.post('customer_booking',submit)
									  .then(res =>{
									    console.log(res);
									  })
									  
				        		/*$.ajax({
					   				async: false,
					   				cache: false,
					   				type: "POST",
					   				url: "customer_booking",
					   				contentType : "application/json",
					   				data :	JSON.stringify(submit),
					   				timeout: 100000,
					   				success: function(data) {
					   					console.log(data);
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
							   					+'<p>Please click green button below to make a request. '
							   					+'We will get back to you very soon. </p></div>'
							   					+'<div class="modal-footer">'
										   	    +'<a href="#!" id="confirm_request_booking" class="modal-action modal-close btn green lighten-1">Request Now</a>'
										   	    +'</div>';
					   					}else if(data=="over_bus_available"){
					   						text='<div class="modal-content center-align">'
							   					+'<i class="material-icons large error_icon">highlight_off</i>'
							   					+'<h6><b>Sorry, No bus available!</b></h6>'
							   					+'<p>Please click green button below to make a request. '
							   					+'We will get back to you very soon. </p></div>'
							   					+'<div class="modal-footer">'
										   	    +'<a href="#!" id="confirm_request_booking" class="modal-action modal-close btn green lighten-1">Request Now</a>'
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
					   					$('#confirm').modal({
					   						onCloseEnd: function() {
													window.location.replace("customer_home");
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
					   		});*/
				        	}
					   		 
				   		 }else{
				   			var form='<div class="modal-content center-align">'
				   					+'<i class="material-icons large error_icon">highlight_off</i>'
				   					+'<h6><b>Sorry, No bus available!</b></h6>'
				   					+'<p>Please click green button below to make a request. '
				   					+'We will get back to you very soon. </p></div>'
				   					+'<div class="modal-footer">'
							   	    +'<a href="#!" id="confirm_request_booking" class="modal-action modal-close btn green lighten-1">Request Now</a>'
							   	    +'</div>';
				   				document.getElementById('confirm').innerHTML=form;
								$('#confirm').modal({
									onCloseEnd: function() {
											window.location.replace("customer_home");
									} 
								});
								$('#confirm').modal('open');
				   		 }
				 }

	            return false;
	        }
	    });
	 
	//============================== Custom Source Pickup Location ===========================
	 $("#form_coustom_source_pl").validate({
	        rules: {
	        	source_loc_id: {
	        		 valueNotEquals: "none" 
	            },
	            new_source_pickup_name: {
			         required: true,
			         minlength: 1,
			         maxlength: 30
			     }

	        },
	        messages: {
	        	source_loc_id: {
	                required: "*Required"
	            },
	            new_source_pickup_name: {
			         required: "*Required",
			         minlength: "Minimum one digit",
			         maxlength: "Maximum 30 digits"
			     }


	        },
	        errorElement: 'div',
	        errorPlacement: function(error, element) {
	            var placement = $(element).data('error');
	            if (placement) {
	                $(placement).append(error)
	                console.log("correct");
	            } else {
	                error.insertAfter(element);
	                console.log("incorrect");
	            }
	        },
	        submitHandler: function() {
	        	console.log($('#source_loc_id').val());
	        	console.log($('#new_source_pickup_name').val())
	            console.log("Successful Done!!!")
	             var location_id = $("#source_loc_id").val();
				 var pickup_name = $("#new_source_pickup_name").val();
				 $.ajax({
						async: false,
						cache: false,
						type: "GET",
						url: "create_custom_pickup_location",
						data :	{
							location_id:location_id,
							pickup_name:pickup_name
						},
						timeout: 100000,
						success: function(data) {
							console.log("Custom Pick UP Done!");
							console.log(data);
							var location= Object.keys( data.location );
							console.log(data.location[location[1]][0].location_id);
							var l_form='';
							var id; //  pickup location id
							for(i=0;i<location.length;i++){
								l_form+='<optgroup label="'+location[i]+'">';
								for(j=data.location[location[i]].length-1;j>=0;j--){
									if(location_id==data.location[location[i]][0].location_id&&j==data.location[location[i]].length-1){
										l_form+='<option selected value="'+data.location[location[i]][j].id+'">'+data.location[location[i]][j].name+'   ('+location[i]+')</option>';
										id=data.location[location[i]][j].id;
										console.log("Happy")
									}else{
										l_form+='<option value="'+data.location[location[i]][j].id+'">'+data.location[location[i]][j].name+'   ('+location[i]+')</option>';
									}
									
								}
								l_form+='</optgroup>';		
							}
							document.getElementById('source_name').innerHTML=l_form;
							$('#source_name').formSelect();
							selected_source(id);// to retrieve destination
							$('#coustom_source_pl').modal('close');
						},
						error: function(e) {
							console.log("ERROR: ", e);
						},
						done: function(e) {
							console.log("DONE");
						}
				});
	            return false;
	        }
	    });
	//============================== Custom Drop-Off Location ===========================
	 $("#form_coustom_dropoff").validate({
	        rules: {
	        	select_dest_id: {
	        		 valueNotEquals: "none" 
	            },
	            new_dropoff_name: {
			         required: true,
			         minlength: 1,
			         maxlength: 30
			     }

	        },
	        messages: {
	        	select_dest_id: {
	                required: "*Required"
	            },
	            new_dropoff_name: {
			         required: "*Required",
			         minlength: "Minimum one digit",
			         maxlength: "Maximum 30 digits"
			     }


	        },
	        errorElement: 'div',
	        errorPlacement: function(error, element) {
	            var placement = $(element).data('error');
	            if (placement) {
	                $(placement).append(error)
	                console.log("correct");
	            } else {
	                error.insertAfter(element);
	                console.log("incorrect");
	            }
	        },
	        submitHandler: function() {
	        	console.log($('#select_dest_id').val());
	        	console.log($('#new_dropoff_name').val())
	            console.log("Successful Done!!!")
	             var location_id = $("#select_dest_id").val();
				 var drop_name = $("#new_dropoff_name").val();
				 new_custom_dropoff(location_id, drop_name);
	            return false;
	        }
	    });
	 
	 function new_custom_dropoff(location_id, drop_name){
		 console.log("DOOOOO")
		 $.ajax({
				async: false,
				cache: false,
				type: "GET",
				url: "create_custom_dropoff_location",
				data :	{
					location_id:location_id,
					dropoff_name:drop_name
				},
				timeout: 100000,
				success: function(data) {
					console.log(data);
					var form_dropoff='<option value="'+data.id+'">'+data.drop_off_name+'  ('+ data.location_name+')</option>';
					
					document.getElementById('destination_name').innerHTML=form_dropoff;
					$('#destination_name').formSelect();
					$('#coustom_dropoff').modal('close');
				},
				error: function(e) {
					console.log("ERROR: ", e);
				},
				done: function(e) {
					console.log("DONE");
				}
		});
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
	                this.el.children[i].children[j].children[n].outerHTML='<i class="material-icons right">keyboard_arrow_up</i>';
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
	                this.el.children[i].children[j].children[n].outerHTML='<i class="material-icons right">keyboard_arrow_up</i>';
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