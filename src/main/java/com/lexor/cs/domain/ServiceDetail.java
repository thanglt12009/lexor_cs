package com.lexor.cs.domain;

import java.io.Serializable;
import java.util.Date;

public class ServiceDetail implements Serializable {

    private static final long serialVersionUID = 1L;
        
    private Integer serviceMasterID;
    
    private Integer serviceDetailID;
    
    private Integer productID;
    
    private Integer quantity;
    
    private Double soldPrice;
    
    private Double amount;
    
    private Double totalWeight;
    
    private String serialNumber;
    
    private Integer isWarrantly;
    
    private Date warrantyStartDate;
    
    private Date warrantyEndDate;
    
    private Integer paymentType;

    private String logMessage;
    
    private Date createdDate;
    
    private String shipingDay;
    
    private Integer wareHouse;
    
    private String productImage;

    public ServiceDetail() {
        
    }

    public ServiceDetail(Integer serviceDetailID) {
        this.serviceDetailID = serviceDetailID;
    }

    public ServiceDetail(Integer serviceDetailID , Integer serviceMasterID) {
        this.serviceMasterID = serviceMasterID;
        this.serviceDetailID = serviceDetailID; 
    }

    public Integer getServiceMasterID() {
        return serviceMasterID;
    }

    public void setServiceMasterID(Integer serviceMasterID) {
        this.serviceMasterID = serviceMasterID;
    }
    
    public Integer getServiceDetailID() {
        return serviceDetailID;
    }
 
    public void setServiceDetailID(Integer serviceDetailID) {
        this.serviceDetailID = serviceDetailID;
    }
    
    public Integer getProductID() {
        return productID;
    }
 
    public void setProductID(Integer productID) {
        this.productID = productID;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
 
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public double getSoldPrice() {
        return soldPrice;
    }
 
    public void setSoldPrice(double soldPrice) {
        this.soldPrice = soldPrice;
    }
    
    public double getAmount() {
        return amount;
    }
 
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public double getTotalWeight() {
        return totalWeight;
    }
 
    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }
    
    public String getSerialNumber() {
        return serialNumber;
    }
 
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public Integer getIsWarrantly() {
        return isWarrantly;
    }
 
    public void setIsWarrantly(int isWarrantly) {
        this.isWarrantly = isWarrantly;
    }
    
    public Date getwarrantyStartDate() {
        return warrantyStartDate;
    }
 
    public void setwarrantyStartDate(Date warrantyStartDate) {
        this.warrantyStartDate = warrantyStartDate;
    }
    
    public Date getWarrantyEndDate() {
        return warrantyEndDate;
    }
 
    public void setWarrantyEndDate(Date warrantyEndDate) {
        this.warrantyEndDate = warrantyEndDate;
    }
    
    public Integer getPaymentType() {
        return paymentType;
    }
 
    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }
    
    public String getLogMessage() {
        return logMessage;
    }
 
    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }
    
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }    
   
    
    public String getShipingDay() {
        return shipingDay;
    }

    public void setShipingDay(String shipingDay) {
        this.shipingDay = shipingDay;
    }    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceMasterID != null ? serviceMasterID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceDetail)) {
            return false;
        }
        ServiceDetail other = (ServiceDetail) object;
        if ((this.serviceDetailID == null && other.serviceDetailID != null) || (this.serviceDetailID != null && !this.serviceDetailID.equals(other.serviceDetailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lexor.cs.domain.Case[ serviceDetailID=" + serviceDetailID + " ]";
    }    

    /**
     * @return the wareHouse
     */
    public Integer getWareHouse() {
        return wareHouse;
    }

    /**
     * @param wareHouse the wareHouse to set
     */
    public void setWareHouse(Integer wareHouse) {
        this.wareHouse = wareHouse;
    }

    /**
     * @return the productImage
     */
    public String getProductImage() {
        return productImage;
    }

    /**
     * @param productImage the productImage to set
     */
    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
