package com.github.vvzhuchkov.base.model;

public class Car {
    private Long id;
    private String photo;
    private String brand;
    private String model;
    private Long year;
    private String engine;
    private Long price;
    private String location;
    private String availability;

    public Car(Long id, String photo, String brand, String model, Long year, String engine, Long price, String location, String availability) {
        this.id = id;
        this.photo = photo;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.engine = engine;
        this.price = price;
        this.location = location;
        this.availability = availability;
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
}
