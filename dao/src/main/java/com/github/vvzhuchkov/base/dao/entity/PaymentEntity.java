package com.github.vvzhuchkov.base.dao.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payment")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PaymentEntity {
    @Id
    private Long number;
    private String login;
    private Long id;
    private LocalDate pickup;
    private LocalDate dropoff;
    private Long total;
    private String approval;
    private String comment;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "login", insertable = false, updatable = false)
    private ContactEntity contact;

    public PaymentEntity() {
    }


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

    public ContactEntity getContact() {
        return contact;
    }

    public void setContact(ContactEntity contact) {
        this.contact = contact;
    }
}
