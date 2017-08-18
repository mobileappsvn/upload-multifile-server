package com.robert.ws.message;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.robert.ws.interfaces.JSONAble;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * @author Hoàng Minh Đức: 0989 664 386
 */
@XmlRootElement(name = "User")
public class Message implements JSONAble {

	private String code;// error code
	private String desc;// error description
	
	private String keyCode = "code";
	private String keyDesc = "desc";
	
	public Message() {
		
	}
	@XmlElement(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@XmlElement(name = "desc")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	/**
	 * set all values the for Attributes of Errors.
	 * 
	 * @author Hoàng Minh Đức.
	 */
	public Message(String code, String desc) {
		setCode(code);
		setDesc(desc);
	}
	

	public JSONObject toJSON() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		try {
			json.put(keyCode, getCode());
			json.put(keyDesc, getDesc());
		} catch (JSONException ex) {
			ex.printStackTrace();
		}
		return json;
	}
	
}