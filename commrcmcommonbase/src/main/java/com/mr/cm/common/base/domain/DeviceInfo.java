package com.mr.cm.common.base.domain;


public class DeviceInfo {

    private String imei;
    private String imsi;
    private String UA;
    private String deviceId;
    private String deviceType;

    public DeviceInfo(String imei, String imsi, String user_agent, String device_id, String device_type) {
        this.imei = imei;
        this.imsi = imsi;
        this.UA = user_agent;
        this.deviceId = device_id;
        this.deviceType = device_type;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getUA() {
        return UA;
    }

    public void setUA(String uA) {
        UA = uA;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }


}
