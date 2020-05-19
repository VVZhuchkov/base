package com.github.vvzhuchkov.base.dao.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payment")
public class PaymentEntity {
    private Long number;
    private String login;
    private String surname;
    private String name;
    private String passport;
    private Long id;
    private LocalDate pickup;
    private LocalDate dropoff;
    private Long total;
    private String approval;
    private String comment;

    public PaymentEntity() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
