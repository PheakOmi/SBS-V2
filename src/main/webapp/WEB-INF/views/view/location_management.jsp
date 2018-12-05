 <body onload="load()">
 <article class="content grid-page">
                    <div class="title-block">
                        <h3 class="title"> Location Management </h3><br>
                        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">Create</button>




                    </div>
 					<!-- section class="section">
                        <div class="row sameheight-container">
                            <div class="col-md-12">
                                <div class="card sameheight-item">
                                    <div class="card-block">
                                        
                                        <section class="section" id='allLocation'>
                                            
                                            
                                            
                                        </section>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section> -->
                    <section class="example">
                         <div>
                                <div class="card">
                                    <div class="card-block">
                                        <div class="card-title-block">
                                            <h3 class="title"> All Locations and Pick Up Locations </h3>
                                        </div>
                                        <section class="example">
                                            <div class="alltables">

                                            </div>
                                        </section>
                                    </div>
                                </div>
                            </div>
                            

                                                    
                                        </section>
                    </article>
</body>

 <div class="modal fade" id="myModal" role="dialog">
     <div class="modal-dialog">

         <!-- Modal content-->
         <div class="modal-content">
             <div class="modal-header">
                 <h4 class="modal-title center">Create a location</h4>
                 <button type="button" class="close" data-dismiss="modal">&times;</button>
             </div>
             <div class="modal-body">
                 <form id="myForm">
                     <div class="form-group">
                         <label class="control-label">Name</label>
                         <input type="text" class="form-control boxed" id="name" maxlength="30" placeholder="Name" required> </div>
                     <div>


                         <div class="form-group">
                             <label class="control-label">Abbreviation</label>
                             <input type="text" class="form-control boxed" id="sname" maxlength="30" placeholder="Abbr" required> </div>
                         <%--<div>--%>

                             <%--<label>--%>
                                 <%--<input class="checkbox rounded" type="checkbox" id="forstudent">--%>
                                 <%--<span>Also for students?</span>--%>
                             <%--</label>--%>
                         <%--</div>--%>
                         <%--<div class="form-group" style="display:none;" id="ddf">--%>
                             <%--<label class="control-label">Departure time for students</label>--%>
                             <%--<input type="text" name="time" class="form-control boxed" id="depttime"  required> </div>--%>

                         <button type="submit" id="bsubmit" class="btn btn-default" style="display:none;">Create</button>
                     </div>
                 </form>
             </div>
             <div class="modal-footer">
                 <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="background: grey;">Close</button>
                 <button onClick="goTO()" class="btn btn-info">Create</button>
             </div>
         </div>

     </div>
 </div>

 <div class="modal fade" id="myModal2" role="dialog">
     <div class="modal-dialog">

         <!-- Modal content-->
         <div class="modal-content">
             <div class="modal-header">
                 <h4 class="modal-title center">Create a pick up location</h4>
                 <button type="button" class="close" data-dismiss="modal">&times;</button>
             </div>
             <div class="modal-body">
                 <form id="myForm2">
                     <div class="form-group">
                         <label class="control-label">Name</label>
                         <input type="text" class="form-control boxed" id="pname" maxlength="30"  required> </div>

                     <button type="submit" id="bpsubmit" class="btn btn-default" style="display:none;">Create</button>
                 </form>
             </div>
             <div class="modal-footer">
                 <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="background: grey;">Close</button>
                 <button onClick="goTO2()" class="btn btn-info">Create</button>
             </div>
         </div>

     </div>
 </div>


 <div class="modal fade" id="myModal3" role="dialog">
     <div class="modal-dialog">

         <!-- Modal content-->
         <div class="modal-content">
             <div class="modal-header">
                 <h4 class="modal-title center">Update a location</h4>
                 <button type="button" class="close" data-dismiss="modal">&times;</button>
             </div>
             <div class="modal-body">
                 <form id="updateLocationForm">
                     <div class="form-group">
                         <label class="control-label">Name</label>
                         <input type="text" class="form-control boxed" id="updateLName" maxlength="30"  required> </div>

                     <button type="submit" id="updateLocationBtn" class="btn btn-default" style="display:none;">Create</button>
                 </form>
             </div>
             <div class="modal-footer">
                 <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="background: grey;">Close</button>
                 <button onClick="goTO3()" class="btn btn-info">Update</button>
             </div>
         </div>

     </div>
 </div>



 <div class="modal fade" id="myModal4" role="dialog">
     <div class="modal-dialog">

         <!-- Modal content-->
         <div class="modal-content">
             <div class="modal-header">
                 <h4 class="modal-title center">Update a  pick up location</h4>
                 <button type="button" class="close" data-dismiss="modal">&times;</button>
             </div>
             <div class="modal-body">
                 <form id="updatePLocationForm">
                     <div class="form-group">
                         <label class="control-label">Name</label>
                         <input type="text" class="form-control boxed" id="updatePLName" maxlength="30"  required> </div>

                     <button type="submit" id="updatePLocationBtn" class="btn btn-default" style="display:none;">Create</button>
                 </form>
             </div>
             <div class="modal-footer">
                 <button type="button" class="btn btn-default pull-left" data-dismiss="modal" style="background: grey;">Close</button>
                 <button onClick="goTO4()" class="btn btn-info">Update</button>
             </div>
         </div>

     </div>
 </div>

<script>
var l;
var llid;
var plid;
var p_location_data;
var location_data;
var status = 0;

load = function(){
	$(".wickedpicker").css('z-index',"1050");
	$.ajax({
		url:'getAllLocations',
		type:'GET',
		success: function(response){
			console.log(response);
			locations = response.locations;
			p_locations = response.p_locations;
    		for(var i=0;i<locations.length;i++)
    			{
                var l_card = '<table class="table table-bordered">'+
                                '<thead>'+
                                    '<tr>'+
                                        '<th style="background-color:grey; color:white;">'+locations[i].name+'<a data-toggle="modal" data-target="#myModal3" onClick="assignValue2('+locations[i].id+')"><i class="fa fa-edit" style=" margin-left: 10px;"></i></a><a data-toggle="modal" data-target="#myModal2" onClick="assignValue('+locations[i].id+')"><button type="button" class="btn btn-info btn-sm pull-right" style="padding:0.25rem 0.25rem; font-size:0.5rem; color:white;">Create Pick Up Location</button></a>'+'</th>'+
                                            '<th><a href="javascript:delLocation('+locations[i].id+')\"><i style="color:#e85a71;" class="fa fa-trash"></i></a></th>'+
                                    '</tr>'+
                                '</thead>'+
                                '<tbody id="location'+locations[i].id+'">'+
                                '</tbody></table>';
                $(".alltables").append(l_card);
    			
    			}
    		for (var i=0;i<p_locations.length;i++)
    			{
    			
                var p_card = '<tr>'+
                                '<td style=" padding-left: 40px;">'+p_locations[i].name+'<a data-toggle="modal" data-target="#myModal4" onClick="assignValue3('+p_locations[i].id+')"><i class="fa fa-edit" style=" margin-left: 10px;"></i></a>'+'</td>'+
                                '<td><a href="javascript:delPickUp('+p_locations[i].id+')\"><i class="fa fa-trash"></i></a></td>'+
                            '</tr>';
                $("#location" + p_locations[i].location_id).append(p_card);

    			}
			
			
	
	
		
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
goTO2 = function(){
	$('#bpsubmit').trigger('click');
	$('#pname').val("");
}
goTO3 = function(){	
	$('#updateLocationBtn').trigger('click');
}
goTO4 = function(){	
	$('#updatePLocationBtn').trigger('click');
}

 assignValue = function (a){
	l=a; 
}
 assignValue2 = function (a){
		llid=a;
		$.ajax({
    		url:'getLocationById',
    		type:'GET',
    		data:{	id:a   },
    		traditional: true,			
    		success: function(response){
    				location_data  = response.location;
    				$("#updateLName").val(location_data.name);
    				},
    		error: function(err){
    				console.log(JSON.stringify(err));
    				
    				}
    		
    			});	
		
	}
 assignValue3 = function (a){
		plid=a;
		$.ajax({
 		url:'getPickUpLocationById',
 		type:'GET',
 		data:{	id:a   },
 		traditional: true,			
 		success: function(response){
 				p_location_data  = response.p_location;
 				$("#updatePLName").val(p_location_data.name);
 				},
 		error: function(err){
 				console.log(JSON.stringify(err));
 				
 				}
 		
 			});	
		
	}

$(document).ready(function(){
	$("#locationMng").addClass("active");
    $(".sameheight-item").removeAttr("style");
	$("#myForm").on('submit',function(e){
		e.preventDefault();
		var b_name = $("#name").val().trim();
        var s_name = $("#sname").val().trim();
        var time = "nth";
        var fors = false;
        if(parseInt(status)>0){
            if(parseInt(status)%2!=0)
            {
                time = toDate($("#depttime").val(),'h:m')
                fors = true
            }
        }

		var format = /[!@#$%^&*()_~`+\-=\[\]{};':"\\|,.<>\/?]+/;
		if(b_name==''||s_name=='')
		{
		swal("Oops!", "The input cannot be empty", "error")
		return
		}
		if(format.test(b_name)||format.test(s_name))
		{
		swal("Oops!", "You cannot input special characters", "error")  
		return
		}
		if(getlength(b_name)>30||getlength(s_name)>30)
		{
			swal("Oops!", "Input Cannot Be More Than 30 Digits", "error")
			return
		}
		$.ajax({
    		url:'createLocation',
    		type:'GET',
    		data:{	name:b_name,
                    abbreviation:s_name,
                    // forstudent:fors,
                    // dept_time2:time
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
    				            window.location = "location_management";
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
	
	
	
	$("#myForm2").on('submit',function(e){
		console.log("Fired2")
		e.preventDefault();
		var p_name = $("#pname").val().trim();
		var format = /[!@#$%^&*()_~`+\-=\[\]{};':"\\|,.<>\/?]+/;
		if(p_name=='')
		{
		swal("Oops!", "The input cannot be empty", "error")
		return
		}
		if(format.test(p_name))
		{
		swal("Oops!", "You cannot input special characters", "error")  
		return
		}
		if(getlength(p_name)>30)
		{
			swal("Oops!", "Name Cannot Be More Than 30 Digits", "error")  
			return
		}
		$.ajax({
    		url:'createPickUpLocation',
    		type:'GET',
    		data:{	name:p_name,
    				location_id:l
    			},
    		traditional: true,			
    		success: function(response){
    				if(response.status=="1")
    					window.location.reload();
    				//var obj = jQuery.parseJSON(response);
    				else 
     					swal("Oops!", response.message, "error")    
    				
    				},
    		error: function(err){
    				console.log(JSON.stringify(err));
    				
    				}
    		
    			});	
		
	});	
	
	
	$("#updateLocationForm").on('submit',function(e){
		e.preventDefault();
		var p_name = $("#updateLName").val().trim();
		var format = /[!@#$%^&*()_~`+\-=\[\]{};':"\\|,.<>\/?]+/;
		if(p_name=='')
		{
		swal("Oops!", "The input cannot be empty", "error")
		return
		}
		if(format.test(p_name))
		{
		swal("Oops!", "You cannot input special characters", "error")  
		return
		}
		if(getlength(p_name)>30)
		{
			swal("Oops!", "Name Cannot Be More Than 30 Digits", "error")  
			return
		}
		$.ajax({
    		url:'updateLocation',
    		type:'GET',
    		data:{	id:llid,
    				name:p_name
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
    				            window.location = "location_management";
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

        

        $("#forstudent").change(function() {
            if(this.checked) {
                console.log("Called")
            }
        });
		
	});	
	
	
	

	$("#updatePLocationForm").on('submit',function(e){
		e.preventDefault();
		var p_name = $("#updatePLName").val().trim();
		var format = /[!@#$%^&*()_~`+\-=\[\]{};':"\\|,.<>\/?]+/;
		if(p_name=='')
		{
		swal("Oops!", "The input cannot be empty", "error")
		return
		}
		if(format.test(p_name))
		{
		swal("Oops!", "You cannot input special characters", "error")  
		return
		}
		if(getlength(p_name)>30)
		{
			swal("Oops!", "Name Cannot Be More Than 30 Digits", "error")  
			return
		}
		$.ajax({
    		url:'updatePickUpLocation',
    		type:'GET',
    		data:{	id:plid,
    				name:p_name
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
    				            window.location = "location_management";
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



delLocation=function(id)
{
swal({
    title: "Do you want to delete this location?",
    text: "All information related to this location will be deleted!",
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
    	     url:'deleteLocation',
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
    	                 window.location = "location_management";
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
    
    
delPickUp=function(id)
{
swal({
    title: "Do you want to delete this pick up location?",
    text: "All information related to this pick up location will be deleted!",
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
    	     url:'deletePickUpLocation',
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
    	                 window.location = "location_management";
    	             });
    	         }, 10);

    	      }

    	          else 
    	           {
    	           swal("Oops!","It is not deleted", "error")

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

// $('#forstudent').on('click', function(){
//     console.log("clicked")
//             $('#ddf').toggle();
//             status = parseInt(status) +1;
//         });

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