<%@taglib prefix="sec"
          uri="http://www.springframework.org/security/tags"%>
<body onload="load()">
<article class="content cards-page">

    <section class="section">
        <div class="row sameheight-container">
            <div class="col-md-12">
                <div class="card card-block sameheight-item">
                    <div class="title-block">
                        <h3 class="title"> Schedule Detail </h3>
                    </div>
                    <form class="form-inline clearfix" style="display:block;" id="myForm" >
                        <div class="form-group col-md-4" style="margin-bottom:2%; float:left;">
                            <label for="exampleInputPassword3"  style="margin-right:4%;">Code</label>
                            <input type="text" class="form-control" id="scode" style="width: inherit;" placeholder=Code required disabled> </div>
                        <div class="form-group col-md-4" style="margin-bottom:2%; float:left;">
                            <label for="exampleInputEmail3"  style="margin-right:4%;">Driver</label>
                            <select class="form-control cpast" style="width: inherit;" id="sdriver"><option></option></select> </div>
                        <div class="form-group col-md-4" style="margin-bottom:2%; float:left;">
                            <label for="exampleInputPassword3"  style="margin-right:4%;">Bus</label>
                            <select class="form-control cpast" style="width: inherit;" id="sbus"><option></option></select> </div>
                        <div class="form-group col-md-4" style="margin-bottom:2%; float:left;">
                            <label for="exampleInputEmail3"  style="margin-right:4%;">From</label>
                            <select class="form-control cpast" style="width: inherit;" id="sfrom" required><option></option></select> </div>
                        <div class="form-group col-md-4" style="margin-bottom:2%;float:left;">
                            <label for="exampleInputPassword3"  style="margin-right:4%;">To</label>
                            <select class="form-control cpast" style="width: inherit;" id="sto" required><option></option></select> </div>
                        <div class="form-group col-md-4" style="margin-bottom:2%;float:left;">
                            <label for="exampleInputPassword3"  style="margin-right:4%;">Bookings Allowed</label>
                            <input type="text" class="form-control" id="sallowed" style="width: inherit;" placeholder="Bookings Allowed" required disabled> </div>
                        <div class="form-group col-md-4" style="margin-bottom:2%;float:left;">
                            <label for="exampleInputEmail3"  style="margin-right:4%;">Departure Date</label>
                            <input type="text" name="no_past_date" class="form-control cpast" id="sdeptdate" style="width: inherit;" placeholder="Departure Date" required> </div>
                        <div class="form-group col-md-4" style="margin-bottom:2%;float:left;">
                            <label for="exampleInputPassword3"  style="margin-right:4%;">Departure Time</label>
                            <input type="text" name="time" class="form-control cpast" id="sdepttime" style="width: inherit;" placeholder="Departure Time" required> </div>
                        <div class="form-group col-md-4" style="margin-bottom:2%;float:left;">
                            <label for="exampleInputPassword3"  style="margin-right:4%;">Number Booked</label>
                            <input type="text" class="form-control" id="sbooked" style="width: inherit;" placeholder="Booked" required disabled> </div>
                        <div class="form-group col-md-12" style="margin-bottom:2%;">
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <button type="submit" class="btn btn-info" id="ubtn">Update</button>
                            </sec:authorize>
                        </div>

                    </form>



                    <div class="col-xl-12">
                        <div class="card-block" style="padding-left:2px;">
                            <ul class="nav nav-tabs nav-tabs-bordered">
                                <li class="nav-item">
                                    <a href="#home" style="background-color:#52BCD3" class="nav-link active" data-target="#home" data-toggle="tab" aria-controls="home" role="tab">All bookings of this schedule</a>
                                </li>

                            </ul>
                            <!-- Tab panes -->
                            <div class="tab-content tabs-bordered">
                                <div class="tab-pane fade in active" style="display:initial;" id="home">
                                    <div class="table-responsive">
                                        <table class="table table-striped table-bordered table-hover">
                                            <thead>
                                            <tr>

                                                <th>No</th>
                                                <th>Code</th>
                                                <th>Name</th>
                                                <th>Phone Number</th>
                                                <th>Number of bookings</th>
                                                <th>User Type</th>

                                            </tr>
                                            </thead>
                                            <tbody id="allBooking">

                                            </tbody>

                                        </table>
                                    </div>
                                </div>

                            </div>

                        </div>
                        <button onclick="goLog()" style="color:white;" class="btn btn-info pull-right btn-sm" id="logBtn"> <i class="fas fa-sticky-note"></i> View Log</button>
                    </div>


                </div>
            </div>

        </div>
    </section>


</article>




<div id="user_info_modal" class="modal">
    <div class="modal-content center">
        <h6 class="center light-blue-text">User Information</h6>
        <table id="get_user_info"></table>
    </div>
</div>



</body>
<script type="text/javascript">
    var all_bus;
    var all_driver;
    var all_customer;
    var idd;
    var s_code;
    load = function () {
        $('.sameheight-item').attr( "style", "" );
        var bootstrapjs = $("<script>");
        $(bootstrapjs).attr('src', '/resources/Bootstrap/js/bootstrap.min.js');
        $(bootstrapjs).appendTo('body');
        var data = ${data};
        console.log(data)
        var bookings = data.bookings;
        var buses  = data.buses;
        all_bus = buses;
        all_driver = data.drivers;
        all_customer = data.customers;
        all_booking = bookings;
        locations = data.locations;
        var p_locations = data.p_locations;
        var schedule  = data.schedule;
        current_schedule = schedule;
        idd = schedule.id;
        s_code = schedule.code;
        $("#scode").val(schedule.code);
        $("#sbooked").val(schedule.number_staff+schedule.number_student+schedule.number_customer);
        $("#sallowed").val(schedule.no_seat);
        $("#sdeptdate").val(formatDate(schedule.dept_date));
        $("#sdepttime").val(schedule.dept_time.trim().substring(0, schedule.dept_time.length-3));
        for(i=0; i<locations.length; i++)
            $("#sfrom").append("<option value="+locations[i].id+">"+locations[i].name+" </option>");
        $("#sfrom").val(schedule.source_id)
        $("#sto").append("<option value="+schedule.destination_id+">"+searchLocation(schedule.destination_id,locations)+" </option>");
        $("#sto").val(schedule.destination_id)
        for(i=0; i<all_bus.length; i++)
        {
            $("#sbus").append("<option value="+all_bus[i].id+">"+all_bus[i].model+" "+all_bus[i].plate_number+" </option>");
        }
        for(i=0; i<all_driver.length; i++)
            $("#sdriver").append("<option value="+all_driver[i].id+">"+all_driver[i].name+" </option>");
        $("#sdriver").val(schedule.driver_id);
        $("#sbus").val(schedule.bus_id);
        if(data.daydiff=="past")
        {
            $(".cpast").prop('disabled', true);
            $("#ubtn").hide();
        }

    }



    $(document).ready(function(){
        $("#scheduleMng").addClass("active");
        $('.sameheight-item').attr( "style", "" );

        $("#myForm").on('submit',function(e){
            e.preventDefault();
            var dateee = $("#sdeptdate").val();
            var convertedDate = dateee.replace(/(\d\d)\/(\d\d)\/(\d{4})/, "$3-$1-$2");
            var driverId = 0;
            if($("#sdriver").val()==""||$("#sdriver").val()==null)
            {}
            else
                driverId = parseInt($("#sdriver").val());

            var busId = 0;
            if($("#sbus").val()==""||$("#sbus").val()==null)
            {}
            else
                busId = parseInt($("#sbus").val());

            if($("#scode").val()!=current_schedule.code)
            {
                swal("Action Disallowed!", "You cannot update schedule code", "error")
                return
            }
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
            if($("#sallowed").val()<(current_schedule.number_staff+current_schedule.number_student+current_schedule.number_customer))
            {
                swal("Action Disallowed!", "Bookings Allowed cannot be updated to a smaller number than Number Booked!", "error")
                return
            }
            $.ajax({
                url:'updateSchedule',
                type:'GET',
                data:{
                    id:current_schedule.id,
                    driver_id:driverId,
                    bus_id:busId,
                    source_id: parseInt($("#sfrom").val()),
                    destination_id: parseInt($("#sto").val()),
                    no_seat: parseInt($("#sallowed").val()),
                    dept_date:convertedDate,
                    dept_time:toDate($("#sdepttime").val(),'h:m')


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
                                window.location = "current_schedule";
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
            $("#sallowed").val(searchBusSeat(input, all_bus))

        });


    });


    formatDate =function (date) {
        var d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

        return [month, day, year].join('/');
    };

    function searchLocation(id, myArray){
        for (var i=0; i < myArray.length; i++) {
            if (myArray[i].id === id) {
                return myArray[i].name;
            }
        }
    }





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

    function goLog() {
        location.href='schedule_log_list?id='+idd;

    }







</script>
