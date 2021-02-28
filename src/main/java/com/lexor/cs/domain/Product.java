package com.lexor.cs.domain;

import java.io.Serializable;

/**
 *
 * @author THANGLT1
 */
public class Product implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String name;
    private Float price;
    private int quantity;

    
    public Product() {
    
    }
    
    public Product(String name, int quantity, Float price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
