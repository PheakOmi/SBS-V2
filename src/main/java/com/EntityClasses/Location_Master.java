package com.EntityClasses;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Location_Master {
		private int id;
		private String name;
		private String abbreviation;
		private Timestamp created_at;
		private Timestamp updated_at;
		private boolean enabled;
		private Time dept_time;
		private Boolean forstudent;
		private String dept_time2;
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
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
		public boolean isEnabled() {
			return enabled;
		}
		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
		public Time getDept_time() {
			return dept_time;
		}
		public void setDept_time(Time dept_time) {
			this.dept_time = dept_time;
		}
		public Boolean getForstudent() {
			return forstudent;
		}
		public void setForstudent(Boolean forstudent) {
			this.forstudent = forstudent;
		}
		public String getDept_time2() {
			return dept_time2;
		}
		public void setDept_time2(String dept_time2) {
			this.dept_time2 = dept_time2;
		}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
}
