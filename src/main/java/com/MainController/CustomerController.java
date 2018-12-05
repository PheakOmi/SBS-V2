package com.MainController;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.DaoClasses.*;
import com.ModelClasses.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.EntityClasses.Pickup_Location_Master;
import com.EntityClasses.User_Info;
import com.EntityClasses.Cost;
import com.ModelClasses.Customer_Booking;
import com.ModelClasses.New_Pickup_Location;
import com.ModelClasses.UserModel;
import com.PaymentGateway.PayWayApiCheckout;


@Controller
public class CustomerController {
	Custom_Dao customer=new Custom_Imp();
	
	//========================= Sign Up UI================================
	@RequestMapping(value="/sign_up")
	public ModelAndView sign_up() {
		return new ModelAndView("sign_up");
	}
	//========================= Forget Password UI================================
	@RequestMapping(value="/forget_password")
	public ModelAndView forget_password() {
		return new ModelAndView("forget_password");
	}
	//========================= Forget Password UI================================
	@RequestMapping(value="/submit_reset_password_email", method=RequestMethod.POST)
	@ResponseBody public Map<String, Object> submit_reset_password_email(@RequestBody UserModel user) {
		System.out.println("Eamil:: "+user.getEmail());
		Custom_Dao cust=new Custom_Imp();
		Map<String, Object> ret=cust.check_and_send_email(user.getEmail());
		System.out.println("Return:: "+ret);
		return ret;
	}
	// Reset Password
	@RequestMapping(value = "/reset_password", method = RequestMethod.GET)
	public ModelAndView reset_password(HttpServletRequest request,String name) {
			ModelAndView view = null;
			Custom_Dao cust=new Custom_Imp();
			List<User_Info> user=cust.check_valid_tocken(name);
	        System.out.println(name);
			try{
				if(user.size()==0){
					view = new ModelAndView("invalid_link_reset_password");
				}else{
					Map<String, Object> model = new HashMap<String, Object>();
					model.put("id", user.get(0).getId());
					model.put("email", user.get(0).getEmail());
					view = new ModelAndView("valid_link_reset_password","model",model);
				}
			}catch (RuntimeException e)//NullpointerException
			{
				view = new ModelAndView("invalid_link_reset_password");
			}
			return view;
	}	
	// Submit New  Password
		@RequestMapping(value = "/submit_new_password", method = RequestMethod.POST)
		@ResponseBody public Boolean submit_new_password(@RequestBody UserModel user) {
				Custom_Dao cust=new Custom_Imp();
				return cust.submit_new_password(user);
		}

	@RequestMapping(value="/today", method=RequestMethod.GET)
	public @ResponseBody String today() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		f.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
	    return f.format(new Date());
	}
	//=========================User Information================================
	@RequestMapping(value="/user_info", method=RequestMethod.GET)
		public @ResponseBody Map<String,Object> user_info() {
		Map<String, Object> map = customer.user_info();
		System.out.println("Map User_Info:");
		System.out.println(map);
		return map;
	}

	//=========================Location Information================================
		@RequestMapping(value="/location_data", method=RequestMethod.GET)
			public @ResponseBody Map<String, Object> location1() {
			Map<String,Object> map = customer.location();
			return map;
		}
	//=========================Departure Time Information================================
		@RequestMapping(value="/departure_time_info", method=RequestMethod.GET)
			public @ResponseBody List<Map<String,Object>> departure_time_info() {
			List<Map<String, Object>> list = customer.departure_time_info();
			return list;
		}		
	//=========================Check Location Information================================
	@RequestMapping(value="/check_location", method=RequestMethod.GET)
		public @ResponseBody Map<String, Map<String, List<Pickup_Location_Master>>> check_location1(int id) {
		Map<String, Map<String, List<Pickup_Location_Master>>> map = customer.check_location(id);
		return map;
	}	
	//========================= Create customer pickup location ================================
	@RequestMapping(value="/create_custom_pickup_location", method=RequestMethod.GET)
	public @ResponseBody Map<String, Map<String, List<Pickup_Location_Master>>> create_custom_pickup_location(New_Pickup_Location np) {
			System.out.println(np.getLocation_id());
			Map<String, Map<String, List<Pickup_Location_Master>>> ret = customer.create_custom_pickup_location(np);
			return ret;
	}
	//========================= Create customer drop-off location ================================
	@RequestMapping(value="/create_custom_dropoff_location", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> create_custom_dropoff_location(New_Pickup_Location np) {
			System.out.println(np.getLocation_id());
			Map<String, Object> ret = customer.create_custom_dropoff_location(np);
			return ret;
	}
	//=========================Customer Booking Information================================

	@RequestMapping(value="/customer_booking", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> customer_booking(@RequestBody Customer_Booking[] cb) throws ParseException {
		    // Before Payment
//			for(int i=0;i<cb.length;i++){
//				cb[i].setStatus("book");
//			}
//			String ret = customer.customer_booking(cb);
		Map<String,Object> map = new HashMap<String,Object>();
		Customer_Schedule_Generation_Dao user=new Customer_Schedule_Generation_Imp();
		String ret = user.booking(cb);
		map.put("transaction_id",ret);

		return map;

		}	
	
	//=========================confirm_phone_number================================
	@RequestMapping(value="/confirm_phone_number", method=RequestMethod.GET)
	public @ResponseBody String confirm_phone_number(UserModel cb) {
		String ret = customer.confirm_phone_number(cb);
		System.out.println("TTTTTTTTTTTTT "+cb.getPhone());
		return ret;
	}
	//=========================Request Book Now================================
	@RequestMapping(value="/request_book_now", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> request_book_now(@RequestBody ID_Class id_class) throws ParseException {
		//Request_Booking_Dao req=new Request_Booking();
		//String ret = req.request_book_now(id_class.getId());
		//System.out.println(id_class.getId());
		Request_Booking_Dao user =new Request_Booking();
		String ret = user.booking(id_class);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("transaction_id", ret);
		return map;
	}
	//=========================Customer Request Booking Information================================
	@RequestMapping(value="/customer_request_booking", method=RequestMethod.POST)
	public @ResponseBody String customer_request_booking(@RequestBody Customer_Booking cb) {
			String ret = customer.customer_request_booking(cb);
			return ret;
	}
	//=========================Customer Cancel Request Booking Information================================
	@RequestMapping(value="/cancel_request_booking", method=RequestMethod.POST)
	public @ResponseBody String cancel_request_booking(@RequestBody ID_Class id_class) {
		String ret = customer.cancel_request_booking(id_class.getId());
			return ret;
	}
	//=========================To get Booking History of User================================
	@RequestMapping(value="/booking_history")
	public ModelAndView booking_history() {
		return new ModelAndView("booking_history");
	}
	//=========================To Request Booking================================
	@RequestMapping(value="/request_booking")
	public ModelAndView request_booking() {
		return new ModelAndView("request_booking");
	}
	//=========================Customer Booking History Information================================
	@RequestMapping(value="/customer_booking_history", method=RequestMethod.GET)
		public @ResponseBody List<Map<String,Object>> customer_booking_history() {
		List<Map<String, Object>> map = customer.cusomer_booking_history();
		return map;
	}	
	//======================== Get Booking Request Information================================
		@RequestMapping(value="/get_request_booking", method=RequestMethod.GET)
		public @ResponseBody List<Map<String,Object>> get_request_booking() {
		List<Map<String, Object>> map = customer.get_request_booking();
        System.out.println(map);
		return map;
	}	
	//======================== Get Booking Request Information base id================================
	@RequestMapping(value="/get_request_booking_id", method=RequestMethod.GET)
		public @ResponseBody List<Map<String,Object>> get_request_booking_id(int id) {
		List<Map<String, Object>> map = customer.get_request_booking_id(id);
		return map;
	}	
	//======================== Get Sch Bus Information base id================================
		@RequestMapping(value="/get_sch_bus_info", method=RequestMethod.GET)
			public @ResponseBody List<Map<String,Object>> get_sch_bus_info(int id) {
			List<Map<String, Object>> map = customer.get_sch_bus_info(id);
			return map;
		}	
		//======================== Get Sch Driver Information base id================================
		@RequestMapping(value="/get_sch_driver_info", method=RequestMethod.GET)
			public @ResponseBody List<Map<String,Object>> get_sch_driver_info(int id) {
			List<Map<String, Object>> map = customer.get_sch_driver_info(id);
			return map;
		}	
		//======================== Get Sch Driver Information base id================================
		@RequestMapping(value="/get_sch_driver_info2", method=RequestMethod.GET)
			public @ResponseBody List<Map<String,Object>> get_sch_driver_info2(int id) {
			List<Map<String, Object>> map = customer.get_sch_driver_info2(id);
			System.out.println("DDDDDDDDDD "+ id);
			return map;
		}
		//======================== Get Sch Driver Information base id================================
		@RequestMapping(value="/get_sch_bus_info2", method=RequestMethod.GET)
			public @ResponseBody List<Map<String,Object>> get_sch_bus_info2(int id) {
			List<Map<String, Object>> map = customer.get_sch_bus_info2(id);
			System.out.println("DDDDDDDDDD "+ id);
			return map;
		}		
	
	//=========================To Cancel Booking Ticket================================
	@RequestMapping(value="/cancel_booking_ticket", method=RequestMethod.POST)
		public @ResponseBody String cancel_booking_ticket(@RequestBody ID_Class id_delete) {
		System.out.println("percentage"+id_delete.getPercentage());
		String ret = customer.cancel_booking_ticket(id_delete.getId(), id_delete.getPercentage());
		return ret;
	}

	@RequestMapping(value="/customer/**")
	public ModelAndView customer_mobile() {
		return new ModelAndView("customer_mobile");
	}



	//=========================To Cancel Booking Ticket================================
	@RequestMapping(value="/get_qrcode", method=RequestMethod.GET)
		public @ResponseBody List<Map<String, Object>> get_qrcode(int id) {
		List<Map<String, Object>> ret = customer.get_qrcode(id);
		return ret;
	}



	//=========================To update customer phone number================================
	@RequestMapping(value="/update_phone", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatePhone(@RequestBody UserModel userModel) {
		return customer.updatePhone(userModel);
	}

	@RequestMapping(value="/date_time")
	public String DateTime(){
		Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTimeString = sdf.format(d);
        System.out.println(currentDateTimeString);
        return currentDateTimeString;
	}
	@RequestMapping(value="/get_hash", method=RequestMethod.POST)
	@ResponseBody public Map<String,Object> getHash(@RequestBody Get_Hash get_hash){
		Map<String,Object> map = new HashMap<String, Object>();
		PayWayApiCheckout payWayApiCheckout = new PayWayApiCheckout();
		map.put("hash",payWayApiCheckout.getHash(get_hash.getTransaction_id(),get_hash.getAmount()));
		return map;
	}

	@RequestMapping(value="/pay_way")
	public ModelAndView paymentGetWay() {
		return new ModelAndView("payment");
	}

	@RequestMapping(value="/cost_master",method=RequestMethod.GET)
	@ResponseBody public Cost Cost_Master() {
		return customer.Cost_Master();
	}

	//=========================PayWay Push Back Notification================================
	@RequestMapping(value="/push_back_notification", method=RequestMethod.POST)
	public @ResponseBody String pushBackNotification(@RequestBody PushBackNotification pb) 
			throws ParseException{
		//String ret = customer.customer_request_booking(cb);
		System.out.println("-----> Push back.............");
		System.out.println("Tran Id"+pb.getTran_id());
		System.out.println("Tran Id"+pb.getStatus());
		String ret=null;
		Custom_Dao customer=new Custom_Imp();
		ret =customer.pushBackNotification(pb);
		return ret;
	}

	
	public static void main(String args[]) throws ParseException{
//		Custom_Dao cus=new Custom_Imp();
//		//cus.send_QRCODE();
//		System.out.println("End.....");
		
		//Push back notification
		PushBackNotification pb=new PushBackNotification();
		pb.setTran_id("vKl1ofh5p80440koji11");
		pb.setStatus("0");
		System.out.println("-----> Push back.............");
		System.out.println("Tran Id"+pb.getTran_id());
		System.out.println("Tran Id"+pb.getStatus());
		String ret=null;
		Custom_Dao customer=new Custom_Imp();
		ret =customer.pushBackNotification(pb);
	}
	
	
	
}
