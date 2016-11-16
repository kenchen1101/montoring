package com.tripb2b.montoring.jstorm.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MontoringMetric implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2617009030328696430L;
	
	public static Logger LOG = LoggerFactory.getLogger(MontoringMetric.class);

	private String metric;
	private String endpoint;
	private String tags;
	private String value;
	private String timestamp;
	private String counterType;
	private String step;
	
	public synchronized String getMetric() {
		return metric;
	}
	public synchronized void setMetric(String metric) {
		this.metric = metric;
	}
	public synchronized String getEndpoint() {
		return endpoint;
	}
	public synchronized void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public synchronized String getTags() {
		return tags;
	}
	public synchronized void setTags(String tags) {
		this.tags = tags;
	}
	public synchronized String getValue() {
		return value;
	}
	public synchronized void setValue(String value) {
		this.value = value;
	}
	public synchronized String getTimestamp() {
		return timestamp;
	}
	public synchronized void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public synchronized String getCounterType() {
		return counterType;
	}
	public synchronized void setCounterType(String counterType) {
		this.counterType = counterType;
	}
	public synchronized String getStep() {
		return step;
	}
	public synchronized void setStep(String step) {
		this.step = step;
	}
	
	@Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
	
}
