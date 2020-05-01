package com.github.vvzhuchkov.base.model;

import java.util.Date;

public class Payment {
    private Long number;
    private String login;
    private Long id;
    private Date pickup;
    private Date dropoff;
    private Long total;
    private boolean approval;

    public Payment(Long number, String login, Long id, Date pickup, Date dropoff, Long total, boolean approval) {
        this.number = number;
        this.login = login;
        this.id = id;
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.total = total;
        this.approval = approval;
    }


    public String getLogin() {
        return login;
    }

    public Long getId() {
        return id;
    }

    public Date getPickup() {
        return pickup;
    }

    public Date getDropoff() {
        return dropoff;
    }

    public Long getTotal() {
        return total;
    }

    public boolean isApproval() {
        return approval;
    }
}
