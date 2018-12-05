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
                                                            <th>Code</th>
                                                            <th>Bus</th>
                                                            <th>Driver</th>
                                                            <th>From</th>
                                                            <th>To</th>
                                                            <th>Departure Date</th>
                                                            <th>Departure Time</th>
                                                            <th>Allowed</th>
                                                            <th>Booked</th>
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
<div id="driver_info_modal" class="modal">
    <div class="modal-content center">
        <h6 class="center light-blue-text">Driver Information</h6>
        <table id="get_driver_info"></table>
    </div>
</div>
<div id="bus_info_modal" class="modal">
    <div class="modal-content center">
        <h6 class="center light-blue-text">Bus Information</h6>
        <table id="get_bus_info"></table>
    </div>
</div>
                
</body>
<script type="text/javascript">
load = function(){	
	var data = ${data};
	console.log(data)
    var schedules = data.schedules;
    var locations = data.locations;
    var buses = data.buses;
    var drivers  = data.drivers;
    for (var i=0;i<schedules.length;i++)
    {
        var cc="bus_info";
        var ccl = 'color:blue';
        var schedule = '<tr class="hoverr search" s-title="'+schedules[i].code+'" data-url="schedule_detail?id='+schedules[i].id+'">'
            +'<td>'+schedules[i].code+'</td>'
            +'<td class="'+cc+'" data='+schedules[i].bus_id+' style="'+ccl+'">'+searchBus(schedules[i].bus_id,buses)+'</td>'
            +'<td class="driver_info" data='+schedules[i].driver_id+' style="color:blue">'+searchDriver(schedules[i].driver_id,drivers)+'</td>'
            +'<td>'+searchLocation(schedules[i].source_id,locations)+'</td>'
            +'<td>'+searchLocation(schedules[i].destination_id,locations)+'</td>'
            +'<td>'+formatDate(schedules[i].dept_date)+'</td>'
            +'<td>'+(schedules[i].dept_time)+'</td>'
            +'<td>'+schedules[i].no_seat+'</td>'
            +'<td>'+(schedules[i].number_staff+schedules[i].number_student+schedules[i].number_customer)+'</td></tr>';
        $("#allSchedules").append(schedule);
    }
    $( ".unhoverr" ).on('click', function(e) {
        e.stopPropagation();
        var s_id = parseInt($(this).attr('data-url'));
        deleteSchedule(s_id);
    });

    $(".hoverr").on('click', function(e) {
        e.stopPropagation();
        location.href=$(this).attr('data-url');
    });
    $( ".driver_info" ).on('click', function(e) {
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
    $( ".bus_info" ).on('click', function(e) {
        console.log("KK");
        e.stopPropagation();
        var id=$(this).attr('data');
        console.log(id);
        $.ajax({
            async: false,
            cache: false,
            type: "GET",
            url: "get_sch_bus_info2",
            data :{'id':id},
            timeout: 100000,
            success: function(data) {
                console.log(data);
                var data='<tr><th>Bus Model</th><td><b>:</b>  &nbsp&nbsp '+data[0].model+'</td></tr>'
                    +'<tr><th>Plate Number</th><td><b>:</b>  &nbsp&nbsp '+ data[0].plate_number+'</td></tr>'
                    +'<tr><th>Number of seats</th><td><b>:</b> &nbsp&nbsp '+data[0].number_seat +'</td></tr>'
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

function searchDriver(id, myArray){
    if(id==0)
        return"";
    for (var i=0; i < myArray.length; i++) {
        if (myArray[i].id === id) {
            return myArray[i].name;
        }
    }
}



function searchBus(id, myArray){
    if(id==0)
        return"";
    for (var i=0; i < myArray.length; i++) {
        if (myArray[i].id === id) {
            return myArray[i].model;
        }
    }
}



function timeConverter(t){
    var time=t;
    var hours = Number(time.match(/^(\d+)/)[1]);
    var minutes = Number(time.match(/:(\d+)/)[1]);
    var AMPM = time.match(/\s(.*)$/)[1].toLowerCase();

    if (AMPM == "pm" && hours < 12) hours = hours + 12;
    if (AMPM == "am" && hours == 12) hours = hours - 12;
    var sHours = hours.toString();
    var sMinutes = minutes.toString();
    if (hours < 10) sHours = "0" + sHours;
    if (minutes < 10) sMinutes = "0" + sMinutes;

    return(sHours +':'+sMinutes);

}
	



</script>
