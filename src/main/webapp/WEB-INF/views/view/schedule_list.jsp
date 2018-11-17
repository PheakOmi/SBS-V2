<body onload="load()">
<article class="content cards-page">
                    <div class="title-block">
                        <h3 class="title"> Schedules </h3><br>
                        
                        
<!--                         <p class="title-description"> Create, Update or View all buses information </p> -->
                    </div>
                    <section class="section">
	                   <div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-block">
                                        <div class="card-title-block">
                                            <h3 class="title" id="ddate"></h3>
                                            
                                        </div>
                                        <section class="example">
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th>No</th>
                                                            <th>Code</th>
                                                            <th>Bus</th>
                                                            <th>Driver</th>
                                                            <th>From</th>
                                                            <th>To</th>
                                                            <th>Departure Date</th>
                                                            <th>Departure Time</th>
                                                            <th>Number of bookings</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody id="allSchedules">
                                                       
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
<script type="text/javascript">
load = function(){	
	var data = ${data};
	var schedules = data.schedules;
	console.log(schedules)
	var locations = data.locations;
	var buses = data.buses;
    var drivers = data.drivers;
	for (var i=0;i<schedules.length;i++)
	{
		schedules[i].dept_date = new Date(schedules[0].dept_date);
		schedules[i].created_at = new Date(schedules[0].created_at);
		schedules[i].updated_dt = new Date(schedules[0].updated_dt);
	var schedule = '<tr class="hoverr" data-url="schedule_detail?id='+schedules[i].id+'"><td>'+(i+1)+'</td>'
						+'<td>'+schedules[i].code+'</td>'
						+'<td>'+searchBus(schedules[i].bus_id,buses)+'</td>'
						+'<td>'+searchDriver(schedules[i].driver_id,drivers)+'</td>'
						+'<td>'+searchLocation(schedules[i].source_id,locations)+'</td>'
						+'<td>'+searchLocation(schedules[i].destination_id,locations)+'</td>'
						+'<td>'+formatDate(schedules[i].dept_date)+'</td>'
						+'<td>'+schedules[i].dept_time+'</td>'
						+'<td>'+schedules[i].number_booking+'</td></tr>';
	$("#allSchedules").append(schedule);				
	}
	$("#ddate").text("All schedules on "+formatDate(schedules[0].dept_date));
	$(".hoverr").on('click', function() {
    	location.href=$(this).attr('data-url');
	});
}

goTO = function(){
	$('#bsubmit').trigger('click');
}


$(document).ready(function(){
	$("#scheduleMng").addClass("active");
	
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

function searchBus(id, myArray){
    for (var i=0; i < myArray.length; i++) {
        if (myArray[i].id === id) {
            return myArray[i].model;
        }
    }
}

function searchDriver(id, myArray){
    if(id==0)
        return"";
    for (var i=0; i < myArray.length; i++) {
        if (myArray[i].id === id) {
            return myArray[i].name;
        }
    }
}



	



</script>
