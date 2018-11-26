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
                
                
              
              
              
                
                <button style="display: none;"class="btn btn-primary" data-toggle="modal" data-target="#myModal" data-backdrop="static" data-keyboard="false" id="mybtn">
    Login modal</button>
    <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title center">Which schedules?</h4>
          <button onClick="closeModal()" type="button" class="close">&times;</button>
        </div>
        <div class="modal-body">
          <form id="myForm">
                                        <div class="form-group">
                                            <label class="control-label">Bus</label>
                                            <select class="form-control boxed" id="bus">
                                                <option></option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Driver</label>
                                            <select class="form-control boxed" id="driver">
                                                <option></option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">From (Source Location)</label>
                                            <select class="form-control boxed" id="from">
                                            	<option></option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">To (Destination Location)</label>
                                            <select class="form-control boxed" id="to">
                                            	<option></option>
                                            </select>
                                        </div>
                                        
                                         <div class="form-group">
                                            <label class="control-label">Departure Date</label>
                                            <input type="text" name ="date" class="form-control boxed" id="deptdate"> </div>
                                         <div class="form-group">
                                            <label class="control-label">Departure Time</label>
                                            <select class="form-control boxed" id="depttime">
                                                <option value="nth"></option>
                                            </select> 
                                            </div>
                                         
                                            <button type="submit" id="bsubmit" class="btn btn-default" style="display:none;">Create</button>
                                        	</form>
        </div>
        <div class="modal-footer">
          <button onClick="closeModal()" type="button" style="color:black;" class="btn btn-default">Close</button>
          <button onClick="goTO()" class="btn btn-info">View Before Generating</button>
        </div>
      </div>
      
    </div>
  </div>
  
  
  
  

                
</body>
<script>
var locations;

$(document).ready(function(){
	var bootstrapjs = $("<script>");
  	$(bootstrapjs).attr('src', '/resources/Bootstrap/js/bootstrap.min.js');
  	$(bootstrapjs).appendTo('body');
	$("#sreport").addClass("active");
	// $('#myModal').on('hide.bs.modal', function (e) {
	// 	window.location.href = "current_schedule";
	// })
	
    $(".ir2").slideToggle();
    $("#ddr1").toggleClass("irr2");
    $("#ddr2").toggleClass("irr2");
    $( "#reportMng" ).off();
    $("[name=date]").keydown(function (event) {
            event.preventDefault();
        });

	$("#from").change(function(){
		var input  = this.value;
		var location_id;
		$('#to').children('option:not(:first)').remove();
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
			$("#to").append("<option value="+locations[i].id+">"+locations[i].name+" </option>");
			
		}	
	    
	});
	
	
	$("#myForm").on('submit',function(e){
		count=0;
		e.preventDefault();
        var from = $("#from").val();
        var to = $("#to").val();
        var date = $("#deptdate").val();
        var time = $("#depttime").val();
        var bus  = $("#bus").val();
        var driver  = $("#driver").val();
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
		if(date==""||date==null)
			{
                count++;
                date = "nth";
            }
		if(time=="nth")
			count++;
        if(bus==""||bus==null)
        {
            count++;
            bus = 0;
        }
        else
            bus = parseInt(bus);
        if(driver==""||driver==null)
        {
            count++;
            driver = 0;
        }
        else
            driver = parseInt(driver);
        
		if(count>=6)
			{
			swal("Action Disallowed!", "Please fill out at least one field!", "error")
			return
			}
		else
			{
			$.ajax({
    		url:'sReportSubmit',
    		type:'GET',
    		data:{	from_id:from,
    				to_id:to,
    				dept_date:date,
    				n:time,
                    schedule_id:bus,
                    user_id:driver

    			},
    		traditional: true,			
    		success: function(response){
                     if(response.schedules.length>0)
                    {
                        setTimeout(function() {
                            swal({
                                title: "Done!",
                                text: response.schedules.length+" schedules were found!",
                                type: "success"
                            }, function() {

                                var schedules = response.schedules;
                                var locations = response.locations;
                                var buses = response.buses;
                                var drivers  = response.drivers;
                                for (var i=0;i<schedules.length;i++)
                                        {
                                        var schedule = '<tr search" s-title="'+schedules[i].code+'" data-url="schedule_detail?id='+schedules[i].id+'">'
                                                            +'<td>'+schedules[i].code+'</td>'
                                                            +'<td>'+searchBus(schedules[i].bus_id,buses)+'</td>'
                                                            +'<td>'+searchDriver(schedules[i].driver_id,drivers)+'</td>'
                                                            +'<td>'+searchLocation(schedules[i].source_id,locations)+'</td>'
                                                            +'<td>'+searchLocation(schedules[i].destination_id,locations)+'</td>'
                                                            +'<td>'+formatDate(schedules[i].dept_date)+'</td>'
                                                            +'<td>'+schedules[i].dept_time+'</td>'
                                                            +'<td>'+(schedules[i].number_staff+schedules[i].number_student+schedules[i].number_customer)+'</td></tr>';
                                        $("#allSchedules").append(schedule);                
                                        }
                                        $('#myModal').modal('toggle');
                                        $( ".unhoverr" ).on('click', function(e) {
                                            e.stopPropagation();    
                                            var s_id = parseInt($(this).attr('data-url'));
                                            deleteSchedule(s_id);
                                        });
                            });
                        }, 10);
                    }
                    else 
                        swal("Oops!", "No schedules as you required!", "error")
                  },
    		error: function(err){
    				console.log(JSON.stringify(err));
    				
    				}
    		
    			});	
			}
		
	});
	
	
	
	
});

load = function(){
	$('#mybtn').trigger('click');
    var ad = ${data};
    console.log(ad);
    locations = ad.locations;
    var times = ad.times;
    var buses = ad.buses;
    var drivers = ad.drivers;
    for(i=0; i<locations.length; i++)                   
        $("#from").append("<option value="+locations[i].id+">"+locations[i].name+" </option>");
    for(i=0; i<times.length; i++)                   
        $("#depttime").append("<option value="+times[i].dept_time+">"+times[i].dept_time+" </option>");
    for(i=0; i<buses.length; i++)                   
        $("#bus").append("<option value="+buses[i].id+">"+buses[i].model+" "+buses[i].plate_number+" </option>");
	for(i=0; i<drivers.length; i++)                   
        $("#driver").append("<option value="+drivers[i].id+">"+drivers[i].name+" </option>");
}


goTO = function(){
    $('#bsubmit').trigger('click');
}

closeModal = function(){
    window.location.href = "current_schedule";
}

function toDate(dStr,format) {
    var now = new Date();
    console.log(dStr)
    if (format == "h:m") {
        now.setHours(dStr.substr(0,dStr.indexOf(":")));
        now.setMinutes(dStr.substr(dStr.indexOf(":")+1));
        now.setSeconds(0);
        return now.getHours()+":"+now.getMinutes()+":"+now.getSeconds();
    }else 
        return "Invalid Format";
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