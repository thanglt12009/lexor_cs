package com.lexor.cs.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author THANGLT1
 */
public class RMASO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer RMAID;
    
    private Integer SOID;
    
    private double fee;
    
    private double total;
    
    private String createdDate;
    
    private String updatedDate;
    
    public RMASO() {
    
    }
    
    public RMASO(int RMAID) {
        this.RMAID = RMAID;
    }
    
    public RMASO(int RMAID, int SOID) {
        this.RMAID = RMAID;
        this.SOID = SOID;
    }        
    
    public Integer getRMAID() {
        return RMAID;
    }
    
    public void setRMAID(int RMAID) {
        this.RMAID = RMAID;
    }
    
    public Integer getSOID() {
        return SOID;
    }
    
    public void setSOID(int SOID) {
        this.SOID = SOID;
    }     
    
    public double getFee() {
        return fee;
    }
    
    public void setFee(double fee) {
        this.fee = fee;
    } 

    public double getTotal() {
        return total;
    }
    
    public void setTotal(double total) {
        this.total = total;
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
    
    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
}
