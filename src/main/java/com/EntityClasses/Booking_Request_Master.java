package com.EntityClasses;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Booking_Request_Master {
	private int id;
	private int destination_id;
	private int source_id;
	private int from_id;
	private int approved_by;
	private int to_id;
	private Time dept_time;
	private Date dept_date;
	private String description;
	private String feedback;
	private String status;
	private String token;
	private int user_id;
	private Timestamp created_at;
	private Timestamp updated_at;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getFrom_id() {
		return from_id;
	}

	public void setFrom_id(int from_id) {
		this.from_id = from_id;
	}

	public int getApproved_by() {
		return approved_by;
	}

	public void setApproved_by(int approved_by) {
		this.approved_by = approved_by;
	}

	public int getTo_id() {
		return to_id;
	}

	public void setTo_id(int to_id) {
		this.to_id = to_id;
	}

	public Time getDept_time() {
		return dept_time;
	}

	public void setDept_time(Time dept_time) {
		this.dept_time = dept_time;
	}

	public Date getDept_date() {
		return dept_date;
	}

	public void setDept_date(Date dept_date) {
		this.dept_date = dept_date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
