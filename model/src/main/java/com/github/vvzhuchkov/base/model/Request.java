package com.github.vvzhuchkov.base.model;

import java.time.LocalDate;

public class Request {
    private String login;
    private String location;
    private LocalDate pickup;
    private LocalDate dropoff;

    public Request(String login, String location, LocalDate pickup, LocalDate dropoff) {
        this.login = login;
        this.location = location;
        this.pickup = pickup;
        this.dropoff = dropoff;
    }

    public String getLogin() {
        return login;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getPickup() {
        return pickup;
    }

    public LocalDate getDropoff() {
        return dropoff;
    }
}
