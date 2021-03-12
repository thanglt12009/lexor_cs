package com.lexor.cs.domain;

import java.io.Serializable;

/**
 *
 * @author THANGLT1
 */
public class ApiUser implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String code;
    private String image;
    private int ID;
    
    public ApiUser() {
    
    }
    
    public ApiUser(int ID, String name) {
        this.name = name;
        this.ID = ID;
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
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the productID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param productID the productID to set
     */
    public void setID(int productID) {
        this.ID = productID;
    }
    
}
