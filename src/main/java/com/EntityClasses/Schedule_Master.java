package com.EntityClasses;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Schedule_Master {
		private int id;
		private String code;
		private int bus_id;
		private int driver_id;
		private int destination_id;
		private int source_id;
		private int from_id;
		private int to_id;
		private Date dept_date;
		private Time dept_time;
		private Time arrival_time;
		private Time actual_dept_time;
		private String description;
		private int no_seat;
		private int number_customer;
		private int number_staff;
		private int number_student;
		private Timestamp created_at;
		private Timestamp updated_at;
		
		
		public int getFrom_id() {
			return from_id;
		}
		public void setFrom_id(int from_id) {
			this.from_id = from_id;
		}
		public int getTo_id() {
			return to_id;
		}
		public void setTo_id(int to_id) {
			this.to_id = to_id;
			
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
		public int getBus_id() {
			return bus_id;
		}
		public void setBus_id(int bus_id) {
			this.bus_id = bus_id;
		}
		public int getDriver_id() {
			return driver_id;
		}
		public void setDriver_id(int driver_id) {
			this.driver_id = driver_id;
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
		public int getNumber_customer() {
			return number_customer;
		}
		public void setNumber_customer(int number_customer) {
			this.number_customer = number_customer;
		}
		public int getNumber_staff() {
			return number_staff;
		}
		public void setNumber_staff(int number_staff) {
			this.number_staff = number_staff;
		}
		public int getNumber_student() {
			return number_student;
		}
		public void setNumber_student(int number_student) {
			this.number_student = number_student;
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
		public Time getArrival_time() {
			return arrival_time;
		}
		public void setArrival_time(Time arrival_time) {
			this.arrival_time = arrival_time;
		}

        public Time getActual_dept_time() {
            return actual_dept_time;
        }

        public void setActual_dept_time(Time actual_dept_time) {
            this.actual_dept_time = actual_dept_time;
        }

        public int getNo_seat() {
            return no_seat;
        }

        public void setNo_seat(int no_seat) {
            this.no_seat = no_seat;
        }
}
