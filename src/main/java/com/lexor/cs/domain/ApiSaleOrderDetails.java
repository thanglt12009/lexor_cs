package com.lexor.cs.domain;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author THANGLT1
 */
public class ApiSaleOrderDetails implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer SOID;
    private Integer salonId;
    private Integer idUSZip;
    private String salonAddress;
    private List<Product> products;
    
    public ApiSaleOrderDetails() {
    
    }
    
    public ApiSaleOrderDetails(int SOID, int salonID, String salonAddress, int idUSZip, List<Product> products) {
        this.SOID = SOID;
        this.idUSZip = idUSZip;
        this.salonId = salonID;
        this.products = products;
        this.salonAddress = salonAddress;
    }

    /**
     * @return the SOID
     */
    public Integer getSOID() {
        return SOID;
    }

    /**
     * @param SOID the SOID to set
     */
    public void setSOID(Integer SOID) {
        this.SOID = SOID;
    }

    /**
     * @return the salonId
     */
    public Integer getSalonId() {
        return salonId;
    }

    /**
     * @param salonId the salonId to set
     */
    public void setSalonId(Integer salonId) {
        this.salonId = salonId;
    }

    /**
     * @return the idUSZip
     */
    public Integer getIdUSZip() {
        return idUSZip;
    }

    /**
     * @param idUSZip the idUSZip to set
     */
    public void setIdUSZip(Integer idUSZip) {
        this.idUSZip = idUSZip;
    }

    /**
     * @return the products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * @return the salonAddress
     */
    public String getSalonAddress() {
        return salonAddress;
    }

    /**
     * @param salonAddress the salonAddress to set
     */
    public void setSalonAddress(String salonAddress) {
        this.salonAddress = salonAddress;
    }
}
