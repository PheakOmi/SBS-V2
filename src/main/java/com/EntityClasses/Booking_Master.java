package com.EntityClasses;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Booking_Master {
		private int id;
		private int user_id;
		private int destination_id;
		private int source_id;
		private int schedule_id;
		private int from_id;
		private int to_id;
		private Date booking_date;
		private Date dept_date;
		private Time dept_time;
		private int number_booking;
		private int child;
		private int adult;
		private float total_cost; //$
		private String notification;
		private String qr;
		private String code;
		private String status;
		private String description;
		private Timestamp created_at;
		private Timestamp updated_at;
		private Boolean qr_status;
		private Boolean email_confirm;
		private String qr_name;
		private String payment;
		private String transaction_id;
		private int booking_request_id;
	
		public int getFrom_id() {
			return from_id;
		}
		public void setFrom_id(int from_id) {
			this.from_id = from_id;
		}
		public float getTotal_cost() {
			return total_cost;
		}
		public void setTotal_cost(float total_cost) {
			this.total_cost = total_cost;
		}
		public int getTo_id() {
			return to_id;
		}
		public void setTo_id(int to_id) {
			this.to_id = to_id;
		
		}
		public Boolean getEmail_confirm() {
			return email_confirm;
		}
		public void setEmail_confirm(Boolean email_confirm) {
			this.email_confirm = email_confirm;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getUser_id() {
			return user_id;
		}
		public void setUser_id(int user_id) {
			this.user_id = user_id;
		}
		public int getDestination_id() {
			return destination_id;
		}
		public void setDestination_id(int destination_id) {
			this.destination_id = destination_id;
		}
		public int getSource_id() {
			return source_id;
		}
		public void setSource_id(int source_id) {
			this.source_id = source_id;
		}
		public int getSchedule_id() {
			return schedule_id;
		}
		public void setSchedule_id(int schedule_id) {
			this.schedule_id = schedule_id;
		}
		public Date getBooking_date() {
			return booking_date;
		}
		public void setBooking_date(Date booking_date) {
			this.booking_date = booking_date;
		}
		public Date getDept_date() {
			return dept_date;
		}
		public void setDept_date(Date dept_date) {
			this.dept_date = dept_date;
		}
		public Time getDept_time() {
			return dept_time;
		}
		public void setDept_time(Time dept_time) {
			this.dept_time = dept_time;
		}
		public int getNumber_booking() {
			return number_booking;
		}
		public void setNumber_booking(int number_booking) {
			this.number_booking = number_booking;
		}
		public String getNotification() {
			return notification;
		}
		public void setNotification(String notification) {
			this.notification = notification;
		}
		public String getQr() {
			return qr;
		}
		public void setQr(String qr) {
			this.qr = qr;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Timestamp getCreated_at() {
			return created_at;
		}
		public void setCreated_at(Timestamp created_at) {
			this.created_at = created_at;
		}
		public Timestamp getUpdated_at() {
			return updated_at;
		}
		public void setUpdated_at(Timestamp updated_at) {
			this.updated_at = updated_at;
		}
		public int getChild() {
			return child;
		}
		public void setChild(int child) {
			this.child = child;
		}
		public int getAdult() {
			return adult;
		}
		public void setAdult(int adult) {
			this.adult = adult;
		}
		public Boolean getQr_status() {
			return qr_status;
		}
		public void setQr_status(Boolean qr_status) {
			this.qr_status = qr_status;
		}
		public String getQr_name() {
			return qr_name;
		}
		public void setQr_name(String qr_name) {
			this.qr_name = qr_name;
		}

		public String getPayment() {
			return payment;
		}

		public void setPayment(String payment) {
			this.payment = payment;
		}

		public String getTransaction_id() {
			return transaction_id;
		}

		public void setTransaction_id(String transaction_id) {
			this.transaction_id = transaction_id;
		}
		public int getBooking_request_id() {
			return booking_request_id;
		}
		public void setBooking_request_id(int booking_request_id) {
			this.booking_request_id = booking_request_id;
		}
		
		
}
