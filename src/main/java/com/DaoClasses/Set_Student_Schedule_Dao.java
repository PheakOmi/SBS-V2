package com.DaoClasses;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import com.EntityClasses.Booking_Master;
import com.EntityClasses.Schedule_Master;
import com.ModelClasses.Customer_Booking;

public interface Set_Student_Schedule_Dao {
	public void student_schedule(Session session,Customer_Booking cb) throws ParseException;
	public List<Booking_Master> get_all_booker(Session session,int from_id, int to_id, String time, String date);
	public List<Map<String,Object>> get_all_bus(Session session,Customer_Booking cb,int from, int to) throws ParseException;
	public int delete_Schedule(Session session, int from_id,int to_id,String time, String date,List<Integer> except_sch_id);
	public Map<Object, List<Booking_Master>> create_schedule(Set_Student_Schedule booking,Session session,List<Map<String,Object>> all_bus, List<Booking_Master> all_booker1, Customer_Booking cb, int total_seat_of_all_bus, int number_of_passenger);
	public List<List<Map<String,Object>>> choose_correct_bus(Set_Student_Schedule booking,List<Map<String,Object>> all_bus,Customer_Booking cb, int number_of_passenger, int total_seat_of_all_bus);
	public List<Map<String,Object>> same_date_differ_route(Session session,Customer_Booking cb,int from, int to) throws ParseException;
	public List<Map<String,Object>> same_date_same_route(Session session,Customer_Booking cb,int from, int to) throws ParseException;
	public List<Integer> get_existing_bus_and_driver(Set_Student_Schedule booking,Session session,Customer_Booking cb);
	public void create_unassigned_booking(Session session,List<Booking_Master> list_stu);
	public List<int[]> getListRound(Session session);
	public List<Map<String,Object>> getAvailableTime(String tmr_dt, Session session);
	public List <Schedule_Master> get_schedules(Session session,Customer_Booking cb);
	public void createSchedule() throws ParseException;
	public void asign_to_existing_schedule(Session session,List<Booking_Master> list_stu,List<Schedule_Master> list_schedule);
}
