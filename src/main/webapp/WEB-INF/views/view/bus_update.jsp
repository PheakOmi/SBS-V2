<body onload="load()">
<article class="content cards-page">
                    	
                    <section class="section">
                        <div class="row sameheight-container">
                            <div class="col-md-12">
                                <div class="card card-block sameheight-item">
                                    <div class="title-block">
                                        <h3 class="title"> Bus Information </h3>
                                    </div>
                                    <br><br>
                                    <form class="form-group" id ="myForm">
                                        <div class="form-group">
                                            <label for="exampleInputEmail3">Model</label>
                                            <input type="text" class="form-control" id="model" maxlength="20" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputEmail3">Plate Number</label>
                                            <input type="text" class="form-control" id="plate_number" maxlength="9" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputEmail3">Number of seat</label>
                                            <input type="text" class="form-control" id="number_of_seat" maxlength="2" required disabled>
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputEmail3">Description</label>
                                            <textarea rows="4" cols="100" class="form-inline boxed" id="description" maxlength="80"></textarea>
                                        </div>
                                        <button data-toggle="tooltip" title="Back To Previous Page" class="btn btn-default" id="btnCancel" onclick="parent.history.go(-1)">Cancel</button> 
                                        <button data-toggle="tooltip" title="Update Information" type="submit" class="btn btn-primary" on>Update</button>
                                       
                                        
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
	var bus = data.bus;
	$("#model").val(bus.model);
	$("#plate_number").val(bus.plate_number);
	$("#number_of_seat").val(bus.number_of_seat);
	$("#description").val(bus.description);
	id = bus.id;
}

$(document).ready(function(){
	$("#busMng").addClass("active");
	$("#number_of_seat").keypress(function (e) {
	     //if the letter is not digit then display error and don't type anything
	     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
	        //display error message
	        $("#errmsg").html("Digits Only").show().fadeOut("slow");
	               return false;
	    }
	   });
	$("#myForm").on('submit',function(e){
		e.preventDefault();
		var b_plate = $("#plate_number").val().trim();
		var b_model = $("#model").val().trim();
		var b_number_of_seat = $("#number_of_seat").val().trim();
		var b_des = $("#description").val().trim();
		var format = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/;
		if((b_plate=='') || (b_model=='')||(b_number_of_seat==''))
		{
		swal("Oops!", "The input cannot be empty", "error")
		return
		}
        if(b_model.toLowerCase().search("rental")!=-1)
        {
        swal("Oops!", "The keyword Rental is reserverd, you cannot create bus with this model!", "error")  
        return
        }
		if((format.test(b_plate)) || (format.test(b_model))|| (format.test(b_number_of_seat)))
		{
		swal("Oops!", "You cannot input special characters in any one of the fields", "error")  
		return
		}
		if(getlength(b_plate)>9)
		{
			swal("Oops!", "Plate Number Cannot Be More Than 9 Digits", "error")  
			return
		}
		if(getlength(b_model)>20)
		{
			swal("Oops!", "Model Cannot Be More Than 20 Characters", "error")  
			return
		}
		if(getlength(b_number_of_seat)>2)
		{
			swal("Oops!", "Number of seats Cannot Be More Than 2 Digits", "error")  
			return
		}
		if(getlength(b_des)>80)
		{
			swal("Oops!", "Description Cannot Be More Than 80 Digits", "error")  
			return
		}
		$.ajax({
    		url:'updateBus',
    		type:'GET',
    		data:{	id:id,
    				plate_number:b_plate,
    				model:b_model,
    				number_of_seat:parseInt(b_number_of_seat),
    				description:b_des
    			},
    		traditional: true,			
    		success: function(response){
    				if(response.status=="1")
    					{
    					setTimeout(function() {
    				        swal({
    				            title: "Done!",
    				            text: response.message,
    				            type: "success"
    				        }, function() {
    				            window.location = "bus_management";
    				        });
    				    }, 10);
    					
    					}
    				//var obj = jQuery.parseJSON(response);
    				else 
     					swal("Oops!", response.message, "error")    
    				
    				},
    		error: function(err){
    				console.log(JSON.stringify(err));
    				
    				}
    		
    			});	
		
	});	
});


function getlength(number) {
    return number.toString().length;
}

</script>
