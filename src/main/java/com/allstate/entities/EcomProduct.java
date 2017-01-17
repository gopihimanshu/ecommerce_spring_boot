package com.allstate.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ecomproducts")

public class EcomProduct {
    private int id;
    private String name;
    private String stock_number;
    private String description;
    private int rating;
    private int no_of_reviews;
    private int list_of_price;
    private int per_off;
    private int actual_price;
    private int quantity;
    private boolean isrestricted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}



