<body onload="load()">
<article class="content cards-page">

<section class="section">
    <div class="row sameheight-container">
        <div class="col-md-12">
            <div class="card card-block sameheight-item">
                <div class="title-block">
                    <h3 class="title"> Refill Tickets For Users </h3>
                </div>



                    <div class="form-group col-md-12" style="margin-bottom:2%;color: white;">
						<button class="btn btn-info" style="color: white;" data-toggle="modal" data-target="#myModal">Click To Refilll</button>
						</div>
            </div>
        </div>
    </div>
</section>

</article>
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">

      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title center">Refill tickets for users</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          <form id="myForm">
              <div class="form-group">
                  <label>
                      <input class="checkbox rounded" type="checkbox" id="forall">
                      <span>Refill for all user</span>
                  </label>
              </div>
              <div class="form-group" id="duser">
                  <label class="control-label">Users</label>
                  <select class="form-control boxed js-example-basic-multiple" multiple="multiple" id="student" required></select>
              </div>


              <div class="form-group">
                  <label>
                      <input class="checkbox rounded" type="checkbox" id="add">
                      <span>Include with current ticket</span>
                  </label>
              </div>
              <div class="form-group">
                  <label class="control-label">Number of tickets</label>
                  <input type="text" class="form-control boxed" id="noticket" maxlength="2" required> </div>

               <button type="submit" id="bsubmit" class="btn btn-default" style="display:none;">Create</button>
          </form>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
          <button type="button " class="btn btn-info" onClick="goTO()">Create</button>
        </div>
      </div>

    </div>
  </div>
</body>
<script type="text/javascript">

$(document).ready(function(){
	$("#ticketMng").addClass("active");


    $(".ir").slideToggle();
                        $("#dds1").toggleClass("irr");
                        $("#dds2").toggleClass("irr");
	$( "#settingMng" ).off();

  $("#myForm").on('submit',function(e){
    console.log("Fired");
  	e.preventDefault();
  	$.ajax({
    		url:'refillTicket',
    		type:'GET',
    		data:{	    forall:true,
                    forold:true,
                    users:[],
                    noticket:10
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


	$("#noticket").keypress(function (e) {
         //if the letter is not digit then display error and don't type anything
         if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            //display error message
            $("#errmsg").html("Digits Only").show().fadeOut("slow");
                   return false;
        }
       });

    $("#forall").change(function() {
            if(this.checked) {
                $("#duser").hide();
            }
            else
                $("#duser").show();
        });


});

goTO = function(){
  $('#bsubmit').trigger('click');
  console.log("AAA");
}

load = function(){
  $.ajax({
        url:'getAllStudents',
        type:'GET',
        success: function(response){
          r = response;
          $('#student').select2({data: response.students});

        },
      error: function(err){
        swal("Oops!", "Cannot get all student data", "error")
        console.log(JSON.stringify(err));
        }

});
}

</script>
