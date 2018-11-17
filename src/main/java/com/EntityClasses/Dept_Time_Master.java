package com.EntityClasses;

import java.sql.Time;

import java.sql.Timestamp;

public class Dept_Time_Master {
	private int id;
	private Time dept_time;
	private Boolean enabled;
	private Timestamp created_at;
	private Timestamp updated_at;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Time getDept_time() {
		return dept_time;
	}
	public void setDept_time(Time dept_time) {
		this.dept_time = dept_time;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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
