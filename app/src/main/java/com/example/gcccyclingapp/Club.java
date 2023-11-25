package com.example.gcccyclingapp;

public class Club extends Account {

    private String clubOwnerName, link, contact, phone;
    private String event;

    public Club(String clubOwnerName, String username, String password, String role) {
        super(username, password, role);
        this.clubOwnerName = clubOwnerName;
    }

    public void setClubName(String clubName) {
        this.clubOwnerName = clubName;
    }

    public String getClubName() {
        return this.clubOwnerName;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return this.link;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return this.contact;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return this.event;
    }

}