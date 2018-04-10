package pl.coderslab.models;

import java.sql.Date;

public class Solution {
	private int id;
	private Date created;
	private Date updated;
	private String description;
	private int excercise_id;
	private int users_id;
	
	
	
	public Solution(Date created, Date updated, String description, int excercise_id, int users_id) {
		this.created = created;
		this.updated = updated;
		this.description = description;
		this.excercise_id = excercise_id;
		this.users_id = users_id;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getExcercise_id() {
		return excercise_id;
	}
	public void setExcercise_id(int excercise_id) {
		this.excercise_id = excercise_id;
	}
	public int getUsers_id() {
		return users_id;
	}
	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}
	public int getId() {
		return id;
	}
	
	
}
