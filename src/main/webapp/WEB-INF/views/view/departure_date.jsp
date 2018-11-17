<body onload="load()">
<article class="content cards-page">
                        
                    <section class="section">
                       <div class="col-md-6">
                                <div class="card">
                                    <div class="card-block">
                                        <div class="card-title-block">
                                            <h3 class="title"> All departure dates of each batch </h3>
                                        </div>
                                        <section class="example">
                                            <table class="table table-striped table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>No</th>
                                                        <th>Batch</th>
                                                        <th>Departure Date</th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody id="tdate">
                                                        
                                                </tbody>
                                            </table>
                                        </section>
                                    </div>
                                </div>
                            </div>
                            

                                        
                                        <div class="form-group col-md-12" style="margin-bottom:2%;">
                                            <button type="submit" id="create" class="btn btn-info" data-toggle="modal" data-target="#myModal">Create</button>                                  
                                        </div>
                    </section>
                    
                    
                </article>
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title center">Create a departure date for a batch</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          <form id="myForm">
                                        <div class="form-group">
                                            <label class="control-label">Batch Name</label>
                                            <input type="text" class="form-control boxed" id="batch"required> </div>
                                        <div class="form-group">
                                            <label class="control-label">Departure Date</label>
                                                <select class="form-control boxed" id="date"required>
                                                    <option value=0>Sunday</option>
                                                    <option value=1>Monday</option>
                                                    <option value=2>Tuesday</option>
                                                    <option value=3>Wednesday</option>
                                                    <option value=4>Thursday</option>
                                                    <option value=5>Friday</option>
                                                    <option value=6>Saturday</option>
                                                </select> 
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



<div class="modal fade" id="updateModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title center">Update a departure date for a batch</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          <form id="updateForm">
                                        <div class="form-group">
                                            <label class="control-label">Batch Name</label>
                                            <input type="text" class="form-control boxed" id="ubatch"required> </div>
                                        <div class="form-group">
                                            <label class="control-label">Departure Date</label>
                                                <select class="form-control boxed" id="udate"required>
                                                    <option value=0>Sunday</option>
                                                    <option value=1>Monday</option>
                                                    <option value=2>Tuesday</option>
                                                    <option value=3>Wednesday</option>
                                                    <option value=4>Thursday</option>
                                                    <option value=5>Friday</option>
                                                    <option value=6>Saturday</option>
                                                </select> 
                                            </div>
                                         <button type="submit" id="bsubmit2" class="btn btn-default" style="display:none;">Update</button>
                                    </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
          <button onClick="goTO2()" class="btn btn-info">Update</button>
        </div>
      </div>
      
    </div>
  </div>



                
</body>
<script type="text/javascript">
    load = function(){  
    var ad = ${data};
    data = ad.times;
    console.log(data)
    for(var i=0;i<data.length;i++)
    {
    var day =  dateName(data[i].date_of_leaving);
       
       var row = "<tr class='record' data-url='"+data[i].id+"'><td>"+(i+1)+"</td>"+
        "<td>"+data[i].name+"</td>"+
        "<td>"+day+"</td>"+
        '<td class="unhoverr" data-url="'+data[i].id+'" style="color:#e85a71"><i class="fa fa-trash"></i></td></tr>';
        $("#tdate").append(row);
    }


    $( ".record" ).on('click', function(e) {
                var s_id = parseInt($(this).attr('data-url'));
                idd = s_id;
                openModal(s_id);
            });


    $( ".unhoverr" ).on('click', function(e) {
                e.stopPropagation();    
                var s_id = parseInt($(this).attr('data-url'));
                deleteDate(s_id);
            });
    
}


$(document).ready(function(){
    $("#deptdateMng").addClass("active");

    $(".ir").slideToggle();
                        $("#dds1").toggleClass("irr");
                        $("#dds2").toggleClass("irr");
    $( "#settingMng" ).off();
   
    $("[name=time]").keydown(function (event) { 
            event.preventDefault();
        });


    


    $("#myForm").on('submit',function(e){
        e.preventDefault();
        var format = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/;
        if($("#batch").val()==""||$("#batch").val()==null)
        {
        swal("Action Disallowed!", "You cannot leave Batch field blank!", "error")
        return
        }
        if(getlength($("#batch").val())>20)
        {
            swal("Oops!", "Batch Name Cannot Be More Than 20 Characters!", "error")  
            return
        }
        if((format.test($("#batch").val())))
        {
        swal("Oops!", "You cannot input special characters in Batch!", "error")  
        return
        }
        $.ajax({
            url:'createDate',
            type:'GET',
            data:{  date_of_leaving:parseInt($("#date").val()),
                    name:$("#batch").val() },
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
                                window.location = "departure_date";
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





    $("#updateForm").on('submit',function(e){
        e.preventDefault();
        var format = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/;
        if($("#ubatch").val()==""||$("#ubatch").val()==null)
        {
        swal("Action Disallowed!", "You cannot leave Batch field blank!", "error")
        return
        }
        if(getlength($("#ubatch").val())>20)
        {
            swal("Oops!", "Batch Name Cannot Be More Than 20 Characters!", "error")  
            return
        }
        if((format.test($("#ubatch").val())))
        {
        swal("Oops!", "You cannot input special characters in Batch!", "error")  
        return
        }
        $.ajax({
            url:'updateDate',
            type:'GET',
            data:{  
                    id:idd,
                    date_of_leaving:parseInt($("#udate").val()),
                    name:$("#ubatch").val() },
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
                                window.location = "departure_date";
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











goTO = function(){
    $('#bsubmit').trigger('click');
}
goTO2 = function(){
    $('#bsubmit2').trigger('click');
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


deleteDate=function(s_id)
{
console.log(s_id)
swal({
    title: "Do you want to delete this departure date?",
    text: "",
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
                url:'deleteDate?id='+s_id,
                type:'GET',
                success: function(response){
                    if(response.status=="1")
                  {
                  setTimeout(function() {
                         swal({
                             title: "Done!",
                             text: response.message,
                             type: "success"
                         }, function() {
                            window.location.reload();
                         });
                     }, 10);

                  }

                      else
                       {
                       swal("Oops!",response.message, "error")

                       }        
                },
                error: function(err){
                swal("Oops!", "Cannot get all  data", "error")
                console.log(JSON.stringify(err));
                }
            });
      } 
    });
    }

function getlength(number) {
return number.toString().length;
}  

function searchDate(id, myArray){
    for (var i=0; i < myArray.length; i++) {
        if (myArray[i].id === id) {
            return myArray[i];
        }
    }
}

function openModal(s_id)
{
    $('#updateModal').modal('toggle');
    var d = searchDate(s_id,data);
    console.log(s_id)
    $("#ubatch").val(d.name);
    $("#udate").val(d.date_of_leaving);

}

function dateName(day)
{
    switch (day) {
    case 0:
        day = "Sunday";
        return day;
        ;
    case 1:
        day = "Monday";
        return day;
        ;
    case 2:
        day = "Tuesday";
        return day;
        ;
    case 3:
        day = "Wednesday";
        return day;
        ;
    case 4:
        day = "Thursday";
        return day;
        ;
    case 5:
        day = "Friday";
        return day;
        ;
    case 6:
        day = "Saturday";
        return day;
}
}
</script>