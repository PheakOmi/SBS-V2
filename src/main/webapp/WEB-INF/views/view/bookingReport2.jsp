<body onload="load()">
<article class="content cards-page">
                   
                     
                    <section class="section">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-block">
                                        <div class="card-title-block">
                                            <h3 class="title"> Bookings Report </h3>
                                            
                                        </div>
                                        <section class="example">
                                            <div class="table-responsive">
                                                <table id="tt" class="table table-striped table-bordered table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th>No</th>
                                                            <th>Code</th>
                                                            <th>Name</th>
                                                            <th>From</th>
                                                            <th>To</th>
                                                            <th>Departure Date</th>
                                                            <th>Departure Time</th>
                                                            <th>Number of bookings</th>
                                                            <th>Status</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody id="allBooking">
                                                       
                                                    </tbody>
                                                </table>
                                            </div>
                                        </section>
                                    </div>
                                    <div class="col-md-12">
                                <button class="btn btn-info pull-right" onClick="generateReport()"  style="color:white;" id="moveBtn"> <i class="fa fa-bar-chart"></i> Generate Report</button>
                            </div>
                                </div>

                            </div> 

                        </div>
                    </section>
                </article>

                
</body>
<script>
var locations;

$(document).ready(function(){
	var bootstrapjs = $("<script>");
  	$(bootstrapjs).attr('src', '/resources/Bootstrap/js/bootstrap.min.js');
  	$(bootstrapjs).appendTo('body');
	$("#breport").addClass("active");
	
    $(".ir2").slideToggle();
    $("#ddr1").toggleClass("irr");
    $("#ddr2").toggleClass("irr");
    $( "#reportMng" ).off();
    
});

load = function(){
	var response = ${data};
    bookings = JSON.parse(response.bookings);
    locations = response.locations;
    customers = response.customers;
    console.log(locations)
    for (var i=0;i<bookings.length;i++)
        {
        var booking = '<tr class="hoverr search" s-title="'+bookings[i].code+'" data-url="booking_detail?id='+bookings[i].id+'"><td>'+(i+1)+'</td>'
                            +'<td>'+bookings[i].code+'</td>'
                            +'<td>'+searchCustomer(bookings[i].user_id,customers)+'</td>'
                            +'<td>'+searchLocation(bookings[i].from_id,locations)+'</td>'
                            +'<td>'+searchLocation(bookings[i].to_id,locations)+'</td>'
                            +'<td>'+formatDate(bookings[i].dept_date)+'</td>'
                            +'<td>'+bookings[i].dept_time+'</td>'
                            +'<td>'+bookings[i].number_booking+'</td>'
                            +'<td>'+bookings[i].notification+'</td></tr>';
        $("#allBooking").append(booking);               
        }
    $(".hoverr").on('click', function() {
        location.href=$(this).attr('data-url');
    });
}






generateReport = function()
{
            var data_type = 'data:application/vnd.ms-excel';
            var table_div = document.getElementById('tt');
            var table_html = table_div.outerHTML.replace(/ /g, '%20');
        
            var a = document.createElement('a');
            a.href = data_type + ', ' + table_html;
            a.download = 'Bookings_Report' + Math.floor((Math.random() * 9999999) + 1000000) + '.xls';
            a.click();
       
}
</script>