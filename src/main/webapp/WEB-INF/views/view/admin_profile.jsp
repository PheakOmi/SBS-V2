<body>
<article class="content cards-page">
                    	
<section class="section">
    <div class="row sameheight-container">
        <div class="col-md-12">
            <div class="card card-block sameheight-item">
                <div class="title-block">
                    <h3 class="title"> Change Password </h3>
                </div>
                <form class="form-inline" id="myForm">
                   
                    <div class="form-group col-md-4" style="margin-bottom:2%;">
                        <label for="exampleInputPassword3"  style="margin-right:4%;">Current Password</label>
                        <input type="password" class="form-control" style="width: inherit;" id="ucurrentpassword" placeholder="Current Password" required> </div>
                    <div class="form-group col-md-4" style="margin-bottom:2%;">
                        <label for="exampleInputPassword3"  style="margin-right:4%;">New Password</label>
                        <input type="password" class="form-control" style="width: inherit;" id="unewpassword" placeholder=Password required> </div>
                    <div class="form-group col-md-4" style="margin-bottom:2%;">
                        <label for="exampleInputPassword3"  style="margin-right:4%;">Confirm Password</label>
                        <input type="password" class="form-control" style="width: inherit;" id="uconfirmpassword" placeholder="Confirm Password" required> </div>
                    

                    <div class="form-group col-md-12" style="margin-bottom:2%;">
						<button type="submit" data-toggle="tooltip" title="Update Information" class="btn btn-info">Update</button>
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
<input type="hidden" id="csrfToken" value="${_csrf.token}"/>
<input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>    
</body>
<script type="text/javascript">

$(document).ready(function(){

    var token = $('#csrfToken').val();
    var header = $('#csrfHeader').val();
    axios.defaults.headers.common[header] = token;




	
	$("#myForm").on('submit',function(e){
		e.preventDefault();
        var format = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/;
        if($("#ucurrentpassword").val()==""||$("#ucurrentpassword").val()==null)
        {
        swal("Action Disallowed!", "You cannot leave Current Password field blank!", "error")
        return
        }
		if($("#unewpassword").val()==""||$("#unewpassword").val()==null)
		{
		swal("Action Disallowed!", "You cannot leave New Password field blank!", "error")
		return
		}
		if($("#uconfirmpassword").val()==""||$("#uconfirmpassword").val()==null)
		{
		swal("Action Disallowed!", "You cannot leave Confirm Password field blank!", "error")
		return
		}
		
        
        if($("#unewpassword").val()!=$("#uconfirmpassword").val())
        {
        swal("Oops!", "Passwords are not matched!", "error")  
        return
        }
        
        
        if(getlength($("#unewpassword").val())>30)
        {
            swal("Oops!", "New Password Cannot Be More Than 30 Characters", "error")  
            return
        }
         if(getlength($("#unewpassword").val())<8)
        {
            swal("Oops!", "New Password Cannot Be Less Than 8 Characters", "error")  
            return
        }


		
        axios.post('changePass', {
                    password:$("#ucurrentpassword").val(),
                    email:eee,
                    profile:$("#unewpassword").val()
            
        })
            .then(function (response) {
                console.log(response.data)
                if(response.data.status=="1")
                        {
                        setTimeout(function() {
                            swal({
                                title: "Done!",
                                text: response.data.message,
                                type: "success"
                            }, function() {
                                formSubmit();
                            });
                        }, 10);
                        
                        }
                    //var obj = jQuery.parseJSON(response);
                    else     
                        swal("Oops!", response.data.message, "error")
                
            })
            .catch(function(e){
                    console.log(JSON.stringify(e)); 
            })


        
	});
});

function getlength(number) {
return number.toString().length;
}      
	
</script>
