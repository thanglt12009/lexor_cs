package com.lexor.cs.domain;

import java.io.Serializable;

public class ServiceAPIDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String productName;
    
    private Integer quantity;
    
    private Double soldPrice;
    
    private Double amount;
    
    private Integer originalSO;
    

    public ServiceAPIDetail() {
        
    }

    public ServiceAPIDetail(String productName, Integer quantity, Double soldPrice, Double amount, Integer originalSO) {
        this.productName = productName;
        this.quantity = quantity;
        this.soldPrice = soldPrice;
        this.amount = amount;
        this.originalSO = originalSO;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the soldPrice
     */
    public Double getSoldPrice() {
        return soldPrice;
    }

    /**
     * @param soldPrice the soldPrice to set
     */
    public void setSoldPrice(Double soldPrice) {
        this.soldPrice = soldPrice;
    }

    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * @return the originalSO
     */
    public Integer getOriginalSO() {
        return originalSO;
    }

    /**
     * @param originalSO the originalSO to set
     */
    public void setOriginalSO(Integer originalSO) {
        this.originalSO = originalSO;
    }
    
}
