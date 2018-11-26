<body onload="load()">
<article class="content cards-page">
                    	
                    <section class="section">
                        <div class="row sameheight-container">
                            <div class="col-md-12">
                                <div class="card card-block sameheight-item">
                                    <div class="title-block">
                                        <h3 class="title"> Log Detail </h3> <br>

                      
                                    </div>
                                   
                                    
                                    <form class="form-inline" id ="myForm">
                                    	<div class="form-group col-md-4" style="margin-bottom:2%;">
                                            <label for="exampleInputPassword3"  style="margin-right:4%;">On</label>
                                            <input type="text" class="form-control" disabled id="lon" style="width: inherit;">
                                        </div>
                                        
                                        <div class="form-group col-md-4" style="margin-bottom:2%;">
                                            <label for="exampleInputPassword3"  style="margin-right:4%;">Action</label>
                                            <input type="text" class="form-control" disabled id="laction" style="width: inherit;">
                                        </div>
                                        
                                        <div class="form-group col-md-4" style="margin-bottom:2%;">
                                            <label for="exampleInputPassword3"  style="margin-right:4%;">By</label>
                                            <input type="text" class="form-control" disabled id="lby" style="width: inherit;">
                                        </div>
                                        

                                        <div class="form-group col-md-12" style="margin-bottom:2%;">
                                            <label for="exampleInputPassword3"  style="margin-right:4%;">Description</label>
                                            <textarea rows="4" cols="100" disabled class="form-inline boxed" id="ldescription"  style="width: inherit;"></textarea>
                                        </div>
                                        
                                        
                                        <button type="submit" id ="bsubmit" style="display:none;">aa</button>
                                        
                                        
                                        
                               
                                       
                                        
                                    </form>
                                </div>
                            </div>
                        </div>
                    </section>
                    
                </article>
                
</body>
<script type="text/javascript">
var id;
var u_id;
load = function () {
	var data = ${data};
	console.log(data)
	var log = data.log;
	$("#lon").val(toD(log.created_at));
    $("#laction").val(log.action);
    $("#lby").val(log.updated_by);
	$("#ldescription").val(log.description);
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
