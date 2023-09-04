package com.realEstate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;

@Entity
public class Property {
    @Id
    private int id;
    private String address;

    @Positive
    private double price;

    @Positive
    private int numberOfBedrooms;

    @Positive
    private int numberOfBathrooms;

    @Positive
    private double areaSquareFeet;

    public Property(int id, String address, double price, int numberOfBedrooms, int numberOfBathrooms, double areaSquareFeet) {
        this.id = id;
        this.address = address;
        this.price = price;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.areaSquareFeet = areaSquareFeet;
    }

    public Property() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public double getAreaSquareFeet() {
        return areaSquareFeet;
    }

    public void setAreaSquareFeet(double areaSquareFeet) {
        this.areaSquareFeet = areaSquareFeet;
    }
}
