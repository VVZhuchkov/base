package com.github.vvzhuchkov.base.model;

import java.time.LocalDate;

public class Order {
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

    public Order(String login, Long id, LocalDate pickup, LocalDate dropoff, Long days) {
        this.login = login;
        this.id = id;
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.days = days;
    }

    public Order(Long number, String login, Long id, String photo, String brand, String model, Long year, String engine, Long price, String location, String availability, LocalDate pickup, LocalDate dropoff, Long days) {
        this.number = number;
        this.login = login;
        this.id = id;
        this.photo = photo;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.engine = engine;
        this.price = price;
        this.location = location;
        this.availability = availability;
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.days = days;
    }

    public Long getNumber() { return number; }

    public String getLogin() {
        return login;
    }

    public Long getId() {
        return id;
    }

    public String getPhoto() {
        return photo;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Long getYear() {
        return year;
    }

    public String getEngine() {
        return engine;
    }

    public Long getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public String getAvailability() {
        return availability;
    }

    public LocalDate getPickup() {
        return pickup;
    }

    public LocalDate getDropoff() {
        return dropoff;
    }

    public Long getDays() { return days;
    }
}
