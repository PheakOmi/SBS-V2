<body onload="load()">
<article class="content cards-page">

                        <section class="section">
                       <div>
                                <div class="card">
                                    <div class="card-block">
                                        <div class="card-title-block">
                                            <h3 class="title"> All Logs of schedule <span id="scode"></span> </h3>
                                        </div>
                                        <section class="example">
                                            <table class="table table-striped table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>Datetime</th>
                                                        <th>Action</th>
                                                        <th>By</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="all_log">
                                                        
                                                </tbody>
                                            </table>
                                        </section>
                                    </div>
                                </div>
                            </div>
                            

                                        
                                        
                    </section>

<!--                         <p class="title-description"> Create, Update or View all buses information </p> -->
                    </div>
                    <section class="section">
	                        
                        	
                        <!-- /.row -->
                    </section>
                    
                </article>
                
</body>
<script type="text/javascript">
load = function(){
    var data = ${data};
    console.log(data)
    var logs = data.logs
    $("#scode").text(data.code);
    for(var i=0;i<logs.length;i++)
    {
        var row = "<tr class='hoverr' data-url='log_detail?id="+logs[i].id+"'>"+
            "<td>"+toD(logs[i].created_at)+"</td>"+
            "<td>"+logs[i].action+"</td>"+
            "<td>"+logs[i].updated_by+"</td></tr>";
        $("#all_log").append(row);
    }

    $(".hoverr").on('click', function() {
        location.href=$(this).attr('data-url');
    });

}

$(document).ready(function(){
	$("#scheduleMng").addClass("active");

});

function toD(date) {
    myDate = new Date(1000*date);
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [month, day, year].join('/');
}



	
</script>
