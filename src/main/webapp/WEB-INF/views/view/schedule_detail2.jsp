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
                            <select class="form-control cbooked" style="width: inherit;" id="sfrom" required><option></option></select> </div>
                        <div class="form-group col-md-4" style="margin-bottom:2%;float:left;">
                            <label for="exampleInputPassword3"  style="margin-right:4%;">To</label>
                            <select class="form-control cbooked" style="width: inherit;" id="sto" required><option></option></select> </div>
                        <div class="form-group col-md-4" style="margin-bottom:2%;float:left;">
                            <label for="exampleInputPassword3"  style="margin-right:4%;">Bookings Allowed</label>
                            <input type="text" class="form-control" id="sallowed" style="width: inherit;" placeholder="Bookings Allowed" required disabled> </div>
                        <div class="form-group col-md-4" style="margin-bottom:2%;float:left;">
                            <label for="exampleInputEmail3"  style="margin-right:4%;">Departure Date</label>
                            <input type="text" name="no_past_date" class="form-control cbooked" id="sdeptdate" style="width: inherit;" placeholder="Departure Date" required> </div>
                        <div class="form-group col-md-4" style="margin-bottom:2%;float:left;">
                            <label for="exampleInputPassword3"  style="margin-right:4%;">Departure Time</label>
                            <input type="text" name="time" class="form-control cbooked" id="sdepttime" style="width: inherit;" placeholder="Departure Time" required> </div>
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
                        <button onclick="goLog()" style="color:white; display: none" class="btn btn-info pull-right btn-sm" id="logBtn"> <i class="fas fa-sticky-note"></i> View Log</button>
                    </div>

                    <div class="col-md-12">
                        <button class="btn btn-warning pull-left" onClick="openModal()" style="color:white;" id="moveBtn">Move to other schedule</button>
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



<div class="modal fade" id="selectBusModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title center">Move bookings to another schedule</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form id="moveForm">
                    <div class="form-group">
                        <label class="control-label">Select a driver</label>
                        <select class="form-control boxed" id="mdriver" required>
                            <option></option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Select a bus</label>
                        <select class="form-control boxed" id="mbus" required>
                            <option></option>
                        </select>
                    </div>
                    <button type="submit" id="bsubmit" class="btn btn-default" style="display:none;">Create</button>
                </form>
            </div>
            <div class="modal-footer">
                <button onClick="goTO()" class="btn btn-info">Create a new schedule</button>
            </div>
        </div>

    </div>
</div>





<div class="modal fade" id="moveModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title center">Move bookings to another schedule</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form id="mf">
                    <div class="form-group">
                        <label class="control-label">Choose a schedule</label>
                        <select class="form-control boxed" id="mschedule">
                            <option></option>
                        </select>
                    </div>
                    <button type="submit" id="bsubmit2" class="btn btn-default" style="display:none;">Create</button>
                </form>
            </div>
            <div class="modal-footer">
                <button onClick="goTO2()" class="btn btn-info" id="moveNew">Create a new schedule</button>
                <button onClick="goTO3()" class="btn btn-info" id="moveSimple">Move</button>
            </div>
        </div>

    </div>
</div>



</body>
<script type="text/javascript">
    var all_bus;
    var all_driver;
    var all_customer;
    var idd;
    var all_booking;
    var s_code;
    var all_schedule;
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
        all_schedule = data.schedules;
        var p_locations = data.p_locations;
        schedule  = data.schedule;
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

        var option;
        for(i=0; i<all_bus.length; i++)
        {
            if((schedule.number_staff+schedule.number_student+schedule.number_customer)>parseInt(all_bus[i].number_of_seat)) {
                console.log("TTTT")
                option = "<option disabled value="+all_bus[i].id+">"+all_bus[i].model+" "+all_bus[i].plate_number+" ("+all_bus[i].number_of_seat+")"+" </option>";
            }
            else
                option = "<option value="+all_bus[i].id+">"+all_bus[i].model+" "+all_bus[i].plate_number+" ("+all_bus[i].number_of_seat+")"+" </option>";
            $("#sbus").append(option);
        }
        for(i=0; i<all_driver.length; i++)
            $("#sdriver").append("<option value="+all_driver[i].id+">"+all_driver[i].name+" </option>");
        $("#sdriver").val(schedule.driver_id);
        $("#sbus").val(schedule.bus_id);


        for (var i=0;i<bookings.length;i++)
        {
            var color = ""
            if(bookings[i].notification=="Cancelled")
                color = "background-color: #FADAD8;";

            var booking = '<tr class="hoverr" style="'+color+'" data-url="booking_detail?id='+bookings[i].id+'" toGet="'+bookings[i].id+'">'
                +'<td class="unhoverr"><label class="item-check" id="select-all-items"><input type="checkbox" class="checkbox">'
                +'<span></span></label></td>'
                +'<td>'+(i+1)+'</td>'
                +'<td>'+bookings[i].code+'</td>'
                +'<td class="user_info" style="color:blue" data='+bookings[i].user_id+'>'+searchCustomer(bookings[i].user_id,all_customer)+'</td>'
                +'<td>'+searchPhone(bookings[i].user_id,all_customer)+'</td>'
                +'<td>'+bookings[i].number_booking+'</td>'
                +'<td>'+bookings[i].description+'</td></tr>';
            $("#allBooking").append(booking);
        }
        $( ".unhoverr" ).on('click', function(e) {
            e.stopPropagation();
        });
        $(".cbooked").prop('disabled', true);
        if(data.daydiff=="past")
        {
            $(".cbooked").prop('disabled', true);
            $(".cpast").prop('disabled', true);
            $("#ubtn").hide();
        }


        $( ".user_info" ).on('click', function(e) {
            console.log("KK");
            e.stopPropagation();
            var id=$(this).attr('data');
            console.log(id);
            $.ajax({
                async: false,
                cache: false,
                type: "GET",
                url: "get_sch_driver_info2",
                data :{'id':id},
                timeout: 100000,
                success: function(data) {
                    console.log(data);
                    if(data[0].phone_number==""||data[0].phone_number==null)
                        data[0].phone_number = "";
                    var data='<tr><th>User\'s Name</th><td><b>:</b>  &nbsp&nbsp '+data[0].name+'</td></tr>'
                        +'<tr><th>Phone Number</th><td><b>:</b>  &nbsp&nbsp '+ data[0].phone_number+'</td></tr>'
                        +'<tr><th>Email</th><td><b>:</b> &nbsp&nbsp '+data[0].email +'</td></tr>'
                    document.getElementById('get_user_info').innerHTML=data;
                    $('#user_info_modal').modal();
                    $('#user_info_modal').modal('open');

                },
                error: function(e) {
                    console.log("ERROR: ", e);
                },
                done: function(e) {
                    console.log("DONE");
                }
            });
        });




        $(".hoverr").on('click', function(e) {
            e.stopPropagation();
            location.href=$(this).attr('data-url');
        });

        $("#logBtn").show();

        $(".checkbox").on('click', function(e) {
            console.log("Chh")
            var a = $(this).parents(".hoverr")[0];
            $(a).toggleClass("selected");
            showMoveBtn(a);
        });


        $("#moveBtn").hide();
    }



    $(document).ready(function(){
        $("#scheduleMng").addClass("active");
        $('.sameheight-item').attr( "style", "" );

        $("#sbus").change(function(){
            var input  = this.value;
            $("#sallowed").val(searchBusSeat(input, all_bus))

        });

        $("#myForm").on('submit',function(e){
            e.preventDefault();

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
            if($("#sallowed").val()<(current_schedule.number_staff+current_schedule.number_student+current_schedule.number_customer))
            {
                swal("Action Disallowed!", "Bookings Allowed cannot be updated to a smaller number than Number Booked!", "error")
                return
            }
            $.ajax({
                url:'updateSchedule2',
                type:'GET',
                data:{
                    id:current_schedule.id,
                    driver_id:driverId,
                    no_seat: parseInt($("#sallowed").val()),
                    bus_id:busId,

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



        $("#moveForm").on('submit',function(e){
            e.preventDefault();
            var b_ids = [];
            $(".selected").each(function(){
                b_ids.push(parseInt($(this).attr("toget")));
            });
            var total_seat=0;
            for (var i=0;i<b_ids.length;i++)
            {
                total_seat+=searchBooking(b_ids[i],all_booking);
            }
            console.log(total_seat)
            if($("#mbus").val()==""||$("#mbus").val()==null)
            {
                swal("Action Disallowed!", "You cannot leave Bus field blank.", "error")
                return
            }
            if($("#mdriver").val()==""||$("#mdriver").val()==null)
            {
                swal("Action Disallowed!", "You cannot leave Driver field blank.", "error")
                return
            }
            if(validateNumberOfSeat(parseInt($("#mbus").val()),all_bus,total_seat))
            {
                $.ajax({
                    url:'moveNew',
                    type:'GET',
                    data:{
                        id:idd,
                        b:b_ids,
                        driver_id:parseInt($("#mdriver").val()),
                        bus_id:parseInt($("#mbus").val())
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
                                    window.location.reload();;
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
            }
            else
            {
                swal("Action Disallowed!", "This bus's seats are not enough", "error")
            }
        });





        $("#mf").on('submit',function(e){
            e.preventDefault();
            console.log($("#mschedule").val());
            var old_date = $("#sdeptdate").val();
            var s = searchSchedule($("#mschedule").val(),all_schedule)
            console.log("SSSSSSS ",s)
            var new_date = formatDate(new Date(s.dept_date));
            var n1= String (formatDate(new Date()))+" "+ s.dept_time;
            var new_time = Date.parse(n1);
            var o1 = String (formatDate(new Date())) +" "+ $("#sdepttime").val();
            var old_time = Date.parse(o1);
            console.log(old_time);
            console.log(new_time);
            var b_ids = [];
            $(".selected").each(function(){
                b_ids.push(parseInt($(this).attr("toget")));
            });
            var total_seat=0;
            for (var i=0;i<b_ids.length;i++)
            {
                total_seat+=searchBooking(b_ids[i],all_booking);
            }
            if(old_date!=new_date||old_time!=new_time)
            {
                swal("Action Disallowed!", "You cannot move to the schedule which has different departure date and time!", "error")
                return
            }
            if((searchBusSeat(s.bus_id,all_bus)-s.number_student+s.number_customer)>=total_seat)
            {
                $.ajax({
                    url:'moveSimple',
                    type:'GET',
                    data:{
                        old_id:idd,
                        new_id:$("#mschedule").val(),
                        b:b_ids,
                        number_student:total_seat
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
                                    window.location.reload();;
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
            }
            else
            {
                swal("Action Disallowed!", "This bus's seats of that schedule are not enough", "error")
            }



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




    function searchCustomer(id, myArray){
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


    function searchBooking(id, myArray){
        for (var i=0; i < myArray.length; i++) {
            if (myArray[i].id === id) {
                return myArray[i].number_booking;
            }
        }
    }



    function searchPhone(id, myArray){
        for (var i=0; i < myArray.length; i++) {
            if (myArray[i].id === id) {
                if(myArray[i].phone_number==null||myArray[i].phone_number=="")
                    return "";

                return myArray[i].phone_number;
            }
        }
    }

    function searchSchedule(id, myArray){
        for (var i=0; i < myArray.length; i++) {
            if (myArray[i].id == id) {
                return myArray[i];
            }
        }
    }


    function showMoveBtn(e){

        var numItems = $('.selected').length;
        if(numItems>0)
            $("#moveBtn").show();
        else
            $("#moveBtn").hide();

    }


    function openModal(){
        $('#moveModal').modal('toggle');
        $('#mschedule').children('option:not(:first)').remove();
        for(var i=0; i<all_schedule.length; i++)
        {
            if(all_schedule[i].id!=idd)
            {
                var s  = "<option value="+all_schedule[i].id+">"+all_schedule[i].code+"</option>";
                $("#mschedule").append(s);
            }
        }
        $("#moveSimple").hide();
        $("#moveNew").show();

    }


    $( "#mschedule" ).on('change', function(e) {
        if(this.value==""||this.value==null)
        {
            $("#moveSimple").hide();
            $("#moveNew").show();
        }
        else
        {
            $("#moveNew").hide();
            $("#moveSimple").show();
        }

    });

    goTO = function(){
        $('#bsubmit').trigger('click');
    }

    goTO3 = function(){
        $('#bsubmit2').trigger('click');
    }

    goTO2 = function(){
        $('#moveModal').modal('toggle');
        $("#selectBusModal").modal('toggle');
        $('#mdriver').children('option:not(:first)').remove();
        $('#mbus').children('option:not(:first)').remove();
        for(i=0; i<all_bus.length; i++)
            $("#mbus").append("<option value="+all_bus[i].id+">"+all_bus[i].model+" </option>");
        for(i=0; i<all_driver.length; i++)
            $("#mdriver").append("<option value="+all_driver[i].id+">"+all_driver[i].name+" </option>");
    }


    function validateNumberOfSeat(id, myArray,seats){
        for (var i=0; i < myArray.length; i++) {
            if (myArray[i].id === id) {
                if(myArray[i].number_of_seat>=seats)
                    return true;
                else
                    return false;
            }
        }
    }








</script>
