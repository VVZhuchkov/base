package com.github.vvzhuchkov.base.model;

public class ApprComm {
    private Long number;
    private String approval;
    private String comment;

    public ApprComm(Long number, String approval, String comment) {
        this.number = number;
        this.approval = approval;
        this.comment = comment;
    }

    public Long getNumber() {
        return number;
    }

    public String getApproval() {
        return approval;
    }

    public String getComment() {
        return comment;
    }
}
