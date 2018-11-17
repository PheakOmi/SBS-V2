<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><tiles:insertAttribute name="title" /></title>

    <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="https://code.jquery.com/ui/1.11.3/jquery-ui.min.js" integrity="sha256-xI/qyl9vpwWFOXz7+x/9WkG5j/SVnSw21viy8fWwbeE=" crossorigin="anonymous"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

    <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">

    <style>

        body {
            margin-bottom: 40px;
            margin-top: 40px;
            text-align: center;
            font-size: 14px;
            font-family: 'Roboto', sans-serif;
            background:url(http://www.digiphotohub.com/wp-content/uploads/2015/09/bigstock-Abstract-Blurred-Background-Of-92820527.jpg);
        }

        #wrap {
            width: 1100px;
            margin: 0 auto;
        }

        #external-events {
            float: left;
            width: 150px;
            padding: 0 10px;
            text-align: left;
        }

        #external-events h4 {
            font-size: 16px;
            margin-top: 0;
            padding-top: 1em;
        }

        .external-event { /* try to mimick the look of a real event */
            margin: 10px 0;
            padding: 2px 4px;
            background: #3366CC;
            color: #fff;
            font-size: .85em;
            cursor: pointer;
        }

        #external-events p {
            margin: 1.5em 0;
            font-size: 11px;
            color: #666;
        }

        #external-events p input {
            margin: 0;
            vertical-align: middle;
        }

        #calendar {
            /* 		float: right; */
            margin: 0 auto;
            width: 900px;
            background-color: #FFFFFF;
            border-radius: 6px;
            box-shadow: 0 1px 2px #C3C3C3;
            -webkit-box-shadow: 0px 0px 21px 2px rgba(0,0,0,0.18);
            -moz-box-shadow: 0px 0px 21px 2px rgba(0,0,0,0.18);
            box-shadow: 0px 0px 21px 2px rgba(0,0,0,0.18);
        }

    </style>

    <spring:url value="/resources/Bootstrap/css/input_schedule.css" var="ISCss"/>
    <link rel="stylesheet" href="${ISCss}">
    <spring:url value="/resources/Bootstrap/css/time/wickedpicker.css" var="timeStyle"/>
    <link rel="stylesheet" href="${timeStyle}">
    <spring:url value="/resources/Bootstrap/js/time/wickedpicker.js" var="TimeJS"/>
    <!-- Sweet alert -->
    <spring:url value="/resources/Bootstrap/css/sweetalert.css" var="alertStyle"/>
    <link rel="stylesheet" href="${alertStyle}">
    <spring:url value="/resources/Bootstrap/js/sweetalert.min.js" var="alertJS"/>
    <script src="${TimeJS}"></script>
    <script src="${alertJS}"></script>

</head>



<body>


  <div class="main-wrapper">
            <div class="app" id="app">
            <%--<tiles:insertAttribute name="header" />--%>
      <%--<tiles:insertAttribute name="sidebar" />--%>
      <tiles:insertAttribute name="body" />

            </div>
  </div>



<script>

                    $(document).ready(function(){

                        var date = new Date();
                        var d = date.getDate();
                        var m = date.getMonth();
                        var y = date.getFullYear();

                        $.ajax({
                            url:'getAllSchedules',
                            type:'GET',
                            success: function(response){
                                var schedule_arr = []
                                for(i=0;i<response.schedules.length;i++) {
                                    var data = response.schedules[i]
                                    var sdate = new Date(data.dept_date)
                                    var title = '&nbsp;'+searchLocation(data.source_id,response.locations) + "-" + searchLocation(data.destination_id, response.locations)+'&nbsp;&nbsp;&nbsp;&nbsp;'+ timeConv(data.dept_time)+'&nbsp;&nbsp;&nbsp;<span style="color:orange;">'+searchBusSeat(data.bus_id,response.buses)+'/'+(parseInt(data.number_staff)+parseInt(data.number_student)+parseInt(data.number_customer))+"</span>"
                                    var date_obj = {
                                        title: title,
                                        start: new Date(sdate.getFullYear(), sdate.getMonth(), sdate.getDate()),
                                        url: 'schedule_detail?id=' + data.id,
                                        className: 'success'
                                    }
                                    schedule_arr.push(date_obj)
                                    $( ".unhoverr" ).on('click', function(e) {
                                        e.stopPropagation();
                                        var s_id = parseInt($(this).attr('data-url'));
                                        deleteSchedule(s_id);
                                    });
                                }

                                render_cal(schedule_arr)

                            },
                            error: function(err){
                                // swal("Oops!", "Cannot get all schedules data", "error")
                                console.log(JSON.stringify(err));
                            }

                        });
                        function render_cal(param) {
                            var calendar =  $('#calendar').fullCalendar({
                                header: {
                                    left: 'title',
                                    center: 'month',
                                    right: 'prev,next today'
                                },
                                editable: false,
                                firstDay: 1, //  1(Monday) this can be changed to 0(Sunday) for the USA system
                                selectable: false,
                                defaultView: 'month',
                                axisFormat: 'h:mm',
                                columnFormat: {
                                    month: 'ddd',    // Mon
                                    week: 'ddd d', // Mon 7
                                    day: 'dddd M/d',  // Monday 9/7
                                    agendaDay: 'dddd d'
                                },
                                titleFormat: {
                                    month: 'MMMM yyyy', // September 2009
                                    week: "MMMM yyyy", // September 2009
                                    day: 'MMMM yyyy'                  // Tuesday, Sep 8, 2009
                                },
                                allDaySlot: false,
                                selectHelper: true,
                                droppable: false, // this allows things to be dropped onto the calendar !!!
                                drop: function(date, allDay) {
                                    console.log("DDDD")
                                },
                                events:param
                            });
                        }






                      
                      $.ajax({
                            url:'getBookingRequestNotification',
                            type:'GET',
                            success: function(response){
                              if(response.requests.length>0)
                              $("#notii").text(response.requests.length);
                            },
                          error: function(err){
                            console.log(JSON.stringify(err));
                            }
                            
                          });




                      $.ajax({
                              async: false,
                              cache: false,
                              type: "GET",
                              url: "user_info",
                              contentType: "application/json",
                              timeout: 100000,
                              success: function(data) {
                                console.log(data);
                                document.getElementById('fname').innerHTML=data.username;
                                eee=data.email;
                                
                              },
                              error: function(e) {
                                console.log("ERROR: ", e);
                              },
                              done: function(e) {
                                console.log("DONE");
                              }
                          });


                        $( "#settingMng" ).on( "click", function() {
                        $(".ir").slideToggle();
                        $("#dds1").toggleClass("irr");
                        $("#dds2").toggleClass("irr");
                      });

                        $( "#reportMng" ).on( "click", function() {
                        $(".ir2").slideToggle();
                        $("#ddr1").toggleClass("irr");
                        $("#ddr2").toggleClass("irr");
                      });
                




                        
                    });

$(window).bind("load", function() {
    var data = ${data};
    var buses  = data.buses;
    all_bus = buses;
    var code = data.code;
    locations = data.main_locations;
    var drivers = data.drivers;
    all_driver = drivers;
    for(i=0; i<buses.length; i++)
        $("#sbus").append("<option value="+buses[i].id+">"+buses[i].model+" "+buses[i].plate_number+" </option>");
    for(i=0; i<locations.length; i++)
        $("#sfrom").append("<option value="+locations[i].id+">"+locations[i].name+" </option>");
    for(i=0; i<drivers.length; i++)
        $("#sdriver").append("<option value="+drivers[i].id+">"+drivers[i].name+" </option>");

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
        $("#sno_seat").val(searchBusSeat(input, all_bus))

    });



    $("#myForm").on('submit',function(e){
        e.preventDefault();
        var driver, bus;
        if($("#sdriver").val()==""||$("#sdriver").val()==null)
        {
            driver = 0
        }
        else
            driver = parseInt($("#sdriver").val())
        if($("#sbus").val()==""||$("#sbus").val()==null)
        {
            bus = 0
        }
        else
            bus = parseInt($("#sbus").val())
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

        var date_arr = [];
        $(".selected").each(function(){
            var dateee = $(this).attr("info")
            var convertedDate = dateee.replace(/(\d\d)\/(\d\d)\/(\d{4})/, "$3-$1-$2");
            date_arr.push(convertedDate);
        });
        if(date_arr.length>1)
        {
            url = "createMultipleSchedule",
            data = {
                    driver_id:driver,
                    bus_id:bus,
                    source_id:parseInt($("#sfrom").val()),
                    destination_id:parseInt($("#sto").val()),
                    no_seat:parseInt($("#sno_seat").val()),
                    date_arr:date_arr,
                    dept_time:toDate($("#sdepttime").val(),'h:m')
                    }
        }
        else
        {
            url = "createSchedule"
            data = {
                    driver_id:driver,
                    bus_id:bus,
                    source_id:parseInt($("#sfrom").val()),
                    destination_id:parseInt($("#sto").val()),
                    no_seat:parseInt($("#sno_seat").val()),
                    dept_date:date_arr[0],
                    dept_time:toDate($("#sdepttime").val(),'h:m')
                    }
        }
        $.ajax({
            url:url,
            type:'GET',
            data:data,
            traditional: true,
            success: function(response){
                console.log(response)
                if(response.status=="all fine")
                {
                    setTimeout(function() {
                        swal({
                            title: "Done!",
                            text: response.message,
                            type: "success"
                        }, function() {
                            window.location.reload()
                        });
                    }, 10);

                }


                else
                    swal("Oops!", response.message, "error")

            },
            error: function(err){
                console.log(JSON.stringify(err));

            }

        });

    });


});

function showInputBtn(e){

    var numItems = $('.selected').length;
    // console.log(numItems)
    if(numItems>0)
        $("#inputBtn").show();
    else
        $("#inputBtn").hide();

}
function searchBusSeat(id, myArray){
    for (var i=0; i < myArray.length; i++) {
        if (myArray[i].id === parseInt(id)) {
            return myArray[i].number_of_seat;
        }
    }
}

function searchLocation(id, myArray){
    for (var i=0; i < myArray.length; i++) {
        if (myArray[i].id === parseInt(id)) {
            return myArray[i].abbreviation;
        }
    }
}


function openModal() {
    var dates = "";
    $(".selected").each(function(){
        console.log(this)
        var date = $(this).attr("info")
        dates = dates+date+", "
    });
    $("#sdeptdate").val(dates)
    $('#myModal').modal('show');
}

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

function timeConv(param) {
    var time = param;
    var hours = Number(time.match(/^(\d+)/)[1]);
    var minutes = Number(time.match(/:(\d+)/)[1]);
    var AMPM = time.match(/\s(.*)$/)[1];
    if(AMPM == "PM" && hours<12) hours = hours+12;
    if(AMPM == "AM" && hours==12) hours = hours-12;
    var sHours = hours.toString();
    var sMinutes = minutes.toString();
    if(hours<10) sHours = "0" + sHours;
    if(minutes<10) sMinutes = "0" + sMinutes;
    return sHours + ":" + sMinutes;

}

function sendEmail() {
    $.ajax({
        url:"sendMonthlySchedule",
        type:'GET',
        data:{id:month_to_send.getMonth()+1,
              idd:month_to_send.getFullYear()
        },
        traditional: true,
        success: function(response){
            console.log(response)
            if(response.status==1)
            {
                setTimeout(function() {
                    swal({
                        title: "Done!",
                        text: response.message,
                        type: "success"
                    }, function() {
                        window.location.reload()
                    });
                }, 10);

            }


            else
                swal("Oops!", response.message, "error")

        },
        error: function(err){
            console.log(JSON.stringify(err));

        }

    });

}


                    deleteSchedule=function(s_id)
                    {
                        swal({
                                title: "Do you want to delete this schedule?",
                                text: "Make sure there is no booking in this schedule.",
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
                                        url:'deleteSchedule?id='+s_id,
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
                                            swal("Oops!", "Cannot get all buses data", "error")
                                            console.log(JSON.stringify(err));
                                        }
                                    });
                                }
                            });
                    }
                    
</script>

</body>
</html>