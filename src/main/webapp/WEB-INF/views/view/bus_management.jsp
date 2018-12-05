<body onload="load()">
<article class="content cards-page">
                    <div class="title-block">
                        <h3 class="title"> Bus Management </h3><br>
                        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">Create</button></div>
                        <section class="section">
                       <div>
                                <div class="card">
                                    <div class="card-block">
                                        <div class="card-title-block">
                                            <h3 class="title"> All Buses </h3>
                                        </div>
                                        <section class="example">
                                            <table class="table table-striped table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>No</th>
                                                        <th>Model</th>
                                                        <th>Plate Number</th>
                                                        <th>Number of seat</th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody id="all_bus">
                                                        
                                                </tbody>
                                            </table>
                                        </section>
                                    </div>
                                </div>
                            </div>
                            

                                        
                                        
                    </section>

                        <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">     
        <div class="modal-header">
          <h4 class="modal-title center">Create a bus</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          <form id="myForm">
                                        <div class="form-group">
                                            <label class="control-label">Model</label>
                                            <input type="text" class="form-control boxed" id="model" maxlength="20" required> </div>
                                         <div class="form-group">
                                            <label class="control-label">Plate Number</label>
                                            <input type="text" class="form-control boxed" id="plate_number" maxlength="9" required> </div>
                                         <div class="form-group">
                                            <label class="control-label">Number of seat</label>
                                            <input type="text" class="form-control boxed" id="number_of_seat" maxlength="2" required> </div>
                                        <div class="form-group">
                                            <label class="control-label">Description</label>
                                            <textarea rows="3" class="form-control boxed" id="description" maxlength="80"></textarea>
                                        </div>
                                         <button type="submit" id="bsubmit" class="btn btn-default" style="display:none;">Create</button>
                                    </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
          <button onClick="goTO()" class="btn btn-info">Create</button>
        </div>
      </div>
      
    </div>
  </div>
<!--                         <p class="title-description"> Create, Update or View all buses information </p> -->
                    </div>
                    <section class="section">
	                        
                        	
                        <!-- /.row -->
                    </section>
                    
                </article>
                
</body>
<script type="text/javascript">
load = function(){	
	$.ajax({
		url:'getAllBuses',
		type:'GET',
		success: function(response){
			buses = response.buses;
            console.log(buses)
        for(var i=0;i<buses.length;i++)
            {
               var row = "<tr class='hoverr' data-url='bus_update?id="+buses[i].id+"'><td>"+(i+1)+"</td>"+
                "<td>"+buses[i].model+"</td>"+
                "<td>"+buses[i].plate_number+"</td>"+
                "<td>"+buses[i].number_of_seat+"</td>"+
                '<td class="unhoverr" data-url="'+buses[i].id+'" style="color:#e85a71"><i class="fa fa-trash"></i></td></tr>';
                $("#all_bus").append(row);
            }

    $(".hoverr").on('click', function() {
                location.href=$(this).attr('data-url');
            });

    $( ".unhoverr" ).on('click', function(e) {
                e.stopPropagation();    
                var s_id = parseInt($(this).attr('data-url'));
                javascript:func(s_id);
            });
			
			
	
	
		
		},
	error: function(err){
		swal("Oops!", "Cannot get all buses data", "error")
		console.log(JSON.stringify(err));
		}
		
	});
	
}

goTO = function(){
	$('#bsubmit').trigger('click');
}


$(document).ready(function(){
	$("#busMng").addClass("active");
    var bootstrapjs = $("<script>");
    $(bootstrapjs).attr('src', '/resources/Bootstrap/js/bootstrap.min.js');
    $(bootstrapjs).appendTo('body');
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
		var format = /[!@#$%^&*()~`_+\-=\[\]{};':"\\|,.<>\/?]+/;
		if((b_plate=='') || (b_model=='')||(b_number_of_seat==''))
		{
		swal("Oops!", "The input cannot be empty", "error")
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
        parseInt(b_number_of_seat)
        if(parseInt(b_number_of_seat)<=0)
        {
            swal("Oops!", "Number of seat cannot be less than 0", "error")  
            return
        }
		$.ajax({
    		url:'createBus',
    		type:'GET',
    		data:{	plate_number:b_plate,
    				model:b_model,
    				number_of_seat:parseInt(b_number_of_seat),
    				description:b_des    			},
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

	


func=function(id)
{
swal({
    title: "Do you want to delete this bus?",
    text: "All information related to this bus will be deleted!",
    type: "warning",
    showCancelButton: true,
    confirmButtonColor: "#E71D36",
    confirmButtonText: "Delete",
    cancelButtonText: "Cancel",
    closeOnConfirm: false,
    closeOnCancel: true
  },
    function (isConfirm) {
      if (isConfirm) {
    	  $.ajax({
    	     url:'deleteBus',
    	     type:'GET',
    	     data:{id:id},
    	     traditional: true,
    	     success: function(response){
    	      if(response.status=="200")
    	      {
    	      setTimeout(function() {
    	             swal({
    	                 title: "Done!",
    	                 text: "You have deleted it successfully!",
    	                 type: "success"
    	             }, function() {
    	                 window.location = "bus_management";
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
    
function getlength(number) {
    return number.toString().length;
}
	
</script>
