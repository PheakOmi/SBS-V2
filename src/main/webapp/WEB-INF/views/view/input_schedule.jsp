<%@taglib prefix="sec"
		  uri="http://www.springframework.org/security/tags"%>
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
						<select class="form-control boxed" id="sbus" required multiple><option></option></select> </div>
						<%--<select class="form-control boxed js-example-basic-multiple" multiple="multiple" id="student" required></select>--%>
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
					<div class="form-group" style="margin-bottom:2%;">
						<label class="control-label">Return</label>
						<select class="form-control boxed" id="sreturn">
							<option value=0></option>
							<option value=1>Same Date</option>
							<option value=2>Respectively</option>
						</select>
					</div>
					<div id="option1" style="display:none">
						<div class="form-group" style="margin-bottom:2%;">
							<label class="control-label">Return Date</label>
							<input type="text" name="no_past_date" class="form-control boxed" id="sreturndate1" placeholder="Return Date"> </div>
						<div class="form-group" style="margin-bottom:2%;">
							<label class="control-label">Return Time</label>
							<input type="text" name="time" class="form-control boxed" id="sreturntime1" placeholder="Return Time"> </div>
					</div>

					<div id="option2" style="display:none">
						<div class="form-group" style="margin-bottom:2%;">
							<label class="control-label">Return Time</label>
							<input type="text" name="time" class="form-control boxed" id="sreturntime2" placeholder="Return Time"> </div>
					</div>

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



