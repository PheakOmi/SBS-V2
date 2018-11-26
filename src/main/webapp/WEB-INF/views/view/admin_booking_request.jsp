<body onload="load()">
<article class="content cards-page">
                    <div class="title-block">
                        <h3 class="title"> Booking Requests </h3>
                         <p class="title-description"> All to-be-approved requests </p>
                         <button type="button" class="btn btn-pill-right btn-info pull-right" style="color:white;" onclick="location.href='historical_booking_request';">View all historical and approved requests <i class="fa fa-angle-right"></i></button>
                    </div>
                    <section class="section">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-block">
                                        <div class="card-title-block">
                                            <h3 class="title">Requests to be approved </h3>
                                            
                                        </div>
                                        <section class="example">
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th>Name</th>
                                                            <th>From</th>
                                                            <th>To</th>
                                                            <th>Departure Date</th>
                                                            <th>Departure Time</th>
                                                            <th>Status</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody id="allRequest">
                                                       
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
                
</body>
<script>
var requests, locations,customers;
load = function(){	
	$.ajax({
		url:'getAllCurrentBookingRequests',
		type:'GET',
		success: function(response){
			console.log(response)
			requests = response.requests;
			locations = response.locations;
            customers = response.customers;
			for (var i=0;i<requests.length;i++)
			{
			var booking = '<tr class="hoverr" style="background-color:#f9cdad;" data-url="request_detail?id='+requests[i].id+'">'
								+'<td>'+searchCustomer(requests[i].user_id,customers)+'</td>'
								+'<td>'+searchLocation(requests[i].source_id,locations)+'</td>'
								+'<td>'+searchLocation(requests[i].destination_id,locations)+'</td>'
								+'<td>'+formatDate(requests[i].dept_date)+'</td>'
								+'<td>'+requests[i].dept_time+'</td>'
								+'<td>'+requests[i].status+'</td></tr>';
			$("#allRequest").append(booking);				
			}
		$(".hoverr").on('click', function() {
	    	location.href=$(this).attr('data-url');
		});
			
    	},
	error: function(err){
		swal("Oops!", "Cannot get all booking request data", "error")
		console.log(JSON.stringify(err));
		}
		
	});
	
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

    return [month, day, year].join('-');
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
</script>