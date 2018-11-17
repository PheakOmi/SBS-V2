package com.DaoClasses;

import getInfoLogin.IdUser;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.EntityClasses.Booking_Master;
import com.EntityClasses.Bus_Master;
import com.EntityClasses.Location_Master;
import com.EntityClasses.Schedule_Master;
import com.EntityClasses.User_Info;
import com.HibernateUtil.HibernateUtil;
import com.ModelClasses.Customer_Booking;

public class Set_Student_Schedule implements Set_Student_Schedule_Dao{
	IdUser user=new IdUser();

	public String DateNow(){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		f.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
		System.out.println(f.format(new Date()));
        return f.format(new Date());
	}
	public String DateTimeNow(){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		f.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
		System.out.println(f.format(new Date()));
		return f.format(new Date());
	}
	public String TomorrowDateTime() throws ParseException{
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		f.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
		System.out.println(f.format(new Date()));
		Date dt = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").parse(f.format(new Date()));
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		String tmrDateTimeString = sdf.format(dt);
		System.out.println(tmrDateTimeString);
		return tmrDateTimeString;
	}
	public static String getScheduleSequence(int id){ 
		   int code;
		   String scode = new String();
		   code = 10000000+id; 
		   scode = Integer.toString(code); 
		   scode = scode.substring(1); 
		   return "S"+scode; 
		  
	}
	public static String getBookingSequence(int id){ 
		   int code;
		   String scode = new String();
		   code = 10000000+id; 
		   scode = Integer.toString(code); 
		   scode = scode.substring(1); 
		   return "B"+scode; 
		  
	}
	
	//======================== combination for choosing bus till ============================
	List<List<Map<String,Object>>> list =new ArrayList<List<Map<String,Object>>>();
	List<Integer> total_choosen_bus_list=new ArrayList<Integer>();
	int number_of_bus=0;		// temporary use (value will always change)
	int total_bus=0;			// permanent use (value will never change)
	List<List<Map<String,Object>>> list_bus_choosen =new ArrayList<List<Map<String,Object>>>();	
		
	public void createSchedule() throws ParseException{
		System.out.println("---------------->");
		System.out.println("  createSchedule() ");
		Set_Student_Schedule_Dao stu=new Set_Student_Schedule();
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession(); 
        try {
        	trns = session.beginTransaction();
        	Set_Student_Schedule s=new Set_Student_Schedule();
    		String tmr_dt=s.TomorrowDateTime();
    		System.out.println("tmr date: "+tmr_dt);
    		String tmr_date=tmr_dt.split(" ")[0];
    		String tmr_time=tmr_dt.split(" ")[1];
//        	>  Query time
    		List<Map<String,Object>> list_time=stu.getAvailableTime(tmr_dt, session);
//        	>  Query from, Query to -----> permutation of from and to --> list of round
    		System.out.println("list_time: ");
    		System.out.println(list_time);
    		List<int[]> list_round= stu.getListRound(session);  
    		System.out.println("list_round: " );
    		System.out.println(list_round);
//        	-----> Loop time and round ---> Assign with schedule function base on list>0
    		for(int i=0;i<list_time.size();i++){
    			for(int j=0; j<list_round.size();j++){
    				List<Booking_Master> booked=session.createQuery("from Booking_Master where description='student' " +
                            "and dept_date=:date and dept_time=:time and to_id=:to and from_id=:from and notification!='Cancelled'")
    						.setDate("date",java.sql.Date.valueOf(list_time.get(i).get("date").toString()))
    						.setTime("time", java.sql.Time.valueOf(list_time.get(i).get("time").toString()))
    						.setParameter("to", list_round.get(j)[1])
    						.setParameter("from", list_round.get(j)[0]).list();
    				System.out.println("date: "+list_time.get(i).get("date").toString());
    				System.out.println("time: "+list_time.get(i).get("time").toString());
    				System.out.println("from: "+list_round.get(j)[0]);
    				System.out.println("to: "+list_round.get(j)[1]);
    				System.out.println("booked.size: "+booked.size());
    				System.out.println();
    				if(booked.size()>0){
    					Customer_Booking cb=new Customer_Booking();
        				cb.setDate(list_time.get(i).get("date").toString());
        				cb.setTime(list_time.get(i).get("time").toString());
        				cb.setSource(list_round.get(j)[0]);
        				cb.setDestination(list_round.get(j)[1]);
        				stu.student_schedule(session, cb); // to assign/reset schedule
    				}
    			}
    		}
        	trns.commit();
        } catch (RuntimeException e) {
        	e.printStackTrace();
            if (trns != null) {
                trns.rollback();
            }
        }finally {
            session.flush();
            session.close();
        } 
	}
	
	public List <Schedule_Master> get_schedules(Session session,Customer_Booking cb){ 
		System.out.println("---------------->");
    	System.out.println("---------------->get_schedules(");
	  	  List <Schedule_Master> schedules  = new ArrayList<Schedule_Master>(); 

	  	        try { 
	  	        	schedules=session.createQuery("from Schedule_Master where dept_date=:date and dept_time=:time and to_id=:to and from_id=:from")
							.setDate("date",java.sql.Date.valueOf(cb.getDate()))
							.setTime("time", java.sql.Time.valueOf(cb.getTime()))
							.setParameter("to", cb.getDestination())
							.setParameter("from", cb.getSource()).list();
	  	        } catch (RuntimeException e) { 
	  	            e.printStackTrace(); 
	  	            return schedules; 
	  	        } 
	  	        return schedules; 
	  	   
	  	 }
	public List<Map<String,Object>> getAvailableTime(String tmr_dt, Session session){
		System.out.println("---------------->");
    	System.out.println("---------------->getAvailableTime(");
		List<Map<String,Object>> list_time=new ArrayList<Map<String,Object>>();   
        try {
        	Set_Student_Schedule s=new Set_Student_Schedule();
    		String tmr_date=tmr_dt.split(" ")[0];
    		String tmr_time=tmr_dt.split(" ")[1];
    		List<Time> list_time1=new ArrayList<Time>();   
    		List<Time> list_time2=new ArrayList<Time>();   
    		list_time1 = session.createQuery("select dept_time from Booking_Master where dept_date=? "
        			+ "and description='student' and schedule_id='0' group by dept_date, "
        			+ "dept_time, from_id, to_id ").setDate(0, java.sql.Date.valueOf(s.DateNow())).list();
    		
        	list_time2 = session.createQuery("select dept_time from Booking_Master where dept_date=? "
        			+ "and description='student' and schedule_id='0' group by dept_date, "
        			+ "dept_time, from_id, to_id ").setDate(0, java.sql.Date.valueOf(tmr_date)).list();
            for(int i=0;i<list_time1.size();i++){
            	Time time=list_time1.get(i);
            	if(time.after(java.sql.Time.valueOf(tmr_time))){
            		Map<String, Object> map=new HashMap<String, Object>();
            		map.put("time", time);
            		map.put("date", s.DateNow());
            		list_time.add(map);
            	}
            }
            for(int i=0;i<list_time2.size();i++){
            	Time time=list_time2.get(i);
            	if(time.before(java.sql.Time.valueOf(tmr_time))){
            		Map<String, Object> map=new HashMap<String, Object>();
            		map.put("time", time);
            		map.put("date", tmr_date);
            		list_time.add(map);
            	}
            }
            
        } catch (RuntimeException e) {
        	e.printStackTrace();
        }  
		return list_time;
		
	}
	public List<int[]> getListRound(Session session){
		System.out.println("---------------->");
    	System.out.println("---------------->getListRound(");
		List<int[]> list_round=new ArrayList<int[]>();  
        try {
        	List<Location_Master> locat = session.createQuery("from Location_Master where enabled=?").setBoolean(0, true).list();
        	for(int i=0;i<locat.size();i++){
        		for(int j=0;j<locat.size();j++){
        			if(locat.get(i).getId()!=locat.get(j).getId()){
        				int[] li={locat.get(i).getId(),locat.get(j).getId()};
        				list_round.add(li);
        			}
        		}
        	}
            
        } catch (RuntimeException e) {
        	e.printStackTrace();
        }  
		return list_round;
		
	}
	public void student_schedule(Session session,Customer_Booking cb) throws ParseException{	
		System.out.println("---------------->");
    	System.out.println("---------------->student_schedule(");
		Set_Student_Schedule_Dao stu=new Set_Student_Schedule();
		Set_Student_Schedule booking=new Set_Student_Schedule();
        booking.list=new ArrayList<List<Map<String,Object>>>();
        booking.total_choosen_bus_list=new ArrayList<Integer>();
        booking.list_bus_choosen=new ArrayList<List<Map<String,Object>>>();
        booking.number_of_bus=0;
        booking.total_bus=0; 
        List<Schedule_Master> schedule=new ArrayList<Schedule_Master>();
        List<Booking_Master> list_passenger=new ArrayList<Booking_Master>();  //mix
        List<Booking_Master> all_booker1=new ArrayList<Booking_Master>(); //customer then student
        List<Booking_Master> list_stu=new ArrayList<Booking_Master>();
        List<Booking_Master> list_customer=new ArrayList<Booking_Master>();
        int total_seat_of_all_bus=0;
        int passenger_seat=0;
	    List<Map<String,Object>> all_bus =new ArrayList<Map<String,Object>>();  
	    Set_Student_Schedule c=new Set_Student_Schedule();
	        
			try {
				
				//Check Student Schedule if it already create no need to create more
				Boolean check_sch=c.check_stu_scheule(session, cb.getSource(), cb.getDestination()
						, cb.getTime(), cb.getDate());
				if(check_sch){
					list_passenger=stu.get_all_booker(session, cb.getSource(), cb.getDestination()
							, cb.getTime(), cb.getDate());
		          	for(int i=0;i<list_passenger.size();i++){
		          		passenger_seat+=list_passenger.get(i).getNumber_booking();
		          		if(list_passenger.get(i).getDescription().equals("customer")){
		          			list_customer.add(list_passenger.get(i));
		          			all_booker1.add(list_passenger.get(i));
		          		}
		          		else{
		          			list_stu.add(list_passenger.get(i));
		          		}
		          	}
		          	for(int i=0;i<list_stu.size();i++){
		          		all_booker1.add(list_stu.get(i));
		          	}
					
		          	schedule=stu.get_schedules(session, cb);
		          	int existing_bus_seat=0;
		          	for(int i=0;i<schedule.size();i++){
//		          		existing_bus_seat+=schedule.get(i).getNumber_booking()+schedule.get(i).getRemaining_seat();
		          	}
		          	
		          	//True----> Assign to existing bus/schedule
		          	if(passenger_seat<=existing_bus_seat){
		          		//Assign student with existing schedule
		          		stu.asign_to_existing_schedule(session, list_stu, schedule);
		          	}//Else re-create schedule
		          	else{
	              		//query list of all available bus (result must be in order number of seat for small to big )
	                    all_bus= stu.get_all_bus(session,cb,cb.getSource(),cb.getDestination());
	        	        
	        	        //if have bus or have no bus
	                    if(all_bus.size()>0){
	                    	//5. Fins total seat of all bus
	                    	for(int i=0;i<all_bus.size();i++){
	           	             	total_seat_of_all_bus+=Integer.valueOf((String) all_bus.get(i).get("number_of_seat")); 
	                    	}
	                    	//6. create new schedule
	                    	Map<Object, List<Booking_Master>> sch_with_users=stu.create_schedule(booking,session,all_bus,all_booker1,cb,total_seat_of_all_bus,passenger_seat); 			// 6. Set/Reset New Schedule 
	                    	
	                    	if(sch_with_users.size()==0){
	                    		System.out.println("-----> sch_with_users.size()==0");
	                    		stu.create_unassigned_booking(session, list_stu);
	                    		//return "over_bus_available";
	                    	}else{
	                    		//Get List Existing Driver Assign match with Bus
	                    		List<Integer> existing_bus_driver= stu.get_existing_bus_and_driver(booking,session,cb);
	                    		//7.Delete Schedule //Cannot delete it show error: a different object with the same identifier value was already associated with the session
	                        	//int delete=delete_Schedule(session,cb.getSource(),cb.getDestination(),cb.getTime(), cb.getDate());	// 5. Delete old Schedule 
	                    		//Delete after new schedule create
	                        	List<Integer> list_assign=new ArrayList<Integer>();
	                        	System.out.println(booking.list_bus_choosen);
	                        	List<Integer> except_sch_id=new ArrayList<Integer>();
	                        	session.clear();// Must be clear before create new schedule_master record
	                        	for(int h=0;h<sch_with_users.size();h++){
	                        		int num_booking=0;
	                        		int num_customer=0;
	                        		int number_stu=0;
	                        		Schedule_Master sch=new Schedule_Master();
	                        		sch.setBus_id(Integer.valueOf((String) booking.list_bus_choosen.get(0).get(h).get("id")));
	                        		sch.setDriver_id(existing_bus_driver.get(h));
	                        		sch.setSource_id(cb.getSource());
	                        		sch.setDestination_id(cb.getDestination());
	                        		sch.setFrom_id(cb.getSource());
	                        		sch.setTo_id(cb.getDestination());
	                        		sch.setDept_date(java.sql.Date.valueOf(cb.getDate()));
	                        		sch.setDept_time(java.sql.Time.valueOf(cb.getTime()));
	                        		sch.setCreated_at(java.sql.Timestamp.valueOf(c.DateTimeNow()));
	                        		sch.setUpdated_at(java.sql.Timestamp.valueOf(c.DateTimeNow()));
	                        		
	                        		session.save(sch);
	                        		for(int y=0;y<sch_with_users.get(h).size();y++){
	                        			list_assign.add(sch_with_users.get(h).get(y).getId());
	                        			num_booking+=sch_with_users.get(h).get(y).getNumber_booking();
	                        			if(sch_with_users.get(h).get(y).getDescription().equals("customer")){
											num_customer+=sch_with_users.get(h).get(y).getNumber_booking();
										}else       //student
											{
											number_stu+=sch_with_users.get(h).get(y).getNumber_booking();
										}
	                        			Query query = session.createQuery("update Booking_Master set notification='Booked',schedule_id = :sch_id, qr= :qr" +
	                            				" where id = :id");
	    			                    query.setParameter("sch_id", sch.getId());
	    			                    query.setParameter("qr", cb.getSource()+""+cb.getDestination()+""+cb.getDate()+""+cb.getTime()+""+sch_with_users.get(h).get(y).getId());
	    			                    query.setParameter("id", sch_with_users.get(h).get(y).getId());
	    			                    int result = query.executeUpdate();
	                    			}
	                        		
//	                        		sch.setNumber_booking(num_booking);
//	                        		sch.setRemaining_seat(Integer.valueOf((String) booking.list_bus_choosen.get(0).get(h).get("number_of_seat"))-num_booking);
	                        		sch.setNumber_customer(num_customer);
	                        		sch.setNumber_staff(0);
	                        		sch.setNumber_student(number_stu);
	                        		sch.setCode(Set_Student_Schedule.getScheduleSequence(sch.getId()));
	                        		except_sch_id.add(sch.getId());
	                    		}
	                        	
	                        	//check whether have remaining student not yet assign or not
	                        	List<Booking_Master> unassigned=new ArrayList<Booking_Master>();
	                        	if(list_assign.size()!=all_booker1.size()){
	                        		for (Booking_Master user: all_booker1) 
	                        		{ 
	                        			Boolean status=true;
	                        			for (int id: list_assign) 
	                            		{ 
	                        				if(id==user.getId()){
	                        					status=false;
	                        				}
	                            		}
	                        			if(status){
	                        				unassigned.add(user);
	                        			}
	                        		}
	                        	}
	                        	if(unassigned.size()>0){
	                        		System.out.println("------------> unassigned.size()>0");
	                        		stu.create_unassigned_booking(session,unassigned);
	                        	}
	                        	int delete=delete_Schedule(session,cb.getSource(),cb.getDestination(),cb.getTime(), cb.getDate(),except_sch_id);
	                    	}
	                    	
	                    }else{
	                    	System.out.println("--------------> No bus");
	        	      	    stu.create_unassigned_booking(session,list_stu);
	        	        }
	              	}
				}
        } catch (RuntimeException e) {
        	e.printStackTrace();
        }        
	}	
		
	public void asign_to_existing_schedule(Session session,List<Booking_Master> list_stu,List<Schedule_Master> list_schedule){
		System.out.println("---------------->");
    	System.out.println("---------------->asign_to_existing_schedule(");
    	System.out.println(list_stu);
		int index=0;
		try{
			List<Map<String,Object>> sch=new ArrayList<Map<String,Object>>();
			outerloop:
			for(int i=0;i<list_schedule.size();i++){
				int amount=0;
				Boolean check=false;   
//				for(int j=0;j<list_schedule.get(i).getRemaining_seat();j++){
//						if(index<list_stu.size()){
//							Query query = session.createQuery("update Booking_Master set notification='Booked',schedule_id = :sch_id, qr= :qr" +
//			        				" where id = :id");
//			                query.setParameter("sch_id", list_schedule.get(i).getId());
//			                query.setParameter("qr", list_stu.get(index).getFrom_id()+""+list_stu.get(index).getTo_id()+""+list_stu.get(index).getDept_date()+""+list_stu.get(index).getDept_time()+""+list_stu.get(index).getId());
//			                query.setParameter("id", list_stu.get(index).getId());
//			                int result = query.executeUpdate();
//	                        check=true;//check have update schedule or not
//	                        amount++;  //set amount to update with this schedule
//
//			                index++;//check loop
//						}
//						else{
//							if(check){
//								Map<String,Object> map=new HashMap<String,Object>();
//								map.put("scheule",list_schedule.get(i) );
//								map.put("amount", amount);
//								sch.add(map);
//							}
//							break outerloop;
//						}
//				}
				System.out.println("check: "+check);
				if(check){
					Map<String,Object> map=new HashMap<String,Object>();
					map.put("scheule",list_schedule.get(i) );
					map.put("amount", amount);
					sch.add(map);
				}
				
			}
			System.out.println("sch list: "+ sch.size());
			for(Map<String, Object> map: sch){
				Schedule_Master sm=(Schedule_Master) map.get("scheule");
				int num=Integer.valueOf(map.get("amount").toString());
				Query query1 = session.createQuery("update Schedule_Master set number_booking=:num_booking, " +
                        "remaining_seat=:remain_seat, number_student=:number_student" +
	    				" where id = :id");

//				System.out.println("1. :"+sm.getNumber_booking());
//                System.out.println("2. :"+sm.getRemaining_seat());
//                System.out.println("3. :"+num);
//	            query1.setParameter("num_booking",Integer.valueOf(sm.getNumber_booking())+num );
//	            query1.setParameter("remain_seat", Integer.valueOf(sm.getRemaining_seat())-num);
	            query1.setParameter("number_student", Integer.valueOf(sm.getNumber_student()+num));
	            query1.setParameter("id", sm.getId());
	            int result1 = query1.executeUpdate();
			}

		} catch (RuntimeException e) {
        	e.printStackTrace();
        }
	}

	public void create_unassigned_booking(Session session,List<Booking_Master> list_stu){
		System.out.println("---------------->");
    	System.out.println("---------------->create_unassigned_booking(");
    	System.out.println(list_stu.size());
			try{
				for(int j=0;j<list_stu.size();j++){

					Booking_Master booking = (Booking_Master) session.load(Booking_Master.class,list_stu.get(j).getId());
					booking.setNotification("Unassigned");
					session.update(booking);
					User_Info user_info = (User_Info) session.load(User_Info.class,booking.getUser_id());
					user_info.setNumber_ticket(user_info.getNumber_ticket()-1);
					session.update(user_info);
				}
					
			} catch (RuntimeException e) {
	        	e.printStackTrace();
	        }
		}
	public Map<Object, List<Booking_Master>> create_schedule(Set_Student_Schedule booking,Session session, List<Map<String,Object>> all_bus, List<Booking_Master> all_booker1, Customer_Booking cb, int total_seat_of_all_bus,int number_of_passenger){	
		System.out.println("---------------->");
		System.out.println("create_schedule");
		Boolean recursive=true;
		List<Booking_Master> user_sch_assign=new ArrayList<Booking_Master>();
        Map<Object,List<Booking_Master>> sch_with_users=new HashMap<Object,List<Booking_Master>>();
        Set_Student_Schedule_Dao custom_imp=new Set_Student_Schedule();  
        Set_Student_Schedule c=new Set_Student_Schedule();
		while(recursive){
			int ib=0; //index of passenger
			Boolean last_bus_choosing=true;
			booking.list_bus_choosen =new ArrayList<List<Map<String,Object>>>();
			user_sch_assign=new ArrayList<Booking_Master>();
			int total_seat_of_bus_chosen = number_of_passenger; //for recursive purpose to increase passenger in order to extend more bus
			int next_total_seat_of_bus_chosen = 0; 
			try {
	            
				//Final Bus Chosen the correct bus because people always accept even 1
	            if(number_of_passenger<=total_seat_of_all_bus){
	            	booking.list_bus_choosen=custom_imp.choose_correct_bus(booking,all_bus,cb,total_seat_of_bus_chosen,total_seat_of_all_bus);   //total_seat_of_bus_chosen==number_of_passenger
	            }else{
	            	System.out.println("----------------->>>>>>>>..");
	            	List<Map<String, Object>> add_list=new ArrayList<Map<String, Object>>();
	            	for(int i=0;i<all_bus.size();i++){	
		                  Map<String,Object> map =new HashMap<String,Object>();				
		                  map.put("bus_model", all_bus.get(i).get("bus_model"));
		                  map.put("number_of_seat", all_bus.get(i).get("number_of_seat"));
		                  map.put("id", all_bus.get(i).get("id"));
		                  add_list.add(map);
		             } 
	            	booking.list_bus_choosen.add(add_list);
	            	recursive=false;
	            	
	            }
	            
	            if(booking.list_bus_choosen.size()>0){
	            	List<Map<String, Object>> sort_bus =new ArrayList<Map<String, Object>>(booking.list_bus_choosen.get(0));
			        Collections.sort(sort_bus, new Comparator<Map<String,Object>> () {
				         public int compare(Map<String, Object> m1, Map<String, Object> m2) {
				             return (Integer.valueOf((String) m2.get("number_of_seat"))).compareTo(Integer.valueOf((String) m1.get("number_of_seat"))); //descending order
				         }
				     });
			        booking.list_bus_choosen=new ArrayList<List<Map<String,Object>>>();
			        booking.list_bus_choosen.add(sort_bus);
			        List<Integer> tem_pass_assign=new ArrayList<Integer>();
			        next_total_seat_of_bus_chosen=0;  //for recursive purpose
			        
		            for(int i=0;i<booking.list_bus_choosen.get(0).size();i++){
		            	System.out.println("Number of toal seat: "+booking.list_bus_choosen.get(0).get(i).get("number_of_seat"));
		            	List<Booking_Master> user_each_bus=new ArrayList<Booking_Master>();
		            	next_total_seat_of_bus_chosen+=Integer.valueOf((String) booking.list_bus_choosen.get(0).get(i).get("number_of_seat"));
		            	int total_pass_each_sch=0;
		        		for(int j=0;j<all_booker1.size()&&total_pass_each_sch<Integer.valueOf((String) booking.list_bus_choosen.get(0).get(i).get("number_of_seat"));j++){
		        			Boolean status_assign=true;
		        			//check user already assign or not yet(if already assign break loop;
		        			for(int k=0;k<user_sch_assign.size();k++){
		        				if(user_sch_assign.get(k).getId()==all_booker1.get(j).getId()){
		        					status_assign=false;
		        				}
		        			}
		        			if(status_assign){
		        				if(total_pass_each_sch+all_booker1.get(j).getNumber_booking()<Integer.valueOf((String) booking.list_bus_choosen.get(0).get(i).get("number_of_seat"))){
		        					user_sch_assign.add(all_booker1.get(j)); 
		        					user_each_bus.add(all_booker1.get(j));
		        					tem_pass_assign.add(j);
		        					total_pass_each_sch+=all_booker1.get(j).getNumber_booking();
		        				}else if(total_pass_each_sch+all_booker1.get(j).getNumber_booking()==Integer.valueOf((String) booking.list_bus_choosen.get(0).get(i).get("number_of_seat"))){
		        					user_sch_assign.add(all_booker1.get(j)); 
		        					tem_pass_assign.add(j);
		        					user_each_bus.add(all_booker1.get(j)); 
		        					total_pass_each_sch+=all_booker1.get(j).getNumber_booking();
		        					break; // Bus Already Full, No more assign
		        				}     				
		        			}
		        		}
		        		sch_with_users.put(i, user_each_bus);
		        	}

		           
		            if(tem_pass_assign.size()==all_booker1.size()){	
		            	break;
		    		}else if((tem_pass_assign.size()<all_booker1.size())&&last_bus_choosing){
		    			number_of_passenger=next_total_seat_of_bus_chosen+1;
		    			if(total_seat_of_all_bus==next_total_seat_of_bus_chosen){
		    				last_bus_choosing=false;
		    			}
		    		}
		    		else{
		    			//sch_with_users=new HashMap<Object,List<Booking_Master>>();
		    			System.out.println("-----------------**********");
		    			break;
		    		}
	            }else{
	            	System.out.println("Recursive!!!! 5");
	    			sch_with_users=new HashMap<Object,List<Booking_Master>>();
	    			break;
	            }
		        
	        } catch (RuntimeException e) {
	        	e.printStackTrace();
	        }  
		}
		  
		return sch_with_users;
	}
	public List<List<Map<String,Object>>> choose_correct_bus(Set_Student_Schedule booking,List<Map<String,Object>> all_bus,Customer_Booking cb, int number_of_passenger, int total_seat_of_all_bus){ 
		System.out.println("---------------->");
    	System.out.println("---------------->choose_correct_bus(");
		List<List<Map<String,Object>>> list_bus_choosen =new ArrayList<List<Map<String,Object>>>(); 
		booking.total_bus = all_bus.size();											
		booking.number_of_bus=all_bus.size();
  	    // Reset old data to empty
		booking.total_choosen_bus_list=new ArrayList<Integer>();
		booking.list =new ArrayList<List<Map<String,Object>>>();     
		try {	  
            
		         //=========3. Start The Process of choosing the correct total bus
		         for(int i=1;i<=booking.total_bus;i++){
		             printCombination(booking,all_bus, all_bus.size(), i,number_of_passenger, total_seat_of_all_bus);
		         }
		         //=========4. After choosing the correct total bus
		         Collections.sort(booking.total_choosen_bus_list);							
		         for(int i=0;i<booking.list.size();i++){
		            int sum_each_bus=0;
		            for(int j=0;j<booking.list.get(i).size();j++){
		                sum_each_bus+=Integer.valueOf((String) booking.list.get(i).get(j).get("number_of_seat"));
		            }
		            if(sum_each_bus==booking.total_choosen_bus_list.get(0)){ 
		               list_bus_choosen.add(booking.list.get(i));
		               break;
		            }
		         } 
	
        } catch (RuntimeException e) {
        	e.printStackTrace();
        } 
		System.out.println("List :      "+booking.list.size());
		return list_bus_choosen;
	}
	
	
	//If it was already created no need run regenerate schedule of student again
	public Boolean check_stu_scheule(Session session,int from_id, int to_id, String time, String date){  
		System.out.println("---------------->");
		System.out.println("check_stu_scheule");
		List<Booking_Master> all_booker1=new ArrayList<Booking_Master>();    
		try {
            all_booker1 = session.createQuery("from Booking_Master where notification!='Cancelled' and from_id=? and to_id=? " +
                    "and dept_time=? and dept_date=? and qr!=null and description='student'")
            		.setParameter(0,from_id).setParameter(1, to_id).
                            setTime(2, java.sql.Time.valueOf(time)).setDate(3,java.sql.Date.valueOf(date)).list();

            System.out.println("all_booker check schedule: ");
            System.out.println(all_booker1.size());
            if(all_booker1.size()>0){
            	return false;
            }else{
            	return true;
            }
        } catch (RuntimeException e) {
        	e.printStackTrace();
        }
		return false; 
	}
	
	public List<Booking_Master> get_all_booker(Session session,int from_id, int to_id, String time, String date){  
		System.out.println("---------------->");
		System.out.println("get_all_booker");
		List<Booking_Master> all_booker1=new ArrayList<Booking_Master>();    
		try {
            all_booker1 = session.createQuery("from Booking_Master where notification!='Cancelled' " +
					"and (payment='Succeed' or payment='Cash') and from_id=? and to_id=? " +
                    "and dept_time=? and dept_date=? order by number_booking desc, created_at ASC")
            		.setParameter(0,from_id).setParameter(1, to_id).
                            setTime(2, java.sql.Time.valueOf(time)).setDate(3,java.sql.Date.valueOf(date)).list();

            System.out.println("all_booker: ");
            System.out.println(all_booker1.size());
        } catch (RuntimeException e) {
        	e.printStackTrace();
        }    
		return all_booker1;
	}
	
	public List<Map<String,Object>> get_all_bus(Session session,Customer_Booking cb,int from, int to) throws ParseException{
		System.out.println("---------------->");
		System.out.println("get_all_bus");
		List<Bus_Master> query_all_bus=new ArrayList<Bus_Master>();
		List<Map<String,Object>> all_bus =new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> same_date_route =new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> same_date_diff_route =new ArrayList<Map<String,Object>>();
		Set_Student_Schedule_Dao custom_imp=new Set_Student_Schedule();
		try {
			same_date_route=custom_imp.same_date_same_route(session, cb, from, to);
			List<Integer> unava1= (List<Integer>) same_date_route.get(0).get("unavailable_bus");
			same_date_diff_route=custom_imp.same_date_differ_route(session, cb, from, to);
			List<Integer> unava2= (List<Integer>) same_date_diff_route.get(0).get("unavailable_bus");
			String excep="";
			for(int i=0;i<unava1.size();i++){
				excep+=" and id!="+unava1.get(i);
			}
			for(int i=0;i<unava2.size();i++){
				excep+=" and id!="+unava2.get(i);
			}
            query_all_bus = session.createQuery("from Bus_Master where availability='true' and enabled=?"+excep+
                    " order by number_of_seat asc").setBoolean(0, true).list();

            if(query_all_bus.size()>0){            
	              for(int i=0;i<query_all_bus.size();i++){	
	                  Map<String,Object> map =new HashMap<String,Object>();				
	                  map.put("bus_model", query_all_bus.get(i).getModel());
	                  map.put("number_of_seat", String.valueOf(query_all_bus.get(i).getNumber_of_seat()));
	                  map.put("id", String.valueOf(query_all_bus.get(i).getId()));
	                  all_bus.add(map);
	              } 
            }
	              
        } catch (RuntimeException e) {
        	e.printStackTrace();
        }    
		return all_bus;
	}
	//Check Bus Available and not from the same route 
	
	public List<Map<String,Object>> same_date_same_route(Session session,Customer_Booking cb,int from, int to) throws ParseException{
		List<Schedule_Master> sch=new ArrayList<Schedule_Master>();
		List<Map<String,Object>> all_bus =new ArrayList<Map<String,Object>>();
		List<Integer> ava_bus=new ArrayList<Integer>();
		List<Integer> una_bus=new ArrayList<Integer>();
		try {
			
			sch=session.createQuery("from Schedule_Master where dept_date=:date and dept_time!=:time and to_id=:to and from_id=:from")
					.setDate("date",java.sql.Date.valueOf(cb.getDate()))
					.setTime("time", java.sql.Time.valueOf(cb.getTime()))
					.setParameter("to", to)
					.setParameter("from", from).list();
			
			for(int i=0;i<sch.size();i++){
				if(time_same_date(sch.get(i).getDept_time().toString(),cb.getTime(),8)){
					ava_bus.add(sch.get(i).getBus_id());
				}else{
					una_bus.add(sch.get(i).getBus_id());
				}
			}
			Map<String, Object> map=new HashMap<String , Object>();
			map.put("unavailable_bus", una_bus);
			map.put("available_bus", ava_bus);
			all_bus.add(map);
			System.out.println("same_date_same_route:  ");
			System.out.println(all_bus);
	              
        } catch (RuntimeException e) {
        	e.printStackTrace();
        }    
		return all_bus;
	}
	//Check Bus Available and not from the same route 
	public List<Map<String,Object>> same_date_differ_route(Session session,Customer_Booking cb,int from, int to) throws ParseException{
			List<Schedule_Master> sch=new ArrayList<Schedule_Master>();
			List<Map<String,Object>> all_bus =new ArrayList<Map<String,Object>>();
			List<Integer> ava_bus=new ArrayList<Integer>();
			List<Integer> una_bus=new ArrayList<Integer>();
			try {	
				sch=session.createQuery("from Schedule_Master where dept_date=:date and from_id!=:from")
						.setDate("date",java.sql.Date.valueOf(cb.getDate()))
						.setParameter("from", from).list();
				
				for(int i=0;i<sch.size();i++){
					if(time_same_date(sch.get(i).getDept_time().toString(),cb.getTime(),4)){
						ava_bus.add(sch.get(i).getBus_id());
					}else{
						una_bus.add(sch.get(i).getBus_id());
					}
				}
				Map<String, Object> map=new HashMap<String , Object>();
				map.put("unavailable_bus", una_bus);
				map.put("available_bus", ava_bus);
				all_bus.add(map);
				System.out.println("same_date_differ_route:  ");
				System.out.println(all_bus);
		              
	        } catch (RuntimeException e) {
	        	e.printStackTrace();
	        }    
			return all_bus;
		}
	public Boolean time_same_date(String user_time, String time,long time_dura) throws ParseException{
 
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date date1 = format.parse(user_time);
		Date date2 = format.parse(time);
		long difference = date2.getTime() - date1.getTime();
		long duration =difference/(1000*60*60);
		System.out.println("PLPL");
		System.out.println("duration: "+duration);
		System.out.println("time_dura: "+time_dura);
		if(duration>=time_dura||duration<=-time_dura){
			return true;
		}else{
			return false;
		}
	}
	public int delete_Schedule(Session session, int from_id,int to_id,String time, String date, List<Integer> except_sch_id){
		System.out.println("---------------->");
		System.out.println("delete_Schedule");
		try {
			String excep="";
			for(int i=0;i<except_sch_id.size();i++){
				excep+=" and id!="+except_sch_id.get(i);
			}
            Query q = session.createQuery("delete Schedule_Master where from_id=? and to_id=? and dept_time=? and dept_date=?"+excep);
        	q.setParameter(0, from_id);
        	q.setParameter(1, to_id);
        	q.setTime(2, java.sql.Time.valueOf(time));
        	q.setDate(3, java.sql.Date.valueOf(date));
        	int result = q.executeUpdate();
        	return result;
        } catch (RuntimeException e) {
        	e.printStackTrace();
        	return 0;
        }    
	}
	
    static void combinationUtil(Set_Student_Schedule booking,List<Map<String,Object>> all_bus, List<Map<String,Object>> data, int start,
                                int end, int index, int r,int all_p,int all_bus_seat)
    {
    	System.out.println("---------------->");
    	System.out.println("---------------->combinationUtil(");
        if (index == r)
        {
        	List<Map<String,Object>> bus_choosen=new ArrayList<Map<String,Object>>();
            int total_current_bus_seat=0;
            for (int j=0; j<r; j++){
               /// total_current_bus_seat+=data[j];
               /// bus_choosen.add(data[j]);
            	System.out.print(data.get(j).get("number_of_seat")+" ");
            	total_current_bus_seat+=Integer.valueOf((String) data.get(j).get("number_of_seat"));
            	Map<String,Object> map=new HashMap<String,Object>();
            	map.put("bus_model", data.get(j).get("bus_model"));
                map.put("number_of_seat", data.get(j).get("number_of_seat"));
                map.put("id", data.get(j).get("id"));
            	bus_choosen.add(map);
            }
           System.out.println(" ");
           System.out.println(total_current_bus_seat);
           System.out.println(" ");
            if(total_current_bus_seat>=all_p){
            	if(bus_choosen.size()<booking.number_of_bus){
            		booking.number_of_bus=bus_choosen.size();
            		booking.list.add(bus_choosen);
            		booking.total_choosen_bus_list.add(total_current_bus_seat);
            	} else if(bus_choosen.size()==booking.total_bus){
            		booking.list.add(bus_choosen);
            		booking.total_choosen_bus_list.add(total_current_bus_seat);
            	}
            }else{
            	System.out.println("Over available bus");
            }
            return;
        }

        for (int i=start; i<=end && end-i+1 >= r-index; i++)
        {
            ///data[index] = Integer.valueOf(all_bus.get(i).get("Bus_Seat"));
        	
        	Map<String,Object> map=new HashMap<String,Object>();
//        	map.put("Bus_Seat", all_bus.get(i).get("Bus_Seat"));
//        	map.put("Bus_Model", all_bus.get(i).get("Bus_Model"));
        	 map.put("bus_model", all_bus.get(i).get("bus_model"));
             map.put("number_of_seat", all_bus.get(i).get("number_of_seat"));
             map.put("id", all_bus.get(i).get("id"));
        	data.add(index,map);
        	
            combinationUtil(booking,all_bus, data, i+1, end, index+1, r,all_p, all_bus_seat);
        }
    }

    static void printCombination(Set_Student_Schedule booking,List<Map<String,Object>> all_bus, int n, int r,int all_p,int all_bus_seat)
    {
    	System.out.println("---------------->");
    	System.out.println("---------------->printCombination(");
        // A temporary array to store all combination one by one
        ///int data[]=new int[r];
    	List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();

        // Print all combination using temprary array 'data[]'
        combinationUtil(booking,all_bus, data, 0, n-1, 0, r,all_p,all_bus_seat);
    }
  
    public List<Integer> get_existing_bus_and_driver(Set_Student_Schedule booking,Session session,Customer_Booking cb){
    	System.out.println("---------------->");
    	System.out.println("---------------->get_existing_bus_and_driver(");
    	List<Schedule_Master> sch=new ArrayList<Schedule_Master>();
    	List<Integer> final_driver_list=new ArrayList<Integer>();
		try {
			
			sch=session.createQuery("from Schedule_Master where dept_date=:date and dept_time=:time and to_id=:to and from_id=:from")
					.setDate("date",java.sql.Date.valueOf(cb.getDate()))
					.setTime("time", java.sql.Time.valueOf(cb.getTime()))
					.setParameter("to", cb.getSource())
					.setParameter("from", cb.getDestination()).list();
			
			// Find driver list and assign driver
			List<Integer> driver_list=new ArrayList<Integer>();
			List<Integer> assign_driver=new ArrayList<Integer>();
			for(int i=0; i<booking.list_bus_choosen.get(0).size();i++){
				Boolean status=true;
				for(int j=0;j<sch.size();j++){
					if(sch.get(j).getBus_id()==Integer.valueOf((String) booking.list_bus_choosen.get(0).get(i).get("id"))){
						driver_list.add(sch.get(j).getDriver_id());
						status=false;
						assign_driver.add(j);
						break;
					}
				}
				if(status){
					driver_list.add(0);
				}
			}
			
			// Find un-assign diver
			List<Integer> unassign_driver=new ArrayList<Integer>();
			for(int i=0;i<sch.size();i++){
				Boolean status=true;
				if(assign_driver.size()>0){
					for(int j=0;j<assign_driver.size();j++){
						if(i==assign_driver.get(j)){
							status=false;
							break;
						}
					}
					if(status){
						unassign_driver.add(sch.get(i).getDriver_id());
					}
				}else{
					//not assign at all
					unassign_driver.add(sch.get(i).getDriver_id());
				}
			}
			
			// Add unassign driver to driver list
			int un_assign=0;
			for(int i=0;i<driver_list.size();i++){
				if(driver_list.get(i)==0){
					if(un_assign<unassign_driver.size()){
						final_driver_list.add(unassign_driver.get(un_assign));
						un_assign++;
					}else{
						final_driver_list.add(0);
					}
				}else{
					final_driver_list.add(driver_list.get(i));
				}
			}
			
	              
        } catch (RuntimeException e) {
        	e.printStackTrace();
        }    
		return final_driver_list;
    }
    //======================== combination for choosing bus till here ============================
	
	public static void main(String args[]) throws ParseException{
		Set_Student_Schedule_Dao stu=new Set_Student_Schedule();
		stu.createSchedule();
	}




}
