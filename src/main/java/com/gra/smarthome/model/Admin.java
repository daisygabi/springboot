package com.gra.smarthome.model;

import java.util.List;

public class Admin {
	private long groupAdminId;
	private long id;
	private String username;

	public long getGroupAdminId() {
		return groupAdminId;
	}

	public void setGroupAdminId(long groupAdminId) {
		this.groupAdminId = groupAdminId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}