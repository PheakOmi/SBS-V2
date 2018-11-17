package com.DaoClasses;

import com.EntityClasses.*;
import com.HibernateUtil.HibernateUtil;
import com.ModelClasses.Customer_Booking;
import com.ModelClasses.ID_Class;

import getInfoLogin.IdUser;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Request_Booking implements Request_Booking_Dao {
    IdUser user = new IdUser();
    //======================== combination for choosing bus till ============================
    List<List<Map<String, Object>>> list = new ArrayList<List<Map<String, Object>>>();
    List<Integer> total_choosen_bus_list = new ArrayList<Integer>();
    int number_of_bus = 0;        // temporary use (value will always change)
    int total_bus = 0;            // permanent use (value will never change)
    List<List<Map<String, Object>>> list_bus_choosen = new ArrayList<List<Map<String, Object>>>();

    public static String getScheduleSequence(int id) {
        int code;
        String scode = new String();
        code = 10000000 + id;
        scode = Integer.toString(code);
        scode = scode.substring(1);
        return "S" + scode;

    }

    public static String getBookingSequence(int id) {
        int code;
        String scode = new String();
        code = 10000000 + id;
        scode = Integer.toString(code);
        scode = scode.substring(1);
        return "B" + scode;

    }
    public String Key(int mount, int id) {
        SecureRandom random = new SecureRandom();
        System.out.println("id: " + (new BigInteger(mount * 5, random).toString(32)) + String.valueOf(id));
        return (new BigInteger(mount * 5, random).toString(32)) + String.valueOf(id);
    }

    static void combinationUtil(Request_Booking booking, List<Map<String, Object>> all_bus, List<Map<String, Object>> data, int start,
                                int end, int index, int r, int all_p, int all_bus_seat) {
        if (index == r) {
            List<Map<String, Object>> bus_choosen = new ArrayList<Map<String, Object>>();
            int total_current_bus_seat = 0;
            for (int j = 0; j < r; j++) {
                /// total_current_bus_seat+=data[j];
                /// bus_choosen.add(data[j]);
                System.out.print(data.get(j).get("number_of_seat") + " ");
                total_current_bus_seat += Integer.valueOf((String) data.get(j).get("number_of_seat"));
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("bus_model", data.get(j).get("bus_model"));
                map.put("number_of_seat", data.get(j).get("number_of_seat"));
                map.put("id", data.get(j).get("id"));
                bus_choosen.add(map);
            }
            System.out.println(" ");
            System.out.println(total_current_bus_seat);
            System.out.println(" ");
            if (total_current_bus_seat >= all_p) {
                if (bus_choosen.size() < booking.number_of_bus) {
                    booking.number_of_bus = bus_choosen.size();
                    booking.list.add(bus_choosen);
                    booking.total_choosen_bus_list.add(total_current_bus_seat);
                } else if (bus_choosen.size() == booking.total_bus) {
                    booking.list.add(bus_choosen);
                    booking.total_choosen_bus_list.add(total_current_bus_seat);
                }
            } else {
                System.out.println("Over available bus");
            }
            return;
        }

        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            ///data[index] = Integer.valueOf(all_bus.get(i).get("Bus_Seat"));

            Map<String, Object> map = new HashMap<String, Object>();
//        	map.put("Bus_Seat", all_bus.get(i).get("Bus_Seat"));
//        	map.put("Bus_Model", all_bus.get(i).get("Bus_Model"));
            map.put("bus_model", all_bus.get(i).get("bus_model"));
            map.put("number_of_seat", all_bus.get(i).get("number_of_seat"));
            map.put("id", all_bus.get(i).get("id"));
            data.add(index, map);

            combinationUtil(booking, all_bus, data, i + 1, end, index + 1, r, all_p, all_bus_seat);
        }
    }

    static void printCombination(Request_Booking booking, List<Map<String, Object>> all_bus, int n, int r, int all_p, int all_bus_seat) {
        // A temporary array to store all combination one by one
        ///int data[]=new int[r];
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

        // Print all combination using temprary array 'data[]'
        combinationUtil(booking, all_bus, data, 0, n - 1, 0, r, all_p, all_bus_seat);
    }

    public static void main(String args[]) {
        Transaction trns1 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Booking_Request_Master> bh = new ArrayList<Booking_Request_Master>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            trns1 = session.beginTransaction();
            bh = session.createQuery("from Booking_Request_Master where user_id=? and dept_date>=? and enabled='true' order by dept_date asc")
                    .setParameter(0, 16).setDate(1, new Date()).list();
            System.out.println("KK");
            System.out.println(new Date().getTime());
            System.out.println(bh.size());
            for (int i = 0; i < bh.size(); i++) {
                Pickup_Location_Master pick_source = new Pickup_Location_Master();
                pick_source = (Pickup_Location_Master) session.createQuery("from Pickup_Location_Master where id=?").setParameter(0, bh.get(i).getSource_id()).list().get(0);
                Pickup_Location_Master pick_destin = new Pickup_Location_Master();
                pick_destin = (Pickup_Location_Master) session.createQuery("from Pickup_Location_Master where id=?").setParameter(0, bh.get(i).getDestination_id()).list().get(0);

                Location_Master source = new Location_Master();
                source = (Location_Master) session.createQuery("from Location_Master where id=?").setParameter(0, bh.get(i).getFrom_id()).list().get(0);
                Location_Master destin = new Location_Master();
                destin = (Location_Master) session.createQuery("from Location_Master where id=?").setParameter(0, bh.get(i).getTo_id()).list().get(0);
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", String.valueOf(bh.get(i).getId()));
                map.put("dept_date", bh.get(i).getDept_date().toString());
                if (bh.get(i).getStatus().equals("Confirmed")) {
//                    map.put("dept_time", bh.get(i).getProvided_time());
                } else {
                    map.put("dept_time", bh.get(i).getDept_time().toString());
                }
                map.put("pick_source_id", String.valueOf(pick_source.getId()));
                map.put("pick_source_name", String.valueOf(pick_source.getName()));
                map.put("drop_dest_id", String.valueOf(pick_destin.getId()));
                map.put("drop_dest_name", String.valueOf(pick_destin.getName()));
                map.put("scource", source.getName());
                map.put("scource_id", String.valueOf(source.getId()));
                map.put("destination", destin.getName());
                map.put("destination_id", String.valueOf(destin.getId()));
//                map.put("number_of_ticket", String.valueOf(bh.get(i).getNumber_of_booking()));
//                map.put("provided_time", bh.get(i).getProvided_time());
                map.put("status", bh.get(i).getStatus());

                list.add(map);
            }
            System.out.println(list);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public String DateTimeNow() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
        f.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
        System.out.println(f.format(new Date()));
        return f.format(new Date());
    }

    public String transactionID(int mount, int id) {
        SecureRandom random = new SecureRandom();
        System.out.println("id: " + (new BigInteger(mount * 5, random).toString(32)) + String.valueOf(id));
        return "vK" + (new BigInteger(mount * 5, random).toString(32)) +"i"+ String.valueOf(id);
    }
    
    public static String dateToString(Date date){
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		return f.format(date);
	}

	public static String timeToString(Date date){
		SimpleDateFormat f=new SimpleDateFormat("HH:mm:ss");
		return f.format(date);
	}

    //======================== Store Booking (After Request have confirmed by admin) Record When user booked  =================
    public String booking(ID_Class id_class) throws ParseException {
    	
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trns1 = null;
        Booking_Request_Master book = new Booking_Request_Master();
        Booking_Master new_booker = new Booking_Master();
        Request_Booking c = new Request_Booking();
        String transactionID = null;
        try {
            trns1 = session.beginTransaction();
           
            Booking_Request_Master cb = (Booking_Request_Master) session.createQuery("from Booking_Request_Master where id=?")
                    .setParameter(0, id_class.getId()).list().get(0);


            Pickup_Location_Master pick_source = new Pickup_Location_Master();
            pick_source = (Pickup_Location_Master) session.createQuery("from Pickup_Location_Master where id=?")
                    .setParameter(0, cb.getSource_id()).list().get(0);
            Pickup_Location_Master pick_destin = new Pickup_Location_Master();
            pick_destin = (Pickup_Location_Master) session.createQuery("from Pickup_Location_Master where id=?")
                    .setParameter(0, cb.getDestination_id()).list().get(0);

//            int total_passenger=cb.getChild()+(cb.getAdult()*2);
            
            new_booker.setSource_id(pick_source.getId());
            new_booker.setFrom_id(pick_source.getLocation_id());
            new_booker.setDestination_id(pick_destin.getId());
            new_booker.setTo_id(pick_destin.getLocation_id());
            new_booker.setDept_time(cb.getDept_time());
            new_booker.setDept_date(cb.getDept_date());
            new_booker.setCreated_at(java.sql.Timestamp.valueOf(c.DateTimeNow()));
            new_booker.setUpdated_at(java.sql.Timestamp.valueOf(c.DateTimeNow()));
            new_booker.setUser_id(cb.getUser_id());
//            new_booker.setNumber_booking(total_passenger);
//            new_booker.setNotification("Booked");
//            new_booker.setSchedule_id(0);
//            new_booker.setAdult(cb.getAdult());
//            new_booker.setChild(cb.getChild());
//            new_booker.setTotal_cost(cb.getTotal_cost());
            new_booker.setDescription("customer");
            new_booker.setEmail_confirm(false);
            new_booker.setQr_status(false);
            new_booker.setBooking_request_id(id_class.getId());
            new_booker.setPayment("Pending"); // There are three type of payment status -> Pending, Succeed, Failed
            session.save(new_booker);
            

            transactionID = c.transactionID(20 - ("vki"+new_booker.getId()).length(), new_booker.getId());

            new_booker.setTransaction_id(transactionID);
            new_booker.setCode(c.getBookingSequence(new_booker.getId()));
            new_booker.setQr_name(c.Key(50, new_booker.getId()));
            trns1.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            if (trns1 != null) {
                trns1.rollback();
            }
            return null;
        } finally {
            session.flush();
            session.close();
        }
        
        System.out.println("Generate Schedule");
        // Generate Schedule for this booker
        Transaction trns2 = null;
        Session session2 = HibernateUtil.getSessionFactory().openSession();
        try{
        	trns2 = session2.beginTransaction();	
            if(id_class.getPayment().equals("cash")){

            	// Update Booking Status with new session
            	Booking_Master bm= (Booking_Master) session2.load(Booking_Master.class,new_booker.getId());
            	bm.setPayment("Cash");
				session2.update(bm);
				//If it is booking request --> update Booking Request Master
				Booking_Request_Master brm= (Booking_Request_Master) session2.load(Booking_Request_Master.class
						,id_class.getId());
//				brm.setEnabled(false);
				session2.update(brm);	
            	
            	System.out.println("Generate Schedule 3");
        		Customer_Booking cb=new Customer_Booking();
				cb.setDate(dateToString(new_booker.getDept_date()));
				cb.setTime(timeToString(new_booker.getDept_time()));
				cb.setSource(new_booker.getSource_id());
				cb.setDestination(new_booker.getDestination_id());
				cb.setNumber_of_seat(new_booker.getNumber_booking());
				cb.setAdult(new_booker.getAdult());
				cb.setChild(new_booker.getChild());
				cb.setStatus("Booked");
				cb.setTotal_cost(new_booker.getTotal_cost());
				cb.setBooking_master_id(new_booker.getId());
				
            	//generate or regenerate schedule
				System.out.println("=====> Booking Request");
				//Check whether student schedule is created ==> Booking Request
				Request_Booking_Dao qb=new Request_Booking();
				String ret1=qb.customer_booking(session2,cb);
				//Send Confirmation Email
				System.out.println("Return: "+ret1);
				if (ret1.equals("success") || ret1.equals("over_bus_available") || ret1.equals("no_bus_available")) {
					QR_Image_Gemerator.sendEmailQRCode(session2,new_booker);
				}
            }
            trns2.commit();
	    } catch (RuntimeException e) {
	        e.printStackTrace();
	        if (trns2 != null) {
	            trns2.rollback();
	        }
	        return null;
	    } finally {
	    	session2.flush();
	    	session2.close();
	    }
        
        
        return transactionID;
    }

    public String customer_booking(Session session,Customer_Booking cb) throws ParseException {
        System.out.println("customer_booking");
        try {
            //======================== Start loop create schedule ====================================
            int total_seat_of_all_bus = 0;
            int number_of_passenger = 0;
            List<Booking_Master> all_booker1 = new ArrayList<Booking_Master>();
            List<Map<String, Object>> all_bus = new ArrayList<Map<String, Object>>();
            List<Schedule_Master> schedule = new ArrayList<Schedule_Master>();
            Request_Booking_Dao custom_imp = new Request_Booking();
            Request_Booking c = new Request_Booking();
            Request_Booking booking = new Request_Booking();
            booking.list = new ArrayList<List<Map<String, Object>>>();
            booking.total_choosen_bus_list = new ArrayList<Integer>();
            booking.list_bus_choosen = new ArrayList<List<Map<String, Object>>>();
            booking.number_of_bus = 0;
            booking.total_bus = 0;

            Pickup_Location_Master pick_source = new Pickup_Location_Master();
            pick_source = (Pickup_Location_Master) session.createQuery("from Pickup_Location_Master where id=?").setParameter(0, cb.getSource()).list().get(0);
            Pickup_Location_Master pick_destin = new Pickup_Location_Master();
            pick_destin = (Pickup_Location_Master) session.createQuery("from Pickup_Location_Master where id=?").setParameter(0, cb.getDestination()).list().get(0);

            schedule = session.createQuery("from Schedule_Master where dept_date=:date and dept_time=:time and to_id=:to and from_id=:from")
                    .setDate("date", java.sql.Date.valueOf(cb.getDate()))
                    .setTime("time", java.sql.Time.valueOf(cb.getTime()))
                    .setParameter("to", pick_destin.getLocation_id())
                    .setParameter("from", pick_source.getLocation_id()).list();

            Boolean check_ass = true; // Check whether we can assign this passenger to existing schedule or not
//            for (int i = 0; i < schedule.size(); i++) {
//                if (schedule.get(i).getRemaining_seat() >= cb.getNumber_of_seat()) {
//                    //Assign New Passenger here
//                    Booking_Master new_booker = (Booking_Master) session.load(Booking_Master.class, cb.getBooking_master_id());
//                    new_booker.setUpdated_at(java.sql.Timestamp.valueOf(c.DateTimeNow()));
//                    new_booker.setSchedule_id(schedule.get(i).getId());
//                    new_booker.setQr(pick_source.getLocation_id() + "" + pick_destin.getLocation_id()
//                            + "" + cb.getDate() + "" + cb.getTime() + "" + cb.getBooking_master_id());
//                    session.update(new_booker);
//
//                    Query query = session.createQuery("update Schedule_Master set number_booking=:num_booking, remaining_seat=:remain_seat, number_customer=:number_customer" +
//                            " where id = :id");
////                    query.setParameter("num_booking", schedule.get(i).getNumber_booking() + cb.getNumber_of_seat());
////                    query.setParameter("remain_seat", schedule.get(i).getRemaining_seat() - cb.getNumber_of_seat());
//                    query.setParameter("number_customer", schedule.get(i).getNumber_customer() + cb.getNumber_of_seat());
//                    query.setParameter("id", schedule.get(i).getId());
//                    System.out.println(query);
//                    int result = query.executeUpdate();
//
//                    check_ass = false;
//                    break;
//                }
//            }

            if (check_ass) {
                //1. query list of all available bus (result must be in order number of seat for small to big )
                all_bus = custom_imp.get_all_bus(session, cb, pick_source.getLocation_id(), pick_destin.getLocation_id());
                //2. get all bookers
                all_booker1 = custom_imp.get_all_booker(session, pick_source.getLocation_id(), pick_destin.getLocation_id(), cb.getTime(), cb.getDate());
                //3. Find out number of total passengers in DB
                for (int p = 0; p < all_booker1.size(); p++) {
                    number_of_passenger += all_booker1.get(p).getNumber_booking();
                }
                number_of_passenger += cb.getNumber_of_seat();

                //4. if have bus or have no bus
                if (all_bus.size() > 0) {
                    //5. Fins total seat of all bus
                    for (int i = 0; i < all_bus.size(); i++) {
                        total_seat_of_all_bus += Integer.valueOf((String) all_bus.get(i).get("number_of_seat"));
                    }
                    //check whether people is over all available bus seat or not
                    if (number_of_passenger <= total_seat_of_all_bus) {
                        //6. create new schedule
                        Map<Object, List<Booking_Master>> sch_with_users = custom_imp.create_schedule(booking, session, all_bus, all_booker1, pick_source, pick_destin, cb, total_seat_of_all_bus, number_of_passenger);            // 6. Set/Reset New Schedule
                        if (sch_with_users.size() == 0) {
                            custom_imp.create_unassigned_booking(session, cb, pick_source, pick_destin);
                            //return "over_bus_available";
                        } else {
                            //Get List Existing Driver Assign match with Bus
                            List<Integer> existing_bus_driver = custom_imp.get_existing_bus_and_driver(booking, session, cb, pick_source.getLocation_id(), pick_destin.getLocation_id());
                            //7.Delete Schedule
                            int delete = delete_Schedule(session, pick_source.getLocation_id(), pick_destin.getLocation_id(), cb.getTime(), cb.getDate());    // 5. Delete old Schedule

                            System.out.println(booking.list_bus_choosen);
                            session.clear();// Must be clear before create new schedule_master record
                            for (int h = 0; h < sch_with_users.size(); h++) {
                                int num_booking = 0;
                                int num_customer = 0;
                                int number_stu = 0;
                                Schedule_Master sch = new Schedule_Master();
                                sch.setBus_id(Integer.valueOf((String) booking.list_bus_choosen.get(0).get(h).get("id")));
                                sch.setDriver_id(existing_bus_driver.get(h));
                                sch.setSource_id(pick_source.getId());
                                sch.setDestination_id(pick_destin.getId());
                                sch.setFrom_id(pick_source.getLocation_id());
                                sch.setTo_id(pick_destin.getLocation_id());
                                sch.setDept_date(java.sql.Date.valueOf(cb.getDate()));
                                sch.setDept_time(java.sql.Time.valueOf(cb.getTime()));
                                sch.setCreated_at(java.sql.Timestamp.valueOf(c.DateTimeNow()));
                                sch.setUpdated_at(java.sql.Timestamp.valueOf(c.DateTimeNow()));
                                
                                session.save(sch);
                                for (int y = 0; y < sch_with_users.get(h).size(); y++) {
                                    System.out.println("kkkkk1122");
                                    num_booking += sch_with_users.get(h).get(y).getNumber_booking();
                                    System.out.println();
                                    System.out.println(sch_with_users.get(h).get(y).getDescription());
                                    System.out.println();
                                    if (sch_with_users.get(h).get(y).getDescription().equals("customer")) {
                                        num_customer += sch_with_users.get(h).get(y).getNumber_booking();
                                    } else       //student
                                    {
                                        number_stu += sch_with_users.get(h).get(y).getNumber_booking();
                                    }
                                    Query query = session.createQuery("update Booking_Master set schedule_id = :sch_id, qr= :qr" +
                                            " where id = :id");
                                    query.setParameter("sch_id", sch.getId());
                                    query.setParameter("qr", pick_source.getLocation_id() + "" + pick_destin.getLocation_id() + "" + cb.getDate() + "" + cb.getTime() + "" + sch_with_users.get(h).get(y).getId());
                                    query.setParameter("id", sch_with_users.get(h).get(y).getId());
                                    int result = query.executeUpdate();
                                }

                                sch.setCode(Request_Booking.getScheduleSequence(sch.getId()));
//                                sch.setNumber_booking(num_booking);
//                                sch.setRemaining_seat(Integer.valueOf((String)
//                                        booking.list_bus_choosen.get(0).get(h).get("number_of_seat")) - num_booking);
                                sch.setNumber_customer(num_customer);
                                sch.setNumber_staff(0);
                                sch.setNumber_student(number_stu);
//                        			for(int y=0;y<sch_with_users.get(h).size();y++){
//                        				System.out.print(sch_with_users.get(h).get(y).getId()+" ");
//                        			}
//                        			System.out.println(" ");
//                        			for(int y=0;y<sch_with_users.get(h).size();y++){
//                        				System.out.print(sch_with_users.get(h).get(y).getNumber_booking()+" ");
//                        			}
//                                System.out.println(" ");
                            }
                        }
                    } else {
                        System.out.println("Over All Bus available seat 1!!!");
                        custom_imp.create_unassigned_booking(session, cb, pick_source, pick_destin);
                        //return "over_bus_available";

                    }

                } else {
                    //System.out.println("No Bus available!!!");
                    custom_imp.create_unassigned_booking(session, cb, pick_source, pick_destin);
                    //return "no_bus_available";
                }
            }
            //======================== End create schedule ====================================
        } catch (RuntimeException e) {
        	e.printStackTrace();
            return "error";
        } 
        return "success";
    }

    public void create_unassigned_booking(Session session, Customer_Booking cb, Pickup_Location_Master pick_source, Pickup_Location_Master pick_destin) {
        Customer_Schedule_Generation_Imp c = new Customer_Schedule_Generation_Imp();
        try {
            // Record Booking of customer but haven't assign schedule yet
            Booking_Master new_booker = (Booking_Master) session.load(Booking_Master.class, cb.getBooking_master_id());
            new_booker.setUpdated_at(java.sql.Timestamp.valueOf(c.DateTimeNow()));
            new_booker.setNotification("Unassigned");
            session.update(new_booker);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public Map<Object, List<Booking_Master>> create_schedule(Request_Booking booking, Session session, List<Map<String, Object>> all_bus, List<Booking_Master> all_booker1, Pickup_Location_Master pick_source, Pickup_Location_Master pick_destin, Customer_Booking cb, int total_seat_of_all_bus, int number_of_passenger) {
        System.out.println("create_schedule");
        Boolean recursive = true;
        List<Booking_Master> user_sch_assign = new ArrayList<Booking_Master>();
        Map<Object, List<Booking_Master>> sch_with_users = new HashMap<Object, List<Booking_Master>>();
        Request_Booking_Dao custom_imp = new Request_Booking();
        Request_Booking c = new Request_Booking();
//		java.sql.Timestamp.valueOf(c.DateTimeNow())
        while (recursive) {
            int ib = 0; //index of passenger
            Boolean last_bus_choosing = true;
            Boolean current_pass_assign = true;
            booking.list_bus_choosen = new ArrayList<List<Map<String, Object>>>();
            user_sch_assign = new ArrayList<Booking_Master>();
            int total_seat_of_bus_chosen = number_of_passenger; //for recursive purpose to increase passenger in order to extend more bus
            int next_total_seat_of_bus_chosen = 0;
            try {
                //Find out number of total passengers in DB again
                number_of_passenger = 0;
                for (int p = 0; p < all_booker1.size(); p++) {
                    number_of_passenger += all_booker1.get(p).getNumber_booking();
                }

                //Final Bus Chosen the correct bus because people always accept even 1
                booking.list_bus_choosen = custom_imp.choose_correct_bus(booking, all_bus, pick_source, pick_destin, total_seat_of_bus_chosen, total_seat_of_all_bus);   //total_seat_of_bus_chosen==number_of_passenger

                if (booking.list_bus_choosen.size() > 0) {
                    List<Map<String, Object>> sort_bus = new ArrayList<Map<String, Object>>(booking.list_bus_choosen.get(0));
                    Collections.sort(sort_bus, new Comparator<Map<String, Object>>() {
                        public int compare(Map<String, Object> m1, Map<String, Object> m2) {
                            return (Integer.valueOf((String) m2.get("number_of_seat"))).compareTo(Integer.valueOf((String) m1.get("number_of_seat"))); //descending order
                        }
                    });
                    booking.list_bus_choosen = new ArrayList<List<Map<String, Object>>>();
                    booking.list_bus_choosen.add(sort_bus);
                    List<Integer> tem_pass_assign = new ArrayList<Integer>();
                    next_total_seat_of_bus_chosen = 0;  //for recursive purpose

                    for (int i = 0; i < booking.list_bus_choosen.get(0).size(); i++) {
                        System.out.println("Number of toal seat: " + booking.list_bus_choosen.get(0).get(i).get("number_of_seat"));
                        List<Booking_Master> user_each_bus = new ArrayList<Booking_Master>();
                        next_total_seat_of_bus_chosen += Integer.valueOf((String) booking.list_bus_choosen.get(0).get(i).get("number_of_seat"));
                        int total_pass_each_sch = 0;
                        for (int j = 0; j < all_booker1.size() && total_pass_each_sch < Integer.valueOf((String) booking.list_bus_choosen.get(0).get(i).get("number_of_seat")); j++) {
                            Boolean status_assign = true;
                            //check user already assign or not yet(if already assign break loop;
                            for (int k = 0; k < user_sch_assign.size(); k++) {
                                if (user_sch_assign.get(k).getId() == all_booker1.get(j).getId()) {
                                    status_assign = false;
                                }
                            }
                            System.out.println(status_assign);
                            if (status_assign) {
                                if (total_pass_each_sch + all_booker1.get(j).getNumber_booking() < Integer.valueOf((String) booking.list_bus_choosen.get(0).get(i).get("number_of_seat"))) {
                                    user_sch_assign.add(all_booker1.get(j));
                                    user_each_bus.add(all_booker1.get(j));
                                    tem_pass_assign.add(j);
                                    total_pass_each_sch += all_booker1.get(j).getNumber_booking();
                                } else if (total_pass_each_sch + all_booker1.get(j).getNumber_booking() == Integer.valueOf((String) booking.list_bus_choosen.get(0).get(i).get("number_of_seat"))) {
                                    user_sch_assign.add(all_booker1.get(j));
                                    tem_pass_assign.add(j);
                                    user_each_bus.add(all_booker1.get(j));
                                    total_pass_each_sch += all_booker1.get(j).getNumber_booking();
                                    break; // Bus Already Full, No more assign
                                }
                            }
                        }

                        if (current_pass_assign && total_pass_each_sch + cb.getNumber_of_seat() <= Integer.valueOf((String) booking.list_bus_choosen.get(0).get(i).get("number_of_seat"))) {
                            //Assign New Passenger here
                            Booking_Master new_booker = (Booking_Master) session.load(Booking_Master.class, cb.getBooking_master_id());

                            new_booker.setCode(Request_Booking.getBookingSequence(new_booker.getId()));
                            current_pass_assign = false;
                            total_pass_each_sch += cb.getNumber_of_seat();
                            user_each_bus.add(new_booker);
                        }
                        System.out.println("total_pass_each_sch: " + total_pass_each_sch);
                        sch_with_users.put(i, user_each_bus);
                    }

                    //if(tem_pass_assign.size()<all_booker1.size()+cb.getNumber_of_seat()&&total_seat_of_bus_chosen<next_total_seat_of_bus_chosen){
                    if (tem_pass_assign.size() == all_booker1.size() && (!current_pass_assign)) {
                        System.out.println("Recursive!!!! 1");
                        break;
                    } else if ((tem_pass_assign.size() < all_booker1.size() || current_pass_assign) && last_bus_choosing) {
                        System.out.println("Recursive!!!! 3");
                        number_of_passenger = next_total_seat_of_bus_chosen + 1;
                        if (total_seat_of_all_bus == next_total_seat_of_bus_chosen) {
                            last_bus_choosing = false;
                        }
                    } else {
                        System.out.println("Recursive!!!! 4");
                        sch_with_users = new HashMap<Object, List<Booking_Master>>();
                        break;
                    }
                } else {
                    System.out.println("Recursive!!!! 5");
                    sch_with_users = new HashMap<Object, List<Booking_Master>>();
                    break;
                }

            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return sch_with_users;
    }
    //Check Bus Available and not from the same route

    public List<List<Map<String, Object>>> choose_correct_bus(Request_Booking booking, List<Map<String, Object>> all_bus, Pickup_Location_Master pick_source, Pickup_Location_Master pick_destin, int number_of_passenger, int total_seat_of_all_bus) {
        System.out.println("choose_correct_bus");
        List<List<Map<String, Object>>> list_bus_choosen = new ArrayList<List<Map<String, Object>>>();
        booking.total_bus = all_bus.size();
        booking.number_of_bus = all_bus.size();
        // Reset old data to empty
        booking.total_choosen_bus_list = new ArrayList<Integer>();
        booking.list = new ArrayList<List<Map<String, Object>>>();
        try {

            //=========3. Start The Process of choosing the correct total bus
            for (int i = 1; i <= booking.total_bus; i++) {
                printCombination(booking, all_bus, all_bus.size(), i, number_of_passenger, total_seat_of_all_bus);
            }
            //=========4. After choosing the correct total bus
            Collections.sort(booking.total_choosen_bus_list);
            for (int i = 0; i < booking.list.size(); i++) {
                int sum_each_bus = 0;
                for (int j = 0; j < booking.list.get(i).size(); j++) {
                    sum_each_bus += Integer.valueOf((String) booking.list.get(i).get(j).get("number_of_seat"));
                }
                if (sum_each_bus == booking.total_choosen_bus_list.get(0)) {
                    list_bus_choosen.add(booking.list.get(i));
                    break;
                }
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        System.out.println("List :      " + booking.list.size());
        return list_bus_choosen;
    }

    public List<Booking_Master> get_all_booker(Session session, int from_id, int to_id, String time, String date) {
        System.out.println("get_all_booker");
        List<Booking_Master> all_booker1 = new ArrayList<Booking_Master>();
        try {
            all_booker1 = session.createQuery("from Booking_Master where notification!='Cancelled' " +
                    "and (payment='Succeed' or payment='Cash') and schedule_id!='0' and from_id=? " +
                    "and to_id=? and dept_time=? and dept_date=? order by number_booking desc")
                    .setParameter(0, from_id).setParameter(1, to_id)
                    .setTime(2, java.sql.Time.valueOf(time))
                    .setDate(3, java.sql.Date.valueOf(date)).list();

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return all_booker1;
    }

    public List<Map<String, Object>> get_all_bus(Session session, Customer_Booking cb, int from, int to) throws ParseException {
        System.out.println("get_all_bus");
        List<Bus_Master> query_all_bus = new ArrayList<Bus_Master>();
        List<Map<String, Object>> all_bus = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> same_date_route = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> same_date_diff_route = new ArrayList<Map<String, Object>>();
        Request_Booking_Dao custom_imp = new Request_Booking();
        try {
            same_date_route = custom_imp.same_date_same_route(session, cb, from, to);
            List<Integer> unava1 = (List<Integer>) same_date_route.get(0).get("unavailable_bus");
            same_date_diff_route = custom_imp.same_date_differ_route(session, cb, from, to);
            List<Integer> unava2 = (List<Integer>) same_date_diff_route.get(0).get("unavailable_bus");
            String excep = "";
            for (int i = 0; i < unava1.size(); i++) {
                excep += " and id!=" + unava1.get(i);
            }
            for (int i = 0; i < unava2.size(); i++) {
                excep += " and id!=" + unava2.get(i);
            }
            query_all_bus = session.createQuery("from Bus_Master where enabled=?" + excep + " order by number_of_seat asc").setBoolean(0, true).list();
            System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
            System.out.println("from Bus_Master where enabled=?" + excep + " order by number_of_seat asc");
            System.out.println("query_all_bus size: " + query_all_bus.size());
            if (query_all_bus.size() > 0) {
                for (int i = 0; i < query_all_bus.size(); i++) {
                    Map<String, Object> map = new HashMap<String, Object>();
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

    public List<Map<String, Object>> same_date_same_route(Session session, Customer_Booking cb, int from, int to) throws ParseException {
        List<Schedule_Master> sch = new ArrayList<Schedule_Master>();
        List<Map<String, Object>> all_bus = new ArrayList<Map<String, Object>>();
        List<Integer> ava_bus = new ArrayList<Integer>();
        List<Integer> una_bus = new ArrayList<Integer>();
        try {

            sch = session.createQuery("from Schedule_Master where dept_date=:date and dept_time!=:time and to_id=:to and from_id=:from")
                    .setDate("date", java.sql.Date.valueOf(cb.getDate()))
                    .setTime("time", java.sql.Time.valueOf(cb.getTime()))
                    .setParameter("to", to)
                    .setParameter("from", from).list();

            for (int i = 0; i < sch.size(); i++) {
                if (time_same_date(sch.get(i).getDept_time().toString(), cb.getTime(), 8)) {
                    ava_bus.add(sch.get(i).getBus_id());
                } else {
                    una_bus.add(sch.get(i).getBus_id());
                }
            }
            Map<String, Object> map = new HashMap<String, Object>();
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
    public List<Map<String, Object>> same_date_differ_route(Session session, Customer_Booking cb, int from, int to) throws ParseException {
        List<Schedule_Master> sch = new ArrayList<Schedule_Master>();
        List<Map<String, Object>> all_bus = new ArrayList<Map<String, Object>>();
        List<Integer> ava_bus = new ArrayList<Integer>();
        List<Integer> una_bus = new ArrayList<Integer>();
        try {
            sch = session.createQuery("from Schedule_Master where dept_date=:date and from_id!=:from")
                    .setDate("date", java.sql.Date.valueOf(cb.getDate()))
                    .setParameter("from", from).list();

            for (int i = 0; i < sch.size(); i++) {
                if (time_same_date(sch.get(i).getDept_time().toString(), cb.getTime(), 4)) {
                    ava_bus.add(sch.get(i).getBus_id());
                } else {
                    una_bus.add(sch.get(i).getBus_id());
                }
            }
            Map<String, Object> map = new HashMap<String, Object>();
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

    public Boolean time_same_date(String user_time, String time, long time_dura) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1 = format.parse(user_time);
        Date date2 = format.parse(time);
        long difference = date2.getTime() - date1.getTime();
        long duration = difference / (1000 * 60 * 60);
        System.out.println("PLPL");
        System.out.println("duration: " + duration);
        System.out.println("time_dura: " + time_dura);
        if (duration >= time_dura || duration <= -time_dura) {
            return true;
        } else {
            return false;
        }
    }

    public int delete_Schedule(Session session, int from_id, int to_id, String time, String date) {
        System.out.println("delete_Schedule");
        try {
            Query q = session.createQuery("delete Schedule_Master where from_id=? and to_id=? and dept_time=? and dept_date=?");
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
    //======================== combination for choosing bus till here ============================

    public List<Integer> get_existing_bus_and_driver(Request_Booking booking, Session session, Customer_Booking cb, int from, int to) {
        List<Schedule_Master> sch = new ArrayList<Schedule_Master>();
        List<Integer> final_driver_list = new ArrayList<Integer>();
        try {

            sch = session.createQuery("from Schedule_Master where dept_date=:date and dept_time=:time and to_id=:to and from_id=:from")
                    .setDate("date", java.sql.Date.valueOf(cb.getDate()))
                    .setTime("time", java.sql.Time.valueOf(cb.getTime()))
                    .setParameter("to", to)
                    .setParameter("from", from).list();

            // Find driver list and assign driver
            List<Integer> driver_list = new ArrayList<Integer>();
            List<Integer> assign_driver = new ArrayList<Integer>();
            for (int i = 0; i < booking.list_bus_choosen.get(0).size(); i++) {
                Boolean status = true;
                for (int j = 0; j < sch.size(); j++) {
                    if (sch.get(j).getBus_id() == Integer.valueOf((String) booking.list_bus_choosen.get(0).get(i).get("id"))) {
                        driver_list.add(sch.get(j).getDriver_id());
                        status = false;
                        assign_driver.add(j);
                        break;
                    }
                }
                if (status) {
                    driver_list.add(0);
                }
            }

            // Find un-assign diver
            List<Integer> unassign_driver = new ArrayList<Integer>();
            for (int i = 0; i < sch.size(); i++) {
                Boolean status = true;
                if (assign_driver.size() > 0) {
                    for (int j = 0; j < assign_driver.size(); j++) {
                        if (i == assign_driver.get(j)) {
                            status = false;
                            break;
                        }
                    }
                    if (status) {
                        unassign_driver.add(sch.get(i).getDriver_id());
                    }
                } else {
                    //not assign at all
                    unassign_driver.add(sch.get(i).getDriver_id());
                }
            }

            // Add unassign driver to driver list
            int un_assign = 0;
            for (int i = 0; i < driver_list.size(); i++) {
                if (driver_list.get(i) == 0) {
                    if (un_assign < unassign_driver.size()) {
                        final_driver_list.add(unassign_driver.get(un_assign));
                        un_assign++;
                    } else {
                        final_driver_list.add(0);
                    }
                } else {
                    final_driver_list.add(driver_list.get(i));
                }
            }


        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return final_driver_list;
    }

    public String request_book_now(int id) throws ParseException {
        Request_Booking_Dao cus = new Request_Booking();
        Transaction trns1 = null;
        Customer_Booking cb = new Customer_Booking();
        Customer_Booking[] arr_cb = new Customer_Booking[1];
        Session session = HibernateUtil.getSessionFactory().openSession();
        String book = null;
        try {
            trns1 = session.beginTransaction();
            Booking_Request_Master br = (Booking_Request_Master) session.createQuery("from Booking_Request_Master where id=?").setParameter(0, id).list().get(0);

            cb.setDate(br.getDept_date().toString().subSequence(0, 10).toString());
//            cb.setTime(br.getProvided_time());
//            cb.setSource(br.getSource_id());
//            cb.setDestination(br.getDestination_id());
//            cb.setNumber_of_seat(br.getNumber_of_booking());
            arr_cb[0] = cb;
            // book=cus.customer_booking(arr_cb);
            if (book.equals("success") || book.equals("over_bus_available") || book.equals("no_bus_available")) {
                Query query = session.createQuery("update Booking_Request_Master set enabled='false' where id = :id");
                query.setParameter("id", id);
                int result = query.executeUpdate();
                trns1.commit();
            }

        } catch (RuntimeException e) {
        	e.printStackTrace();
            if (trns1 != null) {
                trns1.rollback();
            }
            return "error";
        } finally {
            session.flush();
            session.close();
        }
        return book;
    }


}
