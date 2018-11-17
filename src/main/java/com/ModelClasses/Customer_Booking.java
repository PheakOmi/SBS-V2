package com.ModelClasses;
public class Customer_Booking {
	private int source;
	private int destination;
	private String time;
	private String date;
	private int number_of_seat;
	private int adult;
	private int child;
	private String description;
	private String status;//book and request_book
	private float total_cost;
	private int booking_master_id;
	private String pay;


	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getDestination() {
		return destination;
	}

	public void setDestination(int destination) {
		this.destination = destination;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNumber_of_seat() {
		return number_of_seat;
	}

	public void setNumber_of_seat(int number_of_seat) {
		this.number_of_seat = number_of_seat;
	}

	public int getAdult() {
		return adult;
	}

	public void setAdult(int adult) {
		this.adult = adult;
	}

	public int getChild() {
		return child;
	}

	public void setChild(int child) {
		this.child = child;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(float total_cost) {
		this.total_cost = total_cost;
	}

	public int getBooking_master_id() {
		return booking_master_id;
	}

	public void setBooking_master_id(int booking_master_id) {
		this.booking_master_id = booking_master_id;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}
}
