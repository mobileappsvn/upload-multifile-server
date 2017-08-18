package com.robert.ws.authenticate.jsonwebtoken;

import com.robert.ws.interfaces.JSONAble;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class User implements JSONAble {
	private long id;
	private String email;
	private String username;
	private String deviceId;
	private String phone;
	private String password;
	
	public User() {}
	
	public User(long id, String username, String email) {
		this.id = id;
		this.username = username;
		this.email = email;
	}
	public User(String deviceId, String phone, String password) {
		this.deviceId = deviceId;
		this.phone = phone;
		this.password = password;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String role) {
		this.email = role;
	}
	
	@Override
	public String toString() {
		return toJSON().toString();
	}
	
	public JSONObject toJSON() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		try {
			json.put("id", getId());
			json.put("username", getUsername());
			json.put("email", getEmail());
			json.put("deviceId", getDeviceId());
			json.put("phone", getPhone());
			json.put("password", getPassword());
			
		} catch (JSONException ex) {
			ex.printStackTrace();
		}
		return json;
	}
}
