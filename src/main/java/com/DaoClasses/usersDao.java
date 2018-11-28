package com.DaoClasses;


import java.util.List;
import java.util.Map;

import com.EntityClasses.*;
import com.ModelClasses.*;

import java.text.ParseException;



public interface usersDao {
	public User_Info findByUserName(String name);
	public boolean createUser(UserModel user,String type);
	public boolean updateUser(User_Info user,UserModel user_model,String type);
	public int saveBus(Bus_Master bus);
	public List<Bus_Master> getAllBuses();
	public List<Bus_Master> getAllBuses2();
	public Bus_Master getBusById (int id);
	public int updateBus(Bus_Master bus);
	public int deleteBus(int id);
	public int saveLocation(Location_Master location);
	public List<Location_Master> getAllLocations();
	public int savePickUpLocation(Pickup_Location_Master p_location);
	public List<Pickup_Location_Master> getAllPickUpLocations();
	public Location_Master getLocationById(int id);
	public int updateLocation(Location_Master location);
	public Pickup_Location_Master getPickUpLocationById(int id);
	public int updatePickUpLocation(Pickup_Location_Master p_location);
	public int deleteLocation(int id);
	public int deletePickUpLocation(int id);
	public List <Booking_Master> getAllCurrentBookings();
	public List <Booking_Master> getAllHistoricalBookings();
	public Booking_Master getBookingById (int id);
	public List <?> getAllSchedulesByMonth(String month, String year) throws ParseException, java.text.ParseException;
	public List <Schedule_Master> schedule_list(String date, String month, String year) throws ParseException, java.text.ParseException;
	public List<Booking_Master> getBookingByScheduleId (int id);
	public Schedule_Master getScheduleById (int id);
	public List <Schedule_Master> getAllHistoricalSchedules();
	public List <Schedule_Master> getAllCurrentSchedules();
	public List <Schedule_Master> getAllSchedules();
	public List<User_Info> getAllUsers();
	public Map<String, Object> saveSchedule(Schedule_Model schedule)throws ParseException;
	public Map<String, Object> saveMultipleSchedule(Schedule_Model schedule)throws ParseException;
	public Map<String, Object> updateSchedule(Schedule_Model schedule) throws ParseException;
	public int updateSchedule2(Schedule_Model schedule) throws ParseException;
	public List <Booking_Request_Master> getAllCurrentBookingRequests();
	public Booking_Request_Master getBookingRequestById (int id);
	public int confirmRequest(Booking_Request_Master request);
	public List <Booking_Request_Master> getAllHistoricalBookingRequests();
	public int rejectRequest(Booking_Request_Master request);
	public int deleteSchedule(int id);
	public int moveSchedule(int arr[], int id);
	public int saveSchedule2(Schedule_Model schedule)throws ParseException;
	public int moveSimple(int arr[], int old_id, int new_id, int bookings);
	public List<User_Info> getAlDrivers();
	public List<User_Info> getAlCustomers();
	public void rejectedRequest(String email);
	public void confirmedRequest(String email);
	public User_Info getCustomerById(int id);
	public int createUserr(User_Info user);
	public int changePass(User_Info user);
	public List<Dept_Time_Master> getAllTimes();
	public int saveTime(Schedule_Model schedule) throws ParseException;
	public int deleteTime(int id);
	public List<Booking_Master> getBookingReporting(B_Model booking) throws ParseException;
	public List<Booking_Master> getScheduleReporting(B_Model booking) throws ParseException;
	public int saveDate(Batch_Master b) throws ParseException;
	public List<Batch_Master> getAllDates();
	public int deleteDate(int id);
	public int updateDate(Batch_Master batch);
	public Map<String, String> moveToRental(Schedule_Model model);
	public Cost getAllPrices();
	public int updateCost(Cost cost);
	public int saveCost(Cost cost);
	public List<Booking_Master> getAllUnpaidBookings();
	public List<Refund_Master> getAllRefunds();
	public int ignoreRefund(Refund_Master refund);
	public int doneRefund(Refund_Master refund);
	public int payBooking(Booking_Master booking);
	public List<User_Info> getAllStudents();
	public Map<String, Object> sendMonthlySchedule(Schedule_Model model) throws ParseException;
	public int apReqEmail(String token, String type);
	public List <Schedule_Log> getAllLogsBySchedeuleId(int id);
	public Schedule_Log getScheduleLogById (int id);
	public Map<String, Object> saveSchedule(Schedule_Model schedule, String type) throws ParseException;
	public Map<String, Object> saveMultipleSchedule(Schedule_Model schedule, String type) throws ParseException;
	public Map<String, Object> saveMultipleSchedule(Schedule_Model schedule, String type, String choice) throws ParseException;
}
