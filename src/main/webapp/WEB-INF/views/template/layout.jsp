<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><tiles:insertAttribute name="title" /></title>






<!--  jQuery -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<!--  jQuery Validation -->
  <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.js"></script> 
  <script src="https://unpkg.com/axios/dist/axios.min.js"></script>



<!-- App JS -->      
<spring:url value="/resources/Bootstrap/js/app.js" var="AppJS"/>
<spring:url value="/resources/Bootstrap/js/vendor.js" var="VendorJS"/>

      
<!-- Sweet alert -->
<spring:url value="/resources/Bootstrap/css/sweetalert.css" var="alertStyle"/>
      <link rel="stylesheet" href="${alertStyle}">
<spring:url value="/resources/Bootstrap/js/sweetalert.min.js" var="alertJS"/>
      



    
<!-- App CSS -->
<spring:url value="/resources/Bootstrap/css/app-blue.css" var="appBlue"/>
      <link rel="stylesheet" href="${appBlue}"> 
<spring:url value="/resources/Bootstrap/css/vendor.css" var="vendorCss"/>
      <link rel="stylesheet" href="${vendorCss}"> 
      
      



      
<!-- Date Picker -->     
<spring:url value="/resources/Bootstrap/js/date/jquery-ui.js" var="dateJS2"/>
<spring:url value="/resources/Bootstrap/js/time/wickedpicker.js" var="TimeJS"/>

<spring:url value="/resources/Bootstrap/css/date/jquery-ui.css" var="dateStyle"/>
<link rel="stylesheet" href="${dateStyle}">
<spring:url value="/resources/Bootstrap/css/time/wickedpicker.css" var="timeStyle"/>
<link rel="stylesheet" href="${timeStyle}">



<!-- Multiple Select -->     
<spring:url value="/resources/Bootstrap/js/multipleselect/select2.full.min.js" var="MultipleJS"/>
<spring:url value="/resources/Bootstrap/js/multipleselect/en.js" var="MultipleJS2"/>
<spring:url value="/resources/Bootstrap/css/multipleselect/select2.min.css" var="MultipleCSS"/>
<link rel="stylesheet" href="${MultipleCSS}"><link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">




</head>



<body>


  <div class="main-wrapper">
            <div class="app" id="app">
            <tiles:insertAttribute name="header" />
      <tiles:insertAttribute name="sidebar" />
      <tiles:insertAttribute name="body" />
      
            </div>
  </div>



<script src="${VendorJS}"></script>
<script src="${alertJS}"></script>
<script src="${TimeJS}"></script>
<script src="${MultipleJS}"></script>
<script src="${MultipleJS2}"></script>
<script src="${dateJS2}"></script>
<script src="${AppJS}"></script>


<script>

                    
var eee;
                    $(document).ready(function(){
            
                      
                      // $.ajax({
                      //       url:'getBookingRequestNotification',
                      //       type:'GET',
                      //       success: function(response){
                      //         if(response.requests.length>0)
                      //         $("#notii").text(response.requests.length);
                      //       },
                      //     error: function(err){
                      //       console.log(JSON.stringify(err));
                      //       }
                      //
                      //     });




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
                
                      
                      $(".js-example-basic-multiple").select2();
                      var date_input=$('input[name="date"]');
                      var no_past_date_input=$('input[name="no_past_date"]');
                      var time_input = $('input[name="time"]');
                      var options={
                          format: 'yyyy/mm/dd',
                          todayHighlight: true,
                          autoclose: true,
                        };
                      var dateToday = new Date();
                      var no_past_options={
                            format: 'yyyy/mm/dd',
                            todayHighlight: true,
                            autoclose: true,
                            minDate: dateToday,
                        };
                        var slickjs = $("<link>");
                        $(slickjs).attr('rel', 'stylesheet');
                        $(slickjs).attr('href', '/resources/Bootstrap/css/calendar.css');
                        $(slickjs).appendTo('head');
                        var options2 = { now: "08:00", //hh:mm 24 hour format only, defaults to current time
                                 twentyFour: true, //Display 24 hour format, defaults to false 
                                 upArrow: 'wickedpicker__controls__control-up', //The up arrow class selector to use, for custom CSS 
                                 downArrow: 'wickedpicker__controls__control-down', //The down arrow class selector to use, for custom CSS 
                                 close: 'wickedpicker__close', //The close class selector to use, for custom CSS 
                                 hoverState: 'hover-state', //The hover state class to use, for custom CSS 
                                 title: 'Pick a time', //The Wickedpicker's title, 
                                 showSeconds: false, //Whether or not to show seconds, 
                                 secondsInterval: 1, //Change interval for seconds, defaults to 1  , 
                                 minutesInterval: 1, //Change interval for minutes, defaults to 1 
                                 beforeShow: null, //A function to be called before the Wickedpicker is shown 
                                 show: null, //A function to be called when the Wickedpicker is shown 
                                 clearable: false, //Make the picker's input clearable (has clickable "x")  
                        };
                        time_input.wickedpicker(options2);
                        date_input.datepicker(options);
                        no_past_date_input.datepicker(no_past_options);


                        
                        $('[data-toggle="tooltip"]').tooltip();
                        
                        
                        
                        
                        
                    });
                    
</script>

</body>
</html>