package com.MainController;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.EntityClasses.*;
import com.HibernateUtil.HibernateUtil;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.ServiceClasses.usersService;

@Controller
public class DriverController {
	@Autowired
	usersService usersServiceD;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/checkValidity", method = RequestMethod.GET)
	@ResponseBody public Map<String,Object> checkValidity(String email,String password){
		User_Info user = usersServiceD.findByUserName(email);
		//System.out.println(email);
		//System.out.println(password);
		boolean validity = false;
		String role = "";
		Map<String,Object> map = new HashMap<String,Object>();
		if(user != null){
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
			if(passwordEncoder.matches(password, user.getPassword())){
				
				for(UserRole Role : user.getUserRole()){
					role = Role.getRole();
					if(role.equals("ROLE_DRIVER")){
						validity = true;  
				        map.put("list_schedules", getSchedule(user.getId()));
				        map.put("user_id", user.getId());
				        map.put("username", user.getUsername());
				        map.put("fullname", user.getName());
				        map.put("email", user.getEmail());
					}
				}
				
			}
			
		}
		map.put("validity", validity);
		map.put("role",role);
		
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody public Map<String,Object> checkValidity(String email){
		User_Info user = usersServiceD.findByUserName(email);
		//System.out.println(email);
		boolean validity = false;
		String role = "";
		Map<String,Object> map = new HashMap<String,Object>();
		if(user != null){
				for(UserRole Role : user.getUserRole()){
					role = Role.getRole();
					if(role.equals("ROLE_DRIVER")){
						validity = true;  
				        map.put("list_schedules", getSchedule(user.getId()));
				        map.put("user_id", user.getId());
				        map.put("username", user.getUsername());
				        map.put("fullname", user.getName());
				        map.put("email", user.getEmail());
					}
				}
			
		}
		map.put("validity", validity);
		map.put("role",role);
		
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSchedule(int userId){
		List <Schedule_Master> schedules  = new ArrayList<Schedule_Master>();
		List<Map<String,Object>> list_schedules = new ArrayList<Map<String,Object>>();
		Transaction trns = null; 
        Session session = HibernateUtil.getSessionFactory().openSession(); 
        try { 
            trns =  session.beginTransaction(); 
            schedules = session.createQuery("from Schedule_Master where driver_id=? and dept_date>=? order by dept_date asc").setParameter(0, userId).setDate(1, new Date()).list();
            //System.out.println("Schedules: "+schedules);
            
            for(int i=0;i<schedules.size();i++){
            	Map<String, Object> map1 = new HashMap<String, Object>();
            	Location_Master des_from=new Location_Master();
            	des_from = (Location_Master) session.createQuery("from Location_Master where id=?").setParameter(0, schedules.get(i).getSource_id()).list().get(0);
            	Location_Master des_to=new Location_Master();
            	des_to = (Location_Master) session.createQuery("from Location_Master where id=?").setParameter(0, schedules.get(i).getDestination_id()).list().get(0);
            	
            	map1.put("id", schedules.get(i).getId());
            	map1.put("dep_date", DateFormat(schedules.get(i).getDept_date()));
            	map1.put("dep_time", schedules.get(i).getDept_time());
            	map1.put("arr_time", schedules.get(i).getArrival_time());
            	map1.put("des_from", des_from.getName());
            	map1.put("des_to", des_to.getName());
//            	map1.put("no_of_booking", schedules.get(i).getNumber_booking());
//            	map1.put("remaining_seats", schedules.get(i).getRemaining_seat());
            	map1.put("customer", schedules.get(i).getNumber_customer());
            	map1.put("student", schedules.get(i).getNumber_student());
            	map1.put("staff",schedules.get(i).getNumber_staff());
            	map1.put("bus_id",schedules.get(i).getBus_id());
            	map1.put("driver_id",schedules.get(i).getDriver_id());
            	
            	List<Booking_Master> pass = new ArrayList<Booking_Master>();
            	pass = (List<Booking_Master>) session.createQuery("from Booking_Master where schedule_id=? and notification='Booked'")
                		.setParameter(0,schedules.get(i).getId()).list();
   
            	List<Map<String,Object>> list_pass = new ArrayList<Map<String,Object>>();
            	for(int j=0;j<pass.size();j++){
            		User_Info passenger=new User_Info();
	            	passenger = (User_Info) session.createQuery("from User_Info where id=?").setParameter(0, pass.get(j).getUser_id()).list().get(0);
	            	int idd = pass.get(j).getUser_id();
	            	UserRole Role = (UserRole) session.load(UserRole.class,idd);

	            	//UserRole Role = (UserRole) passenger.getUserRole();
	            	
            		Map<String, Object> map_pass = new HashMap<String, Object>();
            		map_pass.put("id", pass.get(j).getId());
            		map_pass.put("role", Role.getRole().toString());
            		map_pass.put("user_id", pass.get(j).getUser_id());
            		map_pass.put("user_name", passenger.getUsername());
            		map_pass.put("full_name", passenger.getName());
            		map_pass.put("no_of_booking", pass.get(j).getNumber_booking());
            		map_pass.put("phone", passenger.getPhone_number());
            		map_pass.put("email", passenger.getEmail());
            		map_pass.put("qrcode", pass.get(j).getQr());
            		map_pass.put("qr_status", pass.get(j).getQr_status());
            		//System.out.println("Qr_Status: "+pass.get(j).getQr_status());
            		list_pass.add(map_pass);
            	}
            	
            	map1.put("passenger_list", list_pass);
            	list_schedules.add(map1);
            }

        } catch (RuntimeException e) { 
            e.printStackTrace(); 
         
        } finally { 
            session.flush(); 
            session.close(); 
        } 
        
		return list_schedules;
		
	}
	
	public String DateFormat(Date date){
//		Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-dd");
        String currentDateTimeString = sdf.format(date);
        //System.out.println("DateFormat: "+ currentDateTimeString);
        return currentDateTimeString;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updatePassenger", method = RequestMethod.GET)
	@ResponseBody public Map<String, Object> updatePassenger(@RequestHeader(value="data") Map<String,Object> obj, String email) throws JsonParseException, JsonMappingException, IOException{
		//System.out.println("UPDATE DATA: "+obj.get("data"));
		String ids = (String) obj.get("data");
		
		ObjectMapper mapper = new ObjectMapper();
		List<Integer> integers = mapper.readValue(ids, List.class);
		Map<String,Object> scheduleReturn = new HashMap<String,Object>();
		scheduleReturn = checkValidity(email);
		//System.out.println("Email: "+email);
		//System.out.println("Validity: "+scheduleReturn.get("validity").equals(true));
		if ( scheduleReturn.get("validity").equals(true) ){
			if(checkUpdate(integers)){
				scheduleReturn.put("update", "success");
			}else{
				scheduleReturn.put("update", "fail");
			}
		}
		
		return scheduleReturn;
	}
	
	public Boolean checkUpdate(List<Integer> integers){
		Boolean status = null;
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            System.out.println("Lengthhhhhhhhh: "+integers.size());
            System.out.println("Integers: "+integers);
            if(integers.isEmpty()){
            	  System.out.println("No Passengers to update!!!");
            }else{
            	for(Integer i:integers)
                {
                	String queryString = "FROM Booking_Master where id=:id";
                    System.out.println("Update Booking ############# "+i);
                    Query query = session.createQuery(queryString);
                    query.setInteger("id",i);
                    Booking_Master updatedPassenger  = (Booking_Master) query.uniqueResult();
                    Timestamp updated_at = new Timestamp(System.currentTimeMillis());
                    updatedPassenger.setQr_status(true);; // set status
//                	updatedPassenger.setUpdated_at(updated_at);
                    session.update(updatedPassenger);
                    //System.out.println("Update Passengers is committed. ");
                    
                }
                session.getTransaction().commit();
            }
            status = true;  
            
        } catch (RuntimeException e) {
        	if (trns != null) {
                trns.rollback();
            }
        	status = false;
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
		return status;   
	}

}
