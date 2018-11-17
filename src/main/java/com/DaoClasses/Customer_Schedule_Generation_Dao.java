package com.DaoClasses;

import com.EntityClasses.Booking_Master;
import com.EntityClasses.Pickup_Location_Master;
import com.EntityClasses.User_Info;
import com.ModelClasses.Customer_Booking;
import org.hibernate.Session;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface Customer_Schedule_Generation_Dao {
    public String booking(Customer_Booking[] cb) throws ParseException;

    public String customer_schedule_generation(Session session,Customer_Booking cb) throws ParseException;

    public void create_unassigned_booking(Session session, Customer_Booking cb, Pickup_Location_Master pick_source, Pickup_Location_Master pick_destin);

    public Map<Object, List<Booking_Master>> create_schedule(Customer_Schedule_Generation_Imp booking, Session session,
                                                             List<Map<String, Object>> all_bus, List<Booking_Master> all_booker1,
                                                             Pickup_Location_Master pick_source, Pickup_Location_Master pick_destin,
                                                             Customer_Booking cb, int total_seat_of_all_bus, int number_of_passenger);

    public List<List<Map<String, Object>>> choose_correct_bus(Customer_Schedule_Generation_Imp booking, List<Map<String, Object>> all_bus,
                                                              Pickup_Location_Master pick_source, Pickup_Location_Master pick_destin,
                                                              int number_of_passenger, int total_seat_of_all_bus);

    public List<Booking_Master> get_all_booker(Session session, int from_id, int to_id, String time, String date);

    public List<Map<String, Object>> get_all_bus(Session session, Customer_Booking cb, int from, int to) throws ParseException;

    public List<Map<String, Object>> same_date_same_route(Session session, Customer_Booking cb, int from, int to) throws ParseException;

    public List<Map<String, Object>> same_date_differ_route(Session session, Customer_Booking cb, int from, int to) throws ParseException;

    public Boolean time_same_date(String user_time, String time, long time_dura) throws ParseException;

    public List<User_Info> get_all_available_drivers(Session session, Customer_Booking cb, int from, int to) throws ParseException;

    public List<Map<String, Object>> same_date_same_rout(Session session, Customer_Booking cb, int from, int to) throws ParseException;

    public List<Map<String, Object>> same_date_differ_rout(Session session, Customer_Booking cb, int from, int to) throws ParseException;

    public List<User_Info> getAllD(List<Integer> idd);

    public int delete_Schedule(Session session, int from_id, int to_id, String time, String date);

    public List<Integer> get_existing_bus_and_driver(Customer_Schedule_Generation_Imp booking, Session session, Customer_Booking cb, int from, int to);

}
