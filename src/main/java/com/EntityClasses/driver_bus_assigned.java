package com.EntityClasses;

import java.sql.Timestamp;
import java.util.Date;

public class driver_bus_assigned {
		private int id;
		private int schedule_id;
		private int driver_id;
		private int bus_id;
		private Date dept_date;
		private Date dept_time;
		private Timestamp created_at;
		private Timestamp updated_at;
		
		
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getSchedule_id() {
			return schedule_id;
		}
		public void setSchedule_id(int schedule_id) {
			this.schedule_id = schedule_id;
		}
		public int getDriver_id() {
			return driver_id;
		}
		public void setDriver_id(int driver_id) {
			this.driver_id = driver_id;
		}
		public int getBus_id() {
			return bus_id;
		}
		public void setBus_id(int bus_id) {
			this.bus_id = bus_id;
		}
		public Date getDept_date() {
			return dept_date;
		}
		public void setDept_date(Date dept_date) {
			this.dept_date = dept_date;
		}
		public Date getDept_time() {
			return dept_time;
		}
		public void setDept_time(Date dept_time) {
			this.dept_time = dept_time;
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
		
		
}
