<body onload="load()">
<article class="content cards-page">
                        
                    <section class="section">
                       <div class="col-md-6">
                                <div class="card">
                                    <div class="card-block">
                                        <div class="card-title-block">
                                            <h3 class="title"> All departure time </h3>
                                        </div>
                                        <section class="example">
                                            <table class="table table-striped table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>No</th>
                                                        <th>Departure Time</th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody id="ttime">
                                                        
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
          <h4 class="modal-title center">Create a departure time</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          <form id="myForm">
                                        <div class="form-group">
                                            <label class="control-label">Departure Time</label>
                                            <input type="text" name="time" class="form-control boxed" id="depttime"required> </div>
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

                
</body>
<script type="text/javascript">
    load = function(){  
    var ad = ${data};
    var data = ad.times;
    console.log(data)
    $(".wickedpicker").css('z-index',"1050");
    for(var i=0;i<data.length;i++)
    {
       var row = "<tr class='record'><td>"+(i+1)+"</td>"+
        "<td>"+data[i].dept_time+"</td>"+
        '<td class="unhoverr" data-url="'+data[i].id+'" style="color:#e85a71"><i class="fa fa-trash"></i></td></tr>';
        $("#ttime").append(row);
    }
    $( ".unhoverr" ).on('click', function(e) {
                e.stopPropagation();    
                var s_id = parseInt($(this).attr('data-url'));
                deleteTime(s_id);
            });
}


$(document).ready(function(){
    $("#depttimeMng").addClass("active");

    $(".ir").slideToggle();
                        $("#dds1").toggleClass("irr");
                        $("#dds2").toggleClass("irr");
    $( "#settingMng" ).off();
   
    $("[name=time]").keydown(function (event) { 
            event.preventDefault();
        });

    $("#myForm").on('submit',function(e){
        e.preventDefault();
        $.ajax({
            url:'createTime',
            type:'GET',
            data:{  dept_time:toDate($("#depttime").val(),'h:m') },
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
                                window.location = "departure_time";
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


deleteTime=function(s_id)
{
console.log(s_id)
swal({
    title: "Do you want to delete this departure time?",
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
                url:'deleteTime?id='+s_id,
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
</script>