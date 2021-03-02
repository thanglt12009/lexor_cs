package com.lexor.cs.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author THANGLT1
 */
public class RMAPayment implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer RMAID;
    
    private Integer PaymentID;
    
    private String PaymentType;
    
    private double PaymentAmount;
    
    private Integer PaymentStatus; 
    
    public RMAPayment() {
    
    }
    
    public RMAPayment(int RMAID) {
        this.RMAID = RMAID;
    }
    
    public RMAPayment(int RMAID, int PaymentID) {
        this.RMAID = RMAID;
        this.PaymentID = PaymentID;
    }        
    
    public Integer getRMAID() {
        return RMAID;
    }
    
    public void setRMAID(int RMAID) {
        this.RMAID = RMAID;
    }
    
    public Integer getPaymentID() {
        return PaymentID;
    }
    
    public void setPaymentID(int PaymentID) {
        this.PaymentID = PaymentID;
    }     
    
    public double getPaymentAmount() {
        return PaymentAmount;
    }
    
    public void setPaymentType(String PaymentType) {
        this.PaymentType = PaymentType;
    }
    
    public String getPaymentType() {
        return PaymentType;
    }
    
    public void setPaymentAmount(double PaymentAmount) {
        this.PaymentAmount = PaymentAmount;
    } 

    public Integer getPaymentStatus() {
        return PaymentStatus;
    }
     
    public void setPaymentStatus(Integer PaymentStatus) {
        this.PaymentStatus = PaymentStatus;
    }
    
    public void setTotal(Integer PaymentStatus) {
        this.PaymentStatus = PaymentStatus;
    }         
}
