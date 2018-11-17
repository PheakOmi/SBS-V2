package com.EntityClasses;



import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRole{

	
	private Integer userRoleId;
				
	private String role;
		
	private User_Info user_info;

	private Timestamp created_at;
	private Timestamp updated_at;
	
/*	
	public UserRole(String role, User user) {
		this.role=role;
		this.user=user;
	}
	
*/	

	

	/*public UserRole(String user_type, Timestamp created_at, User_Info user) {
		this.role=user_type;
		this.created_at=created_at;
		this.user_info=user;
	
	}*/


	public Integer getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	
	public User_Info getUser_info() {
		return user_info;
	}

	public void setUser_info(User_Info user_info) {
		this.user_info = user_info;
	}

	
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
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