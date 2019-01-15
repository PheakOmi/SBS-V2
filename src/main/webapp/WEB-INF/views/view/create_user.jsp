<body>
<article class="content cards-page">
                    	
<section class="section">
    <div class="row sameheight-container">
        <div class="col-md-12">
            <div class="card card-block sameheight-item">
                <div class="title-block">
                    <h3 class="title"> Create User </h3>
                </div>
                <form class="form-inline" id="myForm">
                   <div class="form-group col-md-4" style="margin-bottom:2%;">
                        <label for="exampleInputPassword3"  style="margin-right:4%;">Username</label>
                        <input type="text" class="form-control" style="width: inherit;" id="uusername" placeholder="Username" required> </div>
                    <div class="form-group col-md-4" style="margin-bottom:2%;">
                        <label for="exampleInputPassword3"  style="margin-right:4%;">Email</label>
                        <input type="text" class="form-control" style="width: inherit;" id="uemail" placeholder=Email required> </div>
                    <div class="form-group col-md-4" style="margin-bottom:2%;">
                        <label for="exampleInputPassword3"  style="margin-right:4%;">Password</label>
                        <input type="password" class="form-control" style="width: inherit;" id="upassword" placeholder=Password required> </div>
                    <div class="form-group col-md-4" style="margin-bottom:2%;">
                        <label for="exampleInputPassword3"  style="margin-right:4%;">Confirm Password</label>
                        <input type="password" class="form-control" style="width: inherit;" id="uconfirmpassword" placeholder="Confirm Password" required> </div>
                    <div class="form-group col-md-4" style="margin-bottom:2%;">
                        <label for="exampleInputPassword3"  style="margin-right:4%;">Phone Number</label>
                        <input type="text" class="form-control" style="width: inherit;" id="uphonenumber" placeholder="Phone Number" required> </div>
                   
                    <div class="form-group col-md-4" style="margin-bottom:2%;">
                        <label for="exampleInputEmail3"  style="margin-right:4%;">User Type</label>
                        <select class="form-control" style="width: inherit;" id="uusertype" required>
                            <option value="ROLE_ADMIN">Admin</option>
                            <option value="ROLE_DRIVER">Driver</option>

                        </select></div>

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
<input type="hidden" id="csrfToken" value="${_csrf.token}"/>
<input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>    
</body>
<script type="text/javascript">

$(document).ready(function(){
	$("#userMng").addClass("active");

    var token = $('#csrfToken').val();
    var header = $('#csrfHeader').val();
    axios.defaults.headers.common[header] = token;


    $(".ir").slideToggle();
                        $("#dds1").toggleClass("irr");
                        $("#dds2").toggleClass("irr");
	$( "#settingMng" ).off();
    $("[name=date]").keydown(function (event) {
            event.preventDefault();
        });
    $("[name=time]").keydown(function (event) { 
            event.preventDefault();
        });


	$("#uphonenumber").keypress(function (e) {
         //if the letter is not digit then display error and don't type anything
         if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            //display error message
            $("#errmsg").html("Digits Only").show().fadeOut("slow");
                   return false;
        }
       });
	
	$("#myForm").on('submit',function(e){
		e.preventDefault();
        var format = /[!@#$%^&*()~`_+\-=\[\]{};':"\\|,.<>\/?]+/;
		if($("#uusername").val()==""||$("#uusername").val()==null)
		{
		swal("Action Disallowed!", "You cannot leave Name field blank!", "error")
		return
		}
		if($("#uemail").val()==""||$("#uemail").val()==null)
		{
		swal("Action Disallowed!", "You cannot leave Email field blank!", "error")
		return
		}
		if($("#upassword").val()==""||$("#upassword").val()==null)
		{
		swal("Action Disallowed!", "You cannot leave Password field blank!", "error")
		return
		}
		if($("#uconfirmpassword").val()==""||$("#uconfirmpassword").val()==null)
		{
		swal("Action Disallowed!", "You cannot leave Confirm Password field blank!", "error")
		return
		}
		if($("#uphonenumber").val()==""||$("#uphonenumber").val()==null)
		{
		swal("Action Disallowed!", "You cannot leave Phone Number field blank!", "error")
		return
		}
		if($("#uusertype").val()==""||$("#uusertype").val()==null)
		{
		swal("Action Disallowed!", "You cannot leave User Type blank!", "error")
		return
		}
        if((format.test($("#uusername").val())) || (format.test($("#uphonenumber").val())))
        {
        swal("Oops!", "You cannot input special characters in Name or Phone Number field!", "error")  
        return
        }
        if($("#upassword").val()!=$("#uconfirmpassword").val())
        {
        swal("Oops!", "Passwords are not matched!", "error")  
        return
        }
        if( !validateEmail($("#uemail").val()))
        {
        swal("Oops!", "Email is not in correct format!", "error")  
        return
        }

        if(getlength($("#uusername").val())>30)
        {
            swal("Oops!", "Name Cannot Be More Than 30 Characters", "error")  
            return
        }
        if(getlength($("#uusername").val())<2)
        {
            swal("Oops!", "Name Cannot Be Less Than 2 Characters", "error")  
            return
        }
        if(getlength($("#upassword").val())>30)
        {
            swal("Oops!", "Password Cannot Be More Than 30 Characters", "error")  
            return
        }
         if(getlength($("#upassword").val())<8)
        {
            swal("Oops!", "Password Cannot Be Less Than 8 Characters", "error")  
            return
        }
        if(getlength($("#uphonenumber").val())>15)
        {
            swal("Oops!", "Phone Number Cannot Be More Than 15 Digits", "error")  
            return
        }
        if(getlength($("#uphonenumber").val())<8)
        {
            swal("Oops!", "Phone Number Cannot Be Less Than 8 Digits", "error")  
            return
        }


		
        axios.post('createUserr', {
                    username:$("#uusername").val(),
                    email:$("#uemail").val(),
                    password:$("#upassword").val(),
                    phone_number:$("#uphonenumber").val(),
                    profile:$("#uusertype").val()
            
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
                                window.location = "create_user";
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


formatDate =function (date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
      if (day.length < 2) day = '0' + day;

    return [month, day, year].join('-');
};

function validateEmail($email) {
                  var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
                  return emailReg.test( $email );
                } 
function getlength(number) {
return number.toString().length;
}      
	
</script>
