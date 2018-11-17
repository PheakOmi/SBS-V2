<body onload="load()">
<article class="content cards-page">
                    	
                    <section class="section">
                        <div class="row sameheight-container">
                            <div class="col-md-12">
                                <div class="card card-block sameheight-item">
                                    <div class="title-block">
                                        <h3 class="title"> Booking Request Information </h3> <br>
                                     
                                        
                      
                                    </div>
                                    
                                    
                                    
                                   
                                    
                                    <form class="form-inline" id ="myForm">
                                    	<div class="form-group col-md-4" style="margin-bottom:2%;">
                                            <label for="exampleInputPassword3"  style="margin-right:4%;">Name</label>
                                            <input type="text" class="form-control" id="uname" style="width: inherit;"> 
                                        </div>
                                        
                                        <div class="form-group col-md-4" style="margin-bottom:2%;">
                                            <label for="exampleInputPassword3"  style="margin-right:4%;">From</label>
                                            <input type="text" class="form-control" id="from" style="width: inherit;"> 
                                        </div>
                                        
                                        <div class="form-group col-md-4" style="margin-bottom:2%;">
                                            <label for="exampleInputPassword3"  style="margin-right:4%;">To</label>
                                            <input type="text" class="form-control" id="to" style="width: inherit;"> 
                                        </div>
                                        
                               			<div class="form-group col-md-4" style="margin-bottom:2%;">
                                            <label for="exampleInputPassword3"  style="margin-right:4%;">Departure Date</label>
                                            <input type="text" class="form-control" id="dept_date" style="width: inherit;"> 
                                        </div>
                                        
                                        <div class="form-group col-md-4" style="margin-bottom:2%;">
                                            <label for="exampleInputPassword3"  style="margin-right:4%;">Departure Time</label>
                                            <input type="text" class="form-control" id="dept_time" style="width: inherit;"> 
                                        </div>
                                        
                                        <div class="form-group col-md-4" style="margin-bottom:2%;">
                                            <label for="exampleInputPassword3"  style="margin-right:4%;">Requested On</label>
                                            <input type="text" class="form-control" id="requestedOn" style="width: inherit;"> 
                                        </div>
                                        
                                        <div class="form-group col-md-4" style="margin-bottom:2%;">
                                            <label for="exampleInputPassword3"  style="margin-right:4%;">Booking Time Allowance</label>
                                            <input type="text" name="time" class="form-control" id="timeAllowance" style="width: inherit;"> 
                                        </div>
                                        
                                        <div class="form-group col-md-4" style="margin-bottom:2%;">
                                            <label for="exampleInputPassword3"  style="margin-right:4%;">Number of bookings</label>
                                            <input type="text" class="form-control" id="no_booking" style="width: inherit;"> 
                                        </div>
                                       
                                        
                                        <div class="form-group col-md-4" style="margin-bottom:2%;">
                                            <label for="exampleInputPassword3"  style="margin-right:4%;">Status</label>
                                            <input type="text" class="form-control" id="status" style="width: inherit;"> 
                                        </div>
                                        
                                        
                                        <div class="form-group col-md-12" style="margin-bottom:2%;">
                                            <label for="exampleInputPassword3"  style="margin-right:4%;">Description</label>
                                            <textarea rows="4" cols="100" class="form-inline boxed" id="description"  style="width: inherit;"></textarea> 
                                        </div>
                                        
                                        <button type="submit" id ="bsubmit" style="display:none;">aa</button>
                                        
                                        
                                        
                               
                                       
                                        
                                    </form>
                                </div>
                            </div>
                        </div>
                    </section>
                    
                </article>
                
</body>
<script type="text/javascript">
var id;
load = function () {
	var data = ${data};
	var request = data.request;
	var locations = data.locations;
	var p_locations = data.p_locations;
    var all_customer = data.customers;
	id= parseInt(request.id);
	console.log(data)
	$("#uname").val(searchCustomer(request.user_id,all_customer));
    if(request.source_id!=0)
        $("#from").val(searchPLocation(request.source_id,p_locations)+", "+searchLocation(request.from_id,locations));
    else
        $("#from").val(searchLocation(request.from_id,locations));
    if(request.destination_id!=0)
        $("#to").val(searchPLocation(request.destination_id,p_locations)+", "+searchLocation(request.to_id,locations));
    else
        $("#to").val(searchLocation(request.to_id,locations));
	$("#dept_date").val(formatDate(request.dept_date));
	$("#dept_time").val(request.dept_time);
	$("#requestedOn").val(formatDate(request.created_at));
	$("#timeAllowance").val(request.provided_time);
	$("#no_booking").val(request.number_of_booking);
	$("#description").val(request.description);
	$("#status").val(request.status);
	
	
	
	$("input").prop('disabled', true);
	$("#description").prop('disabled', true);
}

$(document).ready(function(){
	$("#bookingRequestMng").addClass("active");
	
});



formatDate =function (date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
      if (day.length < 2) day = '0' + day;

    return [year, month, day].join('/');
};



function searchLocation(id, myArray){
    for (var i=0; i < myArray.length; i++) {
        if (myArray[i].id === id) {
            return myArray[i].name;
        }
    }
}


function searchPLocation(id, myArray){
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



	
</script>
