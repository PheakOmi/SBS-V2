<body onload="load()">
<article class="content cards-page">
                    <div class="title-block">
                        <h3 class="title"> Customer Refunds </h3><br>
                        
                        
<!--                         <p class="title-description"> Create, Update or View all buses information </p> -->
                    </div>
                    <section class="section">
	                   <div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="card-block">
                                        <div class="card-title-block">
                                            <h3 class="title" id="ddate"></h3>
                                            
                                        </div>
                                        <section class="example">
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th>Customer Name</th>
                                                            <th>Departure Date</th>
                                                            <th>Departure Time</th>
                                                            <th>Percentage To Refund</th>
                                                            <th>Transaction ID</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody id="allRefunds">
                                                       
                                                    </tbody>
                                                </table>
                                            </div>
                                        </section>
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
load = function(){	
	var data = ${data};
    var bookings = data.bookings
    var refunds = data.refunds
    var customers = data.customers
    for (var i=0;i<bookings.length;i++)
                {
                var booking = '<tr class="hoverr" data-url="refund_detail?id='+bookings[i].id+'&rid='+searchRefundID(bookings[i].id, refunds)+'">'+'<td class="user_info" style="color:blue" data='+bookings[i].user_id+'>'+searchCustomer(bookings[i].user_id,customers)+'</td>'
                                    +'<td>'+formatDate(bookings[i].dept_date)+'</td>'
                                    +'<td>'+bookings[i].dept_time+'</td>'
                                    +'<td>'+searchRefund(bookings[i].id, refunds)+' %'+'</td>'
                                    +'<td style="color:#F6B352;" class="unhover">'+bookings[i].transaction_id+'</td></tr>';
                $("#allRefunds").append(booking);               
                }

                $(".hoverr").on('click', function() {
                location.href=$(this).attr('data-url');
                });

                $( ".unhover" ).on('click', function(e) {
                e.stopPropagation();    
            });
                
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
                            
                        },
                        error: function(e) {
                            console.log("ERROR: ", e);
                        },
                        done: function(e) {
                            console.log("DONE");
                        }
                    });
             });

}

goTO = function(){
	$('#bsubmit').trigger('click');
}


$(document).ready(function(){
	$("#scheduleMng").addClass("active");
	
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


function searchCustomer(id, myArray){
    for (var i=0; i < myArray.length; i++) {
        if (myArray[i].id === id) {
            return myArray[i].name;
        }
    }
}


function searchRefund(id, myArray){
    if(id==0)
        return"";
    for (var i=0; i < myArray.length; i++) {
        if (myArray[i].id === id) {
            return myArray[i].percentage;
        }
    }
}
function searchRefundID(id, myArray){
    if(id==0)
        return"";
    for (var i=0; i < myArray.length; i++) {
        if (myArray[i].id === id) {
            return myArray[i].id;
        }
    }
}


	



</script>
