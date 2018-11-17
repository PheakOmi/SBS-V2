package com.EntityClasses;

import java.sql.Timestamp;
import java.util.Set;



public class User_Info {

		private int id;
		private String name;
		private String email;
		private String password;
		private String googlePassword;
		private Timestamp created_at;
		private Timestamp updated_at;
		private boolean enabled;
		private int batch_id;
		private String reset_token;
		private String username;
		private String gender;
		private String phone_number;
		private int number_ticket;
		private String profile;
		private Set<UserRole> userRole; 
		private String type="system";

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
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		public String getGooglePassword() {
			return googlePassword;
		}
		public void setGooglePassword(String googlePassword) {
			this.googlePassword = googlePassword;
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
		public int getBatch_id() {
			return batch_id;
		}
		public void setBatch_id(int batch_id) {
			this.batch_id = batch_id;
		}
		public String getReset_token() {
			return reset_token;
		}
		public void setReset_token(String reset_token) {
			this.reset_token = reset_token;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getPhone_number() {
			return phone_number;
		}
		public void setPhone_number(String phone_number) {
			this.phone_number = phone_number;
		}
		public int getNumber_ticket() {
			return number_ticket;
		}
		public void setNumber_ticket(int number_ticket) {
			this.number_ticket = number_ticket;
		}
		public String getProfile() {
			return profile;
		}
		public void setProfile(String profile) {
			this.profile = profile;
		}
		public Set<UserRole> getUserRole() {
			return userRole;
		}
		public void setUserRole(Set<UserRole> userRole) {
			this.userRole = userRole;
		}

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
}
