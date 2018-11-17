package com.EntityClasses;

import java.sql.Timestamp;

public class Bus_Master {
		private int id;
		private String plate_number;
		private String model;
		private int number_of_seat;
		private Boolean availability;
		private String description;
		private Timestamp created_at;
		private Timestamp updated_at;
		private boolean enabled;
		
		
		
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getModel() {
			return model;
		}
		public void setModel(String model) {
			this.model = model;
		}
		public String getPlate_number() {
			return plate_number;
		}
		public void setPlate_number(String plate_number) {
			this.plate_number = plate_number;
		}
		public int getNumber_of_seat() {
			return number_of_seat;
		}
		public void setNumber_of_seat(int number_of_seat) {
			this.number_of_seat = number_of_seat;
		}
		public Boolean getAvailability() {
			return availability;
		}
		public void setAvailability(Boolean availability) {
			this.availability = availability;
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
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
		
}
