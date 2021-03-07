package com.lexor.cs.domain;

import java.io.Serializable;

/**
 *
 * @author THANGLT1
 */
public class ServiceLog implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer serviceID;
    
    private Integer LogID;
    
    private String logMessage; 
    
    private String createdDate; 
    
    public ServiceLog() {
    
    }
    
    public Integer getLogID() {
        return LogID;
    }     
    
     public void setLogID(int LogID) {
        this.LogID = LogID;
    }
     
    public String getLogMessage() {
        return logMessage;
    }
    
    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }
             
    public String getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the serviceID
     */
    public Integer getServiceID() {
        return serviceID;
    }

    /**
     * @param serviceID the serviceID to set
     */
    public void setServiceID(Integer serviceID) {
        this.serviceID = serviceID;
    }
    
}
