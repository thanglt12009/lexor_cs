package com.lexor.cs.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author THANGLT1
 */
public class RMAMessage implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer RMAID;
    
    private Integer MessageID;
    
    private String Subject;
    
    private String SendTo;
    
     private String MessageBody;
    
    private String CreatedDate; 
    
    public RMAMessage() {
    
    }
    
    public RMAMessage(int RMAID) {
        this.RMAID = RMAID;
    }
    
    public RMAMessage(int RMAID, int MessageID) {
        this.RMAID = RMAID;
        this.MessageID = MessageID;
    }        
    
    public Integer getRMAID() {
        return RMAID;
    }
    
    public void setRMAID(int RMAID) {
        this.RMAID = RMAID;
    }
    
    public Integer getMessageID() {
        return MessageID;
    }
    
    public void setMessageID(int MessageID) {
        this.MessageID = MessageID;
    }  
        
    public String getSendTo() {
        return SendTo;
    }
    
    public void setSendTo(String SendTo) {
        this.SendTo = SendTo;
    } 
    
    public String getSubject() {
        return Subject;
    }
    
    public void setSubject(String Subject) {
        this.Subject = Subject;
    } 

    public String getMessageBody() {
        return MessageBody;
    }
    
    public void setMessageBody(String MessageBody) {
        this.MessageBody = MessageBody;
    }     
    
    public String getCreatedDate() {
        return CreatedDate;
    }
    
    public void setCreatedDate(String CreatedDate) {
        this.CreatedDate = CreatedDate;
    }
     
}
