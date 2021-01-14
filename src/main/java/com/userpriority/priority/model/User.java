package com.userpriority.priority.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;

@Entity(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotNull
	private String userName;
	@NotNull
	private String pasword;
	@ElementCollection()
	@CollectionTable(name = "UserSatisfactionList", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "priority")
	private List<UserSatisfactionRate> priorityList = new ArrayList<>();
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "roletable", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "roles")
	private List<String> roles = new ArrayList<String>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasword() {
		return pasword;
	}

	public void setPasword(String pasword) {
		this.pasword = pasword;
	}

	public List<UserSatisfactionRate> getPriorityList() {
		return priorityList;
	}

	public void setPriorityList(List<UserSatisfactionRate> priorityList) {
		this.priorityList = priorityList;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}