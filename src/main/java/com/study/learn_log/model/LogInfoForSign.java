package com.study.learn_log.model;

public class LogInfoForSign {

	private Integer time;
	private Integer topic;
	private String source;
	private String city;
	private Integer logType;
	private Integer subLogType;
	private Integer flag;
	private String ip;
	private String userId;
	private Integer value = 1;
	private LogContent logInfo;
	private String subLogTypeSign;// 动态查询subLogType的标志

	private long excuteTime; // 执行所用的时间(毫秒)

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getTopic() {
		return topic;
	}

	public void setTopic(Integer topic) {
		this.topic = topic;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public Integer getSubLogType() {
		return subLogType;
	}

	public void setSubLogType(Integer subLogType) {
		this.subLogType = subLogType;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getIp() {
		return ip;
	}

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    //兼容老代码
    public String getClientIp() {
        return ip;
    }
	
	public void setClientIp(String ip) {
	    this.ip = ip;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public LogContent getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(LogContent logInfo) {
		this.logInfo = logInfo;
	}

	public String getSubLogTypeSign() {
		return subLogTypeSign;
	}

	public void setSubLogTypeSign(String subLogTypeSign) {
		this.subLogTypeSign = subLogTypeSign;
	}

	public long getExcuteTime() {
		return excuteTime;
	}

	public void setExcuteTime(long excuteTime) {
		this.excuteTime = excuteTime;
	}

	@Override
	public String toString() {
		return "LogInfoForSign [time=" + time + ", topic=" + topic + ", source=" + source + ", city=" + city
				+ ", logType=" + logType + ", subLogType=" + subLogType + ", flag=" + flag + ", ip=" + ip
				+ ", userId=" + userId + ", value=" + value + ", logInfo=" + logInfo + ", subLogTypeSign="
				+ subLogTypeSign + ", excuteTime=" + excuteTime + "]";
	}

}