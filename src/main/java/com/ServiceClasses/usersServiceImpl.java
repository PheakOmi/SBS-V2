package com.ServiceClasses;


import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DaoClasses.usersDao;
import com.EntityClasses.*;
import com.ModelClasses.B_Model;
import com.ModelClasses.Schedule_Model;
import com.ModelClasses.UserModel;




@Service
public class usersServiceImpl implements usersService{

	@Autowired
	usersDao usersDao1;
	public User_Info findByUserName(String name){
		return usersDao1.findByUserName(name);
	}
	public int saveBus(Bus_Master bus) {
		return usersDao1.saveBus(bus);
	}
	public List<Bus_Master> getAllBuses(){
		return usersDao1.getAllBuses();
	}
	public List<Bus_Master> getAllBuses2(){
		return usersDao1.getAllBuses2();
	}
	public Bus_Master getBusById (int id){
		return usersDao1.getBusById(id);
	}
	public int updateBus(Bus_Master bus){
		return usersDao1.updateBus(bus);
	}
	public int deleteBus(int id){
		return usersDao1.deleteBus(id);
	}
	public int saveLocation(Location_Master location) {
		return usersDao1.saveLocation(location);
	}
	public List<Location_Master> getAllLocations(){
		return usersDao1.getAllLocations();
	}
	public int savePickUpLocation(Pickup_Location_Master p_location){
		return usersDao1.savePickUpLocation(p_location);
	}
	public List<Pickup_Location_Master> getAllPickUpLocations(){
		return usersDao1.getAllPickUpLocations();
	}
	public Location_Master getLocationById(int id){
		return usersDao1.getLocationById(id);
	}
	public int updateLocation(Location_Master location){
		return usersDao1.updateLocation(location);
	}
	public Pickup_Location_Master getPickUpLocationById(int id){
		return usersDao1.getPickUpLocationById(id);
	}
	public int updatePickUpLocation(Pickup_Location_Master p_location){
		return usersDao1.updatePickUpLocation(p_location);
	}
	public int deleteLocation(int id){
		return usersDao1.deleteLocation(id);
	}
	public int deletePickUpLocation(int id){
		return usersDao1.deletePickUpLocation(id);
	}
	public List <Booking_Master> getAllCurrentBookings(){
		return usersDao1.getAllCurrentBookings();
	}
	public List <Booking_Master> getAllHistoricalBookings(){
		return usersDao1.getAllHistoricalBookings();
	}
	public Booking_Master getBookingById (int id){
		return usersDao1.getBookingById(id);
	}
	public List <?> getAllSchedulesByMonth(String month, String year) throws ParseException, java.text.ParseException{
		return usersDao1.getAllSchedulesByMonth(month, year);
	}
	public List<Booking_Master> getBookingByScheduleId (int id){
		return usersDao1.getBookingByScheduleId(id);
	}
	public Schedule_Master getScheduleById (int id){
		return usersDao1.getScheduleById(id);
	}
	public List <Schedule_Master> getAllHistoricalSchedules(){
		return usersDao1.getAllHistoricalSchedules();
	}
	public List <Schedule_Master> getAllCurrentSchedules(){
		return usersDao1.getAllCurrentSchedules();
	}
	public List <Schedule_Master> getAllSchedules(){
		return usersDao1.getAllSchedules();
	}
	public List<User_Info> getAllUsers(){
		return usersDao1.getAllUsers();
	}
	public Map<String, Object> saveSchedule(Schedule_Model schedule)throws ParseException{
		return usersDao1.saveSchedule(schedule);
	}
	public Map<String, Object> saveMultipleSchedule(Schedule_Model schedule)throws ParseException{
		return usersDao1.saveMultipleSchedule(schedule);
	}
	public Map<String, Object> updateSchedule(Schedule_Model schedule) throws ParseException{
		return usersDao1.updateSchedule(schedule);
	}
	public int updateSchedule2(Schedule_Model schedule) throws ParseException{
		return usersDao1.updateSchedule2(schedule);
	}
	public List <Booking_Request_Master> getAllCurrentBookingRequests(){
		return usersDao1.getAllCurrentBookingRequests();
	}
	public Booking_Request_Master getBookingRequestById (int id){
		return usersDao1.getBookingRequestById(id);
	}
	public int confirmRequest(Booking_Request_Master request){
		return usersDao1.confirmRequest(request);
	}
	public List <Booking_Request_Master> getAllHistoricalBookingRequests(){
		return usersDao1.getAllHistoricalBookingRequests();
	}
	public int rejectRequest(Booking_Request_Master request){
		return usersDao1.rejectRequest(request);
	}
	public int deleteSchedule(int id){
		return usersDao1.deleteSchedule(id);
	}
	public int moveSchedule(int arr[], int id){
		return usersDao1.moveSchedule(arr, id);
	}
	public int saveSchedule2(Schedule_Model schedule)throws ParseException{
		return usersDao1.saveSchedule2(schedule);
	}
	public int moveSimple(int arr[], int old_id, int new_id, int bookings){
		return usersDao1.moveSimple(arr, old_id, new_id, bookings);
	}
	public boolean createUser(UserModel user,String type) {

		return usersDao1.createUser(user,type);
	}
	public boolean updateUser(User_Info user, UserModel user_model,String type) {
		
		return usersDao1.updateUser(user, user_model,type);
	}
	public List<User_Info> getAlDrivers(){
		return usersDao1.getAlDrivers();
	}
	public List<User_Info> getAlCustomers(){
		return usersDao1.getAlCustomers();
	}
	public void rejectedRequest(String email){
		 usersDao1.rejectedRequest(email);
	}
	public void confirmedRequest(String email){
		usersDao1.confirmedRequest(email);
	}
	public User_Info getCustomerById(int id){
		return usersDao1.getCustomerById(id);
	}
	public int createUserr(User_Info user){
		return usersDao1.createUserr(user);
	}
	public int changePass(User_Info user){
		return usersDao1.changePass(user);
	}
	
	public List<Dept_Time_Master> getAllTimes(){
		return usersDao1.getAllTimes();
	}
	public int saveTime(Schedule_Model schedule) throws ParseException{
		return usersDao1.saveTime(schedule);	
	}
	public int deleteTime(int id){
		return usersDao1.deleteTime(id);	
	}
	public List<Booking_Master> getBookingReporting(B_Model booking) throws ParseException{
		return usersDao1.getBookingReporting(booking);
	}
	public List<Booking_Master> getScheduleReporting(B_Model booking) throws ParseException{
		return usersDao1.getScheduleReporting(booking);
	}
	public int saveDate(Batch_Master b) throws ParseException{
		return usersDao1.saveDate(b);
	}
	public List<Batch_Master> getAllDates(){
		return usersDao1.getAllDates();
	}
	public int deleteDate(int id){
		return usersDao1.deleteDate(id);
	}
	public int updateDate(Batch_Master batch){
		return usersDao1.updateDate(batch);
	}
	public Map<String, String> moveToRental(Schedule_Model model){
		return usersDao1.moveToRental(model);
	}
	public Cost getAllPrices(){
		return usersDao1.getAllPrices();
	}
	public int updateCost(Cost cost){
		return usersDao1.updateCost(cost);
	}
	public int saveCost(Cost cost){
		return usersDao1.saveCost(cost);
	}
	public List<Booking_Master> getAllUnpaidBookings()
	{
		return usersDao1.getAllUnpaidBookings();
	}
	public List<Refund_Master> getAllRefunds()
	{
		return usersDao1.getAllRefunds();
	}
	public int ignoreRefund(Refund_Master refund){
		return usersDao1.ignoreRefund(refund);
	}
	public int payBooking(Booking_Master booking){
		return usersDao1.payBooking(booking);
	}
	public int doneRefund(Refund_Master refund){
		return usersDao1.doneRefund(refund);	
	}
	public List<User_Info> getAllStudents(){
		return usersDao1.getAllStudents();
	}
	public Map<String, Object> sendMonthlySchedule(Schedule_Model model) throws ParseException{
		return usersDao1.sendMonthlySchedule(model);
	}
	public int apReqEmail(String token, String type){
		return usersDao1.apReqEmail(token, type);
	}
	public List <Schedule_Log> getAllLogsBySchedeuleId(int id){
		return usersDao1.getAllLogsBySchedeuleId(id);

	}
	public Schedule_Log getScheduleLogById (int id){
		return usersDao1.getScheduleLogById(id);
	}
	public Map<String, Object> saveSchedule(Schedule_Model schedule, String type) throws ParseException{
		return usersDao1.saveSchedule(schedule, type);
	}
	public Map<String, Object> saveMultipleSchedule(Schedule_Model schedule, String type) throws ParseException{
		return usersDao1.saveMultipleSchedule(schedule,type);
	}
	public Map<String, Object> saveMultipleSchedule(Schedule_Model schedule, String type, String choice) throws ParseException{
		return usersDao1.saveMultipleSchedule(schedule,type,choice);
	}
	public List<Schedule_Master> forCalendarview() throws ParseException{
		return usersDao1.forCalendarview();
	}
	public List <Schedule_Master> schedule_list(Schedule_Model model) throws ParseException, java.text.ParseException{
		return usersDao1.schedule_list(model);
	}
}


