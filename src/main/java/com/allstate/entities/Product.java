package com.allstate.entities;

import javax.persistence.*;

@Entity
@Table(name = "products")

public class Product {
    private int id;
    private String name;
    private String stock_number;
    private String description;
    private int rating;
    private int no_of_reviews;
    private int list_price;
    private int per_off;
    private int actual_price;
    private int quantity;
    private boolean isrestricted;

    @Id
    @GeneratedValue
    public int getId() { return id; }
    public void setId(int id) {this.id = id; }

    @Column(unique = true)
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Column(unique = true)
    public String getStock_number() {return stock_number;}
    public void setStock_number(String stock_number) {this.stock_number = stock_number;}

    @Column(nullable = false)
    public int getList_price() {return list_price;}
    public void setList_price(int list_price) {this.list_price = list_price;}


    public int getPer_off() {return per_off;}
    public void setPer_off(int per_off) {this.per_off = per_off;}

    public int getActual_price() {return actual_price;}
    public void setActual_price(int actual_price) {this.actual_price = actual_price;}

    public int getRating() {return rating;}
    public void setRating(int rating) {this.rating = rating;}

    public int getNo_of_reviews() {return no_of_reviews;}
    public void setNo_of_reviews(int no_of_reviews) {this.no_of_reviews = no_of_reviews;}
}



