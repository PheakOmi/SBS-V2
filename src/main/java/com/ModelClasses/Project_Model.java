package com.ModelClasses;

import java.sql.Timestamp;

public class Project_Model {
	private int id;
	private String project_name;
	private String project_code;
	private String skillset;
	private int project_type;
	private int project_co;
	private int project_leader;
	//private String project_member;
	private String description;
	private int initially_planned;
	private int budget;
	private String kit_point;
	private String deadline;
	private String start_date;
	private String end_date;
	private Timestamp created_at;
	private Timestamp updated_at;
	private String status;
	private int member[];
	private int skill[];
	private boolean auto;
	
	
	
	public int[] getSkill() {
		return skill;
	}
	public void setSkill(int[] skill) {
		this.skill = skill;
	}
	public boolean isAuto() {
		return auto;
	}
	public void setAuto(boolean auto) {
		this.auto = auto;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
//	public int[] getStage() {
//		return stage;
//	}
//	public void setStage(int[] stage) {
//		this.stage = stage;
//	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getProject_code() {
		return project_code;
	}
	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}
	public String getSkillset() {
		return skillset;
	}
	public void setSkillset(String skillset) {
		this.skillset = skillset;
	}
	public int getProject_type() {
		return project_type;
	}
	public void setProject_type(int project_type) {
		this.project_type = project_type;
	}
	public int getProject_co() {
		return project_co;
	}
	public void setProject_co(int project_co) {
		this.project_co = project_co;
	}
	public int getProject_leader() {
		return project_leader;
	}
	public void setProject_leader(int project_leader) {
		this.project_leader = project_leader;
	}
	public int[] getMember() {
		return member;
	}
	public void setMember(int[] member) {
		this.member = member;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getInitially_planned() {
		return initially_planned;
	}
	public void setInitially_planned(int initially_planned) {
		this.initially_planned = initially_planned;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	public String getKit_point() {
		return kit_point;
	}
	public void setKit_point(String kit_point) {
		this.kit_point = kit_point;
	}
	
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
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
