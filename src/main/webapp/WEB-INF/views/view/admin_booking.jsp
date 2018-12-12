<body onload="load()">
<article class="content cards-page">
					<div class="title-block">
                         <div class="clearfix" style="margin-bottom: 10px;">
                         	<div class="pull-left">
                         		<h3 class="title"> Bookings </h3>
                         		<p class="title-description"> All current and future bookings </p> 
                         	</div>
                         	<div class="btn-group pull-right menulist">
		                        <button type="button" data-toggle="tooltip" title="All Bookings" class="btn btn-info" id="allbtn" style="color:white;border-right:2px solid white;">All<span style="background-color: orange; margin-left: 6px; margin-right: -6px;" class="badge pull-right" id="allnotii"></span></button>
		                        <button type="button" data-toggle="tooltip" title="All Customer Bookings" class="btn btn-info" id="customerbtn" style="color:white; border-right:2px solid white;"><i class="fa fa-users"></i><span style="background-color: orange; margin-left: 6px; margin-right: -6px;" class="badge pull-right" id="customernotii"></span></button>
		                        <button type="button" data-toggle="tooltip" title="All Student Bookings" class="btn btn-info"  id="studentbtn" style="color:white;"><i class="fa fa-graduation-cap"></i><span style="background-color: orange; margin-left: 6px; margin-right: -6px;" class="badge pull-right" id="studentnotii"></span></button>
		                    </div>
                         	
                         </div>
                         
                       		<div style="display: flex;flex-direction: column;">
                       			<div style="margin-bottom: 10px;">
		                         <button type="button" data-toggle="tooltip" title="Historical Bookings" class="btn btn-pill-right btn-info pull-right" style="color:white;" onclick="location.href='historical_booking';">View all historical bookings <i class="fa fa-angle-right"></i></button>

		                          

		                          <button type="button" data-toggle="modal" data-target="#filterModal" class="btn btn-info pull-left" style="color:white;">Filters <i class="fa fa-filter"></i></button>
		                         
	                      	 </div>
	                      	 <button data-toggle="tooltip" title="Assign Bookings To New Schedule" class="btn btn-warning pull-left" onClick="confirmMove()" style="color:white; width: 180px;" id="moveBtn">Assign Schedule <i class="fa fa-exchange"></i></button>
                       		</div>



                       		<%--<div style="display: flex;flex-direction: column;">--%>
                       			<%--<div style="margin-bottom: 10px;">--%>
		                         <%--<button type="button" class="btn btn-info pull-left" style="color:white;" onclick="location.href='refund_list';">Booking Refunds <span style="background-color: red; margin-left:7px;" class="badge pull-right" id="refundnoti"></span> </button>--%>

		                          <%----%>

		                          <%--<button type="button" class="btn btn-info pull-left" style="color:white; margin-left: 10px;" onclick="location.href='unpaid_booking_list';">Unpaid Bookings <span style="background-color: red; margin-left:7px;" class="badge pull-right" id="unpaidnoti"></span></button>--%>
		                         <%----%>
	                      	 <%--</div>--%>
	                      	 <%----%>
                       		<%--</div>--%>



	                         

	                      	 <div>
	                      	 
	                      	 </div>
	                      	 </div>


                   
                     <div style="margin-bottom: 10px;">
	                      	     <input type="text" class="form-control" placeholder="Search for any booking by code or username..." id="txtbox"/>
	                    	 </div>	
                    <section class="section">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-block">
                                        <div class="card-title-block">
                                            <h3 class="title"> Current and future bookings </h3>
                                            
                                        </div>

                                        <section class="example">
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered table-hover">
                                                    <thead>
                                                        <tr>
                                                        	<%--<th></th>--%>
                                           
                                                            <th>Code</th>
                                                            <th>Name</th>
                                                            <th>From</th>
                                                            <th>To</th>
                                                            <th>Departure Date</th>
                                                            <th>Departure Time</th>
                                                            <th>User Type</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody id="allBooking">
                                                       
                                                    </tbody>
                                                </table>
                                            </div>
                                        </section>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </article>
                <div id="user_info_modal" class="modal">
                    <div class="modal-content center">
                        <h6 class="center light-blue-text">User Information</h6>
                        <table id="get_user_info"></table>
                    </div>
      </div>






      <div class="modal fade" id="filterModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title center">Which bookings?</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          <form id="filterForm">
                                        
                                        <div class="form-group">
                                            <label class="control-label">From (Source Location)</label>
                                            <select class="form-control boxed" id="ffrom">
                                            	<option></option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">To (Destination Location)</label>
                                            <select class="form-control boxed" id="fto">
                                            	<option></option>
                                            </select>
                                        </div>
                                        
                                         <div class="form-group">
                                            <label class="control-label">Departure Date</label>
                                            <input type="text" name ="date" class="form-control boxed" id="fdeptdate"> </div>
                                         <div class="form-group">
                                            <label class="control-label">User Type</label>
                                            <select class="form-control boxed" id="fuser">
                                            	<option value=""></option>
                                                <option value="customer">Customer</option>
                                                <option value="student">Student</option>
                                            </select> 
                                            </div>
                                         
                                            <button type="submit" id="bsubmit" class="btn btn-default" style="display:none;">Create</button>
                                        	</form>
        </div>
        <div class="modal-footer">
          <button type="button" style="color:black;" class="btn btn-default" data-dismiss="modal">Close</button>
          <button onClick="goTO()" class="btn btn-info">Done!</button>
        </div>
      </div>
      
    </div>
  </div>
</body>

<script>
load = function(){	
	 $("#allbtn").addClass("active");
	$.ajax({
		url:'getAllCurrentBookings',
		type:'GET',
		success: function(response){
			console.log(response)
			bookings = response.bookings;
			locations = response.locations;
			customers = response.customers;
			// unpaid = response.unpaid;
			// refund = response.refund;
			// if(unpaid.length>0)
            //     $("#unpaidnoti").text(unpaid.length);
            // if(refund.length>0)
            //     $("#refundnoti").text(refund.length);
			all = bookings.length
			console.log(locations)
			var no_student = 0
			var no_customer = 0
			for (var i=0;i<bookings.length;i++)
				{
				if (bookings[i].description=="student")
					no_student++
				else
					no_customer++

				var color="";
				var cb="<td></td>";

                if(bookings[i].notification=="Cancelled")
                    color = "background-color: #FADAD8;";

				var booking = '<tr class="hoverr search '+bookings[i].description+'" tofind="'+bookings[i].id+'"style="'+color+'"s-title="'+bookings[i].code+searchCustomer(bookings[i].user_id,customers).toLowerCase()+'" deptdate="'+formatDate(bookings[i].dept_date)+'" from="'+bookings[i].source_id+'"'+
				'destination="'+bookings[i].destination_id+'"'+'source="'+bookings[i].source_id+'"'+'to="'+bookings[i].destination_id+'"'+'data-url="booking_detail?id='+bookings[i].id+'">'
									// +cb
									
									+'<td>'+bookings[i].code+'</td>'
									+'<td class="user_info" style="color:blue" data='+bookings[i].user_id+'>'+searchCustomer(bookings[i].user_id,customers)+'</td>'
									+'<td>'+searchLocation(bookings[i].source_id,locations)+'</td>'
									+'<td>'+searchLocation(bookings[i].destination_id,locations)+'</td>'
									+'<td>'+formatDate(bookings[i].dept_date)+'</td>'
									+'<td>'+bookings[i].dept_time+'</td>'
									+'<td>'+bookings[i].description+'</td></tr>';
				$("#allBooking").append(booking);				
				}

				$("#studentnotii").text(no_student);
				$("#customernotii").text(no_customer);
				$("#allnotii").text(no_customer+no_student);

				for(i=0; i<locations.length; i++)                   
        			$("#ffrom").append("<option value="+locations[i].id+">"+locations[i].name+" </option>");
 
			$(".checkbox").on('click', function(e) {
				var a = $(this).parents(".hoverr")[0];
				$(a).toggleClass("selected");
				showMoveBtn(a);
			});

			$(".hoverr").on('click', function() {
    	    	location.href=$(this).attr('data-url');
    		});
    		$( ".unhoverr" ).on('click', function(e) {
				e.stopPropagation();	
			});
    		$( ".user_info" ).on('click', function(e) {
                 console.log("KK");
                 e.stopPropagation();
                 var id=$(this).attr('data');
                 console.log(id);
                 $.ajax({
                        async: false,
                        cache: false,
                        type: "GET",
                        url: "get_sch_driver_info2",
                        data :{'id':id},
                        timeout: 100000,
                        success: function(data) {
                            console.log(data);
                            if(data[0].phone_number==""||data[0].phone_number==null)
                            	data[0].phone_number = "";
                            var data='<tr><th>User\'s Name</th><td><b>:</b>  &nbsp&nbsp '+data[0].name+'</td></tr>'
                              +'<tr><th>Phone Number</th><td><b>:</b>  &nbsp&nbsp '+ data[0].phone_number+'</td></tr>'
                              +'<tr><th>Email</th><td><b>:</b> &nbsp&nbsp '+data[0].email +'</td></tr>'
                              document.getElementById('get_user_info').innerHTML=data;
                            $('#user_info_modal').modal();
                            $('#user_info_modal').modal('open');
                            
                        },
                        error: function(e) {
                            console.log("ERROR: ", e);
                        },
                        done: function(e) {
                            console.log("DONE");
                        }
                    });
             });
    	},
	error: function(err){
		swal("Oops!", "Cannot get all buses data", "error")
		console.log(JSON.stringify(err));
		}
		
	});
	
	
	
$("#moveBtn").hide();
	


}



$(document).ready(function(){
	$("#bookingMng").addClass("active");
	$("[name=date]").keydown(function (event) {
            event.preventDefault();
        });
	$("#txtbox").keyup(function(){
		   
		   
		   $(".search").each(function(){
	 	         
		          $(this).show();
		         
		        });
		     var searchValue = $("#txtbox").val().toLowerCase();
		     	if(searchValue!=null&&searchValue!="")
		     		{
		     		 $(".search").each(function(){
		    	         var title = $(this).attr('s-title'); 
		    	         title = title.toLowerCase();
		    	         var check = title.search(searchValue);
		    	         if(check==-1)
		    	         {
		    	          $(this).hide();
		    	         }
		    	        });
		     		}
		     	else{
		       
		        $(".search").each(function(){
		   	         
		   	          $(this).show();
		   	         
		   	        });
		     	}
		    });


	$("#ffrom").change(function(){
		var input  = this.value;
		var location_id;
		$('#fto').children('option:not(:first)').remove();
		for(i=0; i<locations.length; i++)
			{
			if(locations[i].id==input)
				location_id = locations[i].id;
				
			}
		for(i=0; i<locations.length; i++)
		{
		if(locations[i].id==location_id)
			{
			
			}
		else
			$("#fto").append("<option value="+locations[i].id+">"+locations[i].name+" </option>");
			
		}	
	    
	});

	$('#filterModal').on('show.bs.modal', function() {
    	$("#fdeptdate").val("")
	});



	$("#filterForm").on('submit',function(e){
		count=0;
		e.preventDefault();
        
		from = $("#ffrom").val()
		to = $("#fto").val()
		filter_date = $("#fdeptdate").val()
		filter_user = $("#fuser").val()

		if(from==""||from==null)
		{
            count++;
            from = 0;
        }
        else
            from = parseInt(from);
		if(to==""||to==null)
		{
            count++;
            to = 0;
        }
        else
            to = parseInt(to);
		if(filter_date==""||filter_date==null)
			{
                count++;
                filter_date = "";
            }
		if(filter_user=="" || filter_user==null)
			count++;

		if(count>=4)
			{
			swal("Action Disallowed!", "Please fill out at least one field!", "error")
			return
			}
		else
			{
				$(".search").each(function(){
							$(this).show();
				});
				if(from!=0)
					{
						$(".search").each(function(){
							if($(this).attr("from")!=from)
							$(this).hide();
						});
					}

				if(to!=0)
					{
						$(".search").each(function(){
							if($(this).attr("to")!=to)
							$(this).hide();
						});
					}
				if(filter_date!="")
					{
						$(".search").each(function(){
							if($(this).attr("deptdate")!=filter_date)
							$(this).hide();
						});
					}
				if(filter_user!="")
					{
						if(filter_user=="customer")
							filter_user = "student"
						else
							filter_user = "customer"
						
						$("."+filter_user).each(function(){
						    	 $(this).hide();
						  });	
						
					}
				var c = 0
				$(".search").each(function(){
							if($(this).attr("style") == 'display: none;')
								c++
					});
				swal("Filtering Done!", all-c+" bookings found!", "success")
				$('#filterModal').modal('toggle');

			}


		


	});
	
	
	$("#customerbtn").on('click', function(e) {
		   
		  $("#allbtn").removeClass("active");
		  $("#studentbtn").removeClass("active");
		  $("#customerbtn").addClass("active");

		  $(".search").each(function(){
				$(this).show();
				});

		  $(".customer").each(function(){
		    	 $(this).show();
		  });

		  $(".student").each(function(){
		    	 $(this).hide();
		  });
		  
	});
	


	$("#studentbtn").on('click', function(e) {
		   
		  $("#allbtn").removeClass("active");
		  $("#customerbtn").removeClass("active");
		  $("#studentbtn").addClass("active");

		  $(".search").each(function(){
			$(this).show();
				});

		  $(".student").each(function(){
		    	 $(this).show();
		  });
		  $(".customer").each(function(){
		    	 $(this).hide();
		  });
		  
	});

	$("#allbtn").on('click', function(e) {
			
		  $("#customerbtn").removeClass("active");
		  $("#studentbtn").removeClass("active");
		  $("#allbtn").addClass("active");
		   
		  $(".customer").each(function(){
		    	 $(this).show();
		  });
		  $(".student").each(function(){
		    	 $(this).show();
		  });
		  
	});

});



function confirmMove(){
	var b_id = []
	var from = 0
	var to = 0
	var source = 0
	var destination = 0
	var str = ""
	var code
	$(".selected").each(function(){
			var id = parseInt($(this).attr("tofind"))
			b_id.push(id)
			if(parseInt($(this).attr("from"))!=0)
				from = parseInt($(this).attr("from"))
			if(parseInt($(this).attr("to"))!=0)
				to = parseInt($(this).attr("to"))
			if(parseInt($(this).attr("source"))!=0)
				source = parseInt($(this).attr("source"))
			if(parseInt($(this).attr("destination"))!=0)
				destination = parseInt($(this).attr("destination"))

			code = searchBooking3(id,bookings)
			str = str+code+", "
		});
	func(b_id, str,from,to,source,destination)
}


func=function(b_id, str,from,to,source,destination)
{
swal({
    title: "Do you want to move these unassigned bookings to a schedule?",
    text: str+" will be moved!",
    type: "warning",
    showCancelButton: true,
    confirmButtonColor: "#76D4F5",
    confirmButtonText: "Move",
    cancelButtonText: "Cancel",
    closeOnConfirm: false,
    closeOnCancel: true
  },
    function (isConfirm) {
      if (isConfirm) {
    	  $.ajax({
    	     url:'moveToRental',
    	     type:'GET',
    	     data:{
    	     	b:b_id,
    	     	to_id:to,
    	     	from_id:from,
    	     	source_id:source,
    	     	destination_id:destination
    	     	},
    	     traditional: true,
    	     success: function(response){
    	     console.log(response)
    	      if(response.status=="1"||response.status=="55")
    	      {
    	      setTimeout(function() {
    	             swal({
    	                 title: "Done!",
    	                 text: response.message,
    	                 type: "success"
    	             }, function() {
    	                 window.location = "admin_booking";
    	             });
    	         }, 10);

    	      }

    	          else 
    	           {
    	           swal("Oops!",response.message, "error")

    	           } 
    	     },
    				error: function(err){
    					
    					console.log(JSON.stringify(err));
    					}
    	       });
      } 
    });
    }



function showMoveBtn(e){
	var dates = []
	var times = []
	var numItems = $('.selected').length;
	var count=0;
	if(numItems>0)
	{
		
		if (numItems!=1) {
			$(".selected").each(function(){
			var id = parseInt($(this).attr("tofind"))
			dates.push(searchBooking(id,bookings))
			times.push(searchBooking2(id,bookings))	         
		    console.log(searchBooking(id,bookings))
			});
			console.log(dates)
			console.log(times)
			if((!!dates.reduce(function(a, b){ return (a === b) ? a : NaN; }))&&(!!times.reduce(function(a, b){ return (a === b) ? a : NaN; })))
			{
				$("#moveBtn").show();
			}
			else
			$("#moveBtn").hide();
		}
		else
			$("#moveBtn").show();
		
	}
	else 
		$("#moveBtn").hide();
	
}


formatDate =function (date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
      if (day.length < 2) day = '0' + day;

    return [month, day, year].join('/');
};

function searchLocation(id, myArray){
    for (var i=0; i < myArray.length; i++) {
        if (myArray[i].id === id) {
            return myArray[i].name;
        }
    }
}


function searchCustomer(id, myArray){
    for (var i=0; i < myArray.length; i++) {
        if (myArray[i].id === id) {
            return myArray[i].name;
        }
    }
}

function searchBooking(id, myArray){
    for (var i=0; i < myArray.length; i++) {
        if (myArray[i].id === id) {
            return formatDate(myArray[i].dept_date);
        }
    }
}
function searchBooking2(id, myArray){
    for (var i=0; i < myArray.length; i++) {
        if (myArray[i].id === id) {
            return myArray[i].dept_time;
        }
    }
}
function searchBooking3(id, myArray){
    for (var i=0; i < myArray.length; i++) {
        if (myArray[i].id === id) {
            return myArray[i].code;
        }
    }
}
goTO = function(){
    $('#bsubmit').trigger('click');
}
</script>