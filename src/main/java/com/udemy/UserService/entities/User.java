package com.udemy.UserService.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Size(min=3, max = 30, message = "Invalid first name")
    private String firstName;


    @Size(min=30, max = 50, message = "Invalid last name")
    private String lastName;

    @Email(message = "Invalid e-Mail id")
    private String emailId;

    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateOfBirth;

    @Size(min=2, max = 2, message = "Invalid Country Code")
    private String nationality;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
