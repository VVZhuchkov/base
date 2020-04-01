package com.github.vvzhuchkov.model;

public class AuthUser {
    private int id;
    private String password;
    private String name;
    private String surname;
    private Department department;
    private Position position;
    private Sphere sphere;

    public AuthUser(int id, String password, String name, String surname, Department department, Position position, Sphere sphere) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.position = position;
        this.sphere = sphere;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Department getDepartment() {
        return department;
    }

    public Position getPosition() {
        return position;
    }

    public Sphere getSphere() {
        return sphere;
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", department=" + department +
                ", position=" + position +
                ", sphere=" + sphere +
                '}';
    }
}
