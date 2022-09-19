package com.pranay.hibernateeventsexample.model;

import com.pranay.hibernateeventsexample.readmodel.MemberSnapshot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "members")
public class Member {

    private long id;
    private String firstName;
    private String lastName;
    private String emailId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email_address", nullable = false)
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public MemberSnapshot snapshot() {
        MemberSnapshot snapshot = new MemberSnapshot();
        snapshot.setId(this.id);
        snapshot.setFirstName(this.firstName);
        snapshot.setLastName(this.lastName);
        snapshot.setEmailId(this.emailId);
        return snapshot;
    };
}
