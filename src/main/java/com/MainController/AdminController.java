package com.MainController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.EntityClasses.*;
import com.ModelClasses.*;

import com.ThreadClasses.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.ThreadClasses.SendCreateThread;
import com.DaoClasses.userDaoImpl;
import com.ServiceClasses.usersService;

@Controller
public class AdminController {
	@Autowired
	usersService usersService1;

	@RequestMapping(value="/customer_home")
	public ModelAndView customer_home() {
		return new ModelAndView("cusomer_home");
	}
//=========================To sign up an account for customer================================
	@RequestMapping(value="/isexist",method=RequestMethod.GET)
	@ResponseBody public Map<String,Object> isExistUser(UserModel userModel) {
		User_Info user = usersService1.findByUserName(userModel.getEmail());
		boolean status = false;
		Map<String,Object> map = new HashMap<String,Object>();
		String role = "";
		if(user != null){

			if(user.getPassword()!=null){
				status = true;
			}
			for(UserRole Role : user.getUserRole()){
				role = Role.getRole();
			}
		}
		map.put("status",status);
		map.put("role",role);
		return map;
	}

//=========================To sign up an account for admin and driver================================
	@RequestMapping(value="/createUserr",method=RequestMethod.POST)
	@ResponseBody public Map<String,Object> createUserr(@RequestBody User_Info user) {
		Map<String,Object> map = new HashMap<String,Object>();
		int check  = usersService1.createUserr(user);
		if(check==0)
		{
			map.put("status","0");
			map.put("message","Email already existed!");
		}
		else if(check==1)
		{
			map.put("status","1");
			map.put("message","User has just been created successfully!");
		}
		else
		{
			map.put("status","5");
			map.put("message","Technical problem occurs");
		}
		return map;
	}

//=========================To change password for admin================================
	@RequestMapping(value="/changePass",method=RequestMethod.POST)
	@ResponseBody public Map<String,Object> changePass(@RequestBody User_Info user) {
		Map<String,Object> map = new HashMap<String,Object>();
		int check  = usersService1.changePass(user);
		if(check==0)
		{
			map.put("status","0");
			map.put("message","Current password is incorrect!");
		}
		else if(check==1)
		{
			map.put("status","1");
			map.put("message","You have just changed it successfully!");
		}
		else
		{
			map.put("status","5");
			map.put("message","Technical problem occurs!");
		}
		return map;
	}


//=========================Returns bus management view================================
	@RequestMapping(value="/bus_management", method=RequestMethod.GET)
	public ModelAndView viewBusMng() {
		return new ModelAndView("bus_management");
	}
	@RequestMapping(value="/ticketmng", method=RequestMethod.GET)
	public ModelAndView ticketmng() {
		return new ModelAndView("ticketmng");
	}

	@RequestMapping(value="/admin_profile", method=RequestMethod.GET)
	public ModelAndView admin_profile() {
		return new ModelAndView("admin_profile");
	}

	@RequestMapping(value="/student_home", method=RequestMethod.GET)
	public ModelAndView Student_Home() {
		return new ModelAndView("student_home");
	}
//=========================Returns report view================================
	@RequestMapping(value="/report", method=RequestMethod.GET)
	public ModelAndView viewReport() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("locations",usersService1.getAllLocations());
		map.put("times",usersService1.getAllTimes());
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("report","data",json);
	}
//=========================Returns report view================================
	@RequestMapping(value="/sReport", method=RequestMethod.GET)
	public ModelAndView sReport() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("locations",usersService1.getAllLocations());
		map.put("times",usersService1.getAllTimes());
		map.put("buses",usersService1.getAllBuses());
		map.put("drivers",usersService1.getAlDrivers());

		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("sReport","data",json);
	}

//=========================Returns admin booking view================================
	@RequestMapping(value="/admin_booking", method=RequestMethod.GET)
	public ModelAndView admin_booking() {
		return new ModelAndView("admin_booking");
	}


//=========================Returns historical_booking_requestt view================================
	@RequestMapping(value="/historical_booking_request", method=RequestMethod.GET)
	public ModelAndView historical_booking_request() {
		return new ModelAndView("historical_booking_request");
	}
//=========================Returns admin booking request view================================
	@RequestMapping(value="/admin_booking_request", method=RequestMethod.GET)
	public ModelAndView admin_booking_request() {
		return new ModelAndView("admin_booking_request");
	}
//=========================Returns location management view================================
	@RequestMapping(value="/location_management", method=RequestMethod.GET)
	public ModelAndView viewLocationMng() {
		return new ModelAndView("location_management");
	}
//=========================Returns current schedule view================================
	@RequestMapping(value="/current_schedule", method=RequestMethod.GET)
	public ModelAndView current_schedule() {
		return new ModelAndView("current_schedule");
	}
//=========================Returns schedule management view================================
	@RequestMapping(value="/schedule", method=RequestMethod.GET)
	public ModelAndView schedule() {
		return new ModelAndView("schedule");
	}
	//=========================Returns schedule management view================================
	@RequestMapping(value="/input_schedule", method=RequestMethod.GET)
	public ModelAndView input_schedule() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", getScheduleSequence());
		map.put("main_locations", usersService1.getAllLocations());
		map.put("locations", usersService1.getAllPickUpLocations());
		map.put("buses", usersService1.getAllBuses());
		map.put("drivers", usersService1.getAlDrivers());
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("input_schedule","data",json);
	}
//=========================Returns historical booking admin view================================
	@RequestMapping(value="/historical_booking", method=RequestMethod.GET)
	public ModelAndView historical_booking() {
		return new ModelAndView("historical_booking");
	}
//=========================Returns historical schedule view================================
	@RequestMapping(value="/historical_schedule", method=RequestMethod.GET)
	public ModelAndView historical_schedule() {
		return new ModelAndView("historical_schedule");
	}
//=========================Returns bus update view================================
	@RequestMapping(value="/bus_update", method=RequestMethod.GET)
	public ModelAndView bus_update(@RequestParam(value = "id", required=true, defaultValue = "0") Integer id) {
		Bus_Master bus = usersService1.getBusById(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bus", bus);
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("bus_update","data",json);
	}


//=========================Returns booking detail view================================
	@RequestMapping(value="/booking_detail", method=RequestMethod.GET)
	public ModelAndView booking_detail(@RequestParam(value = "id", required=true, defaultValue = "0") Integer id) {
		Booking_Master booking = usersService1.getBookingById(id);
		B_Model model = new B_Model();
		model.setN(usersService1.getCustomerById(booking.getUser_id()).getName());
		model.setCode(booking.getCode());
		model.setCreated_at(booking.getCreated_at().toString());
		model.setDept_date(booking.getDept_date().toString());
		model.setDept_time(booking.getDept_time());
		model.setDescription(booking.getDescription());
		model.setId(booking.getId());
		model.setDestination_id(booking.getDestination_id());
		model.setNotification(booking.getNotification());
		model.setNumber_booking(booking.getNumber_booking());
		model.setQr(booking.getQr());
		model.setSchedule_id(booking.getSchedule_id());
		model.setSource_id(booking.getSource_id());
		//model.setUpdated_at(booking.getUpdated_at().toString());
		model.setUser_id(booking.getUser_id());
		model.setFrom_id(booking.getFrom_id());
		model.setTo_id(booking.getTo_id());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("booking", model);
		map.put("locations", usersService1.getAllLocations());
		map.put("p_locations", usersService1.getAllPickUpLocations());
		map.put("customers", usersService1.getAlCustomers());
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("booking_detail","data",json);
	}



	@RequestMapping(value="/schedule_log_list", method=RequestMethod.GET)
	public ModelAndView schedule_log_list(@RequestParam(value = "id", required=true, defaultValue = "0") Integer id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("logs", usersService1.getAllLogsBySchedeuleId(id));
		map.put("code", usersService1.getScheduleById(id).getCode());
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("schedule_log_list","data",json);
	}



	@RequestMapping(value="/refund_detail", method=RequestMethod.GET)
	public ModelAndView refund_detail(@RequestParam(value = "id", required=true, defaultValue = "0") Integer id, @RequestParam(value = "rid", required=true, defaultValue = "0") Integer rid) {
		Booking_Master booking = usersService1.getBookingById(id);
		B_Model model = new B_Model();
		model.setN(usersService1.getCustomerById(booking.getUser_id()).getName());
		model.setCode(booking.getCode());
		model.setCreated_at(booking.getCreated_at().toString());
		model.setDept_date(booking.getDept_date().toString());
		model.setDept_time(booking.getDept_time());
		model.setDescription(booking.getDescription());
		model.setId(booking.getId());
		model.setDestination_id(booking.getDestination_id());
		model.setNotification(booking.getNotification());
		model.setNumber_booking(booking.getNumber_booking());
		model.setQr(rid.toString());
		model.setSchedule_id(booking.getSchedule_id());
		model.setSource_id(booking.getSource_id());
		//model.setUpdated_at(booking.getUpdated_at().toString());
		model.setUser_id(booking.getUser_id());
		model.setFrom_id(booking.getFrom_id());
		model.setTo_id(booking.getTo_id());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("booking", model);
		map.put("locations", usersService1.getAllLocations());
		map.put("p_locations", usersService1.getAllPickUpLocations());
		map.put("customers", usersService1.getAlCustomers());
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("refund_detail","data",json);
	}







//=========================Returns booking request detail view================================
	@RequestMapping(value="/request_detail", method=RequestMethod.GET)
	public ModelAndView request_detail(@RequestParam(value = "id", required=true, defaultValue = "0") Integer id) throws ParseException{
		Booking_Request_Master request = usersService1.getBookingRequestById(id);
		Map<String,Object> map = new HashMap<String,Object>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today=sdf.format(cal.getTime());
		Date d1 = sdf.parse(today);
		Date d2 = null;

		map.put("request", request);
		map.put("p_locations", usersService1.getAllPickUpLocations());
		map.put("locations", usersService1.getAllLocations());
		map.put("customers", usersService1.getAlCustomers());

		d1.setHours(0);
		d1.setMinutes(0);
		d1.setSeconds(0);
		d2 = request.getCreated_at();
		long diff = d2.getTime() - d1.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);
		if (diffDays<0)
			map.put("daydiff","past");
		else
			map.put("daydiff","current");


		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("request_detail","data",json);
	}


	@RequestMapping(value="/log_detail", method=RequestMethod.GET)
	public ModelAndView log_detail(@RequestParam(value = "id", required=true, defaultValue = "0") Integer id) throws ParseException{
		Schedule_Log log = usersService1.getScheduleLogById(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("log", log);
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("log_detail","data",json);
	}


//=========================Returns historical booking request detail view================================
	@RequestMapping(value="/historical_request_detail", method=RequestMethod.GET)
	public ModelAndView historical_request_detail(@RequestParam(value = "id", required=true, defaultValue = "0") Integer id) {
		Booking_Request_Master request = usersService1.getBookingRequestById(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("request", request);
		map.put("locations", usersService1.getAllLocations());
		map.put("p_locations", usersService1.getAllPickUpLocations());
		map.put("customers", usersService1.getAlCustomers());
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("historical_request_detail","data",json);
	}


//=========================Returns schedule detail view================================
	@RequestMapping(value="/schedule_detail", method=RequestMethod.GET)
	public ModelAndView schedule_detail(@RequestParam(value = "id", required=true, defaultValue = "0") Integer id) throws ParseException {
		Map<String,Object> map = new HashMap<String,Object>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today=sdf.format(cal.getTime());
		Date d1 = sdf.parse(today);
		Date d2 = null;
        Schedule_Master schedule = usersService1.getScheduleById(id);
        String screen = "";
		map.put("schedule", schedule);
		map.put("schedules", usersService1.getAllSchedules());
		map.put("locations", usersService1.getAllLocations());
		map.put("p_locations", usersService1.getAllPickUpLocations());
		map.put("buses", usersService1.getAllBuses2());
		map.put("bookings", usersService1.getBookingByScheduleId(id));
		map.put("drivers", usersService1.getAlDrivers());
		map.put("customers", usersService1.getAlCustomers());
		d1.setHours(0);
		d1.setMinutes(0);
		d1.setSeconds(0);
		d2 = schedule.getDept_date();
		long diff = d2.getTime() - d1.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);
		if (diffDays<0)
			map.put("daydiff","past");
		else
			map.put("daydiff","current");

		if(schedule.getNumber_customer()+schedule.getNumber_student()+schedule.getNumber_staff()>0)
			screen+="schedule_detail2";
		else
			screen+="schedule_detail";
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(screen,"data",json);
	}
//=========================Returns create schedule view================================
	@RequestMapping(value="/create_schedule", method=RequestMethod.GET)
	public ModelAndView create_schedule() {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Student> list2 = new ArrayList<Student>();
		map.put("code", getScheduleSequence());
		map.put("main_locations", usersService1.getAllLocations());
		map.put("locations", usersService1.getAllPickUpLocations());
		map.put("buses", usersService1.getAllBuses());
		for (Bus_Master bus:usersService1.getAllBuses()){
			Student student = new Student();
			student.setId(bus.getId());
			student.setText(bus.getModel()+" "+bus.getPlate_number());
			list2.add(student);
		}
		map.put("multibus", list2);
		map.put("drivers", usersService1.getAlDrivers());
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("create_schedule","data",json);
	}

//=========================Returns create user view================================
	@RequestMapping(value="/create_user", method=RequestMethod.GET)
	public ModelAndView create_user() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", getScheduleSequence());
		map.put("main_locations", usersService1.getAllLocations());
		map.put("locations", usersService1.getAllPickUpLocations());
		map.put("buses", usersService1.getAllBuses());
		map.put("drivers", usersService1.getAlDrivers());
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("create_user","data",json);
	}



//====================To save bus============================
	@RequestMapping(value="/createBus", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> toSaveProject(Bus_Master bus) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		int check = usersService1.saveBus(bus);
		if(check==0)
		{
			map.put("status","0");
			map.put("message","Plate number already existed!");
		}
		else if(check==1)
		{
			map.put("status","1");
			map.put("message","Bus has just been created successfully");
		}
		else
		{
			map.put("status","5");
			map.put("message","Technical problem occurs");
		}
		return map;
		}
//====================To save schedule by admin============================
	@RequestMapping(value="/createSchedule", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> toSaveSchedule(Schedule_Model schedule) throws Exception{
		Map<String,Object> res = new HashMap<String,Object>();
		if(schedule.getRound())
		{
			res = usersService1.saveSchedule(schedule, "Round");
			SendCreateThread  sendCreateThread = new SendCreateThread((Schedule_Model) res.get("schedule"));
			SendCreateThread  sendCreateThread2 = new SendCreateThread((Schedule_Model) res.get("return_schedule"));
			sendCreateThread.start();
			sendCreateThread2.start();
			return res;
		}
		res = usersService1.saveSchedule(schedule);
		SendCreateThread  sendCreateThread = new SendCreateThread((Schedule_Model) res.get("schedule"));
		sendCreateThread.start();
		return res;
		}


//====================To save schedule by admin============================
	@RequestMapping(value="/updateSchedule", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> toUpdateSchedule(Schedule_Model schedule) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
	//		System.out.println(schedule.getCode()+"  "+schedule.getDriver_id()+"  "+schedule.getBus_id()+"  "+schedule.getSource_id()+"  "+schedule.getDestination_id()+"  "+schedule.getNumber_booking()+"  "+schedule.getDept_date()+"  "+schedule.getDept_time());;
		Map<String,Object> res = new HashMap<String,Object>();
		res = usersService1.updateSchedule(schedule);
		if(res.get("status").equals("0"))
		{
			map.put("status","0");
			map.put("message","Code already existed!");
		}
		else if(res.get("status").equals("1"))
		{
			map.put("status","1");
			map.put("message","Schedule has just been updated successfully");
		}
		else
		{
			map.put("status","5");
			map.put("message","Technical problem occurs");
		}
		SendUpdateThread  sendUpdateThread = new SendUpdateThread((Schedule_Model) res.get("schedule"), (Schedule_Model) res.get("new_schedule"));
		sendUpdateThread.start();
		return map;
	}

	@RequestMapping(value="/createMultipleSchedule", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> createMultipleSchedule(Schedule_Model schedule) throws Exception{
		Map<String,Object> res = new HashMap<String,Object>();
		if(schedule.getChoice()==1)
		{
			res = usersService1.saveMultipleSchedule(schedule, "Round");
			return res;
		}
		else if(schedule.getChoice()==0){
			res = usersService1.saveMultipleSchedule(schedule);
			return res;
		}
		res = usersService1.saveMultipleSchedule(schedule, "Round", "Respect");
		return res;

	}



	@RequestMapping(value="/reportSubmit", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> reportSubmit(B_Model booking) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println(booking.getFrom_id()+"  "+booking.getTo_id()+"  "+booking.getDept_date()+"  "+booking.getN()+"  "+booking.getNotification());
		map.put("bookings",usersService1.getBookingReporting(booking));
		map.put("locations", usersService1.getAllLocations());
		map.put("p_locations", usersService1.getAllPickUpLocations());
		map.put("customers", usersService1.getAlCustomers());
		return map;
		}


	@RequestMapping(value="/bookingReport2", method=RequestMethod.GET)
	public ModelAndView bookingReport2(@RequestParam(value = "data", required=true, defaultValue = "null") String data) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bookings", data);
		map.put("locations", usersService1.getAllLocations());
		map.put("p_locations", usersService1.getAllPickUpLocations());
		map.put("customers", usersService1.getAlCustomers());
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("bookingReport2","data",json);
	}



	@RequestMapping(value="/sReportSubmit", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> sReportSubmit(B_Model booking) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println(booking.getFrom_id()+"  "+booking.getTo_id()+"  "+booking.getDept_date()+"  "+booking.getN()+"  "+booking.getNotification());
		map.put("schedules",usersService1.getScheduleReporting(booking));
		map.put("locations", usersService1.getAllLocations());
		map.put("buses", usersService1.getAllBuses());
		map.put("drivers", usersService1.getAlDrivers());
		return map;
		}



	@RequestMapping(value="/scheduleReport2", method=RequestMethod.GET)
	public ModelAndView scheduleReport2(@RequestParam(value = "data", required=true, defaultValue = "null") String data) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("schedules", data);
		map.put("locations", usersService1.getAllLocations());
		map.put("buses", usersService1.getAllBuses());
		map.put("drivers", usersService1.getAlDrivers());
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("scheduleReport2","data",json);
	}




	@RequestMapping(value="/createTime", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> toSaveTime(Schedule_Model schedule) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		int check = usersService1.saveTime(schedule);
		if(check==0)
		{
			map.put("status","0");
			map.put("message","Departure time already existed!");
		}
		else if(check==1)
		{
			map.put("status","1");
			map.put("message","New departure time has just been created successfully");
		}
		else
		{
			map.put("status","5");
			map.put("message","Technical problem occurs");
		}
		return map;
		}



		@RequestMapping(value="/createDate", method=RequestMethod.GET)
		public @ResponseBody Map<String,Object> toSaveDate(Batch_Master batch) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		int check = usersService1.saveDate(batch);
		if(check==0)
		{
			map.put("status","0");
			map.put("message","Departure date of the batch already existed!");
		}
		else if(check==1)
		{
			map.put("status","1");
			map.put("message","New departure date has just been created successfully");
		}
		else
		{
			map.put("status","5");
			map.put("message","Technical problem occurs");
		}
		return map;
		}


		@RequestMapping(value="/sendMonthlySchedule", method=RequestMethod.GET)
		public @ResponseBody Map<String,Object> sendMonthlySchedule(Schedule_Model model) throws Exception{
			Map<String,Object> map = new HashMap<String,Object>();
			Map<String,Object> res = new HashMap<String,Object>();
			res = usersService1.sendMonthlySchedule(model);
			if(res.get("status").equals("1"))
			{
				map.put("status","1");
				map.put("message","Emails are being sent in background!");
			}
			else
			{
				map.put("status","5");
				map.put("message","No Schedules Found In This Month!");
			}
			SendMonthlyThread  sendMonthlyThread = new SendMonthlyThread((List <Schedule_Master>)res.get("schedules"));
			sendMonthlyThread.start();

			return map;
		}


		@RequestMapping(value="/createPrice", method=RequestMethod.GET)
		public @ResponseBody Map<String,Object> toSavePrice(Cost cost) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		int check = usersService1.saveCost(cost);
		if(check==1)
		{
			map.put("status","1");
			map.put("message","Prices have just been created successfully!");
		}
		else
		{
			map.put("status","5");
			map.put("message","Technical problem occurs!");
		}
		return map;
		}



		@RequestMapping(value="/updatePrice", method=RequestMethod.GET)
		public @ResponseBody Map<String,Object> toUpdatePrice(Cost cost) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		int check = usersService1.updateCost(cost);
		if(check==1)
		{
			map.put("status","1");
			map.put("message","Prices have just been updated successfully!");
		}
		else
		{
			map.put("status","5");
			map.put("message","Technical problem occurs!");
		}
		return map;
		}



		@RequestMapping(value="/ReqEmail", method=RequestMethod.GET)
		public Map<String,Object> apReqEmail(@RequestParam(value = "token", required=true) String token, @RequestParam(value = "type", required=true) String type) throws Exception{
			Map<String,Object> map = new HashMap<String,Object>();
			int check = usersService1.apReqEmail(token, type);
			System.out.println("Checkkkkkk "+Integer.toString(check));
			if(check==0)
			{
				map.put("status","0");
				map.put("message","Token not found!");
			}
			else if(check==1)
			{
				map.put("status","1");
				map.put("message","Request has just been confirmed successfully");
			}
			else
			{
				map.put("status","5");
				map.put("message","Technical problem occurs");
			}
			return map;
		}


		@RequestMapping(value="/updateDate", method=RequestMethod.GET)
		public @ResponseBody Map<String,Object> toUpdateDate(Batch_Master batch) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		int check = usersService1.updateDate(batch);
			if(check==0)
			{
				map.put("status","0");
				map.put("message","Batch name already existed!");
			}
			else if(check==1)
			{
				map.put("status","1");
				map.put("message","Departure date of that batch has just been updated successfully");
			}
			else
			{
				map.put("status","5");
				map.put("message","Technical problem occurs");
			}
			return map;
		}




	//====================To getBookingByScheduleId by admin============================
		@RequestMapping(value="/getBookingByScheduleId", method=RequestMethod.GET)
		public @ResponseBody Map<String,Object> toGetBookingByScheduleId(@RequestParam(value = "id", required=true) Integer id) throws Exception{
			Map<String,Object> map = new HashMap<String,Object>();
			List <Booking_Master> bookings  = new ArrayList<Booking_Master>();
			bookings = usersService1.getBookingByScheduleId(id);
			int check =bookings.size();
			if(check>0)
				map.put("message","Not Fine");
			else
				map.put("message","Fine");
			return map;
			}
//====================To delete Schedule By id by admin============================
		@RequestMapping(value="/deleteSchedule", method=RequestMethod.GET)
		public @ResponseBody Map<String,Object> deleteSchedule(@RequestParam(value = "id", required=true) Integer id) throws Exception{
			Map<String,Object> map = new HashMap<String,Object>();
			List <Booking_Master> bookings  = new ArrayList<Booking_Master>();
			bookings = usersService1.getBookingByScheduleId(id);
			int check =bookings.size();
			if(usersService1.deleteSchedule(id)==1)
					{
						map.put("status", "1");
						map.put("message","This schedule has just been deleted successfully!");
					}
				else
					{
						map.put("status", "0");
						map.put("message","This schedule has not been deleted!");
					}

			return map;
			}


@RequestMapping(value="/deleteTime", method=RequestMethod.GET)
		public @ResponseBody Map<String,Object> deleteTime(@RequestParam(value = "id", required=true) Integer id) throws Exception{
			Map<String,Object> map = new HashMap<String,Object>();
			int check =usersService1.deleteTime(id);
			if(check==1)
					{
						map.put("status", "1");
						map.put("message","This departure time has just been deleted successfully!");
					}
				else
					{
						map.put("status", "0");
						map.put("message","This departure time has not been deleted!");
					}
			return map;
			}


@RequestMapping(value="/deleteDate", method=RequestMethod.GET)
		public @ResponseBody Map<String,Object> deleteDate(@RequestParam(value = "id", required=true) Integer id) throws Exception{
			Map<String,Object> map = new HashMap<String,Object>();
			int check =usersService1.deleteDate(id);
			if(check==1)
					{
						map.put("status", "1");
						map.put("message","This departure date has just been deleted successfully!");
					}
				else
					{
						map.put("status", "0");
						map.put("message","This departure date has not been deleted!");
					}
			return map;
			}





		//====================To save schedule by admin (Schedule that already had bookings============================
	@RequestMapping(value="/updateSchedule2", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> toUpdateSchedule2(Schedule_Model schedule) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
	//		System.out.println(schedule.getCode()+"  "+schedule.getDriver_id()+"  "+schedule.getBus_id()+"  "+schedule.getSource_id()+"  "+schedule.getDestination_id()+"  "+schedule.getNumber_booking()+"  "+schedule.getDept_date()+"  "+schedule.getDept_time());;
		int check = usersService1.updateSchedule2(schedule);
		if(check==1)
		{
			map.put("status","1");
			map.put("message","Schedule has just been updated successfully");
		}
		else
		{
			map.put("status","5");
			map.put("message","Technical problem occurs");
		}
		return map;
	}
//====================To move booking to new schedule by admin============================
	@RequestMapping(value="/moveNew", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> toMoveNew(Schedule_Model s) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		int all_booking = 0;
		int a[] =s.getB();
		for(int i=0; i<a.length;i++)
			all_booking+=usersService1.getBookingById(a[i]).getNumber_booking();
		Schedule_Model newSchedule = new Schedule_Model();
		Schedule_Master schedule = usersService1.getScheduleById(s.getId());
		newSchedule.setIdd(s.getId());
		newSchedule.setCode(getScheduleSequence());
		newSchedule.setDriver_id(s.getDriver_id());
		newSchedule.setBus_id(s.getBus_id());
		newSchedule.setSource_id(schedule.getSource_id());
		newSchedule.setDestination_id(schedule.getDestination_id());
//		newSchedule.setNumber_booking(all_booking);
		newSchedule.setFrom_id(schedule.getFrom_id());
		newSchedule.setTo_id(schedule.getTo_id());
		int check = usersService1.saveSchedule2(newSchedule);
		if(check==0)
		{
			map.put("status","0");
			map.put("message","Schedule code already existed!");
		}
		else if(check==-5)
		{
			map.put("status","0");
			map.put("message","Technical problem when creating new schedule occurs!");
		}
		else
		{
			int check2 = usersService1.moveSchedule(a,check);
			if(check2==1)
			{
				map.put("status","1");
				map.put("message","New schedule has just been created and its code is "+usersService1.getScheduleById(check).getCode()+"!");
			}
			else
			{
				usersService1.deleteSchedule(check);
				map.put("status","0");
				map.put("message","New schedule has not just been created and bookings are not moved to anywhere!");
			}
		}
		return map;
		}


		@RequestMapping(value="/moveToRental", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> moveToRental(Schedule_Model s) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,String> check = new HashMap<String,String>();
		int b_id[] =s.getB();
		System.out.println("lengthhhhh "+b_id[0]);
		Booking_Master booking = usersService1.getBookingById(b_id[0]);
		s.setDept_date2(booking.getDept_date());
		s.setDept_time2(booking.getDept_time());
		check = usersService1.moveToRental(s);
		if(check.get("status").equals("1"))
		{
			map.put("status","1");
			map.put("message","Bookings have just been successfully moved to a new schedule code "+check.get("code")+"!");
		}
		else if(check.get("status").equals("55"))
		{
			map.put("status","55");
			map.put("message","Bookings have just been successfully moved to an existing schedule code "+check.get("code")+"!");
		}
		else
		{
			map.put("status","0");
			map.put("message","Bookings have not been successfully moved!");
		}
		return map;
		}



//====================To move booking to existing schedule by admin============================
	@RequestMapping(value="/moveSimple", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> toMoveSimlple(Schedule_Model s) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
//		int check = usersService1.moveSimple(s.getB(), s.getOld_id(), s.getNew_id(), s.getNumber_booking());
//		if(check==1)
//		{
//			map.put("status","1");
//			map.put("message","Bookings have just been successfully moved!");
//		}
//		else
//		{
//			map.put("status","0");
//			map.put("message","Bookings have not been successfully moved!");
//		}
		return map;
		}




//====================To confirm request by admin============================
	@RequestMapping(value="/confirmRequest", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> toConfirmRequest(Booking_Request_Master request) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		int check = usersService1.confirmRequest(request);
		if(check==0)
		{
			map.put("status","0");
			map.put("message","Technical problem occurs!");

		}
		else
		{
			map.put("status","1");
			map.put("message","This request has just been confirmed successfully");
			User_Info user = usersService1.getCustomerById(request.getUser_id());
			String email = user.getEmail();
//			usersService1.confirmedRequest(email);
		}

		return map;
		}



//====================To reject request by admin============================
	@RequestMapping(value="/rejectRequest", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> toRejectRequest(Booking_Request_Master request) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		int check = usersService1.rejectRequest(request);
		if(check==0)
		{
			map.put("status","0");
			map.put("message","Technical problem occurs!");

		}
		else
		{
			map.put("status","1");
			map.put("message","This request has just been rejected successfully");
			User_Info user = usersService1.getCustomerById(request.getUser_id());
			String email = user.getEmail();
			usersService1.rejectedRequest(email);
		}

		return map;
		}



	@RequestMapping(value="/ignoreRefund", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> ignoreRefund(Refund_Master refund) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		int check = usersService1.ignoreRefund(refund);
		if(check==0)
		{
			map.put("status","0");
			map.put("message","Technical problem occurs!");

		}
		else
		{
			map.put("status","1");
			map.put("message","This refund has just been rejected successfully");
			// User_Info user = usersService1.getCustomerById(request.getUser_id());
			// String email = user.getEmail();
			// usersService1.rejectedRequest(email);
		}

		return map;
		}

		@RequestMapping(value="/doneRefund", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> doneRefund(Refund_Master refund) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		int check = usersService1.doneRefund(refund);
		if(check==0)
		{
			map.put("status","0");
			map.put("message","Technical problem occurs!");

		}
		else
		{
			map.put("status","1");
			map.put("message","This refund status has just been updated successfully");
			// User_Info user = usersService1.getCustomerById(request.getUser_id());
			// String email = user.getEmail();
			// usersService1.rejectedRequest(email);
		}

		return map;
		}



	@RequestMapping(value="/payBooking", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> payBooking(Booking_Master booking) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		int check = usersService1.payBooking(booking);
		if(check==0)
		{
			map.put("status","0");
			map.put("message","Technical problem occurs!");

		}
		else
		{
			map.put("status","1");
			map.put("message","This booking has just been paid successfully!");
			// User_Info user = usersService1.getCustomerById(request.getUser_id());
			// String email = user.getEmail();
			// usersService1.rejectedRequest(email);
		}

		return map;
		}




//====================To update bus============================
		@RequestMapping(value="/updateBus", method=RequestMethod.GET)
		public @ResponseBody Map<String,Object> toUpdateBus(Bus_Master bus) throws Exception{
			Map<String,Object> map = new HashMap<String,Object>();
			int check = usersService1.updateBus(bus);
			if(check==0)
			{
				map.put("status","0");
				map.put("message","Plate number already existed!");
			}
			else if(check==1)
			{
				map.put("status","1");
				map.put("message","Bus has just been updated successfully");
			}
			else
			{
				map.put("status","5");
				map.put("message","Technical problem occurs");
			}
			return map;
			}
//====================To update location============================
		@RequestMapping(value="/updateLocation", method=RequestMethod.GET)
		public @ResponseBody Map<String,Object> toUpdateLocation(Location_Master location) throws Exception{
			Map<String,Object> map = new HashMap<String,Object>();
			int check = usersService1.updateLocation(location);
			if(check==0)
			{
				map.put("status","0");
				map.put("message","Location name already existed!");
			}
			else if(check==1)
			{
				map.put("status","1");
				map.put("message","Location has just been updated successfully");
			}
			else
			{
				map.put("status","5");
				map.put("message","Technical problem occurs");
			}
			return map;
			}
//====================To update pick up location============================
		@RequestMapping(value="/updatePickUpLocation", method=RequestMethod.GET)
		public @ResponseBody Map<String,Object> toUpdatePickUpLocation(Pickup_Location_Master p_location) throws Exception{
			Map<String,Object> map = new HashMap<String,Object>();
			int check = usersService1.updatePickUpLocation(p_location);
			if(check==0)
			{
				map.put("status","0");
				map.put("message","Pick up location name already existed!");
			}
			else if(check==1)
			{
				map.put("status","1");
				map.put("message","Pick up location has just been updated successfully");
			}
			else
			{
				map.put("status","5");
				map.put("message","Technical problem occurs");
			}
			return map;
			}
//====================To delete bus============================
		@RequestMapping(value="/deleteBus", method = RequestMethod.GET)
		public @ResponseBody Map<String,String>  deleteProjectDetail(@RequestParam(value = "id", required=false) Integer id){
			Map<String,String> map = new HashMap<String,String>();
			int status = 0;
			status= usersService1.deleteBus(id);
			if(status==1)
				{
					map.put("status", "200");
					map.put("message","OK");
				}
			else if(status==5)
				{
					map.put("status", "500");
					map.put("message","Cannot be deleted, This bus has been assigned to either current or future schedule!");
				}
			else
				{
					map.put("status", "300");
					map.put("message","Technical problem occurs");
				}
			return map;
			}
//====================To delete location============================
		@RequestMapping(value="/deleteLocation", method = RequestMethod.GET)
		public @ResponseBody Map<String,String>  deleteLocation(@RequestParam(value = "id", required=false) Integer id){
			Map<String,String> map = new HashMap<String,String>();
			int status = 0;
			status= usersService1.deleteLocation(id);
			if(status==1)
				map.put("status", "200");
			else
				map.put("status", "300");

			return map;
			}
//====================To delete pick up location============================
		@RequestMapping(value="/deletePickUpLocation", method = RequestMethod.GET)
		public @ResponseBody Map<String,String>  deletePickUpLocation(@RequestParam(value = "id", required=false) Integer id){
			Map<String,String> map = new HashMap<String,String>();
			int status = 0;
			status= usersService1.deletePickUpLocation(id);
			if(status==1)
				map.put("status", "200");
			else
				map.put("status", "300");

			return map;
			}

//====================To save location============================
	@RequestMapping(value="/createLocation", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> toSaveLocation(Location_Master location) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		int check = usersService1.saveLocation(location);
		if(check==0)
		{
			map.put("status","0");
			map.put("message","Location name already existed!");
		}
		else if(check==1)
		{
			map.put("status","1");
			map.put("message","Location has just been created successfully");
		}
		else
		{
			map.put("status","5");
			map.put("message","Technical problem occurs");
		}
		return map;
		}
//====================To refill ticket============================
	@RequestMapping(value="/refillTicket", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> refillTicket(Ticket refill) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println("AAAA");
		map.put("a", refill.getForall());
		map.put("o", refill.getForold());
		map.put("users", refill.getUsers());
		map.put("noticket", refill.getNoticket());
		// int check = usersService1.refillTicket(refill);
		// if(check==0)
		// {
		// 	map.put("status","0");
		// 	map.put("message","Locatio	n name already existed!");
		// }
		// else if(check==1)
		// {
		// 	map.put("status","1");
		// 	map.put("message","Location has just been created successfully");
		// }
		// else
		// {
		// 	map.put("status","5");
		// 	map.put("message","Technical problem occurs");
		// }
		return map;
		}
//====================Booking report============================
	@RequestMapping(value="/bookingReport", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> bookingReport(){
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> map2 = new HashMap<String,Object>();
		map.put("locations",usersService1.getAllLocations());
		map.put("times",usersService1.getAllTimes());
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map2.put("data", json);
		return map2;
		}

	@RequestMapping(value="/departure_time", method=RequestMethod.GET)
	public ModelAndView departure_time() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("times", usersService1.getAllTimes());
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("departure_time","data",json);
	}
	@RequestMapping(value="/departure_date", method=RequestMethod.GET)
	public ModelAndView departure_date() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("times", usersService1.getAllDates());
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("departure_date","data",json);
	}
	@RequestMapping(value="/pricemng", method=RequestMethod.GET)
	public ModelAndView pricemng() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("price", usersService1.getAllPrices());
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("pricemng","data",json);
	}


//====================To save location============================
	@RequestMapping(value="/createPickUpLocation", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> toSavePickUpLocation(Pickup_Location_Master p_location) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		int check = usersService1.savePickUpLocation(p_location);
		if(check==0)
		{
			map.put("status","0");
			map.put("message","Pick up location name already existed!");
		}
		else if(check==1)
		{
			map.put("status","1");
			map.put("message","Pick up location has just been created successfully");
		}
		else
		{
			map.put("status","5");
			map.put("message","Technical problem occurs");
		}
		return map;
		}
	@RequestMapping(value="/getAllBuses", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getProjectCategoryList(){

		 Map<String,Object> map = new HashMap<String,Object>();

		   // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
			List<Bus_Master> list = usersService1.getAllBuses();

			if (list != null)
				map.put("buses", list);
			else
				map.put("message","Data not found");

			return map;
	}
	@RequestMapping(value="/getAllStudents", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getAllStudents(){

		 Map<String,Object> map = new HashMap<String,Object>();

		   // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
			List<User_Info> list = usersService1.getAllStudents();
			List <Student> list2 = new ArrayList<Student>();

			if (list != null){
				for (User_Info user:list){
					Student student = new Student();
					student.setId(user.getId());
					student.setText(user.getUsername());
					list2.add(student);
				}
				map.put("students", list2);
			}
			else
				map.put("message","Data not found");

			return map;
	}
	@RequestMapping(value="/getAllCurrentBookings", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getAllCurrentBookings(){

		 Map<String,Object> map = new HashMap<String,Object>();

		   // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
			List<Booking_Master> list = usersService1.getAllCurrentBookings();
			List<Location_Master> list2 =  usersService1.getAllLocations();

			if (list != null)
			{
			map.put("locations", list2);
			map.put("bookings", list);
			map.put("customers", usersService1.getAlCustomers());
			map.put("unpaid", usersService1.getAllUnpaidBookings());
			map.put("refund", usersService1.getAllRefunds());
			}
			else
				map.put("message","Data not found");

			return map;
	}





	@RequestMapping(value="/getAllCurrentBookingRequests", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getAllCurrentBookingRequests(){

		 Map<String,Object> map = new HashMap<String,Object>();

		   // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
			List<Booking_Request_Master> list = usersService1.getAllCurrentBookingRequests();
			List<Location_Master> list2 =  usersService1.getAllLocations();

			if (list != null)
			{
			map.put("locations", list2);
			map.put("requests", list);
			map.put("customers", usersService1.getAlCustomers());
			}
			else
				map.put("message","Data not found");

			return map;
	}


	@RequestMapping(value="/getBookingRequestNotification", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getBookingRequestNotification(){

		 Map<String,Object> map = new HashMap<String,Object>();

		   // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
			List<Booking_Request_Master> list = usersService1.getAllCurrentBookingRequests();

			if (list != null)
				map.put("requests", list);
			else
				map.put("message","Data not found");

			return map;
	}




	@RequestMapping(value="/getAllHistoricalBookingRequests", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getAllHistoricalBookingRequests(){

		 Map<String,Object> map = new HashMap<String,Object>();

		   // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
			List<Booking_Request_Master> list = usersService1.getAllHistoricalBookingRequests();
			List<Location_Master> list2 =  usersService1.getAllLocations();

			if (list != null)
			{
			map.put("locations", list2);
			map.put("requests", list);
			map.put("customers", usersService1.getAlCustomers());
			}
			else
				map.put("message","Data not found");

			return map;
	}






	@RequestMapping(value="/getAllCurrentSchedules", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getAllCurrentSchedules(){

			Map<String,Object> map = new HashMap<String,Object>();
			List<Schedule_Master> list = usersService1.getAllCurrentSchedules();
			if (list != null)
			{
			map.put("schedules", list);
			map.put("locations", usersService1.getAllLocations());
			map.put("buses", usersService1.getAllBuses2());
			map.put("drivers", usersService1.getAlDrivers());
			}
			else
				map.put("message","Data not found");

			return map;
	}

	@RequestMapping(value="/getAllSchedules", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getAllSchedules(){

		Map<String,Object> map = new HashMap<String,Object>();
		List<Schedule_Master> list = usersService1.getAllSchedules();
		if (list != null)
		{
			map.put("schedules", list);
			map.put("locations", usersService1.getAllLocations());
			map.put("buses", usersService1.getAllBuses2());
			map.put("drivers", usersService1.getAlDrivers());
		}
		else
			map.put("message","Data not found");

		return map;
	}




	@RequestMapping(value="/getAllHistoricalSchedules", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getAllHistoricalSchedules(){

			Map<String,Object> map = new HashMap<String,Object>();
			List<Schedule_Master> list = usersService1.getAllHistoricalSchedules();
			if (list != null)
			{
			map.put("schedules", list);
			map.put("locations", usersService1.getAllLocations());
			map.put("buses", usersService1.getAllBuses2());
			map.put("drivers", usersService1.getAlDrivers());
			}
			else
				map.put("message","Data not found");

			return map;
	}




	@RequestMapping(value="/getAllHistoricalBookings", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getAllHistoricalBookings(){

		 Map<String,Object> map = new HashMap<String,Object>();

		   // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
			List<Booking_Master> list = usersService1.getAllHistoricalBookings();
			List<Location_Master> list2 =  usersService1.getAllLocations();

			if (list != null)
				{
				map.put("locations", list2);
				map.put("bookings", list);
				map.put("customers", usersService1.getAlCustomers());
				}

			else
				map.put("message","Data not found");

			return map;
	}

	@RequestMapping(value="/getAllSchedulesByMonth", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getAllSchedulesByMonth(@RequestParam(value = "month", required=true, defaultValue = "0") Integer month, @RequestParam(value = "year", required=true, defaultValue = "0") Integer year) throws ParseException{
		 Map<String,Object> map = new HashMap<String,Object>();
			List<?> list = usersService1.getAllSchedulesByMonth(Integer.toString(month),Integer.toString(year));
			if (list != null)
				map.put("schedules", list);
			else
				map.put("message","Data not found");

			return map;
	}
//=========================Returns schedule list by month view================================
	@RequestMapping(value="/schedule_list", method=RequestMethod.GET)
	public ModelAndView schedule_list(@RequestParam(value = "date", required=true, defaultValue = "0") Integer date,@RequestParam(value = "month", required=true, defaultValue = "0") Integer month, @RequestParam(value = "year", required=true, defaultValue = "0") Integer year) throws ParseException{
		List<Schedule_Master> list = usersService1.schedule_list(Integer.toString(date),Integer.toString(month),Integer.toString(year));
		List<Pickup_Location_Master> list2 =  usersService1.getAllPickUpLocations();
		List<Bus_Master> list3 =  usersService1.getAllBuses2();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("schedules", list);
		map.put("locations", list2);
		map.put("buses", list3);
		map.put("drivers", usersService1.getAlDrivers());
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("schedule_list","data",json);
	}




	@RequestMapping(value="/refund_list", method=RequestMethod.GET)
	public ModelAndView refund_list() throws ParseException{
		List<Refund_Master> refunds =  usersService1.getAllRefunds();
		List<Booking_Master> bookings = new ArrayList<Booking_Master>();
		for (Refund_Master refund:refunds)
		{
			Booking_Master booking = usersService1.getBookingById(refund.getBooking_id());
			bookings.add(booking);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("refunds", refunds);
		map.put("bookings", bookings);
		map.put("customers", usersService1.getAlCustomers());
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("refund_list","data",json);
	}



	@RequestMapping(value="/unpaid_booking_list", method=RequestMethod.GET)
	public ModelAndView unpaid_booking_list() throws ParseException{
		List<Booking_Master> bookings =  usersService1.getAllUnpaidBookings();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bookings", bookings);
		map.put("customers", usersService1.getAlCustomers());
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("unpaid_booking_list","data",json);
	}





	@RequestMapping(value="/getAllLocations", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getAllLocations(){

		 Map<String,Object> map = new HashMap<String,Object>();

		   // DaoClasses.userDaoImpl dao = new DaoClasses.userDaoImpl();
			List<Location_Master> list = usersService1.getAllLocations();
			List<Pickup_Location_Master> list2 =  usersService1.getAllPickUpLocations();

			if (list != null)
				{
				map.put("locations", list);
				map.put("p_locations", list2);
				}
			else
				map.put("message","Data not found");

			return map;
	}

	@RequestMapping(value="/getLocationById", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getLocationById(@RequestParam(value = "id", required=true, defaultValue = "0") Integer id) {
		Location_Master location = usersService1.getLocationById(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("location", location);
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	@RequestMapping(value="/getDById", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getDById(@RequestParam(value = "id", required=true, defaultValue = "0") Integer id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("driver", usersService1.getCustomerById(id));
		return map;
	}

	@RequestMapping(value="/getPickUpLocationById", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getPickUpLocationById(@RequestParam(value = "id", required=true, defaultValue = "0") Integer id) {
		Pickup_Location_Master p_location = usersService1.getPickUpLocationById(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("p_location", p_location);
		ObjectMapper mapper = new ObjectMapper();
		String json="";
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	public static String getScheduleSequence(){
		List<Schedule_Master> schedules = new ArrayList<Schedule_Master>();
		schedules = new userDaoImpl().getAllSchedules();
		int code;
		String scode= "000001";
		for(Schedule_Master s : schedules)
			System.out.println(s.getId());
		if(schedules.size()>0){
			code = 1000000+(int)schedules.size()+1;
			scode = Integer.toString(code);
			scode = scode.substring(1);
			return "S"+scode;
		}
		else
			return "S"+scode;

	}

	public static String getBookingSequence(){
		List<Booking_Master> bookings = new ArrayList<Booking_Master>();
		bookings = new userDaoImpl().getAllBookings();
		int code;
		String scode= "000001";
		for(Booking_Master s : bookings)
			System.out.println(s.getId());
		if(bookings.size()>0){
			code = 1000000+(int)bookings.size()+1;
			scode = Integer.toString(code);
			scode = scode.substring(1);
			return "B"+scode;
		}
		else
			return "B"+scode;

	}
}
