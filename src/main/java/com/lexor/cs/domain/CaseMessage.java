package com.lexor.cs.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author THANGLT1
 */
public class CaseMessage implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer caseID;
    
    private Integer messageID;
    
    private String sendTo;
    
    private String subject;
    
    private String messageBody;
    
    private String createdDate;
    
    private String updatedDate;
    
    public CaseMessage() {
    
    }
    
    public CaseMessage(int caseID) {
        this.caseID = caseID;
    }
    
    public CaseMessage(int caseID, int messageID) {
        this.caseID = caseID;
        this.messageID = messageID;
    }
    
    public Integer getCaseID() {
        return caseID;
    }
    
    public void setCaseID(int caseID) {
        this.caseID = caseID;
    }
    
    public Integer getMessageID() {
        return messageID;
    }
    
    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }
    
    public String getSendTo() {
        return sendTo;
    }
    
    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getMessageBody() {
        return messageBody;
    }
    
    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
    
    public String getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    
     public String getUpdatedDate() {
        return updatedDate;
    }
    
    public void setUpdateDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
}
