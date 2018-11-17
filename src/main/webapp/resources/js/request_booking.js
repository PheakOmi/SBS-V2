$(document).ready(function() {
	 $('select').formSelect();
	 $('.dropdown-button').dropdown();

	 $("#departure_date").flatpickr({
			mode: "single",
			minDate: "today",
			dateFormat: "Y-m-d",
			disableMobile: "true"
	});
	 $("#departure_time").flatpickr({
		 	enableTime: true,
		    noCalendar: true,
		    dateFormat: "H:i",
		    disableMobile: "true"
	});
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
				for(i=0;i<location.length;i++){
					console.log(location[i])
					
					l_form+='<optgroup label="'+location[i]+'">';
					for(j=0;j<data.location[location[i]].length;j++){
						console.log(data.location[location[i]][j]);
						l_form+='<option value="'+data.location[location[i]][j].id+'">'+data.location[location[i]][j].name+'   ('+location[i]+')</option>';
					}
					l_form+='</optgroup>';		
				}
				console.log(l_form)
				document.getElementById('source_name').innerHTML=l_form;
				$('#source_name').formSelect();
			},
			error: function(e) {
				console.log("ERROR: ", e);
			},
			done: function(e) {
				console.log("DONE");
			}
	});
	// ========Select Source Information ==========
	 $('#source_name').change(function() {
			var id = $("#source_name").val();
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
					for(i=0;i<location.length;i++){
						console.log(location[i])
						l_form+='<optgroup label="'+location[i]+'">';
						for(j=0;j<data.location[location[i]].length;j++){
							console.log(data.location[location[i]][j]);
							l_form+='<option value="'+data.location[location[i]][j].id+'">'+data.location[location[i]][j].name+'   ('+location[i]+')</option>';
						}
						l_form+='</optgroup>';		
					}
					document.getElementById('destination_name').innerHTML=l_form;
					$('#destination_name').formSelect();
					$('#select_dest_id').formSelect();
					
				},
				error: function(e) {
					console.log("ERROR: ", e);
				},
				done: function(e) {
					console.log("DONE");
				}
		});
		});

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
	 
	//============================== Validation Part ===========================//
	 $.validator.addMethod("valueNotEquals", function(value, element, arg){
		 console.log(value);
		  return arg !== value;
		 }, "Value must not equal arg.");
	//============================== Request Booking ===========================//
	 function compared_today(dept_date_time){
//		 var today = moment().format('Y-M-D, HH:mm:ss');
//		 var date = new Date(today)
//		 var current_today = date.getFullYear()+ "-" + (date.getMonth()+1) + "-" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
//		 console.log(current_today)
//		 console.log(dept_date_time)
//		 if(new Date(dept_date_time) > new Date(current_today)){
//   			 return true;
//   		 }else{
//   			 return false;
//   		 }
		 
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
		 var today = moment.utc(date, "YYYY-MM-DD  HH:mm:ss");
		 var dept_dt = moment.utc(dept_date_time, "YYYY-MM-DD  HH:mm:ss");
		 
		 console.log(today);
		 console.log(dept_dt);
 		 console.log(moment(dept_dt).isAfter(today));
		 if(moment(dept_dt).isAfter(today)){
   			 return true;
   		 }else{
   			 return false;
   		 }
	 }

	 $('#book_now').click(function(event){
		 console.log("kk mm");
		 var source = $("#source_name").val();
   		 var destination = $("#destination_name").val();
   		 var time=$("#departure_time").val();
   		 //Validation select tag
   		if(source=="undefined"||source==null){
	   		console.log(1);
	   		//source *Required
	   		$("#source_name_error").show();
	   		if(destination=="undefined"||destination==null){
	   			//destination *Required
	   			$("#destination_name_error").show();
	   			console.log(11)
	   		}else{
	   			$("#destination_name_error").hide();
	   		}
	   	}else if(destination=="undefined"||destination==null){
	   		console.log(2);
	   		$("#source_name_error").hide();
	   		$("#destination_name_error").show();
	   	}else{
			$("#source_name_error").hide();
	   		$("#destination_name_error").hide();
		}
	   	$('#form_booking_request').valid();
	   	console.log("finsish");
	 });
	 $("#form_booking_request").validate({
	        rules: {
	            departure_time: {
	        		 valueNotEquals: "none" 
	            },
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
	            departure_time: {
	            	required: "*Required" 
	            },
	            departure_date: {
	            	required: "*Required" 
	            },
	            number_of_seat: {
			         required: "*Required",
			         min: "Manimun 1 tickets each time booking",
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
		   		 var source = $("#source_name").val();
		   		 var destination = $("#destination_name").val();
		   		 var time=$("#departure_time").val();
		   		 var date=$("#departure_date").val();
		   		 var number_of_seat=$("#number_of_seat").val();
		   		 var dept_dt=date+" "+time;
		   		 console.log("dept_dt: "+dept_dt);
		   		 if(source!=="undefined"&&source!==null&&destination!=="undefined"&&destination!==null){
		   			if(compared_today(dept_dt)){
		   				 event.preventDefault();
			   			 $.ajax({
							async: false,
							cache: false,
							type: "GET",
							url: "customer_request_booking",
							data :	{
									 source:source,
									 destination:destination,
									 time:time,
									 date:date,
									 number_of_seat:number_of_seat
							},
							timeout: 100000,
							success: function(data) {
								var text;
			   					if(data=="success"){
			   						text='<div class="modal-content center-align">'
					   					+'<i class="material-icons large success_icon">done</i>'
					   					+'<h6><b>Congrats!</b></h6>'
					   					+'<p>You have just requested successfully.</p>'
					   					+'<p>We will get back to you very soon! </p></div>'
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
			   		 }else{
			   			text='<div class="modal-content center-align">'
		   					+'<i class="material-icons large error_icon">highlight_off</i>'
		   					+'<h6><b>Sorry, Time Was Over!</b></h6>'
		   					+'<p>Please request before departure time!</p></div>'
		   					+'<div class="modal-footer">'
					   	    +'<a href="#!" class="modal-action modal-close btn green lighten-1">OK</a>'
					   	    +'</div>';
			   			document.getElementById('confirm').innerHTML=text;
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
});