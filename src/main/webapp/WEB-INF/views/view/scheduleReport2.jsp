<body onload="load()">
<article class="content cards-page">
                   
                     
                  <section class="section">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-block">
                                        <div class="card-title-block">
                                            <h3 class="title"> Schedules Report </h3>
                                            
                                        </div>
                                        <section class="example">
                                            <div class="table-responsive">
                                                <table id="tt" class="table table-striped table-bordered table-hover">
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
                                        <div class="col-md-12">
                                <button class="btn btn-info pull-right" onClick="generateReport()"  style="color:white;" id="moveBtn"> <i class="fa fa-bar-chart"></i> Generate Report</button>
                            </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </article>

                
</body>
<script>
var locations;

$(document).ready(function(){
	var bootstrapjs = $("<script>");
  	$(bootstrapjs).attr('src', '/resources/Bootstrap/js/bootstrap.min.js');
  	$(bootstrapjs).appendTo('body');
	$("#sreport").addClass("active");
	
    $(".ir2").slideToggle();
    $("#ddr1").toggleClass("irr");
    $("#ddr2").toggleClass("irr");
    $( "#reportMng" ).off();
    
});

load = function(){
	var response = ${data};
    s2 = response
    var schedules = JSON.parse(response.schedules);
    var locations = response.locations;
    var buses = response.buses;
    var drivers  = response.drivers;
    for (var i=0;i<schedules.length;i++)
            {
            var schedule = '<tr search" s-title="'+schedules[i].code+'" data-url="schedule_detail?id='+schedules[i].id+'"><td>'+(i+1)+'</td>'
                                +'<td>'+schedules[i].code+'</td>'
                                +'<td>'+searchBus(schedules[i].bus_id,buses)+'</td>'
                                +'<td>'+searchDriver(schedules[i].driver_id,drivers)+'</td>'
                                +'<td>'+searchLocation(schedules[i].from_id,locations)+'</td>'
                                +'<td>'+searchLocation(schedules[i].to_id,locations)+'</td>'
                                +'<td>'+formatDate(schedules[i].dept_date)+'</td>'
                                +'<td>'+schedules[i].dept_time+'</td>'
                                +'<td>'+schedules[i].number_booking+'</td></tr>';
            $("#allSchedules").append(schedule);                
            }
            $( ".unhoverr" ).on('click', function(e) {
                e.stopPropagation();    
                var s_id = parseInt($(this).attr('data-url'));
                deleteSchedule(s_id);
            });
            
            
}




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
    for (var i=0; i < myArray.length; i++) {
        if (myArray[i].id === id) {
            return myArray[i].model;
        }
    }
}



generateReport = function()
{
            var data_type = 'data:application/vnd.ms-excel';
            var table_div = document.getElementById('tt');
            var table_html = table_div.outerHTML.replace(/ /g, '%20');
        
            var a = document.createElement('a');
            a.href = data_type + ', ' + table_html;
            a.download = 'Schedules_Report' + Math.floor((Math.random() * 9999999) + 1000000) + '.xls';
            a.click();
       
}
</script>