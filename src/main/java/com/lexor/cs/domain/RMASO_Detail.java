package com.lexor.cs.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author THANGLT1
 */
public class RMASO_Detail implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer RMAID;
    
    private Integer SOID;
    
    private Integer SODetail_ID;
    
    private Integer ProductID;
    
    private Integer Quantity;
    
    private double Price; 
    
    private String createdDate;
    
    private String updatedDate;
    
    public RMASO_Detail() {
    
    }
    
    public RMASO_Detail(int RMAID) {
        this.RMAID = RMAID;
    }
    
    public RMASO_Detail(int RMAID, int SOID) {
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
    
    public Integer getSODetail_ID() {
        return SODetail_ID;
    }
    
    public void setSODetail_ID(int SODetail_ID) {
        this.SODetail_ID = SODetail_ID;
    }    
    
    public Integer getProductID() {
        return ProductID;
    }
    
    public void setProductID(Integer ProductID) {
        this.ProductID = ProductID;
    } 
    
    public Integer getQuantity() {
        return Quantity;
    }
    
    public void setQuantity(Integer Quantity) {
        this.Quantity = Quantity;
    } 

    public double getPrice() {
        return Price;
    }
    
    public void setPrice(double Price) {
        this.Price = Price;
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
