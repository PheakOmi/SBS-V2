<body onload="load()">
<article class="content cards-page">
                    	
                    <section class="section">
                        <div class="row sameheight-container">
                            <div class="col-md-12">
                                <div class="card card-block sameheight-item">
                                    <div class="title-block">
                                        <h3 class="title"> Schedule Detail </h3>
                                    </div>
                                    <form class="form-inline" id="myForm">
                                       
                                       <div class="form-group col-md-4" style="margin-bottom:2%;">
                                            <label for="exampleInputEmail3"  style="margin-right:4%;">Driver</label>
                                            <select class="form-control" style="width: inherit;" id="sdriver"><option></option></select> </div>
                                        <div class="form-group col-md-4" style="margin-bottom:2%;">
                                            <label for="exampleInputPassword3"  style="margin-right:4%;">Bus</label>
                                            <select class="form-control" style="width: inherit;" id="sbus" required><option></option></select> </div>
										<div class="form-group col-md-4" style="margin-bottom:2%;">
                                            <label for="exampleInputEmail3"  style="margin-right:4%;">From</label>
                                            <select class="form-control" style="width: inherit;" id="sfrom" required><option></option></select></div>
                                        <div class="form-group col-md-4" style="margin-bottom:2%;">
                                            <label for="exampleInputPassword3"  style="margin-right:4%;">To</label>
                                            <select class="form-control" style="width: inherit;" id="sto" required><option></option></select> </div>     
                                                                               
                                        <div class="form-group col-md-4" style="margin-bottom:2%;">
                                            <label for="exampleInputEmail3"  style="margin-right:4%;">Departure Date</label>
                                            <input type="text" name="no_past_date" class="form-control" style="width: inherit;" id="sdeptdate" placeholder="Departure Date" required> </div>
                                        <div class="form-group col-md-4" style="margin-bottom:2%;">
                                            <label for="exampleInputPassword3"  style="margin-right:4%;">Departure Time</label>
                                            <input type="text" name="time" class="form-control" style="width: inherit;" id="sdepttime" placeholder="Departure Time" required> </div>
                                        <div class="form-group col-md-4" style="margin-bottom:2%;">
                                            <label for="exampleInputPassword3"  style="margin-right:4%;">Number of seats</label>
                                            <input type="text" class="form-control" style="width: inherit;" id="sno_seat" maxlength="3" placeholder="Number of seats" required disabled> </div>
                                        <div class="form-group col-md-12" style="margin-bottom:2%;">
											<button type="submit" class="btn btn-info">Create</button>
											</div>
                                    </form>
                      <!--                  <div class="col-xl-12">
                                    <div class="card-block" style="padding-left:2px;">
                                        <ul class="nav nav-tabs nav-tabs-bordered">
                                            <li class="nav-item">
                                                <a href="#home" style="background-color:#52BCD3" class="nav-link active" data-target="#home" data-toggle="tab" aria-controls="home" role="tab">All bookings of this schedule</a>
                                            </li>
                                        
                                        </ul>
                                        
                                        <div class="tab-content tabs-bordered">
                                            <div class="tab-pane fade in active" style="display:initial;" id="home">
                                                <div class="table-responsive">
                                                <table class="table table-striped table-bordered table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th>No</th>
                                                            <th>Name</th>
                                                            <th>Number of bookings</th>
                                                            
                                                        </tr>
                                                    </thead>
                                                    <tbody id="allBooking">
                                                        
                                                    </tbody>
                                                    
                                                </table>
                                            </div>
                                            </div>
                                            
                                        </div>
                                    </div>
                                  
                            </div>  -->
                                </div>
                            </div>
                        </div>
                    </section>
                    
                    
                </article>
                
</body>
<script type="text/javascript">
var locations;
var s_code;
var all_driver;
var all_bus;
load = function () {
	var data = ${data};
	console.log(data)
	var buses  = data.buses;
	all_bus = buses;
	var code = data.code;
	s_code = code;
	locations = data.main_locations;
	var drivers = data.drivers;
	all_driver = drivers;
	for(i=0; i<buses.length; i++)					
		$("#sbus").append("<option value="+buses[i].id+">"+buses[i].model+" "+buses[i].plate_number+" </option>");
	for(i=0; i<locations.length; i++)
		$("#sfrom").append("<option value="+locations[i].id+">"+locations[i].name+" </option>");
	for(i=0; i<drivers.length; i++)					
		$("#sdriver").append("<option value="+drivers[i].id+">"+drivers[i].name+" </option>");
	
	
}



$(document).ready(function(){
	$("#scheduleMng").addClass("active");
	
    $("[name=date]").keydown(function (event) {
            event.preventDefault();
        });
    $("[name=time]").keydown(function (event) { 
            event.preventDefault();
        });

    $("#sno_seat").keypress(function (e) {
        //if the letter is not digit then display error and don't type anything
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {

            return false;
        }
    });


	$("#sfrom").change(function(){
		var input  = this.value;
		var location_id;
		$('#sto').children('option:not(:first)').remove();
		for(i=0; i<locations.length; i++)
			{
			if(locations[i].id!=input)

			$("#sto").append("<option value="+locations[i].id+">"+locations[i].name+" </option>");
			
		}	
	    
	});

    $("#sbus").change(function(){
        var input  = this.value;
        $("#sno_seat").val(searchBusSeat(input, all_bus))

    });


	
	$("#myForm").on('submit',function(e){
		e.preventDefault();
		var dateee = $("#sdeptdate").val();
		var convertedDate = dateee.replace(/(\d\d)\/(\d\d)\/(\d{4})/, "$3-$1-$2");
        console.log(convertedDate)
        var driver, bus;
		if($("#sdriver").val()==""||$("#sdriver").val()==null)
		{
            driver = 0
		}
		else
		    driver = parseInt($("#sdriver").val())
		if($("#sbus").val()==""||$("#sbus").val()==null)
		{
            bus = 0
		}
		else
		    bus = parseInt($("#sbus").val())
		if($("#sfrom").val()==""||$("#sfrom").val()==null)
		{
		swal("Action Disallowed!", "You cannot leave From field blank!", "error")
		return
		}
		if($("#sto").val()==""||$("#sto").val()==null)
		{
		swal("Action Disallowed!", "You cannot leave To field blank!", "error")
		return
		}
		if($("#sdeptdate").val()==""||$("#sdeptdate").val()==null)
		{
		swal("Action Disallowed!", "You cannot leave Departure Date field blank!", "error")
		return
		}
		if($("#sdepttime").val()==""||$("#sdepttime").val()==null)
		{
		swal("Action Disallowed!", "You cannot leave Departure Time blank!", "error")
		return
		}
		$.ajax({
    		url:'createSchedule',
    		type:'GET',
    		data:{
    				driver_id:driver,
    				bus_id:bus,
    				source_id:parseInt($("#sfrom").val()),
    				destination_id:parseInt($("#sto").val()),
    				no_seat:parseInt($("#sno_seat").val()),
    				dept_date:convertedDate,
    				dept_time:toDate($("#sdepttime").val(),'h:m')
    			},
    		traditional: true,			
    		success: function(response){
    				console.log(response)
    				
    				
    				if(response.status=="all fine")
			  		{
    					setTimeout(function() {
    				        swal({
    				            title: "Done!",
    				            text: response.message,
    				            type: "success"
    				        }, function() {
    				            window.location = "current_schedule";
    				        });
    				    }, 10);

			  		}

			 		
    				else 
     					swal("Oops!", response.message, "error")    
    				
    				},
    		error: function(err){
    				console.log(JSON.stringify(err));
    				
    				}
    		
    			});	
		
	});
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




function searchBusSeat(id, myArray){
    for (var i=0; i < myArray.length; i++) {
        if (myArray[i].id === parseInt(id)) {
            return myArray[i].number_of_seat;
        }
    }
}

function toDate(dStr,format) {
	var now = new Date();
	if (format == "h:m") {
 		now.setHours(dStr.substr(0,dStr.indexOf(":")));
 		now.setMinutes(dStr.substr(dStr.indexOf(":")+1));
 		now.setSeconds(0);
 		return now.getHours()+":"+now.getMinutes()+":"+now.getSeconds();
	}else 
		return "Invalid Format";
}

	
</script>
