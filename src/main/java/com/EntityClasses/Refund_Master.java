package com.EntityClasses;

public class Refund_Master {
		private int id;
		private int booking_id;
		private int percentage;
		private String status;

		
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getBooking_id() {
			return booking_id;
		}
		public void setBooking_id(int booking_id) {
			this.booking_id = booking_id;
		}
	
		public int getPercentage(){
			return percentage;
		}
		public void setPercentage(int percentage){
			this.percentage = percentage;
		}
		
}
