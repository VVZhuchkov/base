package com.github.vvzhuchkov.base.model;

import java.time.LocalDate;

public class Deal {
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

        public Deal(String login, String surname, String name, String passport) {
            this.login = login;
            this.surname = surname;
            this.name = name;
            this.passport = passport;
        }

        public Deal(Long number, String login, String surname, String name, String passport, Long id, LocalDate pickup, LocalDate dropoff, Long total, String approval, String comment) {
            this.number = number;
            this.login = login;
            this.surname = surname;
            this.name = name;
            this.passport = passport;
            this.id = id;
            this.pickup = pickup;
            this.dropoff = dropoff;
            this.total = total;
            this.approval = approval;
            this.comment = comment;
        }

        public Long getNumber() {
            return number;
        }

        public String getLogin() {
            return login;
        }

        public String getSurname() {
            return surname;
        }

        public String getName() {
            return name;
        }

        public String getPassport() {
            return passport;
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

        public Long getTotal() {
            return total;
        }

        public String getApproval() {
            return approval;
        }

        public String getComment() {
            return comment;
        }
    }
