package com.lexor.cs.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author THANGLT1
 */
public class RMASO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer RMASOID;
    
    private Integer RMAID;
    
    private Integer SOID;
    
    private double fee;
    
    private double total;
    
    private String createdDate;
    
    private String updatedDate;
    
    public RMASO() {
    
    }
    
    public RMASO(int RMASOID) {
        this.RMASOID = RMASOID;
    }
    
    public RMASO(int RMASOID, int SOID) {
        this.RMASOID = RMASOID;
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

    /**
     * @return the RMASOID
     */
    public Integer getRMASOID() {
        return RMASOID;
    }

    /**
     * @param RMASOID the RMASOID to set
     */
    public void setRMASOID(Integer RMASOID) {
        this.RMASOID = RMASOID;
    }
    
}
