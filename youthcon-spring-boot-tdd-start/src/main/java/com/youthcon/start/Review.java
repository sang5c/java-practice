package com.youthcon.start;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String phoneNumber;
    private boolean sent;

    protected Review() {}

    public Review(Long id, String content, String phoneNumber) {
        this.id = id;
        this.content = content;
        this.phoneNumber = phoneNumber;
    }

    public Review(Long id, String content, String phoneNumber, boolean sent) {
        this.id = id;
        this.content = content;
        this.phoneNumber = phoneNumber;
        this.sent = sent;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isSent() {
        return sent;
    }

    public void makeSentTrue() {
        this.sent = true;
    }

}
