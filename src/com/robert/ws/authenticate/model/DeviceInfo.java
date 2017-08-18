package com.robert.ws.authenticate.model;

/**
 * 
 * @author Created by Robert Hoang on 02 Nov 2016
 *
 */
public class DeviceInfo {
    public String registrationId;//Gcm Token with Android
    public String deviceId;

    public DeviceInfo(String registrationId, String deviceId) {
        this.registrationId = registrationId;
        this.deviceId = deviceId;
    }
}
