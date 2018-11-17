<body onload="assigN()" >
<article class="content cards-page">
 						<div class="title-block" style="border-bottom:0px solid #d7dde4;">
 						<div class="btn-group pull-right">
                        <button type="button" class="btn btn-info" onclick="window.history.back();" style="color:white;border-right:2px solid white;"><i class="fa fa-list-ul"></i></button>
                        <button type="button" class="btn btn-info" id="btnCalendar" style="color:white;"><i class="fa fa-calendar"></i></button>
                        </div>
                        </div>
                      
                    	
                    <section class="section">
                        <div class="row sameheight-container">
                           <div class="container">

		<div class="calendar">

			<header>				

				<h2 id="hhead"></h2>

				<a class="btn-prev fontawesome-angle-left" href="javascript:assigNPrevious()"></a>
				<a class="btn-next fontawesome-angle-right" href="javascript:assigNNext()"></a>

			</header>
			<div id="cc"></div>
			
			

		</div> <!-- end calendar -->

	</div> <!-- end container -->
                        </div>
                    </section>
                    
                </article>
                
</body>
<script type="text/javascript">
var currentDate;
var ttoday = new Date();

load = function()
{}


$(document).ready(function(){
	$("#scheduleMng").addClass("active");
	$("#btnCalendar").addClass("active");
	
	
	
});




function assigN(){
	
	var dateNow = new Date();
	currentDate = dateNow;
	displayCalendar(dateNow);
	var toFind = ttoday.getDate()+"o"+ttoday.getMonth()+"o"+ttoday.getFullYear();
	$("#"+toFind).addClass("current-day");
	$.ajax({
		url:'getAllSchedulesByMonth?month='+(dateNow.getMonth()+1)+'&year='+dateNow.getFullYear(),
		type:'GET',
		success: function(response){
			putScheduleOnCalendar(response)
			},
	error: function(err){
		swal("Oops!", "Cannot get all schedule data", "error")
		console.log(JSON.stringify(err));
		}
		
	});
}
function assigNNext(){
	document.getElementById("cc").innerHTML="";
	var dateNow = currentDate;
	dateNow.setMonth(dateNow.getMonth()+1);
	displayCalendar(dateNow);
	var toFind = ttoday.getDate()+"o"+ttoday.getMonth()+"o"+ttoday.getFullYear();
	$("#"+toFind).addClass("current-day");
	$.ajax({
		url:'getAllSchedulesByMonth?month='+(dateNow.getMonth()+1)+'&year='+dateNow.getFullYear(),
		type:'GET',
		success: function(response){
			putScheduleOnCalendar(response)
			},
	error: function(err){
		swal("Oops!", "Cannot get all schedule data", "error")
		console.log(JSON.stringify(err));
		}
		
	});

}
function assigNPrevious(){
	document.getElementById("cc").innerHTML="";
	var dateNow = currentDate;
	dateNow.setMonth(dateNow.getMonth()-1);
	displayCalendar(dateNow);
	var toFind = ttoday.getDate()+"o"+ttoday.getMonth()+"o"+ttoday.getFullYear();
	$("#"+toFind).addClass("current-day");
	$.ajax({
		url:'getAllSchedulesByMonth?month='+(dateNow.getMonth()+1)+'&year='+dateNow.getFullYear(),
		type:'GET',
		success: function(response){
			putScheduleOnCalendar(response)
			},
	error: function(err){
		swal("Oops!", "Cannot get all schedule data", "error")
		console.log(JSON.stringify(err));
		}
		
	});
}



function displayCalendar(dd){
 
 
 var htmlContent ="";
 var FebNumberOfDays ="";
 var counter = 1;
 
 
 var dateNow = dd;
 var month = dateNow.getMonth();

 var nextMonth = month+1; //+1; //Used to match up the current month with the correct start date.
 var prevMonth = month -1;
 var day = dateNow.getDate();
 var year = dateNow.getFullYear();
  
 
 //Determing if February (28,or 29)  
 if (month == 1){
    if ( (year%100!=0) && (year%4==0) || (year%400==0)){
      FebNumberOfDays = 29;
    }else{
      FebNumberOfDays = 28;
    }
 }
 
 
 // names of months and week days.
 var monthNames = ["January","February","March","April","May","June","July","August","September","October","November", "December"];
 var dayNames = ["Sunday","Monday","Tuesday","Wednesday","Thrusday","Friday", "Saturday"];
 var dayPerMonth = ["31", ""+FebNumberOfDays+"","31","30","31","30","31","31","30","31","30","31"]
 
 
 // days in previous month and next one , and day of week.
 var nextDate = new Date(nextMonth +' 1 ,'+year);
 
 var weekdays= nextDate.getDay();
 var weekdays2 = weekdays
 var numOfDays = dayPerMonth[month];
     
 
 
 
 // this leave a white space for days of pervious month.
 while (weekdays>0){
    htmlContent += "<td class='prev-month'></td>";
 
 // used in next loop.
     weekdays--;
 }
 
 // loop to build the calander body.
 while (counter <= numOfDays){
 
     // When to start new line.
    if (weekdays2 > 6){
        weekdays2 = 0;
        htmlContent += "</tr><tr>";
    }
 
 
 
    // if counter is current day.
    // highlight current day using the CSS defined in header.
    if (counter == day){
        htmlContent +="<td id='"+counter+"o"+month+"o"+year+"'>"+counter+"</td>";
    }else{
        htmlContent +="<td id='"+counter+"o"+month+"o"+year+"'>"+counter+"</td>";    
 
    }
    
    weekdays2++;
    counter++;
 }
 
 
 
 // building the calendar html body.
 $("#hhead").text(monthNames[month]+" "+ year);
 var calendarBody = "<table>";
 calendarBody +="<thead>  <tr><td>Sun</td>  <td>Mon</td> <td>Tue</td>"+
 "<td>Wed</td> <td>Thu</td> <td>Fri</td> <td>Sat</td> </tr></thead>";
 calendarBody += "<tr>";
 calendarBody += htmlContent;
 calendarBody += "</tr></table>";
 // set the content of div .
 document.getElementById("cc").innerHTML=calendarBody;
 
}


putScheduleOnCalendar = function(data)
	{
	schedules = data.schedules;
	for (var i=0;i<schedules.length;i++)
		{
		var date = new Date(schedules[i].dept_date);
		var arrangedDate = date.getDate()+"o"+date.getMonth()+"o"+date.getFullYear();
		$("#"+arrangedDate).addClass("event");
		$("#"+arrangedDate).addClass("hh");
		$("#"+arrangedDate).attr('data-url', 'schedule_list?date='+date.getDate()+'&month='+(date.getMonth()+1)+'&year='+date.getFullYear());
		
		}
	$(".hh").on('click', function() {
    	location.href=$(this).attr('data-url');
	});
	}

viewAsList = function(){
	
}


	
</script>
