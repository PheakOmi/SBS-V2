<body onload="load()">
<article class="content cards-page">
                    	
                    <section class="section">
                        <div class="row sameheight-container">
                            <div class="col-md-12">
                                <div class="card card-block sameheight-item">
                                    <div class="title-block">
                                        <h3 class="title"> Booking Information </h3>
                                    </div>
                                    <br><br>
                                    <form class="form-group" id ="myForm">
                                    	<div class="form-group">
                                            <label for="exampleInputEmail3">Booking Code</label>
                                            <input type="text" class="form-control" id="code">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputEmail3">Name</label>
                                            <input type="text" class="form-control" id="uname">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputEmail3">From</label>
                                            <input type="text" class="form-control" id="from">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputEmail3">To</label>
                                            <input type="text" class="form-control" id="to">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputEmail3">Departure Date</label>
                                            <input type="text" class="form-control" id="dept_date">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputEmail3">Departure Time</label>
                                            <input type="text" class="form-control" id="dept_time">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputEmail3">Booked On</label>
                                            <input type="text" class="form-control" id="bookedOn">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputEmail3">Number of bookings</label>
                                            <input type="text" class="form-control" id="no_booking">
                                        </div>
                                        
                                        
                                        <button data-toggle="tooltip" title="Back To Previous Page" class="btn btn-default" id="btnCancel" onclick="parent.history.go(-1)"><i class="fa fa-angle-left"></i><b>Back</b></button> 
                               
                                       
                                        
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
	console.log(data)
	var booking = data.booking;
	var locations = data.locations;
	var p_locations = data.p_locations;
    var customers = data.customers;
	console.log(data)
	$("#code").val(booking.code);
	$("#uname").val(searchCustomer(booking.user_id,customers));
	if(booking.source_id!=0)
	    $("#from").val(searchPLocation(booking.source_id,p_locations)+", "+searchLocation(booking.from_id,locations));
	else
        $("#from").val(searchLocation(booking.from_id,locations));
    if(booking.destination_id!=0)
        $("#to").val(searchPLocation(booking.destination_id,p_locations)+", "+searchLocation(booking.to_id,locations));
    else
        $("#to").val(searchLocation(booking.to_id,locations));

	$("#dept_date").val(booking.dept_date);
	$("#dept_time").val(booking.dept_time);
	$("#bookedOn").val(booking.created_at);
	$("#no_booking").val(booking.number_booking);
	
	
	
	$("input").prop('disabled', true);
}

$(document).ready(function(){
	$("#bookingMng").addClass("active");
	$("#myForm").on('submit',function(e){
		e.preventDefault();
	});
});



formatDate =function (date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
      if (day.length < 2) day = '0' + day;

    return [month, day, year].join('/');
};


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




function searchLocation(id, myArray){
    for (var i=0; i < myArray.length; i++) {
        if (myArray[i].id === id) {
            return myArray[i].name;
        }
    }
}

	
</script>
