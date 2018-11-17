<body onload="load()">
<article class="content cards-page">
                        
                    <section class="section">
                       <div class="col-md-6">
                                <div class="card">
                                    <div class="card-block">
                                        <div class="card-title-block">
                                            <h3 class="title"> All Prices </h3>
                                        </div>
                                        <section class="example">
                                            <table class="table table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th>No</th>
                                                        <th>Customer Type</th>
                                                        <th>Price</th>
                                                        
                                                    </tr>
                                                </thead>
                                                <tbody id="tprice">
                                                        
                                                </tbody>
                                            </table>
                                        </section>
                                    </div>
                                </div>
                            </div>
                            

                                        
                                        <div class="form-group col-md-12">
                                            <button type="submit" id="create" class="btn btn-info" data-toggle="modal" data-target="#createModal">Create Prices</button>                                  
                                        </div>
                                        <div class="form-group col-md-12" style="margin-bottom:2%;">
                                            <button type="submit" id="update" class="btn btn-info" onclick="openModal()">Update Prices</button>                                  
                                        </div>
                    </section>
                    
                    
                </article>
<div class="modal fade" id="createModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title center">Create prices</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          <form id="createForm">
                                        <div class="form-group">
                                            <label class="control-label">Adult</label>
                                            <input type="text" maxlength="2" class="form-control boxed nonly" id="adultc"required> </div>
                                        <div class="form-group">
                                            <label class="control-label">Child</label>
                                            <input type="text" maxlength="2" class="form-control boxed nonly" id="childc"required> </div>
                                         <button type="submit" id="createsubmit" class="btn btn-default" style="display:none;">Create</button>
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
          <h4 class="modal-title center">Update prices</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          <form id="updateForm">
                                        <div class="form-group">
                                            <label class="control-label">Adult</label>
                                            <input type="text" maxlength="2" class="form-control boxed nonly" id="adultu"required> </div>
                                        <div class="form-group">
                                            <label class="control-label">Child</label>
                                            <input type="text" maxlength="2" class="form-control boxed nonly" id="childu"required> </div>
                                         <button type="submit" id="updatesubmit" class="btn btn-default" style="display:none;">Update</button>
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
    data = ad.price;
    console.log(data)
    if(data!=null)
    {
        $("#create").hide();
        var adult = "<tr><td>1</td>"+
        "<td>Adult</td>"+
        "<td>"+data.adult+"</td></tr>";
        var child = "<tr><td>2</td>"+
        "<td>Child</td>"+
        "<td>"+data.child+"</td></tr>";
        $("#tprice").append(adult);
        $("#tprice").append(child);
    }
    else
        $("#update").hide();
    
    
}


$(document).ready(function(){
    $("#priceMng").addClass("active");

    $(".ir").slideToggle();
                        $("#dds1").toggleClass("irr");
                        $("#dds2").toggleClass("irr");
    $( "#settingMng" ).off();

    $(".nonly").keypress(function (e) {
         //if the letter is not digit then display error and don't type anything
         if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            //display error message
            $("#errmsg").html("Digits Only").show().fadeOut("slow");
                   return false;
        }
       });
   
    


    


    $("#createForm").on('submit',function(e){
        e.preventDefault();
        $.ajax({
            url:'createPrice',
            type:'GET',
            data:{  adult:$("#adultc").val(),
                    child:$("#childc").val() },
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
                                window.location = "pricemng";
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
        $.ajax({
            url:'updatePrice',
            type:'GET',
            data:{  adult:$("#adultu").val(),
                    child:$("#childu").val() },
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
                                window.location = "pricemng";
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
    $('#createsubmit').trigger('click');
}
goTO2 = function(){
    $('#updatesubmit').trigger('click');
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

function openModal()
{
    $('#updateModal').modal('toggle');
    $("#adultu").val(data.adult);
    $("#childu").val(data.child);

}

</script>