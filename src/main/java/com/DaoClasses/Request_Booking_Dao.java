package com.DaoClasses;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.EntityClasses.Booking_Master;
import com.EntityClasses.Pickup_Location_Master;
import com.ModelClasses.Customer_Booking;
import com.ModelClasses.ID_Class;
import com.ModelClasses.New_Pickup_Location;
import com.ModelClasses.UserModel;

public interface Request_Booking_Dao {
	public String booking(ID_Class id_class) throws ParseException;
	public String customer_booking(Session session,Customer_Booking cb) throws ParseException;
	public List<Booking_Master> get_all_booker(Session session,int from_id, int to_id, String time, String date);
	public List<Map<String,Object>> get_all_bus(Session session,Customer_Booking cb,int from, int to) throws ParseException;
	public int delete_Schedule(Session session, int from_id,int to_id,String time, String date);
	public Map<Object, List<Booking_Master>> create_schedule(Request_Booking booking,Session session,List<Map<String,Object>> all_bus, List<Booking_Master> all_booker1, Pickup_Location_Master pick_source, Pickup_Location_Master pick_destin, Customer_Booking cb, int total_seat_of_all_bus, int number_of_passenger);
	public List<List<Map<String,Object>>> choose_correct_bus(Request_Booking booking,List<Map<String,Object>> all_bus, Pickup_Location_Master pick_source, Pickup_Location_Master pick_destin, int number_of_passenger, int total_seat_of_all_bus);
	public String request_book_now(int id) throws ParseException;
	public List<Map<String,Object>> same_date_differ_route(Session session,Customer_Booking cb,int from, int to) throws ParseException;
	public List<Map<String,Object>> same_date_same_route(Session session,Customer_Booking cb,int from, int to) throws ParseException;
	public List<Integer> get_existing_bus_and_driver(Request_Booking booking,Session session,Customer_Booking cb,int from, int to);
	public void create_unassigned_booking(Session session,Customer_Booking cb,Pickup_Location_Master pick_source,Pickup_Location_Master pick_destin);



}
