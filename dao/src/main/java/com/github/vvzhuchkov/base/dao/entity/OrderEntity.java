package com.github.vvzhuchkov.base.dao.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "order")
public class OrderEntity {
    private Long number;
    private String login;
    private Long id;
    private String photo;
    private String brand;
    private String model;
    private Long year;
    private String engine;
    private Long price;
    private String location;
    private String availability;
    private LocalDate pickup;
    private LocalDate dropoff;
    private Long days;

    public OrderEntity() {
    }

    @Id
    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public LocalDate getPickup() {
        return pickup;
    }

    public void setPickup(LocalDate pickup) {
        this.pickup = pickup;
    }

    public LocalDate getDropoff() {
        return dropoff;
    }

    public void setDropoff(LocalDate dropoff) {
        this.dropoff = dropoff;
    }

    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }
}
