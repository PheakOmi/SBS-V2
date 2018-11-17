<body>
<div id='wrap'>
	<div id='calendar'></div>

	<div style='clear:both'></div>
</div>
<script src="/resources/Bootstrap/js/input_schedule.js"></script>

<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title center">Create a bus</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">

				<form id="myForm">

					<div class="form-group" style="margin-bottom:2%;">
						<label class="control-label">Driver</label>
						<select class="form-control boxed" id="sdriver"><option></option></select> </div>
					<div class="form-group" style="margin-bottom:2%;">
						<label class="control-label">Bus</label>
						<select class="form-control boxed" id="sbus" required><option></option></select> </div>
					<div class="form-group" style="margin-bottom:2%;">
						<label class="control-label">From</label>
						<select class="form-control boxed" id="sfrom" required><option></option></select></div>
					<div class="form-group" style="margin-bottom:2%;">
						<label class="control-label">To</label>
						<select class="form-control boxed" id="sto" required><option></option></select> </div>

					<div class="form-group" style="margin-bottom:2%;">
						<label class="control-label">Departure Date</label>
						<input type="text" class="form-control boxed" id="sdeptdate" placeholder="Departure Date" disabled required> </div>
					<div class="form-group" style="margin-bottom:2%;">
						<label class="control-label">Departure Time</label>
						<input type="text" name="time" class="form-control boxed" id="sdepttime" placeholder="Departure Time" required> </div>
					<div class="form-group" style="margin-bottom:2%;">
						<label class="control-label">Number of seats</label>
						<input type="text" class="form-control boxed" id="sno_seat" maxlength="3" placeholder="Number of seats" required disabled> </div>
					<button type="submit" id="bsubmit" class="btn btn-default" style="display:none;">Create</button>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
				<button onClick="goTO()" class="btn btn-info">Create</button>
			</div>
		</div>

	</div>
</div>
<div style="width:800px; margin:10px auto;">
	<button type="button" id="inputBtn" class="btn btn-success btn-lg btn-block" onclick="openModal()">Input Schedule</button>
	<button type="button" id="emailBtn" class="btn btn-warning btn-lg btn-block" onclick="sendEmail()">Email All Schedules Of This Month</button>
</div>

</body>



