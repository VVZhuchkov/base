package com.github.vvzhuchkov.base.model;

import java.time.LocalDate;

public class Booking {
    private Long number;
    private String login;
    private Long id;
    private LocalDate pickup;
    private LocalDate dropoff;
    private Long days;
    private Car car;

    public Booking(String login, Long id, LocalDate pickup, LocalDate dropoff, Long days) {
        this.login = login;
        this.id = id;
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.days = days;
    }

    public Booking(Long number, String login, Long id, LocalDate pickup, LocalDate dropoff, Long days, Car car) {
        this.number = number;
        this.login = login;
        this.id = id;
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.days = days;
        this.car = car;
    }

    public Long getNumber() { return number; }

    public String getLogin() {
        return login;
    }

    public Long getId() {
        return id;
    }
    
    public LocalDate getPickup() {
        return pickup;
    }

    public LocalDate getDropoff() {
        return dropoff;
    }

    public Long getDays() { return days;
    }

    public Car getCar() {
        return car;
    }
}
