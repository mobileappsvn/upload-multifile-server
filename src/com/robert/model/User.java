package com.robert.model;

import com.robert.ws.interfaces.JSONAble;
import com.wordnik.swagger.annotations.*;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "User")
public class User implements JSONAble {
	private long id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phone;
	private int userStatus;

	@XmlElement(name = "id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@XmlElement(name = "firstName")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@XmlElement(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@XmlElement(name = "lastName")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@XmlElement(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlElement(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@XmlElement(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@XmlElement(name = "userStatus")
	@ApiModelProperty(value = "User Status", allowableValues = "1-registered,2-active,3-closed")
	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	@Override
	public JSONObject toJSON() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		try {
			json.put("id", getId());
			json.put("username", getUsername());
			json.put("firstName", getFirstName());
			json.put("lastName", getLastName());
			json.put("email", getEmail());
			json.put("password", getPassword());
			json.put("phone", getPhone());
			json.put("userStatus", getUserStatus());
			
		} catch (JSONException ex) {
			ex.printStackTrace();
		}
		return json;
	}
}
